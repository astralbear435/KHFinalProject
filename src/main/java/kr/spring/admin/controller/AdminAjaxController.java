package kr.spring.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.admin.mainmenu.domain.MainmenuCommend;
import kr.spring.member.service.MemberService;




@Controller
public class AdminAjaxController {
	@Resource
	private MemberService memberService;
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/admin/member/shelterAgreement.do", method=RequestMethod.POST)
	@ResponseBody
	public void shelterAgreement(@RequestParam("auth") int auth, @RequestParam("id") String id) {
		if(log.isDebugEnabled()) {
			log.debug("<<auth>> : "+auth);
			log.debug("<<id>> : "+id);
		}			
		try {
			memberService.updateAuth(auth, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
