package kr.spring.admin.mainmenu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.admin.mainmenu.domain.MainmenuCommend;
import kr.spring.admin.mainmenu.service.MainmenuService;

@Controller
public class MainmenuController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private MainmenuService mainmenuService;

	//============================ ¸Þ´º º¯°æ Æû==================================
	@RequestMapping(value="/admin/mainmenu/menulist.do", method=RequestMethod.GET)
	public ModelAndView menuForm(){
		int count=0;
		List<MainmenuCommend> list =null;

		count = mainmenuService.selectmenuCount();
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		if(count>0) {
			list = mainmenuService.selectMenuList();
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : "+list);
			}
		}
		ModelAndView mav = new ModelAndView();

		mav.setViewName("menulist");
		mav.addObject("count", count);
		mav.addObject("list",list);
		return mav;
	}

	//============================ ¸Þ´º º¯°æ Æû==================================
}
