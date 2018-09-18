package kr.spring.ap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.ap.domain.ApBoCallCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.service.ApBoCallService;

@Controller
public class ApBoCallController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ApBoCallService apBoCallService;

//==========�ӽú�ȣ�� ������ �θ��� ==========

	//���۵� ������ ó��
	@RequestMapping(value="/ap/apBoCallWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
						@Valid ApBoCallCommand apBoCallCommand, 
						@Valid ApCallCommand apCallCommand, 
						BindingResult result, 
						HttpServletRequest request) {
		
		int call_num = apCallCommand.getCall_num();
		log.debug("<<call_num>> : " + call_num);
		
		
		if(log.isDebugEnabled()) {
			log.debug("<<apBoCallCommand>> : " + apBoCallCommand);
		}

		//�۾���
		apBoCallService.insert(apBoCallCommand);
		
		if(result.hasErrors()) {
			log.debug("������ ���еǾ����ϴ�.");
			return "redirect:/ap/apCalldetail.do?call_num="+apCallCommand.getCall_num();
		}

		
		return "redirect:/ap/apCalldetail.do?call_num="+apCallCommand.getCall_num();
	}

//==========�ӽú�ȣ�� ������ �θ��� ==========
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
		
		//JSON ������ ��ȯ
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(map);
		
		request.setAttribute("jsonData", jsonData);
		
		return map;
	}
	
}
