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
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
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
	@Resource
	private ShelterService shelterService;
	
	//�옄諛붾퉰 珥덇린�솕
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
	
	@ModelAttribute("shelter")
	public ShelterCommand initCommand7() {
		return new ShelterCommand();
	}
	

	//++++++++++++++++++++硫붾돱�뿉�꽌 �씪諛� �쉶�썝 留덉씠�럹�씠吏� �샇異�+++++++++++++++++++++++++++//
	@RequestMapping("mypage/mypage.do")
	public ModelAndView process(HttpSession session, Model model) {		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = mypageService.selectId(id);
		
		
		RecruitCommand volunteer = new RecruitCommand();	
		
		//임시보호자 집으로 부르기
		//일반회원 예약 신청중
		ApCallCommand callHome = new ApCallCommand();
		//일반회원 예약 완료 //일반회원
		ApCallCommand complete = new ApCallCommand();
		//일반회원 예약 완료 //임시보호자
		ApCallCommand completeBoho = new ApCallCommand();
		
		//임시보호자 집에 맡기기
		ApBoCommand bo = new ApBoCommand();	
		ApBoCommand boho = new ApBoCommand();	
		
		
		volunteer.setV_id(id);
		String v_id = volunteer.getV_id();			
		
		//임시보호자 집으로 부르기 //예약 신청중 //일반회원
		callHome.setCall_name(id);
		String call_name = callHome.getCall_name();
		//일반회원 예약 완료 //일반회원
		complete.setCall_name(id);
		String call_name2 = complete.getCall_name();
		
		
		bo.setBo_id(id);
		String bo_id = bo.getBo_id();
		
		boho.setId(id);
				
		List<RecruitCommand> list = null;
		list = mypageService.selectList(v_id);		
				
		OrderCommand donation = new OrderCommand();
		List<OrderCommand> donaList = null;
		donation.setDona_id(id);
		String dona_id = donation.getDona_id();	
		
		int dona_count = mypageService.selectCountdonation(dona_id);
		
		if(dona_count > 0) {
			donaList = mypageService.selectDanaList(dona_id);	
			
		}		
		//임시보호자 집으로 부르기 //일반회원 //예약 신청 중
		List<ApCallCommand> callList = null;
		callList = mypageService.selectCallList(call_name);		
		
		//일반회원 //예약 신청완료
		List<ApCallCommand> callList2 = null;
		callList2 = mypageService.selectCallListComplete(call_name2);
		
		//임시보호자 집에 맡기기
		List<ApBoCommand> boList = null;
		boList = mypageService.selectBoList(bo_id);
		
		List<ApBoCommand> bohoCallList = null;
		bohoCallList = mypageService.selectBohoCallList(id);		
		
		model.addAttribute("command",member);
		model.addAttribute("volunteer",volunteer);
		model.addAttribute("donation",donation);
		model.addAttribute("callHome", callHome);
		model.addAttribute("dona_count", dona_count);
		model.addAttribute("bo", bo);
		model.addAttribute("boho", boho);
		model.addAttribute("complete", complete);
		
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
			log.debug("<<bo>> : " + bo);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<boho>> : " + boho);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage");
		mav.addObject("list",list);
		mav.addObject("command", member);
		mav.addObject("volunteer", volunteer);
		mav.addObject("donaList", donaList);
		mav.addObject("donation", donation);
		//임시보호자 집으로 부르기//일반회원//예약 신청중
		mav.addObject("callHome", callHome);
		mav.addObject("callList", callList);
		//예약 완료
		mav.addObject("complete", complete);
		mav.addObject("callList2", callList2);
		//임시보호자 집에 맡기기
		mav.addObject("bo", bo);
		mav.addObject("boList", boList);
		mav.addObject("boho", boho);
		mav.addObject("bohoCallList", bohoCallList);
		
		
		return mav; 	
		
	}
	
	
	//++++++++++++++++++++罹섎┛�뜑�뿉�꽌 �꽑�깮�븳 遊됱궗�솢�룞 �뵒�뀒�씪 �젙蹂대낫�뿬二쇨린+++++++++++++++++++++++++++//

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

	//++++++++++++++++++++硫붾돱�뿉�꽌 蹂댄샇�냼 �쉶�썝 留덉씠�럹�씠吏� �샇異�+++++++++++++++++++++++++++//
	//auth 媛믪뿉 �뵲�씪
	@RequestMapping("mypage/mypageShelter.do")
	public ModelAndView shelterProcess(HttpSession session, Model model) {		
		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = mypageService.selectId(id);
		
		RecruitCommand recruit = new RecruitCommand();
					
		recruit.setR_id(id);
		String r_id = recruit.getR_id();		
			
		List<RecruitCommand> list = null;
		list = mypageService.selectRecruitList(r_id);
		
		ShelterCommand shelter = shelterService.selectShelter(id);
			
		model.addAttribute("command",member);
		model.addAttribute("recruit",recruit);
		model.addAttribute("shelter",shelter);
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}	
		if(log.isDebugEnabled()) {
			log.debug("<<recruit>> : " + recruit);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<shelter>> : " + shelter);
		}	
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageShelter");
		mav.addObject("list",list);
		mav.addObject("command", member);
		mav.addObject("recruit", recruit);
		mav.addObject("shelter", shelter);		
		
		return mav; 	
		
	}
	
	
	
	
}
		

		

		
	

