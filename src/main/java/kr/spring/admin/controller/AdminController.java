package kr.spring.admin.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.goods.service.GoodsService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.visitor.service.VisitorService;

@Controller
public class AdminController {

	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;

	@Resource
	private VisitorService visitor;
	
	@Resource
	private GoodsService goods;
	
	@RequestMapping("/admin/main.do")
	public ModelAndView mainPage(){
		int totalV=0,todayV=0;
		int totalM=0,todayM=0;
		int totalP=0,todayP=0;
		
		
		todayV=visitor.selectCountToday();
		totalV=visitor.selectVisitorCount();
		totalM=memberService.selectMemberCount();
		todayM=memberService.selectTodayMemberCount();
		totalP=goods.selectTotalPayment();
		todayP=goods.selectTodayPayment();
//		if(log.isDebugEnabled()) {
//			log.debug("<<가입자>> : ");
//		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminMain"); 
		mav.addObject("todayV",todayV);
		mav.addObject("totalV",totalV);
		mav.addObject("totalM",totalM);
		mav.addObject("todayM",todayM);
		mav.addObject("totalP",totalP);
		mav.addObject("todayP",todayP);
		return mav;

	}
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
