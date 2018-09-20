package kr.spring.shelter.controller;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
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

import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.goods.service.GoodsService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.mypage.service.MypageService;
import kr.spring.recriut.service.RecruitService;
import kr.spring.review.domain.ReviewCommand;
import kr.spring.review.service.ReviewService;
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
import kr.spring.util.CipherTemplate;
import kr.spring.util.PagingUtil;

@Controller
public class ShelterController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 5;
	private int pageCount = 10;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private ShelterService shelterService;

	@Resource
	private RecruitService recruitService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private MypageService mypageService;
	
	@Resource
	private ReviewService reviewService;
	
	@Resource
	private CipherTemplate cipherAES;
	
	
	@ModelAttribute("command")
	public ShelterCommand initShelterCommand() {
		return new ShelterCommand();
	}
	@ModelAttribute("review")
	public ReviewCommand initReviewCommand() {
		return new ReviewCommand();
	}
	
	//================== ȸ������ =========================
	//ȸ�� ���� �� ȣ��
	@RequestMapping(value="/shelter/write.do", method=RequestMethod.GET)
	public String formShelter() {
		return "shelterWrite";
	}
	//ȸ�� ���� ������ ����
	@RequestMapping(value="/shelter/write.do", method=RequestMethod.POST)
	public String submitShelter(@Valid ShelterCommand shelterCommand, 
								BindingResult result) {
		
		if(result.hasErrors()) {
			return formShelter();
		}
		
		//CipherTemplate�� �̿��� ��ȣȭ
		shelterCommand.setS_passwd(cipherAES.encrypt(shelterCommand.getS_passwd()));
		
		// ȸ������
		shelterService.insert(shelterCommand);
		//as_goods�� �ӽ� ���
		GoodsCommand goods=new GoodsCommand();
		goods.setAs_id(shelterCommand.getS_id());//���̵�
		goods.setAs_name(shelterCommand.getS_name());//��ȣ�� ��
		goods.setAs_location(shelterCommand.getS_address1());
		goods.setPad(0);
		goods.setDogfood(0);
		goods.setCatfood(0);
		goods.setShampoo(0);
		goods.setCatsand(0);
		goods.setAs_did(0);
		//�ӽð���
		goodsService.insert(goods);

		return "redirect:/main/main.do";
	}
	
	// ============= ���̵� �ߺ� Ȯ�� ==============
	@RequestMapping("/shelter/confirmId.do")
	@ResponseBody
	public Map<String,String> processShelterId(@RequestParam("id") String id){

		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectMember(id);
		
		if(member != null) {
			//���̵� �ߺ�
			map.put("result", "idDuplicated");
		}else {
			//���̵� �� �ߺ�
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	// ============= �̸��� �ߺ� Ȯ�� ==============
		@RequestMapping("/shelter/confirmEmail.do")
		@ResponseBody
		public Map<String,String> processShelter(@RequestParam("email") String email){

			Map<String,String> map = new HashMap<String,String>();
			
			MemberCommand member = memberService.checkMember_e(email);
			System.out.println(member);
			
			if(member != null) {
				// �̸��� �ߺ�
				map.put("result", "emailDuplicated");
			}else {
				// �̸��� �� �ߺ�
				map.put("result", "emailNotFound");
			}
			
			return map;
		}
	
	//================== ȸ�� �� ���� =========================
	// ���� �� ��й�ȣ Ȯ��
	@RequestMapping("/shelter/shelterConfirm.do")
	public String confirmFormShelter(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// ��ȣȭ �� ��й�ȣ�� ��ȣȭ(db�� ���� ����)
		shelter.setS_passwd(cipherAES.decrypt(shelter.getS_passwd()));
		
		model.addAttribute("shelter", shelter);
		
		return "shelterConfirm";
	}
	
	// �� ���� Ȯ��
	@RequestMapping("/shelter/shelterInfo.do")
	public String processShelter(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		int as_did=goodsService.selectDid(id);
		
		// ��ȣȭ �� ��й�ȣ�� ��ȣȭ(db�� ���� ����)
		shelter.setS_passwd(cipherAES.decrypt(shelter.getS_passwd()));
		
		model.addAttribute("shelter", shelter);
		model.addAttribute("as_did", as_did);
		
		return "shelterInfo";
	}

	//================== ȸ�� ���� ���� =========================
	// ���� �� ������ ó��
	@RequestMapping("/shelter/update.do")
	public String submitUpdateShelter(@Valid ShelterCommand shelterCommand,
								BindingResult result, HttpSession session) {
		
		System.out.println("----->" + shelterCommand);
		
		if(result.hasErrors()) {
			return "shelterModify";
		}
		
		// ������ ÷������ �ʾҴٸ� ���� �ִ� ������ �ٽ� �Է�
		if(shelterCommand.getS_filename().equals("")) {
			String id = (String)session.getAttribute("user_id");
			ShelterCommand shelter = shelterService.selectShelter(id);
			
			byte[] originS_uploadfile = shelter.getS_uploadfile();
			String originS_filename = shelter.getS_filename();
			
			shelterCommand.setS_uploadfile(originS_uploadfile);
			shelterCommand.setS_filename(originS_filename);
		}
		
		// CipherTemplate�� �̿��� ��ȣȭ > ���� ���� �Է��� ����� ��ȣȭ����
		shelterCommand.setS_passwd(cipherAES.encrypt(shelterCommand.getS_passwd()));
		
		// ȸ����������
		shelterService.update(shelterCommand);
		
		return "redirect:/shelter/shelterInfo.do"; // ������ �� �Ǿ����� Ȯ���ϱ� ���ؼ� �󼼺���� �ѱ�
	}

	//================== ȸ�� Ż�� =========================
	@RequestMapping("/shelter/delete.do")
	public String submitDeleteShelter(@Valid ShelterCommand shelterCommand, 
								BindingResult result, HttpSession session) {
		
		ShelterCommand shelter = shelterService.selectShelter(shelterCommand.getS_id());// �ڹٺ� ���·� id��������
			
		shelterService.delete(shelter.getS_id());
				
		// �α׾ƿ�
		session.invalidate();
			
		return "redirect:/main/main.do";
			
	}

	//================== ��ȣ�� ����Ʈ =========================
	@RequestMapping(value="/shelter/shelterList.do", method=RequestMethod.GET)
	public ModelAndView shelterListGet(@RequestParam(value="local", defaultValue="") String local,
									@RequestParam(value="name", defaultValue="") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("local", local);
		map.put("name", name);
		
		// �� ���� ���� �Ǵ� �˻� �� ���� ����
		int count = shelterService.selectRowCount(map);
		System.out.println(count);
		
		List<ShelterCommand> list = null;
		if(count > 0) {
			list = shelterService.selectList(map);
			System.out.println(list);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shelterList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping(value="/shelter/shelterList.do", method=RequestMethod.POST)
	public ModelAndView shelterListPost(@RequestParam(value="local", defaultValue="") String local,
									@RequestParam(value="name", defaultValue="") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("local", local);
		map.put("name", name);
		
		// �� ���� ���� �Ǵ� �˻� �� ���� ����
		int count = shelterService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		List<ShelterCommand> list = null;
		if(count > 0) {
			list = shelterService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<list>> : " + list);
			}
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shelterList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		return mav;
	}
	
	// ��ȣ�� ������
	@RequestMapping(value="/shelter/shelterDetail.do", method=RequestMethod.GET)
	public String viewShelter(@RequestParam("id") String id, Model model,@RequestParam(value="pageNum",defaultValue="1")
	int currentPage, HttpSession session) {
		
		String user_id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		int recruitCount = recruitService.recruitCount(id);
		System.out.println(recruitCount);
		
		// �ּҰ����� ��ȣ ������ ������
		String s_address1 = shelter.getS_address1();
		int findIndexOf = s_address1.indexOf("(");
		if(findIndexOf > 0) {
			String address = s_address1.substring(0,findIndexOf-1);
			shelter.setS_address1(address);
		}
				
		//�ش� ��ȣ�Ұ� �� �۸� �������
		int count=reviewService.selectCountId(shelter.getS_id());
		PagingUtil page= new PagingUtil(currentPage, count, rowCount, pageCount,"shelterDetail.do");
		
		List<ReviewCommand> review_list=null;
		if(count>0) {
			review_list = reviewService.shelterReviewList(shelter.getS_id());
			model.addAttribute("review", review_list);
			//��ι��� ����Ʈ �ҷ�����
			Date beforeDate=reviewService.selectBeforeDate(shelter.getS_id());
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("beforeDate",beforeDate);
			map.put("re_asname", shelter.getS_name());
			List<OrderCommand> donaList=reviewService.MyOrder(map);
			model.addAttribute("donaList", donaList);
		}
		
		model.addAttribute("user_id", user_id);
		model.addAttribute("shelter", shelter);
		model.addAttribute("recruitCount", recruitCount);
		model.addAttribute("count", count);
	
		model.addAttribute("pagingHtml", page.getPagingHtml());		
		
		return "shelterDetail";
	}
	
	//�̹��� ���
	@RequestMapping("/shelter/imageView.do")
	public ModelAndView downloadShelter(@RequestParam("id") String id) {

		ShelterCommand shelter = shelterService.selectShelter(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView"); //Ŭ���� ȣ��
		//�Ӽ��� 				�Ӽ���(byte[]�� ������)
		mav.addObject("imageFile", shelter.getS_uploadfile());
		mav.addObject("filename", shelter.getS_filename());

		return mav;
	}

	//====================��ȣ�� �⺻ ��ǰ ������Ʈ(����)=================//
	@RequestMapping("/shelter/insertgoods.do")
	@ResponseBody
	public Map<String,String> insertgoods(@RequestParam("as_id")String as_id,
			@RequestParam("as_name")String as_name,@RequestParam("as_location")String as_location,
			@RequestParam("pad")int pad,@RequestParam("dogfood")int dogfood,@RequestParam("catfood")int catfood
			,@RequestParam("shampoo")int shampoo,@RequestParam("catsand")int catsand){
		Map<String,String> map=new HashMap<String,String>();
		int did=1;
		GoodsCommand goods=new GoodsCommand();
		goods.setAs_id(as_id);
		goods.setAs_name(as_name);
		goods.setAs_location(as_location);
		goods.setPad(pad);
		goods.setDogfood(dogfood);
		goods.setCatfood(catfood);
		goods.setShampoo(shampoo);
		goods.setCatsand(catsand);
		goods.setAs_did(did);
		
		goodsService.updateAs(goods);
		if(log.isDebugEnabled()) {
			log.debug("<<���� ���� �� �� ����>> :"+goods);
		}
		map.put("result","success");		
		return map;
	}			
//===============��ȣ�� ���� �� ���� ===============
		@RequestMapping(value="/shelter/shelterReview.do",method=RequestMethod.GET)
		public ModelAndView reviewForm(HttpSession session, Model model) {
			String id = (String)session.getAttribute("user_id");
			
			ModelAndView mav=new ModelAndView();
			ReviewCommand review=new ReviewCommand();
			 review.setRe_id(id);
			//auth�� ã�ƿ���
			int re_auth=goodsService.selectAuth(id);
			 review.setRe_auth(re_auth);
			 //��ȣ�� �̸� �̸� �ֱ�
			review.setRe_asname(shelterService.selectAsName(id));
			
			if(id==null) {
				 mav.setViewName("member/memberLogin");
				 return mav;
			}else {
				
				mav.setViewName("shelterReviewWrite");
				mav.addObject("review", review);
				mav.addObject("id",id);	
			}
			return mav;
		}
		//���۵� ������ ó��
		@RequestMapping(value="/shelter/shelterReview.do",method=RequestMethod.POST)
		public String submit(@ModelAttribute("review") @Valid ReviewCommand reviewCommand,BindingResult result, HttpServletRequest request)
		{
			if(log.isDebugEnabled()) {
				log.debug("<<���� Ŀ�ǵ� - ��ϵ� ����>> : " + reviewCommand );
			}
			if(result.hasErrors()) {
				return "shelterReviewWrite";
			}
			
			//�� ����ϱ�
			reviewService.insertReview(reviewCommand);
			String id=reviewCommand.getRe_id();
			return "redirect:shelterDetail.do?id="+id+"";
		}	
	//============�۾��� �̹��� ���ε� ===========
		   @RequestMapping("/shelter/imageUploader.do")
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
//�����ϱ�		  
		   @RequestMapping("/shelter/shelterReviewDelete.do")
		   public String deleteDetail(HttpSession session,@RequestParam("re_num") int re_num) {
		   	//�α��� üũ
		   	String id = (String)session.getAttribute("user_id");
		   	
		   	
		   	if(id!=null) {
		   		
		   		reviewService.deleteReview(re_num);	
		   	}
		   	return "redirect:shelterDetail.do?id="+id+"";
		   }	

	
}
