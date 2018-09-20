package kr.spring.volunteer.controller;


import java.util.List;

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

import kr.spring.recriut.service.RecruitService;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.volunteer.service.VolunteerService;

@Controller
public class VolunteerController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private VolunteerService volunteerService;

	@Resource
	private RecruitService recruitService;

	//자바빈 초기화
	@ModelAttribute("command")
	public RecruitCommand initCommand() {
		return new RecruitCommand();
	}


	//봉사활동 신청글 폼 쓰기
	@RequestMapping(value="/volunteer/volunteerWrite.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model, @RequestParam(value="r_num")Integer r_num) {
		String id=(String)session.getAttribute("user_id");

		RecruitCommand volunteer = new RecruitCommand();

		volunteer.setV_id(id);
		volunteer.setR_num(r_num);

		model.addAttribute("command", volunteer);

		return "volunteer/volunteerWrite";
	}


	//봉사활동 신청글 전송
	/*@RequestMapping(value="/volunteer/volunteerWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("volunteer") RecruitCommand volunteer, BindingResult result, HttpServletRequest request, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<volunteerCommand>> : "+ volunteer);
		}
		if(result.hasErrors()) {
			return "voluntaryList";	
		}
		//봉사활동 신청글 전송 확인 페이지 
		String id=(String)session.getAttribute("user_id");
		volunteer.setV_id(id);	
		volunteerService.insert(volunteer);

		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : "+ volunteer );
		}
		return "volunteer/volunteerOkay";
	}*/
	
	
/*		@RequestMapping(value="/volunteer/volunteerUpdate.do", method=RequestMethod.POST)
		public String submit(@ModelAttribute("volunteer") RecruitCommand volunteer, BindingResult result, HttpSession session, HttpServletRequest request) {

			if(log.isDebugEnabled()) {
				log.debug("<<volunteer>> : " + volunteer);
			}			
			if(result.hasErrors()) {
				return "redirect:/mypage/mypage.do";
			}
		
			volunteerService.update(volunteer);

			return "redirect:/mypage/mypage.do";
		}	
*/

}
