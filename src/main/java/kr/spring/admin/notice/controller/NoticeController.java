package kr.spring.admin.notice.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.admin.notice.domin.NoticeCommend;
import kr.spring.admin.notice.service.NoticeService;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
 
@Controller
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name="uploadPath")
	String uploadPath;

	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private NoticeService notice;

	//============글쓰기 이미지 업로드 ===========//
	@RequestMapping(value = "/admin/notice/fileUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> imageUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload) throws IOException {
		OutputStream out = null;
		//PrintWriter printWriter = null;
		Map<String,Object> map=new HashMap<String,Object>();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		Calendar today = Calendar.getInstance();  
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		String monthStr="";


		if(month<10) monthStr ="0"+month;
		else monthStr =""+month;


		String defaultPath = request.getSession().getServletContext().getRealPath("/");
		String contextPath = request.getSession().getServletContext().getContextPath();
		String fileUploadPathTail = "upload\\notice\\"+year+"" +monthStr+"\\";
		String fileUploadPath = defaultPath+fileUploadPathTail;
		String fileUrl="";
		String fileName="";
		System.out.println("defaultPath : " + defaultPath);
		System.out.println("contextPath : " + contextPath);
		System.out.println("fileUploadPathTail : " + fileUploadPathTail);
		System.out.println("fileUploadPath : " + fileUploadPath);
		String test="";
		try{
			if( upload!=null) {
				fileName = upload.getOriginalFilename();
				System.out.println("fileName : " + fileName);
				String fileNameExt = fileName.substring(fileName.indexOf(".")+1);
				System.out.println("fileNameExt : " + fileNameExt);

				if(!"".equals(fileName)) {
					File destD = new File(fileUploadPath);

					if(!destD.exists()) {
						destD.mkdirs();
					}
					File destination = File.createTempFile("ckeditor_","."+fileNameExt,destD);
					upload.transferTo(destination);
					fileUrl=contextPath+"/upload/notice/"+year+"" +monthStr+"/"+destination.getName();
					System.out.println("fileUrl : " + fileUrl );
					System.out.println("destination : " + destination.getName());
					String tmp = destination.getName();
					test = tmp.substring(9, tmp.length()-4);
					System.out.println("test : " + test);
					//printWriter = response.getWriter();

					//printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
					//printWriter.flush();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				/*               if (printWriter != null) {
                   printWriter.close();
               }*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("filename",fileName);
		map.put("uploaded", 1);
		map.put("url", fileUrl);
		return map;
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
		notice.insertNotice(noticeCommend);
		return "redirect:/admin/notice/noticeList.do";
	}
	//============================ 게시글 글 목록 ============================
	@RequestMapping("/admin/notice/noticeList.do")
	public ModelAndView process() {

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
//==========모든 회원이 보는 글 목록==========
		@RequestMapping("/main/noticeList.do")
		public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			//총 글의 갯수 또는 검색 된 글의 갯수
			int count = notice.selectCountList();
			
			if(log.isDebugEnabled()) {
				log.debug("<<count>> : " + count);
			}
			
			PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "List.do");
			
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			List<NoticeCommend> list = null;
			if(count > 0) {
				list = notice.selectNoticeList2(map);
				
				if(log.isDebugEnabled()) {
					log.debug("<<list>> : " + list);
				}
				
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("noticeList2");
			mav.addObject("count", count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
			return mav;
		}
		
		@RequestMapping("/main/noticeDetail.do")
		public ModelAndView process2(@RequestParam("n_idx") int n_inx) {
			
			if(log.isDebugEnabled()) {
				log.debug("<<n_inx>> : " + n_inx);
			}
			
			//해당 글의 조회수 증가
			notice.updateHits(n_inx);
			
			NoticeCommend noticeCommend = notice.selectNotice(n_inx);
			
			//enter에 대한 줄바꿈처리
			noticeCommend.setN_subject(StringUtil.useBrNoHtml(noticeCommend.getN_subject()));
			
			return new ModelAndView("noticeDetail2", "noticeCommend", noticeCommend);
		}

	//============================ 게시글 글 수정 ============================
	//수정 폼
	@RequestMapping(value="/admin/notice/noticeModify.do",method=RequestMethod.GET)
	public String modifyForm(@RequestParam("n_idx") int n_idx, Model model) {

		NoticeCommend noticeCommend = notice.selectNotice(n_idx);

		model.addAttribute("command",noticeCommend); 


		return "noticeModify";
	}
	//수정폼에서 전송된 데이터 처리
	@RequestMapping(value="/admin/notice/noticeModify.do", method=RequestMethod.POST)
	public String modifySubmit(@ModelAttribute("command") @Valid NoticeCommend noticeCommend, BindingResult result,HttpSession session,HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<noticeCommend>> : " + noticeCommend);
		}

		if(result.hasErrors()) {
			return "noticeModify";
		}

		//글 수정
		notice.updateNotice(noticeCommend);
		return "redirect:/admin/notice/noticeList.do";
	}
	//============================ 게시글 글 상세 ============================
		@RequestMapping("/admin/notice/noticeDetail.do")
		public ModelAndView noticeDetail(@RequestParam("n_idx") int n_idx) {

			if(log.isDebugEnabled()) {
				log.debug("<<n_idx>> : "+n_idx);
			}

			//해당 글의 조회수 증가
			notice.updateHits(n_idx);;
			NoticeCommend noticeCommend = notice.selectNotice(n_idx);
			
			return new ModelAndView("admin/notice/noticeDetail","notice",noticeCommend);
		}


}
