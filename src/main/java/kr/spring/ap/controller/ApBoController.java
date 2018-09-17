package kr.spring.ap.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.ap.service.ApBoService;

@Controller
public class ApBoController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ApBoService apBoService;

//==========�ӽú�ȣ�� ������ �θ��� ==========

	//���۵� ������ ó��
	@RequestMapping(value="/ap/apBoWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
						@Valid ApBoCommand apBoCommand, 
						@Valid ApCommand apCommand, 
						BindingResult result, 
						HttpServletRequest request) {
		
		int ap_num = apCommand.getAp_num();
		log.debug("<<ap_num>> : " + ap_num);
		
		
		if(log.isDebugEnabled()) {
			log.debug("<<apBoCommand>> : " + apBoCommand);
		}
		
		if(result.hasErrors()) {
			return "redirect:/ap/apdetail.do?ap_num="+apCommand.getAp_num();
		}
		
		//�۾���
		apBoService.insert(apBoCommand);
		
		return "redirect:/ap/apdetail.do?ap_num="+apCommand.getAp_num();
	}

}
