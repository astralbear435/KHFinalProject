package kr.spring.recruit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.recriut.service.RecruitService;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;


@Controller
public class RecruitController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private RecruitService recruitService;

	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("command")
	public RecruitCommand initCommand() {
		return new RecruitCommand();
	}

	//...................�Խ��� �� ���...........................//
	//��� ��
	@RequestMapping(value="/recruit/recruitWrite.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");

		RecruitCommand recruit = new RecruitCommand();
		recruit.setR_id(id);

		model.addAttribute("recruit", recruit);

		return "recruitWrite";
	}

	//���۵� ������ ó��
	@RequestMapping(value="/recruit/recruitWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("recruit") RecruitCommand recruit, BindingResult result, HttpServletRequest request) {
		if(log.isDebugEnabled()) {

			log.debug("<<recruitCommand>> : " + recruit);
		}

		/*if(result.hasErrors()) {
			return "voluntaryWrite";
		}*/
		String r_content = recruit.getR_content();
		recruit.setR_content(StringUtil.useNoHtml(r_content));
	
		recruitService.insert(recruit);

		return "recruitList";
	}

	//...................�Խ��� �� ���    .......................//
	@RequestMapping("/recruit/recruitList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, @RequestParam(value="keyfield",defaultValue="")String keyfield
			, @RequestParam(value="keyword",defaultValue="")String keyword) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//
		int count = recruitService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>>"+count);
		}

		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"recruitList.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<RecruitCommand> list = null;
		List<ShelterCommand> shelterNameList = null;
		
		if(count > 0) {
			list = recruitService.selectList(map);
			shelterNameList = recruitService.selectBoNameList(map);
		}
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("recruitList");
		mav.addObject("count",count);		
		mav.addObject("list",list);
		mav.addObject("shelterNameList",shelterNameList);
		mav.addObject("pagingUtil",page.getPagingHtml());

		return mav;
	}

	@RequestMapping("/recruit/imageView.do")
	public ModelAndView ViewImage(@RequestParam("r_num") int r_num) {

		RecruitCommand recruit = recruitService.selectBoard(r_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", recruit.getR_image());
		mav.addObject("filename", recruit.getR_filename());

		return mav;
	}

	//...................�Խ��� �� �� ����.......................//
	@RequestMapping("/volunteer/volunteerWrite2.do")
	public ModelAndView process(@RequestParam("r_num") int r_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<r_num>> : "+ r_num );
		}

		RecruitCommand recruit = recruitService.selectBoard(r_num);	
		ShelterCommand shelterName = recruitService.selectBoName(r_num);
		
		if(log.isDebugEnabled()) {
			log.debug("<<recruit>> : "+ recruit );
		}
		if(log.isDebugEnabled()) {
			log.debug("<<shelterName>> : "+ shelterName );
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("volunteer/volunteerWrite");
		mav.addObject("recruit",recruit);
		mav.addObject("shelterName",shelterName);
		
		return mav;
	}
	
	//비회원 봉사활동 신청 못함
	@RequestMapping("/volunteer/noUser.do")
	public String processNoUser(HttpSession session) {

		return "redirect:/recruit/recruitList.do";
	}


	//...................�Խ��� �� ����..........................//
	//���� ��
	@RequestMapping(value="/recruit/recruitUpdate.do", method=RequestMethod.GET)
	public String form(@RequestParam("r_num") int r_num, Model model) {

		RecruitCommand recruit = recruitService.selectBoard(r_num);

		model.addAttribute("recruit", recruit);

		return "recruitModify";
	}
	
	//
	@RequestMapping(value="/recruit/recruitUpdate.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("recruit") RecruitCommand recruit, BindingResult result, HttpSession session, HttpServletRequest request) {

		if(log.isDebugEnabled()) {
			log.debug("<<recruit>> : " + recruit);
		}			
		/*if(result.hasErrors()) {
				return "recruitModify";
			}*/
		//ip����
		//recruitCommand.setR_Ip(request.getRemoteAddr());

		//�� ����
		recruitService.update(recruit);

		return "redirect:/recruit/recruitList.do";
	}

	//...................�Խ��� �� ����..........................//
	@RequestMapping("/recruit/recruitDelete.do")
	public String submit(@RequestParam("r_num") int r_num, HttpSession session) {
		recruitService.deleteVolunteer(r_num);
		recruitService.delete(r_num);


		if(log.isDebugEnabled()) {
			log.debug("<<r_num>> : " + r_num);
		}

		return "redirect:/recruit/recruitList.do";
	}
}
