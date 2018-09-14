package kr.spring.mypage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.mypage.service.MypageService;
import kr.spring.recriut.service.RecruitService;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.volunteer.service.VolunteerService;

@Controller
public class MypageController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MypageService mypageService;
	@Resource
	private VolunteerService volunteerService;
	@Resource
	private RecruitService recruitService;
	
	//자바빈 초기화
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	
	@ModelAttribute("recruit")
	public RecruitCommand initCommand2() {
		return new RecruitCommand();
	}
	
	@ModelAttribute("donation")
	public OrderCommand initCommand3() {
		return new OrderCommand();
	}
	  
	@ModelAttribute("ap")
	public ApCommand initCommand4() {
		return new ApCommand();
	}
	
	@ModelAttribute("callHome")
	public ApCallCommand initCommand5() {
		return new ApCallCommand();
	}
	
	@ModelAttribute("boCall")
	public ApBoCommand initCommand6() {
		return new ApBoCommand();
	}

	//++++++++++++++++++++메뉴에서 일반 회원 마이페이지 호출+++++++++++++++++++++++++++//
	@RequestMapping("mypage/mypage.do")
	public ModelAndView process(HttpSession session, Model model) {		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = mypageService.selectId(id);
		
		RecruitCommand volunteer = new RecruitCommand();
		OrderCommand donation = new OrderCommand();
		ApCallCommand callHome = new ApCallCommand();
		ApBoCommand boCall = new ApBoCommand();
		
		volunteer.setV_id(id);
		String v_id = volunteer.getV_id();
		
		donation.setDona_id(id);
		String dona_id = donation.getDona_id();		
		
		callHome.setCall_name(id);
		String call_name = callHome.getCall_name();
		
		boCall.setBo_id(id);
		String bo_id = boCall.getBo_id();
		
		List<RecruitCommand> list = null;
		list = mypageService.selectList(v_id);
		
		List<OrderCommand> donaList = null;
		donaList = mypageService.selectDanaList(dona_id);	
		
		int dona_count = mypageService.selectCountdonation(dona_id);
		
		List<ApCallCommand> callList = null;
		callList = mypageService.selectCallList(call_name);
		
		List<ApBoCommand> boCallList = null;
		boCallList = mypageService.selectBoCallList(bo_id);
		
		model.addAttribute("command",member);
		model.addAttribute("volunteer",volunteer);
		model.addAttribute("donation",donation);
		model.addAttribute("callHome", callHome);
		model.addAttribute("dona_count", dona_count);
		model.addAttribute("boCall", boCall);
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}	
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : " + volunteer);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<donation>> : " + donation);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<callHome>> : " + callHome);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<boCall>> : " + boCall);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/mypage");
		mav.addObject("list",list);
		mav.addObject("command", member);
		mav.addObject("volunteer", volunteer);
		mav.addObject("donaList", donaList);
		mav.addObject("donation", donation);
		mav.addObject("callList", callList);
		mav.addObject("boCallList", boCallList);
		
		return mav; 	
		
	}
	
	
	//++++++++++++++++++++캘린더에서 선택한 봉사활동 디테일 정보보여주기+++++++++++++++++++++++++++//

	@RequestMapping("/volunteer/volunteerDetail.do")
	public ModelAndView process(HttpSession session, Model model,@RequestParam("v_num") int v_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<v_num>> : "+ v_num );
		}		
			
		String id = (String)session.getAttribute("user_id");		

		RecruitCommand volunteer = volunteerService.selectBoard(v_num);
			
		model.addAttribute("volunteer",volunteer);
		
		if(log.isDebugEnabled()) {
			
		}	
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : " + volunteer);
		}	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("volunteer/volunteerDetail");
		mav.addObject("volunteer", volunteer);
		
		return mav; 	
		
	}

	//++++++++++++++++++++메뉴에서 보호소 회원 마이페이지 호출+++++++++++++++++++++++++++//
	//auth 값에 따라
	@RequestMapping("mypage/mypageShelter.do")
	public ModelAndView shelterProcess(HttpSession session, Model model) {		
		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = mypageService.selectId(id);
		
		RecruitCommand recruit = new RecruitCommand();
		
		recruit.setR_id(id);
		String r_id = recruit.getR_id();		
	
		List<RecruitCommand> list = null;
		list = mypageService.selectRecruitList(r_id);
		
		model.addAttribute("command",member);
		model.addAttribute("recruit",recruit);
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}	
		if(log.isDebugEnabled()) {
			log.debug("<<recruit>> : " + recruit);
		}	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/mypageShelter");
		mav.addObject("list",list);
		mav.addObject("command", member);
		mav.addObject("recruit", recruit);
		
		return mav; 	
		
	}
	
	//후원한 내역 보여주기//변경 위
	@RequestMapping("mypage/donation.do")
	public ModelAndView donationProcess (HttpSession session, Model model) {		
		
		String id = (String)session.getAttribute("user_id");
		
		OrderCommand donation = new OrderCommand();
		
		donation.setDona_id(id);
		String dona_id = donation.getDona_id();	
		List<OrderCommand> list = null;
		list = mypageService.selectDanaList(dona_id);
		
		model.addAttribute("donation",donation);
		
		if(log.isDebugEnabled()) {
			log.debug("<<donation>> : " + donation);
		}	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/mypage");
		mav.addObject("list",list);
		mav.addObject("donation", donation);
		
		return mav; 	
	}
		
	
	
	
	
}
		

		

		
	

