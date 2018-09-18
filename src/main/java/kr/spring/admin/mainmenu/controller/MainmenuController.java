package kr.spring.admin.mainmenu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value="admin/mainmenu/writeMenu.do", method=RequestMethod.POST)
	public String WriteMenu(@RequestParam("menu_use") String menu_use,
			@RequestParam("menu_name") String menu_name, @RequestParam("menu_url") String menu_url,
			@RequestParam("menu_order") int menu_order, @RequestParam("menu_dd") String menu_dd,
			@RequestParam("parent_num") int parent_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<parent_num>> : "+parent_num);
			log.debug("<<menu_dd>> : "+menu_dd); 
		}
		MainmenuCommend mainmenuCommend = new MainmenuCommend();
		mainmenuCommend.setMenu_name(menu_name);
		mainmenuCommend.setMenu_dropdown(menu_dd);
		mainmenuCommend.setMenu_url(menu_url);
		mainmenuCommend.setMenu_order(menu_order);
		mainmenuCommend.setMenu_use(menu_use);
		if(menu_dd.equals("N"))mainmenuCommend.setMenu_depth(1);
		if(parent_num!=0)mainmenuCommend.setMenu_parent_num(parent_num);
		if(log.isDebugEnabled()) {  
			log.debug("<mainmenuCommend> : "+mainmenuCommend);
		}
		mainmenuService.insertMenu(mainmenuCommend);
		return "redirect:/admin/mainmenu/menulist.do";
	}

	//============================ ¸Þ´º º¯°æ Æû==================================
}
