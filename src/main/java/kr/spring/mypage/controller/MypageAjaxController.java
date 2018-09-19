package kr.spring.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.mypage.service.MypageService;
import kr.spring.recriut.service.RecruitService;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
import kr.spring.volunteer.service.VolunteerService;

@Controller
public class MypageAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ShelterService shelterService;
	
	@Resource
	private VolunteerService volunteerService;
	
	@Resource
	private MypageService mypageService;
	
	@Resource
	private RecruitService recruitService;
	
	
	

	@RequestMapping(value="/mypage/volunteerMyCalendar.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam("v_id") String v_id, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<v_id>> : " + v_id);
		}
		
		String id = (String)session.getAttribute("user_id");	
		ApBoCommand bo = new ApBoCommand();
		bo.setBo_id(id);
		String bo_id = bo.getBo_id();
		bo.setId(id);
		String boho_id = bo.getId();
		
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("v_id", v_id);
		map.put("bo_id", bo_id);
		map.put("boho_id", boho_id);
		
		List<RecruitCommand> volunteer = null;
		List<ApBoCommand> boList = null;
		List<ApBoCommand> bohoCallList = null;
		
		volunteer = volunteerService.selectList(map);
		boList = mypageService.selectBoList(bo_id);
		bohoCallList = mypageService.selectBohoCallList(boho_id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : " + volunteer);
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<boList>> : " + boList);
		}		
		if(log.isDebugEnabled()) {
			log.debug("<<bohoCallList>> : " + bohoCallList);
		}	
		
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		mapJson.put("volunteer", volunteer);	
		mapJson.put("boList", boList);
		mapJson.put("bohoCallList", bohoCallList);
		return  mapJson;
	}


/*
	@RequestMapping("/volunteer/volunteerUpdate.do")
	@ResponseBody
	public Map<String,String> modifyReply(RecruitCommand volunteer, HttpSession session, HttpServletRequest request){
		
		if(log.isDebugEnabled()) {
			log.debug("<<volunteer>> : "+ volunteer);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		String user_id = (String)session.getAttribute("user_id");
		if(user_id == null) {
			map.put("result", "logout");
			
		}else if(user_id!=null && user_id.equals(volunteer.getV_id())){
					
		}		
		return map;
	}*/
	
	
	
	
	@RequestMapping(value="/mypage/recruitMyCalendar.do")
	@ResponseBody
	public Map<String,Object> getRecruitList(@RequestParam("r_id") String r_id, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<r_id>> : " + r_id);
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("r_id", r_id);
		
		List<RecruitCommand> recruit = null;
		
		recruit = recruitService.selectRecruitList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<recruit>> : " + recruit);
		}
				
		Map<String,Object> mapJson = new HashMap<String,Object>();
			
		mapJson.put("recruit", recruit);	
		return  mapJson;
	}	

	
	
	/*public Map<String,Object> getDonationList*/
	
/*	@RequestMapping(value="/mypage/donaShelterPage.do")
	@ResponseBody
	public  Map<String,Object> getDonaShelterList(HttpSession session){
				
		Map<String, Object> map = new HashMap<String,Object>();
		
		String s_id = (String)session.getAttribute("user_id");	
		map.put("s_id", s_id);
		
		ShelterCommand shelter = shelterService.selectShelter(s_id);
		String s_name = shelter.getS_name();
				
		OrderCommand donaOrder = new OrderCommand();
		
		
		int countDonaS = mypageService.selectDonaSCount(s_name, s_id);
		
		if(countDonaS > 0) {
			
		}
		
		
		return null;
		
	}
	*/
	//후원 내역 출력
	@RequestMapping(value="/mypage/donaShelterPage.do")
	@ResponseBody
	public Map<String,Object> getDonaShelterList(@RequestParam("dona_asname") String dona_asname, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<dona_asname>> : " + dona_asname);
		}
			
		Map<String,Object> map = new HashMap<String,Object>();
		
		String s_id = (String)session.getAttribute("user_id");
	
			map.put("s_id", s_id);
			map.put("dona_asname", dona_asname);
			
			List<OrderCommand> donaS = null;			
			donaS =	mypageService.selectDonaSList(map);	
			
			if(log.isDebugEnabled()) {
				log.debug("<<donaS>> : " + donaS);
			}
				
			Map<String,Object> mapJson = new HashMap<String,Object>();

			mapJson.put("list", donaS);	
			return  mapJson;
	}
	
	@RequestMapping("/mypage/getProductNameNPrice.do")
	@ResponseBody
	public Map<String,Object> getProductNameNPrice(@RequestParam("g_num") int g_num){
		
		if(log.isDebugEnabled()) {
			log.debug("<<g_num>> : " + g_num);
		}
					
		GoodsListCommand goods =	mypageService.getMyGoods(g_num);	
			
			if(log.isDebugEnabled()) {
				log.debug("<<goods>> : " + goods);
			}
				
			Map<String,Object> mapJson = new HashMap<String,Object>();

			mapJson.put("goods", goods);	
			return  mapJson;
	}	
		
	//마이페이지 후원 내역 출력
	@RequestMapping(value="/mypage/donaMyPage.do")
	@ResponseBody
	public Map<String,Object> getDonaMyList(HttpSession session){
				
		Map<String,Object> map = new HashMap<String,Object>();
		
		String dona_id = (String)session.getAttribute("user_id");
	
			map.put("dona_id", dona_id);
							
			List<OrderCommand> donaList = null;
			donaList =	mypageService.selectDanaList(dona_id);	
			
			if(log.isDebugEnabled()) {
				log.debug("<<donaList>> : " + donaList );
			}
		
			Map<String,Object> mapJson = new HashMap<String,Object>();
			
			mapJson.put("list", donaList);	
			
		/*	GoodsListCommand goods = new GoodsListCommand();	
			for(int i=1;i<6;i++) {
				goods = mypageService.getMyGoods(i);
				String g_name = goods.getG_name();
				Integer g_price = goods.getG_price();
				Integer g_num= goods.getG_num();
				mapJson.put("g_name["+i+"]", g_name);
				mapJson.put("g_price["+i+"]", g_price);
				mapJson.put("g_num["+i+"]", g_num);			
			}*/
			return  mapJson;
	}
}
	

