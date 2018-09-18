package kr.spring.ap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.ap.domain.ApBookCommand;
import kr.spring.ap.service.ApBookService;

@Controller
public class ApBookAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ApBookService apBookService;
	
	//================북마크 등록=================
	@RequestMapping(value="/ap/insertBook.do")
	@ResponseBody
	public Map<String,Object> insertBook(@RequestParam(value="ap_book_num")int ap_book_num,
										@RequestParam(value="ap_book_id")String ap_book_id,
										ApBookCommand apbook,
										HttpSession session){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		int count = apBookService.Bookchecked(apbook);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		
		List<ApBookCommand> booklist = null;
		if(count==1) {
			apBookService.cancleBook(apbook);
		}else{
			apBookService.insertBook(apbook);
		}
		
		booklist = apBookService.checkBook(map);
		if(log.isDebugEnabled()) {
			log.debug("<<apbook>> : "+apbook);
		}
		
		map.put("count", count);
		map.put("booklist", booklist);
		
		return map;
	}
	
	//================북마크 새로고침=================
	@RequestMapping(value="/ap/checkBook.do")
	@ResponseBody
	public Map<String,Object> checkBook(@RequestParam(value="ap_book_num")int ap_book_num,
										@RequestParam(value="ap_book_id")String ap_book_id,
										ApBookCommand apbook,
										HttpSession session,
										HttpServletRequest request) throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		int count = apBookService.Bookchecked(apbook);
		
		List<ApBookCommand> booklist = null;
		
		booklist = apBookService.checkBook(map);
		if(log.isDebugEnabled()) {
		log.debug("<<apbook>> : "+apbook);
		}
		
		map.put("count", count);
		map.put("booklist", booklist);
		
		//JSON 데이터 변환
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(map);
		
		request.setAttribute("jsonData", jsonData);
		
		return map;
	}
	
	
}
