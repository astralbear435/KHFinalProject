package kr.spring.ap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.service.ApCallService;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class ApCallController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 12;
	private int pageCount = 9;
	
	@Resource
	private ApCallService apCallService;

//==========임시보호자 집으로 부르기 ==========
	//등록폼
	@RequestMapping(value="/ap/apCallForm.do", method=RequestMethod.GET)
	public String apcallform(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ApCallCommand callcommand = new ApCallCommand();
		callcommand.setCall_name(id);
		
		model.addAttribute("command",callcommand);
		
		return "apCallForm";
	}
	
	//전송된 데이터 처리
	@RequestMapping(value="/ap/write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
						@Valid ApCallCommand apCallCommand, 
						BindingResult result, 
						HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<apCallCommand>> : " + apCallCommand);
		}
		
		if(result.hasErrors()) {
			return "apWrite";
		}
		
		//글쓰기
		apCallService.insert(apCallCommand);
		
		return "redirect:/ap/apCallList.do";
	}
//==========임시보호자 집으로 부르기 목록==========
	@RequestMapping("/ap/apCallList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
								@RequestParam(value="call_wei", defaultValue="") String call_wei,
								@RequestParam(value="call_re", defaultValue="") String call_re) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("call_wei", call_wei);
		map.put("call_re", call_re);
		
		if(log.isDebugEnabled()) {
			log.debug("call_wei : "+call_wei);
			log.debug("call_re : "+call_re);
		}
		
		//총 글의 갯수 또는 검색 된 글의 갯수
		int count = apCallService.selectCallRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(call_wei, call_re, currentPage, count, rowCount, pageCount, "apCallList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ApCallCommand> list = null;
		if(count > 0) {
			list = apCallService.selectCallList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : " + list);
			}
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("apCallList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
//==========임시보호자 집으로 부르기 글 상세보기==========
	@RequestMapping("/ap/apCalldetail.do")
	public ModelAndView process(@RequestParam("call_num") int call_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<call_num>> : " + call_num);
		}
		
		ApCallCommand apcall = apCallService.selectCallBoard(call_num);
		
		//enter에 대한 줄바꿈처리
		apcall.setCall_intro(StringUtil.useBrNoHtml(apcall.getCall_intro()));
		
		return new ModelAndView("apCallView", "apcall", apcall);
	}
//==========임시보호자 집으로 부르기 글 수정==========	
	//수정폼
	@RequestMapping(value="/ap/apCallupdate.do", method=RequestMethod.GET)
	public String form(@RequestParam("call_num") int call_num, Model model) {
		
		ApCallCommand apcall = apCallService.selectCallBoard(call_num);
		
		model.addAttribute("command", apcall);
		
		return "apCallModify";
		
	}
	//수정폼에서 전송된 데이터 처리
	@RequestMapping(value="/ap/update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid ApCallCommand apCallCommand,
						 BindingResult result, HttpSession session, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<boardCommand>> : " + apCallCommand);
		}
		
		if(result.hasErrors()) {
			return "apCallModify";
		}
		
		//글수정
		apCallService.update(apCallCommand);
		
		return "redirect:/ap/apCallList.do";
	}
//==========게시판 글 삭제==========	
	@RequestMapping("/ap/apCalldelete.do")
	public String submit(@RequestParam("call_num") int call_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<call_num>> : " + call_num);
		}
		
		//글삭제
		apCallService.apCalldelete(call_num);
		
		return "redirect:/ap/apCallList.do";
	}
		
}
