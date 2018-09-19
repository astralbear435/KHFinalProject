package kr.spring.ap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.ap.domain.ApBoCallCommand;
import kr.spring.ap.service.ApBoCallService;

@Controller
public class ApBoCallAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ApBoCallService apBoCallService;

//==========임시보호자 집으로 부르기 상세보기에서 예약확인 ==========
	@RequestMapping(value="/ap/boCheck.do")
	@ResponseBody
	public Map<String,Object> boCheck(@RequestParam(value="call_num")int call_num,
										ApBoCallCommand apBoCallCommand,
										HttpSession session,
										HttpServletRequest request) throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		int count = apBoCallService.boCallCount(call_num);
		
		List<ApBoCallCommand> callList = null;
		
		callList = apBoCallService.boCallList(map);
		if(log.isDebugEnabled()) {
			log.debug("<<apBoCallCommand>> : "+apBoCallCommand);
		}
		
		map.put("count", count);
		map.put("callList", callList);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
			log.debug("<<callList>> : "+count);
		}
		
		//JSON 데이터 변환
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(map);
		
		request.setAttribute("jsonData", jsonData);
		
		return map;
	}
	
	
	
}
