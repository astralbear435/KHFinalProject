package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

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
	public Map<String,String> processId(@RequestParam("m_id") String id) {	//id를 받아야 하기 때문에 @RequestParam
		
		if(log.isDebugEnabled()) {
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectMember(id);
		if(member != null) {	//id 중복
			map.put("result", "idDuplicated");
		} else {	//id 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	@RequestMapping("/member/confirmNickname.do")
	//JSON 문자열 생성
	@ResponseBody
	public Map<String,String> processNickname(@RequestParam("m_nickname") String nickname) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<nickname>> : " + nickname);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.checkMember_n(nickname);
		if(member != null) {	//nickname 중복
			map.put("result", "nicknameDuplicated");
		} else {	//nickname 미중복
			map.put("result", "nicknameNotFound");
		}
		
		return map;
	}
	
	@RequestMapping("/member/confirmEmail.do")
	//JSON 문자열 생성
	@ResponseBody
	public Map<String,String> processEmail(@RequestParam("m_email") String email) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<email>> : " + email);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.checkMember_e(email);
		if(member != null) {	//email 중복
			map.put("result", "emailDuplicated");
		} else {	//email 미중복
			map.put("result", "emailNotFound");
		}
		
		return map;
	}
}

