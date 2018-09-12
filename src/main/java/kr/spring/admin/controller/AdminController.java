package kr.spring.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class AdminController {

	@Resource
	private MemberService memberService;
	
	@RequestMapping("/admin/template/menu.do")
	public String process(HttpSession session, Model model) {	//로그인이 되어있으면 session에서 아이디를 가져옴

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);

		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}*/

		model.addAttribute("member", member);

		return "admin/template/menu";
	}
	@RequestMapping("/admin/template/header.do")
	public String processHeader(HttpSession session, Model model) {	//로그인이 되어있으면 session에서 아이디를 가져옴

		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);

		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}*/

		model.addAttribute("member", member);

		return "admin/template/header";
	}
}
