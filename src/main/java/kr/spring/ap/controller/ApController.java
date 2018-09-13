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
import kr.spring.ap.domain.ApCommand;
import kr.spring.ap.service.ApService;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class ApController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 12;
	private int pageCount = 9;
	
	@Resource
	private ApService apService;

//==========�ӽú�ȣ�� ���� ==========
	//������
	@RequestMapping(value="/ap/apMain.do", method=RequestMethod.GET)
	public String apmainform(HttpSession session, Model model) {

		String id = (String)session.getAttribute("user_id");

		ApCommand command = new ApCommand();
		command.setId(id);

		model.addAttribute("command",command);

		return "apMain";
	}

	
//==========�ӽú�ȣ�� ��û�� ==========
	//���Ǽ� �޴� ��
	@RequestMapping(value="/ap/apForm.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ApCommand command = new ApCommand();
		command.setId(id);
		
		model.addAttribute("command",command);
		
		return "apForm";
	}
	//�����
	@RequestMapping(value="/ap/apForm1.do", method=RequestMethod.GET)
	public String form1(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ApCommand command = new ApCommand();
		command.setId(id);
		
		model.addAttribute("command",command);
		
		return "apForm1";
	}
	//���۵� ������ ó��
	@RequestMapping(value="/ap/apWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
						@Valid ApCommand apCommand, 
						BindingResult result, 
						HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<apCommand>> : " + apCommand);
		}
		
		if(result.hasErrors()) {
			return "apWrite";
		}
		
		//�۾���
		apService.insert(apCommand);
		
		return "redirect:/ap/apMain.do";
	}
	
//==========�ӽú�ȣ�� ������ �θ��� ���==========
	@RequestMapping("/ap/apList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
								@RequestParam(value="keyfield", defaultValue="") String keyfield,
								@RequestParam(value="keyword", defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�� ���� ���� �Ǵ� �˻� �� ���� ����
		int count = apService.selectApRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "apList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ApCommand> list = null;
		if(count > 0) {
			list = apService.selectApList(map);
			log.debug("<<list>> : " + list);
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : " + list);
			}
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("apList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
//==========�ӽú�ȣ�� ������ �θ��� �� �󼼺���==========
	@RequestMapping("/ap/apdetail.do")
	public ModelAndView process(@RequestParam("ap_num") int ap_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<ap_num>> : " + ap_num);
		}
		
		ApCommand apcommand = apService.selectApBoard(ap_num);
		
		//enter�� ���� �ٹٲ�ó��
		apcommand.setAp_ser(StringUtil.useBrNoHtml(apcommand.getAp_ser()));
		
		return new ModelAndView("apView", "apcommand", apcommand);
	}
//==========�ӽú�ȣ�� ������ �θ��� �� ����==========	
	//������
	@RequestMapping(value="/ap/apModify.do", method=RequestMethod.GET)
	public String form(@RequestParam("ap_num") int ap_num, Model model) {
		
		ApCommand apcommand = apService.selectApBoard(ap_num);
		
		model.addAttribute("apCommand", apcommand);
		
		return "apModify";
		
	}
	//���������� ���۵� ������ ó��
	@RequestMapping(value="/ap/apUpdate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid ApCommand apCommand,
						 BindingResult result, HttpSession session, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<apCommand>> : " + apCommand);
		}
		
		if(result.hasErrors()) {
			return "apModify";
		}
		
		//�ۼ���
		apService.update(apCommand);
		
		return "redirect:/ap/apList.do";
	}

//
}
