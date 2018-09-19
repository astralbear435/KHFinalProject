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
public class MemberFindController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/find_id.do")
	//JSON 문자열 생성
	@ResponseBody
	public Map<String,String> processFindId(@RequestParam("m_email") String email) throws Exception {
		
		if(log.isDebugEnabled()) {
			log.debug("<<email>> : " + email);
		}
		
		Map<String,String> map = new HashMap<String,String>();

		MemberCommand member = memberService.findId(email);
		if(member != null) {	//email 확인
			map.put("result", "emailFound");
			map.put("member", member.getM_id());
		} else {	//email 미확인
			map.put("result", "emailNotFound");
		}
		
		return map;
	}
	
}
