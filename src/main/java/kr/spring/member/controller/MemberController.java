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

	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand() {

		return new MemberCommand();
	}

	//=========================admin �α���========================
	@RequestMapping(value="/admin/login.do",method=RequestMethod.GET)
	public String adminlogin() {

		return "admin/login";
	}
	@RequestMapping(value="/admin/login.do",method=RequestMethod.POST)
	public String adminsubmit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, Model model, HttpServletRequest request, HttpSession session) throws Exception{
		if(log.isDebugEnabled()) {

		log.debug("<<memberCommand>> : " + memberCommand);
	}

		if(result.hasFieldErrors("m_id") || result.hasFieldErrors("m_passwd")) {

			return "admin/login";
		}

		//�α��� üũ(id,��й�ȣ ��ġ ���� üũ)
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getM_id());
			boolean check = false;

			if(member != null) {

				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getM_passwd()));

			}

			if(check) {	//��������, �α��� ó��

				session.setAttribute("user_id", member.getM_id());
				session.setAttribute("user_auth", member.getAuth());

				if(log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
				log.debug("<<user_id>> : " + member.getM_id());
				log.debug("<<user_auth>> : " + member.getAuth());
			}

				return "redirect:/admin/main.do";

			} else {	//��������

				throw new Exception();
			}

		} catch(Exception e) {	//�������з� �� ȣ��

			result.reject("invalidIdOrPasswd");
			if(log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
			}
			return "admin/login";
		}

	}
	@RequestMapping(value="/admin/notlogin.do",method=RequestMethod.GET)
	public String notlogin() {

		return "/admin/waring/notlogin";
	}
	//=================== ȸ �� �� �� ====================

	//ȸ����� �� ȣ��
	@RequestMapping(value="/member/write.do",method=RequestMethod.GET)
	public String form() {

		return "memberWrite";
	}

	//ȸ������ ������ ����
	@RequestMapping(value="/member/sendEmail.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, Model model, HttpServletRequest request, HttpSession session) throws Exception {	//@Valid -> ������̼� ������� ��ȿ�� üũ �ϱ�����

		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}

		if(log.isInfoEnabled()) {
			log.info("ȸ������...");
			log.info(memberCommand.toString());
		}*/

		//CipherTemplate�� �̿��� ��ȣȭ
		memberCommand.setM_passwd(cipherAES.encrypt(memberCommand.getM_passwd()));

		//ȸ������
		memberService.insert(memberCommand);


		return "sendEmail";
	}

	//�������� ��ũŬ��
	@RequestMapping(value="/member/emailConfirm.do",method=RequestMethod.GET)
	public String emailConfirm(String m_email, Model model, HttpSession session) throws Exception { // �̸�������

		String id = (String)session.getAttribute("user_id");

		/*if(log.isInfoEnabled()) {
			log.info("<<m_email...>> : " + m_email);
		}*/

		memberService.changeAuth(m_email);
		memberService.verify(m_email);
		model.addAttribute("m_email", m_email);

		return "emailConfirm";
	}

	//�̸��� ������
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


	//=================== ȸ�� �α��� =====================

	//�α��� ��
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String Login() {

		return "memberLogin";

	}

	//�α��� ���� ���۵� ������ ó��
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpServletRequest request, HttpSession session) {

		/*if(log.isDebugEnabled()) {

			log.debug("<<memberCommand>> : " + memberCommand);
		}*/

		if(result.hasFieldErrors("m_id") || result.hasFieldErrors("m_passwd")) {

			return "memberLogin";
		}

		//�α��� üũ(id,��й�ȣ ��ġ ���� üũ)
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getM_id());
			boolean check = false;

			if(member != null) {

				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getM_passwd()));

			}

			if(check) {	//��������, �α��� ó��

				session.setAttribute("user_id", member.getM_id());
				session.setAttribute("user_auth", member.getAuth());

				/*if(log.isDebugEnabled()) {
					log.debug("<<���� ����>>");
					log.debug("<<user_id>> : " + member.getM_id());
					log.debug("<<user_auth>> : " + member.getAuth());
				}*/

				return "redirect:/main/main.do";

			} else {	//��������

				throw new Exception();
			}

		} catch(Exception e) {	//�������з� �� ȣ��

			result.reject("invalidIdOrPasswd");
			/*if(log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
			}*/
			return "memberLogin";
		}
	}


	//================== ȸ�� ���̵�/��й�ȣ ã�� ====================

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


	//===================================== ȸ�� �α׾ƿ� =================================//

	@RequestMapping("/member/logout.do")
	public String processLogin(HttpSession session) {

		//�α׾ƿ�
		session.invalidate();

		return "redirect:/main/main.do";
	}


	//===================================== ȸ�� �� ���� =================================//

	@RequestMapping("/member/detail.do")
	public String process(HttpSession session, Model model) {	//�α����� �Ǿ������� session���� ���̵� ������

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);

		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}*/

		model.addAttribute("member", member);

		return "memberView";
	}

	//�̹��� ���
	@RequestMapping("/member/imageView.do")
	public ModelAndView viewImage(@RequestParam("m_id") String id) {

		MemberCommand member = memberService.selectMember(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", member.getUploadProfile());
		mav.addObject("filename", member.getProfileFilename());

		return mav;
	}


	//===================================== ȸ�� ���� ���� =================================//

	//������
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session, Model model) {

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);

		model.addAttribute("command", member);

		return "memberModify";
	}

	//�������� ���۵� ������ ó��
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result) {

		/*if(log.isDebugEnabled()) {

			log.debug("<<memberCommand>>" + memberCommand);
		}*/

		if(result.hasFieldErrors("m_passwd")) {

			return "memberModify";
		}

		//CipherTemplate�� �̿��� ��ȣȭ
		memberCommand.setM_passwd(cipherAES.encrypt(memberCommand.getM_passwd()));

		//ȸ�� ���� ����
		memberService.updateMember(memberCommand);

		return "redirect:/member/detail.do";
	}


	//===================================== ȸ�� Ż�� =================================//

	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session, Model model) {

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = new MemberCommand();
		member.setM_id(id);

		model.addAttribute("command", member);

		return "memberDelete";
	}

	//ȸ�� ������ ����
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session) {

		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand!!>> : " + memberCommand);
		}*/

		//passwd �ʵ��� ������ üũ
		if(result.hasFieldErrors("m_passwd")) {

			return "memberDelete";
		}

		//��й�ȣ ��ġ ���� üũ
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getM_id());
			boolean check = false;

			if(member != null ) {	//��й�ȣ üũ
				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getM_passwd()));
			}

			if(check) {	//���� ����, ȸ�� ���� ����
				memberService.delete(memberCommand.getM_id());
				//�α׾ƿ�
				session.invalidate();

				return "redirect:/member/deleteConfirm.do";

			} else {	//���� ����

				throw new Exception();
			}
		} catch(Exception e) {

			result.rejectValue("m_passwd", "invalidPassword");

			return "memberDelete";
		}
	}


	//======================== 1:1 ä�� =======================

}