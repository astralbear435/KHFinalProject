package kr.spring.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.mypage.service.MypageService;
import kr.spring.recriut.service.RecruitService;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.volunteer.service.VolunteerService;

@Controller
public class MypageAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private VolunteerService volunteerService;
	
	@Resource
	private MypageService mypageService;
	
	@Resource
	private RecruitService recruitService;
	
	//마이페이지 캘린더 호출
	@RequestMapping(value="/mypage/volunteerMyCalendar.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("v_id") String v_id, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<v_id>> : " + v_id);
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("v_id", v_id);
		
		List<RecruitCommand> volunteer = null;
		
		volunteer = volunteerService.selectList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : " + volunteer);
		}
		
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		mapJson.put("volunteer", volunteer);	
		return  mapJson;
	}


	
	@RequestMapping(value="/volunteer/volunteerUpdate.do", method=RequestMethod.GET)	
	public String form(@RequestParam("v_num") int v_num, Model model) {
		RecruitCommand volunteerCommand = volunteerService.selectBoard(v_num);		
		model.addAttribute("volunteerCommand", volunteerCommand);
		return "volunteerModify";
	}
	//���� ������ ���۵� ������ ó��
	@RequestMapping(value="/volunteer/volunteerUpdate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("volunteerCommand") @Valid RecruitCommand volunteerCommand, BindingResult result, HttpSession session, HttpServletRequest request) {

		if(log.isDebugEnabled()) {
			log.debug("<<volunteerCommand>> : "+volunteerCommand);
		}

		RecruitCommand volunteer  = volunteerService.selectBoard(volunteerCommand.getV_num());		

		volunteerService.update(volunteer);
		//마이페이지로 리턴
		return "redirect:/mypage/mypage.do";
	}
	
	// 보호소 측 캘린더
	@RequestMapping(value="/mypage/recruitMyCalendar.do")
	@ResponseBody
	public Map<String,Object> getRecruitList(@RequestParam("r_id") String r_id, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<r_id>> : " + r_id);
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("r_id", r_id);
		
		List<RecruitCommand> recruit = null;
		
		recruit = recruitService.selectRecruitList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<recruit>> : " + recruit);
		}
				
		Map<String,Object> mapJson = new HashMap<String,Object>();
			
		mapJson.put("recruit", recruit);	
		return  mapJson;
	}	
}
