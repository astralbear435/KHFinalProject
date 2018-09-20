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
		int count =0;
		try {
			menu=mainmenuService.selectMenu(Integer.parseInt(m_num));
			count= mainmenuService.selectmenuCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<menu>> : "+menu); 
			log.debug("<<count>> : "+count); 
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("menu", menu);
		map.put("count", count);
		return map;
	}
	
	@RequestMapping(value="/admin/mainmenu/renewalMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> renewalMenu(){
		List<MainmenuCommend> list =null;
		int count =0;
		try {
			list=mainmenuService.selectMenuList();
			count= mainmenuService.selectmenuCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<menu>> : "+list); 
			log.debug("<<count>> : "+count); 
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	
	@RequestMapping(value="/admin/mainmenu/upOrderMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upOrderMenu(String m_num){
		List<MainmenuCommend> list =null;
		MainmenuCommend menu=null;
		MainmenuCommend t_menu=null;
		String msg="";
		if(log.isDebugEnabled()) {
			log.debug("<<m_num>> : "+m_num);
		}
		int count =0;
		try {
			list=mainmenuService.selectMenuList();
			menu=mainmenuService.selectMenu(Integer.parseInt(m_num));
			count= mainmenuService.selectmenuCount();
			if(log.isDebugEnabled()) {
				log.debug("<<menu>> : "+menu); 
				log.debug("<<count>> : "+count); 
			}
			if((menu.getMenu_order()-1)>0) {
				for(int i =0; i<list.size(); i++) {
					if(list.get(i).getMenu_order()==(menu.getMenu_order()-1)) {
						t_menu=list.get(i);
					}
				}
				t_menu.setMenu_order(menu.getMenu_order());
				mainmenuService.updateMenuOrder(t_menu);
				menu.setMenu_order((menu.getMenu_order()-1));
				mainmenuService.updateMenuOrder(menu);
				if(log.isDebugEnabled()) {
					log.debug("<<menu>> : "+menu); 
				}
				msg="more";
			}else {
				msg="noMore";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping(value="/admin/mainmenu/downOrderMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> downOrderMenu(String m_num){
		List<MainmenuCommend> list =null;
		MainmenuCommend menu=null;
		MainmenuCommend t_menu=null;
		String msg="";
		if(log.isDebugEnabled()) {
			log.debug("<<m_num>> : "+m_num);
		}
		int count =0;
		try {
			list=mainmenuService.selectMenuList();
			menu=mainmenuService.selectMenu(Integer.parseInt(m_num));
			count= mainmenuService.selectmenuCount();
			if(log.isDebugEnabled()) {
				log.debug("<<menu>> : "+menu); 
				log.debug("<<count>> : "+count); 
			}
			if((menu.getMenu_order()+1)<=count) {
				for(int i =0; i<list.size(); i++) {
					if(list.get(i).getMenu_order()==(menu.getMenu_order()+1)) {
						t_menu=list.get(i);
					}
				}
				t_menu.setMenu_order(menu.getMenu_order());
				mainmenuService.updateMenuOrder(t_menu);
				menu.setMenu_order((menu.getMenu_order()+1));
				mainmenuService.updateMenuOrder(menu);
				if(log.isDebugEnabled()) {
					log.debug("<<menu>> : "+menu); 
				}
				msg="more";
			}else {
				msg="noMore";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("msg", msg);
		return map; 
	}
	@RequestMapping(value="/admin/mainmenu/deleteMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteMenu(int m_num){
		if(log.isDebugEnabled()) {
			log.debug("<<menu delete 들어왔다>> : "+m_num);
		}
		String msg="";
		if(log.isDebugEnabled()) {
			log.debug("<<m_num>> : "+m_num);
		}
		try {
			mainmenuService.deleteMenu(m_num);
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
			msg="failed";
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("msg", msg);
		return map; 
	}
	
	
	@RequestMapping(value="/admin/mainmenu/updateMenu.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateMenu(int menu_num,String menu_use,
			String menu_name, String menu_url, int menu_order, String menu_dd,int parent_num){
		String msg ="";
		MainmenuCommend menu= new MainmenuCommend();
		menu.setMenu_num(menu_num);
		menu.setMenu_name(menu_name);
		menu.setMenu_url(menu_url);
		menu.setMenu_use(menu_use);
		menu.setMenu_order(menu_order); 
		menu.setMenu_parent_num(parent_num);
		menu.setMenu_dropdown(menu_dd);
		menu.setMenu_depth(1);
		if(log.isDebugEnabled()) {
			log.debug("<<menu>> : "+menu);
		}
		try {
			mainmenuService.updateMenu(menu);
			msg="success";
		} catch (Exception e) {
			e.printStackTrace();
			msg="failed";
		}
		
		if(log.isDebugEnabled()) {
			log.debug(">>>>>>>>>>>>>>>>>>>>>><<menu>> : "+menu);  
		} 
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("msg", msg);
		 
		return map; 
	}
	
}
