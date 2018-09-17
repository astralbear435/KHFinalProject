package kr.spring.volunteer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.volunteer.service.VolunteerService;

@Controller
public class VolunteerAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private VolunteerService volunteerService;

	@RequestMapping(value="/volunteer/volunteerWrite.do")
	@ResponseBody
	public Map<String,String> setCalendar(@RequestParam("v_date") String v_date, @RequestParam("r_num") String r_num,@RequestParam("v_status") String v_status, HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<v_date>> : " + v_date);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<r_num>> : " + r_num);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<v_status>> : " + v_status);
		}
		
		Map<String,String> mapString = new HashMap<String,String>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		String id = (String)session.getAttribute("user_id");
		
		RecruitCommand volunteer = new RecruitCommand();
		volunteer.setV_id(id);
		String v_id = volunteer.getV_id();
		
		if(v_id == null) {
			mapString.put("result", "logout");
		}
		if(v_id!=null) {
			map.put("v_id", v_id);
			map.put("v_date", v_date);
			map.put("r_num", r_num);
			map.put("v_status", v_status);
			
			volunteerService.insert(map);
			
			mapString.put("result", "success");
		}
				
		return mapString;
	}
	
	
}
