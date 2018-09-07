package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;


@Controller
public class MemberConfirmIdAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/confirmId.do")
	//JSON 문자열 생성
	@ResponseBody
	public Map<String,String> process(@RequestParam("id") String id) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.seslectMember(id);
		if(member != null) {	//id 중복
			map.put("result", "idDuplicated");
		} else {	//id 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
}


















