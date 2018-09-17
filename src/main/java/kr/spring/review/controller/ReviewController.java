package kr.spring.review.controller;

import java.io.File;
import java.io.PrintWriter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.goods.domain.GoodsCommand;
import kr.spring.review.domain.ReviewCommand;
import kr.spring.review.service.ReviewService;
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.util.PagingUtil;

@Controller
public class ReviewController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Resource
	private ReviewService reviewService;
	
	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("command")
	public ReviewCommand initReviewCommand() {
		return new ReviewCommand();
	}
	//==============�۾���============
	@RequestMapping(value="/review/reviewWrite.do",method=RequestMethod.GET)
	public ModelAndView WriteForm(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");
		Map<String,Object> map=new HashMap<String,Object>();
		ModelAndView mav=new ModelAndView();
		ReviewCommand command=new ReviewCommand();
		command.setRe_id(id);
		
		if(id==null) {
			 mav.setViewName("member/memberLogin");
			 return mav;
		}else {
			//��ȣ�� ��� �ҷ�����
			List<ShelterCommand> list=null;
			list=reviewService.selectAsname(map);
			
			mav.setViewName("reviewWrite");
			mav.addObject("command", command);
			mav.addObject("list",list);			
		}
		return mav;
	}
	//���۵� ������ ó��
	@RequestMapping(value="/review/reviewWrite.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid ReviewCommand reviewCommand,BindingResult result, HttpServletRequest request)
	{
		if(log.isDebugEnabled()) {
			log.debug("<<���� Ŀ�ǵ� - ��ϵ� ����>> : " + reviewCommand );
		}
		if(result.hasErrors()) {
			return "reviewWrite";
		}
		//�� ����ϱ�
		reviewService.insertReview(reviewCommand);
		return "redirect:/review/reviewList.do";
	}	
//============�۾��� �̹��� ���ε� ===========
	   @RequestMapping("/review/imageUploader.do")
	   public void uploadImage(MultipartFile file, HttpSession session, HttpServletResponse response) throws Exception {
	      response.setContentType("text/html;charset=utf-8");
	      PrintWriter out = response.getWriter();

	      // ���ε��� ���� ���
	      String realFolder = session.getServletContext().getRealPath("resources/image_upload");

	      // ���ε��� ���� �̸�
	      String org_filename = file.getOriginalFilename();
	      String str_filename = System.currentTimeMillis() + org_filename;

	      System.out.println("���� ���ϸ� : " + org_filename);
	      System.out.println("������ ���ϸ� : " + str_filename);

	      String filepath = realFolder + "\\" + session.getAttribute("user_id") + "\\" + str_filename;
	      System.out.println("���ϰ�� : " + filepath);

	      File f = new File(filepath);
	      if (!f.exists()) {
	         f.mkdirs();
	      }
	      file.transferTo(f);
	      out.println("../resources/image_upload/"+session.getAttribute("user_id")+"/"+str_filename);
	      out.close();
	   }
//==============�� ��� �����===========================
	   @RequestMapping("/review/reviewList.do")
	   public ModelAndView processList(@RequestParam(value="pageNum",defaultValue="1")
		int currentPage,
		@RequestParam(value="keyfield",defaultValue="")
		String keyfield,
		@RequestParam(value="keyword",defaultValue="")
		String keyword,HttpSession session
		) {
			String user_id = (String)session.getAttribute("user_id");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keyfield",keyfield);
			map.put("keyword", keyword);
			//�� ���� �� �� �Ǵ� �˻��� ���� ����
			int count=reviewService.selectCount(map);
			PagingUtil page= new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount,"reviewList.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			List<ReviewCommand> review_list=null;
			
			if(count>0) {
				review_list = reviewService.getReviewList(map);
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("reviewList");
			mav.addObject("review",review_list);
			mav.addObject("count",count);
			mav.addObject("user_id",user_id);
			mav.addObject("pagingHtml", page.getPagingHtml());
			return mav;
	   }
	

}
