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
	//JSON ���ڿ� ����
	@ResponseBody
	public Map<String,String> processId(@RequestParam("m_id") String id) {	//id�� �޾ƾ� �ϱ� ������ @RequestParam
		
		if(log.isDebugEnabled()) {
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectMember(id);
		if(member != null) {	//id �ߺ�
			map.put("result", "idDuplicated");
		} else {	//id ���ߺ�
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	@RequestMapping("/member/confirmNickname.do")
	//JSON ���ڿ� ����
	@ResponseBody
	public Map<String,String> processNickname(@RequestParam("m_nickname") String nickname) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<nickname>> : " + nickname);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.checkMember_n(nickname);
		if(member != null) {	//nickname �ߺ�
			map.put("result", "nicknameDuplicated");
		} else {	//nickname ���ߺ�
			map.put("result", "nicknameNotFound");
		}
		
		return map;
	}
	
	@RequestMapping("/member/confirmEmail.do")
	//JSON ���ڿ� ����
	@ResponseBody
	public Map<String,String> processEmail(@RequestParam("m_email") String email) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<email>> : " + email);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.checkMember_e(email);
		if(member != null) {	//email �ߺ�
			map.put("result", "emailDuplicated");
		} else {	//email ���ߺ�
			map.put("result", "emailNotFound");
		}
		
		return map;
	}
}

