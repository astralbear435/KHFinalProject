package kr.spring.admin.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.admin.mainmenu.domain.MainmenuCommend;
import kr.spring.admin.mainmenu.service.MainmenuService;
import kr.spring.goods.service.GoodsService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
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
	
	@Resource
	private ShelterService shelter;
	
	@Resource
	private MainmenuService mainMenu;
	
	@RequestMapping("/template/menu.do")
	public String mainMenu(HttpSession session, Model model){
		String id="";
		int count=0;
		List<MainmenuCommend> list =null;
		if((String)session.getAttribute("user_id")!=null) {
			id=(String)session.getAttribute("user_id");
		}
		count=mainMenu.selectActiveMenuCount();
		if (count>0) {
			list= mainMenu.selectActiveMenu();
		}
		
		model.addAttribute("id",id);
		model.addAttribute("mlist",list);
		model.addAttribute("mcount",count);
		return "template/menu";
	}
	@RequestMapping("/template/menu2.do")
	public String mainMenu2(HttpSession session, Model model){
		String id="";
		int count=0;
		List<MainmenuCommend> list =null;
		if((String)session.getAttribute("user_id")!=null) {
			id=(String)session.getAttribute("user_id");
		}
		count=mainMenu.selectActiveMenuCount();
		if (count>0) {
			list= mainMenu.selectActiveMenu();
		}
		
		model.addAttribute("id",id);
		model.addAttribute("mlist",list);
		model.addAttribute("mcount",count);
		return "template/menu2";
	}
	@RequestMapping("/admin/main.do")
	public ModelAndView mainPage(){
		int totalV=0,todayV=0;
		int totalM=0,todayM=0;
		int totalP=0,todayP=0;
		int totalS=0,todayS=0;


		todayV=visitor.selectCountToday();
		totalV=visitor.selectVisitorCount();
		totalM=memberService.selectMemberCount();
		todayM=memberService.selectTodayMemberCount();
		totalP=goods.selectTotalPayment();
		if(goods.selectTodayPayment()!=0)todayP=goods.selectTodayPayment();

		totalS=shelter.selectShelterCount();
		todayS=shelter.selectTodayShelterCount();
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
		mav.addObject("totalS",totalS);
		mav.addObject("todayS",todayS);
		return mav;

	}
	@RequestMapping("/admin/template/menu.do")
	public String process(HttpSession session, Model model) {	//로그인이 되어있으면 session에서 아이디를 가져옴
		MemberCommand member=null;
		String id = (String)session.getAttribute("user_id");
		System.out.println("id>>>>>>>>>>>>"+id);
		if(id!=null) {
			member = memberService.selectMember(id);
			model.addAttribute("member", member);
		}
		

		/*if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}*/


		return "admin/template/menu";
	}
	//어드민 헤더
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

	//일반 회원 관리
	@RequestMapping("/admin/member/normalMember.do")
	public ModelAndView normalMember() {
		List<MemberCommand> list=null;
		int count =0;
		
		count = memberService.selectMemberCount() -1;
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		if(count>0)	list = memberService.selectTotalMember();
		ModelAndView mav = new ModelAndView();
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}
		mav.setViewName("normalMember"); 
		mav.addObject("list",list);
		mav.addObject("count",count);

		return mav;

	}

	//보호소 관리
	@RequestMapping("/admin/member/shelterMember.do")
	public ModelAndView shelterMember() {
		List<ShelterCommand> list =null;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shelterMember");
		int count = 0;
		int snum= 0;
		count = shelter.selectShelterCount();
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		if(count>0)	list = shelter.selectShelterList();
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}
		
		mav.setViewName("shelterMember"); 
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("snum",snum);
		return mav;

	}


}
