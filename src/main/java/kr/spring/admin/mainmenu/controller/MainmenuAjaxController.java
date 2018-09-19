package kr.spring.admin.mainmenu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.admin.mainmenu.domain.MainmenuCommend;
import kr.spring.admin.mainmenu.service.MainmenuService;



@Controller
public class MainmenuAjaxController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private MainmenuService mainmenuService;


	@RequestMapping(value="/admin/mainmenu/selectedMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectedMenu(String m_num){
		MainmenuCommend menu=null;
		if(log.isDebugEnabled()) {
			log.debug("<<m_num>> : "+m_num);
		}
		try {
			menu =new MainmenuCommend();
			menu=mainmenuService.selectMenu(Integer.parseInt(m_num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<menu>> : "+menu); 
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("menu", menu);
		return map;
	}
}
