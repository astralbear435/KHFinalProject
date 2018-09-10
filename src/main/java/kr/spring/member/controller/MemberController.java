package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.CipherTemplate;


@Controller
public class MemberController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private CipherTemplate cipherAES;
	
	//자바빈 초기화
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		
		return new MemberCommand();
	}
	
	
	//=================== 회 원 가 입 ====================
	
	//회원등록 폼 호출
	@RequestMapping(value="/member/write.do",method=RequestMethod.GET)
	public String form() {
		
		return "memberWrite";
	}
	
	//회원가입 데이터 전송
	@RequestMapping(value="/member/sendEmail.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, Model model, HttpServletRequest request, HttpSession session) throws Exception {	//@Valid -> 어노테이션 방식으로 유효성 체크 하기위함
		
		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(log.isInfoEnabled()) {
			log.info("회원가입...");
			log.info(memberCommand.toString());
		}*/
		
		//CipherTemplate를 이용한 암호화
		memberCommand.setM_passwd(cipherAES.encrypt(memberCommand.getM_passwd()));
		
		//회원가입
		memberService.insert(memberCommand);
		
		
		return "sendEmail";
	}
		
	//인증메일 링크클릭
	@RequestMapping(value="/member/emailConfirm.do",method=RequestMethod.GET)
	public String emailConfirm(String m_email, Model model, HttpSession session) throws Exception { // 이메일인증
		
		String id = (String)session.getAttribute("user_id");
		
		/*if(log.isInfoEnabled()) {
			log.info("<<m_email...>> : " + m_email);
		}*/
		
		memberService.changeAuth(m_email);
		memberService.verify(m_email);
		model.addAttribute("m_email", m_email);

		return "emailConfirm";
	}
	
	//이메일 재전송
	@RequestMapping(value="/member/resend_e.do",method=RequestMethod.GET)
	public String reVerify(Model model, HttpSession session) throws Exception {

		String id = (String)session.getAttribute("user_id");
		MemberCommand member = memberService.selectMember(id);

		/*if(log.isInfoEnabled()) {
			log.info("<<id~~~~~~~~>> : " + id);
			log.info("<<member~~~~>> : " + member);
		}*/

		memberService.resendEmail(member);

		return "resendEmail";
	}

	
	//=================== 회원 로그인 =====================
	
	//로그인 폼
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String formLogin() {

		return "memberLogin";
	}
	
	//로그인 폼에 전송된 데이터 처리
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpServletRequest request, HttpSession session) {

		if(log.isDebugEnabled()) {

			log.debug("<<memberCommand>> : " + memberCommand);
		}

		//id와 passwd 필드만 체크 (이렇게 하지 않으면 id와 passwd만 있는 자바빈을 따로 만들어야 함)
		if(result.hasFieldErrors("m_id") || result.hasFieldErrors("m_passwd")) {

			return formLogin();
		}

		//로그인 체크(id,비밀번호 일치 여부 체크)
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getM_id());
			boolean check = false;
			
			if(member != null) {
				
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getM_passwd()));
				
			}
			
			if(check) {	//인증성공, 로그인 처리
				session.setAttribute("user_id", member.getM_id());
				session.setAttribute("user_auth", member.getAuth());

				if(log.isDebugEnabled()) {
					log.debug("<<인증 성공>>");
					log.debug("<<user_id>> : " + member.getM_id());
					log.debug("<<user_auth>> : " + member.getAuth());
				}

				return "redirect:/main/main.do";

			} else {	//인증실패
				throw new Exception();
			}

		} catch(Exception e) {	//인증실패로 폼 호출
			result.reject("invalidIdOrPasswd");

			if(log.isDebugEnabled()) {
				log.debug("<<인증 실패>>");
			}

			return formLogin();
		}
	}
	
	
	//================== 회원 아이디/비밀번호 찾기 ====================
	
	@RequestMapping(value="/member/findMember.do",method=RequestMethod.GET)
	public String find() {
		
		return "findMember";
	}
	
	@RequestMapping(value="/member/findMember.do",method=RequestMethod.POST)
	public String sendPw(@ModelAttribute("command") @Valid MemberCommand member, BindingResult result) throws Exception {
		
		if(result.hasFieldErrors("m_email")) {
			
			return find();
		}
		
		try {
			
			MemberCommand memberIn = memberService.checkMember_e(member.getM_email());
			
			if(memberIn != null) {
				
				memberService.updatePw(member.getM_email());
				
				return "redirect:/member/successSendPw.do";
				
			} else {
				
				throw new Exception();
			}
			
		} catch(Exception e) {
			
			result.rejectValue("m_email","invalidEmail");
			
			return find();
		}
		
		
	}
	
	
	//===================================== 회원 로그아웃 =================================//

	@RequestMapping("/member/logout.do")
	public String processLogin(HttpSession session) {

		//로그아웃
		session.invalidate();

		return "redirect:/main/main.do";
	}
	
	
	//===================================== 회원 상세 정보 =================================//
	
	@RequestMapping("/member/detail.do")
	public String process(HttpSession session, Model model) {	//로그인이 되어있으면 session에서 아이디를 가져옴
		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = memberService.selectMember(id);

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}

		model.addAttribute("member", member);

		return "memberView";
	}
	
	//이미지 출력
	@RequestMapping("/member/imageView.do")
	public ModelAndView viewImage(@RequestParam("m_id") String id) {
		MemberCommand member = memberService.selectMember(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", member.getUploadProfile());
		mav.addObject("filename", member.getProfileFilename());

		return mav;
	}

	
	//===================================== 회원 정보 수정 =================================//
	
	//수정폼
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session, Model model) {

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);

		model.addAttribute("command", member);

		return "memberModify";
	}
	
	//수정폼에 전송된 데이터 처리
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result) {

		if(log.isDebugEnabled()) {

			log.debug("<<memberCommand>>" + memberCommand);
		}
		
		if(result.hasFieldErrors("m_passwd")) {
			
			return "memberModify";
		}

		//CipherTemplate를 이용한 암호화
		memberCommand.setM_passwd(cipherAES.encrypt(memberCommand.getM_passwd()));

		//회원 정보 수정
		memberService.updateMember(memberCommand);

		return "redirect:/member/detail.do";
	}
	
	
	//===================================== 회원 탈퇴 =================================//
	
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session, Model model) {

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = new MemberCommand();
		member.setM_id(id);

		model.addAttribute("command", member);

		return "memberDelete";
	}

	//회원 데이터 삭제
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand!!>> : " + memberCommand);
		}

		//passwd 필드의 에러만 체크
		if(result.hasFieldErrors("m_passwd")) {

			return "memberDelete";
		}

		//비밀번호 일치 여부 체크
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getM_id());
			boolean check = false;

			if(member != null ) {	//비밀번호 체크
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getM_passwd()));
			}

			if(check) {	//인증 성공, 회원 정보 삭제
				memberService.delete(memberCommand.getM_id());
				//로그아웃
				session.invalidate();

				return "redirect:/member/deleteConfirm.do";

			} else {	//인증 실패
				throw new Exception();
			}
		} catch(Exception e) {
			result.rejectValue("m_passwd", "invalidPassword");

			return "memberDelete";
		}
	}
	
}







