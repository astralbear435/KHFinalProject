package kr.spring.volunteer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.recriut.service.RecruitService;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.volunteer.service.VolunteerService;

@Controller
public class VolunteerAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private VolunteerService volunteerService;
	
	@Resource
	private RecruitService recruitService;
	

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
	
	 //봉사활동 신청 작성 폼에 기존 신청 데이터 불러오기
	@RequestMapping(value="/volunteer/recruitCalendar.do")
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
	
	
	//봉사활동 일정 수정
		@RequestMapping(value="/volunteer/volunteerUpdate.do")
		@ResponseBody
		public Map<String,String> form(@RequestParam("v_num") int v_num, @RequestParam("v_date") String v_date,@RequestParam("v_status") String v_status, Model model,HttpSession session) {
			
			Map<String,String> map = new HashMap<String,String>();
			
			String id = (String)session.getAttribute("user_id");
			
			Map<String,Object> mapJson = new HashMap<String,Object>();
			mapJson.put("v_num", v_num);
			mapJson.put("v_date", v_date);
			mapJson.put("v_status", v_status);
			
			if(id == null) {
				map.put("result", "logout");
			}else {
				volunteerService.update(mapJson);
				map.put("result", "success");
			}

			return map;
		}
		
		//봉사활동 일정 삭제
		//===============================봉사활동 일정 삭제==========================
		@RequestMapping("/volunteer/volunteerDelete.do")
		@ResponseBody
		public Map<String,String> deleteVolunteer(@RequestParam("v_num") int v_num, HttpSession session) {
			
			Map<String,String> map = new HashMap<String,String>();
			
			String id = (String)session.getAttribute("user_id");
			
			if(id==null) {				
				map.put("result", "logout");
			}else{
				volunteerService.delete(v_num);
				map.put("result", "success");
			}	
			return map;
			
		}
	
}
