package kr.spring.shelter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
import kr.spring.util.CipherTemplate;

@Controller
public class ShelterController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ShelterService shelterService;

	@Resource
	private CipherTemplate cipherAES;

	
	@ModelAttribute("command")
	public ShelterCommand initShelterCommand() {
		return new ShelterCommand();
	}
	
	//================== 회원가입 =========================
	//회원 가입 폼 호출
	@RequestMapping(value="/shelter/write.do", method=RequestMethod.GET)
	public String formShelter() {
		return "shelterWrite";
	}
	//회원 가입 데이터 전송
	@RequestMapping(value="/shelter/write.do", method=RequestMethod.POST)
	public String submitShelter(@ModelAttribute("command") 
							@Valid ShelterCommand shelterCommand, 
							BindingResult result) {
		
		if(result.hasErrors()) {
			return formShelter();
		}
		
		//CipherTemplate을 이용한 암호화
		shelterCommand.setS_passwd(cipherAES.encrypt(shelterCommand.getS_passwd()));

		// 회원가입
		shelterService.insert(shelterCommand);

		return "redirect:/main/main.do";
	}
	
	// ============= 아이디 중복 확인 ==============
	@RequestMapping("/shelter/confirmId.do")
	@ResponseBody
	public Map<String,String> processShelter(@RequestParam("id") String id){

		Map<String,String> map = new HashMap<String,String>();
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		if(shelter != null) {
			//아이디 중복
			map.put("result", "idDuplicated");
		}else {
			//아이디 미 중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}

	//================== 로그인 =========================	
	// 로그인 폼 호출
	@RequestMapping(value="/shelter/shelterLogin.do", method=RequestMethod.GET)
	public String formLoginShelter() {
		return "shelterLogin";
	}

	// 로그인폼에서 전송된 데이터 처리
	@RequestMapping(value="/shelter/shelterLogin.do", method=RequestMethod.POST)
	public String submitLoginShelter(@Valid ShelterCommand shelterCommand, 
								BindingResult result, HttpSession session) {
		
		// memberCommand에 전부 체크하게 어노테이션이 선언되어 있으므로 id와 passwd의 필드만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLoginShelter();
		}
		
		//로그인 체크(id,비밀번호 일치 여부 체크)
		try {
			ShelterCommand shelter = shelterService.selectShelter(shelterCommand.getS_id());
			boolean check = false;
			
			if(shelter != null) {//아이디가 존재하면
				// 비밀번호 일치 여부 체크 > 암호화 된 비번의 일치여부 확인
				check = shelter.isCheckedPasswd(cipherAES.encrypt(shelterCommand.getS_passwd()));
			}
			if(check) {
				//인증 성공, 로그인처리
				session.setAttribute("user_id",shelter.getS_id());
				session.setAttribute("user_auth",shelter.getAuth());
				
				return "redirect:/main/main.do";
			}else {
				//인증 실패 : catch블록으로 넘어감
				throw new Exception();
			}
			
		}catch(Exception e) {
			//인증 실패로 폼 호출
			result.reject("invalidIdOrPassword");
			
			return formLoginShelter();
		}

	}


	//================== 로그아웃 =========================
	@RequestMapping("/shelter/logout.do")
	public String processLoginShelter(HttpSession session) {
		//로그아웃
		session.invalidate();

		return "redirect:/main/main.do";
	}

	
	//================== 회원 상세 정보 =========================
	// 진입 전 비밀번호 확인
	@RequestMapping("/shelter/shelterConfirm.do")
	public String confirmFormShelter(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// 암호화 된 비밀번호를 복호화(db는 변동 없음)
		shelter.setS_passwd(cipherAES.decrypt(shelter.getS_passwd()));
		
		model.addAttribute("shelter", shelter);
		
		return "shelterConfirm";
	}
	
	// 상세 정보 확인
	@RequestMapping("/shelter/shelterInfo.do")
	public String processShelter(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// 암호화 된 비밀번호를 복호화(db는 변동 없음)
		shelter.setS_passwd(cipherAES.decrypt(shelter.getS_passwd()));
		
		model.addAttribute("shelter", shelter);
		
		return "shelterInfo";
	}

	//================== 회원 정보 수정 =========================
	// 전송 된 데이터 처리
	@RequestMapping("/shelter/update.do")
	public String submitUpdateShelter(@Valid ShelterCommand shelterCommand,
								BindingResult result, HttpSession session) {
		
		System.out.println("----->" + shelterCommand);
		
		if(result.hasErrors()) {
			return "shelterModify";
		}
		
		// 파일을 첨부하지 않았다면 원래 있던 파일을 다시 입력
		if(shelterCommand.getS_filename().equals("")) {
			String id = (String)session.getAttribute("user_id");
			ShelterCommand shelter = shelterService.selectShelter(id);
			
			byte[] originS_uploadfile = shelter.getS_uploadfile();
			String originS_filename = shelter.getS_filename();
			
			shelterCommand.setS_uploadfile(originS_uploadfile);
			shelterCommand.setS_filename(originS_filename);
		}
		
		// CipherTemplate을 이용한 암호화 > 수정 전에 입력한 비번을 암호화해줌
		shelterCommand.setS_passwd(cipherAES.encrypt(shelterCommand.getS_passwd()));
		
		// 회원정보수정
		shelterService.update(shelterCommand);
		
		return "redirect:/shelter/shelterInfo.do"; // 수정이 잘 되었는지 확인하기 위해서 상세보기로 넘김
	}

	//================== 회원 탈퇴 =========================
	@RequestMapping("/shelter/delete.do")
	public String submitDeleteShelter(@Valid ShelterCommand shelterCommand, 
								BindingResult result, HttpSession session) {
		
		ShelterCommand shelter = shelterService.selectShelter(shelterCommand.getS_id());// 자바빈 형태로 id가져오기
			
		shelterService.delete(shelter.getS_id());
				
		// 로그아웃
		session.invalidate();
			
		return "redirect:/main/main.do";
			
	}

	//================== 보호소 리스트 =========================
	@RequestMapping(value="/shelter/shelterList.do", method=RequestMethod.GET)
	public ModelAndView shelterListGet(@RequestParam(value="local", defaultValue="") String local,
									@RequestParam(value="name", defaultValue="") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("local", local);
		map.put("name", name);
		
		// 총 글의 갯수 또는 검색 된 글의 갯수
		int count = shelterService.selectRowCount(map);
		
		List<ShelterCommand> list = null;
		if(count > 0) {
			list = shelterService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shelterList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping(value="/shelter/shelterList.do", method=RequestMethod.POST)
	public ModelAndView shelterListPost(@RequestParam(value="local", defaultValue="") String local,
									@RequestParam(value="name", defaultValue="") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("local", local);
		map.put("name", name);
		
		// 총 글의 갯수 또는 검색 된 글의 갯수
		int count = shelterService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		List<ShelterCommand> list = null;
		if(count > 0) {
			list = shelterService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : " + list);
			}
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shelterList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		return mav;
	}
	
	// 보호소 페이지
	@RequestMapping(value="/shelter/shelterDetail.do", method=RequestMethod.GET)
	public String viewShelter(@RequestParam("id") String id, Model model, HttpSession session) {
		
		String user_id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// 주소값에서 괄호 지워서 보내기
		String s_address1 = shelter.getS_address1();
		int findIndexOf = s_address1.indexOf("(");
		String address = s_address1.substring(0,findIndexOf-1);
		shelter.setS_address1(address);
		
		model.addAttribute("user_id", user_id);
		model.addAttribute("shelter", shelter);
		
		return "shelterDetail";
	}
	
	//이미지 출력
	@RequestMapping("/shelter/imageView.do")
	public ModelAndView downloadShelter(@RequestParam("id") String id) {

		ShelterCommand shelter = shelterService.selectShelter(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView"); //클래스 호출
		//속성명 				속성값(byte[]의 데이터)
		mav.addObject("imageFile", shelter.getS_uploadfile());
		mav.addObject("filename", shelter.getS_filename());

		return mav;
	}
}
