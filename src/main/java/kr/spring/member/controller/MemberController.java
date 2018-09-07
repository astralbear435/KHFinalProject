package kr.spring.member.controller;

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

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.CipherTemplate;


@Controller
public class MemberController {
	
	private Logger log = Logger.getLogger(this.getClass());	//import�� �߸��� �� �ֱ� ������ �׻� Ȯ��
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private CipherTemplate cipherAES;
	
	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		
		return new MemberCommand();
	}
	
	
	//===================================== ȸ������ =================================//
	
	//ȸ����� �� ȣ��
	@RequestMapping(value="/member/write.do",method=RequestMethod.GET)
	public String form() {
		
		return "memberWrite";
	}
	
	//ȸ������ ������ ����
	@RequestMapping(value="/member/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result) {	//@Valid -> ������̼� ������� ��ȿ�� üũ �ϱ�����
		
		if(log.isDebugEnabled()) {
			
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasErrors()) {
			
			return form();
		}
		
		//CipherTemplate�� �̿��� ��ȣȭ
		memberCommand.setUser_passwd(cipherAES.encrypt(memberCommand.getUser_passwd()));
		
		//ȸ������
		memberService.insert(memberCommand);
		
		return "redirect:/main/main.do";
	}	
	
	
	//===================================== ȸ�� �α��� =================================//
	
	//�α��� ��
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String formLogin() {
		
		return "memberLogin";	//Tiles ���� ...
	}
	
	//�α��� ���� ���۵� ������ ó��
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//id�� passwd �ʵ常 üũ (�̷��� ���� ������ id�� passwd�� �ִ� �ڹٺ��� ���� ������ ��)
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			
			return formLogin();
		}
		
		//�α��� üũ(id,��й�ȣ ��ġ ���� üũ)
		try {
			MemberCommand member = memberService.seslectMember(memberCommand.getUser_id());
			boolean check = false;
			
			if(member != null) {
				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getUser_passwd()));
			}
			
			if(check) {	//��������, �α��� ó��
				session.setAttribute("user_id", member.getUser_id());
				session.setAttribute("user_auth", member.getAuth());
				
				if(log.isDebugEnabled()) {
					log.debug("<<���� ����>>");
					log.debug("<<user_id>> : " + member.getUser_id());
					log.debug("<<user_auth>> : " + member.getAuth());
				}
				
				return "redirect:/main/main.do";
				
			} else {	//��������
				throw new Exception();
			}
			
		} catch(Exception e) {	//�������з� �� ȣ��
			result.reject("invalidIdOrPasswd");
			
			if(log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
			}
			
			return formLogin();
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
		
		MemberCommand member = memberService.seslectMember(id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}
		
		model.addAttribute("member", member);
		
		return "memberView";
	}
	
	
	//===================================== ȸ�� ���� ���� =================================//
	
	//������
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = memberService.seslectMember(id);
		
		model.addAttribute("command", member);
		
		return "memberModify";
	}
	
	//�������� ���۵� ������ ó��
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result) {
		
		if(log.isDebugEnabled()) {
			
			log.debug("<<memberCommand>>" + memberCommand);
		}
		
		if(result.hasErrors()) {
			
			return "memberModify";
		}
		
		//CipherTemplate�� �̿��� ��ȣȭ
		memberCommand.setUser_passwd(cipherAES.encrypt(memberCommand.getUser_passwd()));
				
		//ȸ�� ���� ����
		memberService.update(memberCommand);
		
		return "redirect:/member/detail.do";
	}
	
	
	//===================================== ȸ�� Ż�� =================================//
	
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = new MemberCommand();
		member.setUser_id(id);
		
		model.addAttribute("command", member);
		
		return "memberDelete";
	}
	
	//ȸ�� ������ ����
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//passwd �ʵ��� ������ üũ
		if(result.hasFieldErrors("passwd")) {
			
			return "memberDelete";
		}
		
		//��й�ȣ ��ġ ���� üũ
		try {
			MemberCommand member = memberService.seslectMember(memberCommand.getUser_id());
			boolean check = false;
			
			if(member != null ) {	//��й�ȣ üũ
				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getUser_passwd()));
			}
			
			if(check) {	//���� ����, ȸ�� ���� ����
				memberService.delete(memberCommand.getUser_id());
				//�α׾ƿ�
				session.invalidate();
				
				return "redirect:/main/main.do";
				
			} else {	//���� ����
				throw new Exception();
			}
		} catch(Exception e) {
			result.rejectValue("passwd", "invalidPassword");
			
			return "memberDelete";
		}
		
	}
}
