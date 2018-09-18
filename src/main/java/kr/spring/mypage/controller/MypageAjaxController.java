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

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.ap.service.ApBoService;
import kr.spring.ap.service.ApCallService;
import kr.spring.ap.service.ApService;
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
	

	@RequestMapping(value="/mypage/volunteerMyCalendar.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("v_id") String v_id, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<v_id>> : " + v_id);
		}
		
		String id = (String)session.getAttribute("user_id");	
		ApBoCommand boCall = new ApBoCommand();
		boCall.setBo_id(id);
		String bo_id = boCall.getBo_id();
		boCall.setId(id);
		String boho_id = boCall.getId();
		
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("v_id", v_id);
		map.put("bo_id", bo_id);
		map.put("boho_id", boho_id);
		
		List<RecruitCommand> volunteer = null;
		List<ApBoCommand> boCallList = null;
		List<ApBoCommand> bohoCallList = null;
		
		volunteer = volunteerService.selectList(map);
		boCallList = mypageService.selectBoCallList(bo_id);
		bohoCallList = mypageService.selectBohoCallList(boho_id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : " + volunteer);
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<boCallList>> : " + boCallList);
		}		
		if(log.isDebugEnabled()) {
			log.debug("<<bohoCallList>> : " + bohoCallList);
		}	
		
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		mapJson.put("volunteer", volunteer);	
		mapJson.put("boCallList", boCallList);
		mapJson.put("bohoCallList", bohoCallList);
		return  mapJson;
	}


/*
	@RequestMapping("/volunteer/volunteerUpdate.do")
	@ResponseBody
	public Map<String,String> modifyReply(RecruitCommand volunteer, HttpSession session, HttpServletRequest request){
		
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : "+ volunteer);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id == null) {
			map.put("result", "logout");
			
		}else if(user_id!=null && user_id.equals(volunteer.getV_id())){
					
		}		
		return map;
	}*/
	
	
	
	
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
	
	/*public Map<String,Object> getDonationList*/
	
	@RequestMapping(value="/mypage/donaShelterPage.do")
	@ResponseBody
	public  Map<String,Object> getDonaShelterList(@RequestParam("r_id") String r_id, HttpSession session){
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("r_id", r_id);
		
		
		
		return null;
		
	}
	
}
