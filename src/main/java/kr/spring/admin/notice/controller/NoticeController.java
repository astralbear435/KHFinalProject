package kr.spring.admin.notice.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.admin.notice.domin.CkeditorCommand;
import kr.spring.admin.notice.domin.NoticeCommend;
import kr.spring.admin.notice.service.NoticeService;

@Controller
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());


	@Resource
	private NoticeService notice;

	//============글쓰기 이미지 업로드 ===========//
	@RequestMapping(value = "/admin/notice/fileUpload.do")
	public String fileUpload(final HttpServletRequest request,CkeditorCommand ckeditorCommand , Model model){
		HttpSession session = request.getSession();
		String rootPath = session.getServletContext().getRealPath("/");
		
		String attachPath = "upload/notice/";

		MultipartFile upload = ckeditorCommand.getN_upload();
		String filename = "";
		String CKEditorFuncNum = "";
		System.out.println("들어옴");
		if(upload != null){
			filename = upload.getOriginalFilename();
			System.out.println(filename);
			ckeditorCommand.setN_filename(filename);
			CKEditorFuncNum = ckeditorCommand.getCKEditorFuncNum();
			try{
				File file = new File(rootPath + attachPath + filename);
				upload.transferTo(file);
			}catch(IOException e){
				e.printStackTrace();
			}  
		}
		model.addAttribute("filePath",attachPath + filename);          //결과값을
		model.addAttribute("CKEditorFuncNum",CKEditorFuncNum);//jsp ckeditor 콜백함수로 보내줘야함
		return "admin/notice/fileUpload";
	}
	//==============게시판 글 등록=============//
	//등록 폼
	@RequestMapping(value="/admin/notice/noticeWrite.do",
			method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");

		NoticeCommend command = new NoticeCommend();
		System.out.println(command);
		command.setN_id(id);

		model.addAttribute("command", command);

		return "noticeWrite";
	}
	//==============데이터 전송=============//
	@RequestMapping(value="/admin/notice/noticeWrite.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid NoticeCommend noticeCommend,BindingResult result, HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<noticeCommend>> : " + noticeCommend );
		}
		if(result.hasErrors()) {
			System.out.println("오류");
			return "noticeWrite";

		}
		//ip 셋팅
		//boardCommand.setIp(request.getRemoteAddr());
		notice.insertNotice(noticeCommend);
		return "redirect:/admin/notice/noticeList.do";
	}
	//============================ 게시글 글 목록 ============================
	@RequestMapping("/admin/notice/noticeList.do")
	public ModelAndView process() {
		Map<String,Object> map = new HashMap<String,Object>();

		int count = notice.selectCountList();
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		List<NoticeCommend> list = null;
		if(count>0) {
			list = notice.selectNoticeList();
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : "+list);
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("noticeList");
		mav.addObject("count",count);
		mav.addObject("list", list);
		return mav;
	}

}
