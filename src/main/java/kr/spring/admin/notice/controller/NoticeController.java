package kr.spring.admin.notice.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.admin.notice.domin.NoticeCommend;
import kr.spring.admin.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private NoticeService notice;
	
	//==============�Խ��� �� ���=============//
		//��� ��
		@RequestMapping(value="/admin/notice/noticeWrite.do",
				method=RequestMethod.GET)
		public String form(HttpSession session, Model model) {
			String id = (String)session.getAttribute("user_id");

			NoticeCommend command = new NoticeCommend();
			command.setN_id(id);

			model.addAttribute("command", command);

			return "noticeWrite";
		}
	
	@RequestMapping(value="/admin/notice/noticeList.do", method=RequestMethod.GET)
    public String noticeList(){
        // uploadAjax.jsp�� ������
    	return "noticeList";
    }
}
