package kr.spring.admin.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoticeController {

	
	@RequestMapping(value="/admin/notice/noticeList.do", method=RequestMethod.GET)
    public String noticeList(){
        // uploadAjax.jsp로 포워딩
    	return "noticeList";
    }
}
