package kr.spring.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.service.GoodsService;
import kr.spring.util.PagingUtil;



@Controller
public class GoodsController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private GoodsService goodsService;
	
	//�ڹٺ� �ʱ�ȭ
		@ModelAttribute("command")
		public GoodsCommand initCommand() {
			return new GoodsCommand();
		}
		//====================�ϴ� ���=================//
		//��� �� ȣ��
		@RequestMapping(value="/goods/write.do",method=RequestMethod.GET)
		public String form() {
		
			return "goodsWrite";
		}
		@RequestMapping(value="/goods/write.do",method=RequestMethod.POST)
		public String submit(@ModelAttribute("command") @Valid GoodsCommand goodsCommand,BindingResult result) {
			if(log.isDebugEnabled()) {
				log.debug("<<goodsCommand ����: >>"+goodsCommand);
			}
			if(result.hasErrors()) {
				return form();
			}
			goodsService.insert(goodsCommand);
			return "redirect:/main/main.do";
		}
		//=================���� ��� �̱�=================
		@RequestMapping("/goods/list.do")
		public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")
				int currentPage,
				@RequestParam(value="keyfield",defaultValue="")
				String keyfield,
				@RequestParam(value="keyword",defaultValue="")
				String keyword,HttpSession session
				) {
			
			String id = (String)session.getAttribute("user_id");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keyfield",keyfield);
			map.put("keyword", keyword);
			//�� ���� �� �� �Ǵ� �˻��� ���� ����
			int count=goodsService.selectRowCount(map);
			PagingUtil page= new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount,"list.do");

			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			Map<String,Object> photo_map = new HashMap<String,Object>();
			List<GoodsCommand> as_list=null;
			List<GoodsListCommand> goodsphotolist=null;

			if(count>0) {
				as_list = goodsService.getASList(map);
			
				if(log.isDebugEnabled()) {
					log.debug("<<list>> : "+as_list);
				}	
			
			}
			int g_count=0;
			goodsphotolist=goodsService.goodsPhotoList(photo_map);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("goodsList");
			mav.addObject("goodslist",goodsphotolist);
			mav.addObject("count",count);
			mav.addObject("user_id",id);
			mav.addObject("as_list", as_list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
		
			return mav;
		}
		//==============��ǰ �� ����====================
		@RequestMapping("/goods/detail.do")
		public ModelAndView process(HttpSession session,@RequestParam("g_num") int g_num,@RequestParam("as_name") String as_name) {
			//�α��� üũ
			String id = (String)session.getAttribute("user_id");
		
			if(log.isDebugEnabled()) {
				log.debug("<<���ǹ�ȣ�����ڽľ�>> : "+g_num);
			}
			 GoodsListCommand goods=goodsService.selectDetailGoods(g_num);
			 GoodsCommand as_detail=goodsService.selectDetailAS(as_name);
			 ModelAndView mav2 = new ModelAndView();
			 mav2.setViewName("goodsDetail");
			 mav2.addObject("goods",goods);
			 mav2.addObject("as_detail",as_detail);
			if(id!=null) {
					 mav2.addObject("user_id",id);	
			 }else {
			    	mav2.addObject("user_id","not");
			 }
			 return mav2;
			
		}
		//===============��ǰ �߰� ��� ================
		@RequestMapping("/goods/addInsert.do")
		@ResponseBody
		public Map<String,String> addGoods(@RequestParam(value="id")String id,@RequestParam(value="name")String name,@RequestParam(value="amount") int amount){
			Map<String,String> map=new HashMap<String,String>();
			
			GoodsListCommand goods=new GoodsListCommand();
			goods.setG_id(id);
			goods.setG_name(name);
			goods.setG_amount(amount);
			if(log.isDebugEnabled()) {
				log.debug("<<���� ����>> :"+goods);
			}
			goodsService.addNewGoods(goods);
			map.put("result","success");
			return map;
		}
	}
