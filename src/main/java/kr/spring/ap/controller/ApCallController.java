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

//==========�ӽú�ȣ�� ������ �θ��� ==========
	//�����
	@RequestMapping(value="/ap/apCallForm.do", method=RequestMethod.GET)
	public String apcallform(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ApCallCommand callcommand = new ApCallCommand();
		callcommand.setCall_name(id);
		
		model.addAttribute("command",callcommand);
		
		return "apCallForm";
	}
	
	//���۵� ������ ó��
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
		
		//�۾���
		apCallService.insert(apCallCommand);
		
		return "redirect:/ap/apCallList.do";
	}
//==========�ӽú�ȣ�� ������ �θ��� ���==========
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
		
		//�� ���� ���� �Ǵ� �˻� �� ���� ����
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
//==========�ӽú�ȣ�� ������ �θ��� �� �󼼺���==========
	@RequestMapping("/ap/apCalldetail.do")
	public ModelAndView process(@RequestParam("call_num") int call_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<call_num>> : " + call_num);
		}
		
		ApCallCommand apcall = apCallService.selectCallBoard(call_num);
		
		//enter�� ���� �ٹٲ�ó��
		apcall.setCall_intro(StringUtil.useBrNoHtml(apcall.getCall_intro()));
		
		return new ModelAndView("apCallView", "apcall", apcall);
	}
//==========�ӽú�ȣ�� ������ �θ��� �� ����==========	
	//������
	@RequestMapping(value="/ap/apCallupdate.do", method=RequestMethod.GET)
	public String form(@RequestParam("call_num") int call_num, Model model) {
		
		ApCallCommand apcall = apCallService.selectCallBoard(call_num);
		
		model.addAttribute("command", apcall);
		
		return "apCallModify";
		
	}
	//���������� ���۵� ������ ó��
	@RequestMapping(value="/ap/update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid ApCallCommand apCallCommand,
						 BindingResult result, HttpSession session, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<boardCommand>> : " + apCallCommand);
		}
		
		if(result.hasErrors()) {
			return "apCallModify";
		}
		
		//�ۼ���
		apCallService.update(apCallCommand);
		
		return "redirect:/ap/apCallList.do";
	}
//==========�Խ��� �� ����==========	
	@RequestMapping("/ap/apCalldelete.do")
	public String submit(@RequestParam("call_num") int call_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<call_num>> : " + call_num);
		}
		
		//�ۻ���
		apCallService.apCalldelete(call_num);
		
		return "redirect:/ap/apCallList.do";
	}
		
}
