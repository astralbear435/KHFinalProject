package kr.spring.shelter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
import kr.spring.util.CipherTemplate;
import kr.spring.util.PagingUtil;

@Controller
public class ShelterController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private ShelterService shelterService;

	@Resource
	private CipherTemplate cipherAES;

	
	@ModelAttribute("command")
	public ShelterCommand initCommand() {
		return new ShelterCommand();
	}
	
	//================== ȸ������ =========================
	//ȸ�� ���� �� ȣ��
	@RequestMapping(value="/shelter/write.do", method=RequestMethod.GET)
	public String form() {
		return "shelterWrite";
	}
	//ȸ�� ���� ������ ����
	@RequestMapping(value="/shelter/write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
							@Valid ShelterCommand shelterCommand, 
							BindingResult result) {

		
		System.out.println("----->" + shelterCommand);
		
		if(result.hasErrors()) {
			return form();
		}
		
		//CipherTemplate�� �̿��� ��ȣȭ
		shelterCommand.setS_passwd(cipherAES.encrypt(shelterCommand.getS_passwd()));

		// ȸ������
		shelterService.insert(shelterCommand);

		return "redirect:/main/main.do";
	}
	
	// ============= ���̵� �ߺ� Ȯ�� ==============
	@RequestMapping("/shelter/confirmId.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("id") String id){

		Map<String,String> map = new HashMap<String,String>();

		ShelterCommand shelter = shelterService.selectShelter(id);

		if(shelter != null) {
			//���̵� �ߺ�
			map.put("result", "idDuplicated");
		}else {
			//���̵� �� �ߺ�
			map.put("result", "idNotFound");
		}
		
		return map;
	}

	//================== �α��� =========================	
	// �α��� �� ȣ��
	@RequestMapping(value="/shelter/shelterLogin.do", method=RequestMethod.GET)
	public String formLogin() {
		return "shelterLogin";
	}

	// �α��������� ���۵� ������ ó��
	@RequestMapping(value="/shelter/shelterLogin.do", method=RequestMethod.POST)
	public String submitLogin(@Valid ShelterCommand shelterCommand, 
								BindingResult result, HttpSession session) {
		
		// memberCommand�� ���� üũ�ϰ� ������̼��� ����Ǿ� �����Ƿ� id�� passwd�� �ʵ常 üũ
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//�α��� üũ(id,��й�ȣ ��ġ ���� üũ)
		try {
			ShelterCommand shelter = shelterService.selectShelter(shelterCommand.getS_id());
			boolean check = false;
			
			if(shelter != null) {//���̵� �����ϸ�
				// ��й�ȣ ��ġ ���� üũ > ��ȣȭ �� ����� ��ġ���� Ȯ��
				check = shelter.isCheckedPasswd(cipherAES.encrypt(shelterCommand.getS_passwd()));
			}
			if(check) {
				//���� ����, �α���ó��
				session.setAttribute("user_id",shelter.getS_id());
				session.setAttribute("user_auth",shelter.getAuth());
				
				return "redirect:/main/main.do";
			}else {
				//���� ���� : catch������� �Ѿ
				throw new Exception();
			}
			
		}catch(Exception e) {
			//���� ���з� �� ȣ��
			result.reject("invalidIdOrPassword");
			
			return formLogin();
		}

	}

	//================== �α׾ƿ� =========================
	@RequestMapping("/member/logout.do")
	public String processLogin(HttpSession session) {
		//�α׾ƿ�
		session.invalidate();

		return "redirect:/main/main.do";
	}
	
	//================== ȸ�� �� ���� =========================
	// ���� �� ��й�ȣ Ȯ��
	@RequestMapping("/shelter/shelterConfirm.do")
	public String confirmForm(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// ��ȣȭ �� ��й�ȣ�� ��ȣȭ(db�� ���� ����)
		shelter.setS_passwd(cipherAES.decrypt(shelter.getS_passwd()));
		
		model.addAttribute("shelter", shelter);
		
		return "shelterConfirm";
	}
	
	// �� ���� Ȯ��
	@RequestMapping("/shelter/shelterInfo.do")
	public String process(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// ��ȣȭ �� ��й�ȣ�� ��ȣȭ(db�� ���� ����)
		shelter.setS_passwd(cipherAES.decrypt(shelter.getS_passwd()));
		
		model.addAttribute("shelter", shelter);
		
		return "shelterInfo";
	}

	//================== ȸ�� ���� ���� =========================
	// ���� �� ������ ó��
	@RequestMapping("/shelter/update.do")
	public String submitUpdate(@Valid ShelterCommand shelterCommand,
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
	public String submitDalete(@Valid ShelterCommand shelterCommand, 
								BindingResult result, HttpSession session) {
		
		ShelterCommand shelter = shelterService.selectShelter(shelterCommand.getS_id());// �ڹٺ� ���·� id��������
			
		shelterService.delete(shelter.getS_id());
				
		// �α׾ƿ�
		session.invalidate();
			
		return "redirect:/main/main.do";
			
	}

	//================== ��ȣ�� ����Ʈ =========================
	@RequestMapping(value="/shelter/shelterList.do", method=RequestMethod.GET)
	public ModelAndView shelterList(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
									  @RequestParam(value="keyfield", defaultValue="") String keyfield,
									  @RequestParam(value="keyword", defaultValue="") String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// �� ���� ���� �Ǵ� �˻� �� ���� ����
		int count = shelterService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "shelterList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
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
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// ��ȣ�� ������
	@RequestMapping(value="/shelter/shelterDetail.do", method=RequestMethod.GET)
	public String view(@RequestParam("id") String id, Model model) {
		
		ShelterCommand shelter = shelterService.selectShelter(id);
		
		// �ּҰ����� ��ȣ ������ ������
		String s_address1 = shelter.getS_address1();
		int findIndexOf = s_address1.indexOf("(");
		String address = s_address1.substring(0,findIndexOf-1);
		shelter.setS_address1(address);
		
		model.addAttribute("shelter", shelter);
		
		return "shelterDetail";
	}
	
	//�̹��� ���
	@RequestMapping("/shelter/imageView.do")
	public ModelAndView download(@RequestParam("id") String id) {

		ShelterCommand shelter = shelterService.selectShelter(id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView"); //Ŭ���� ȣ��
		//�Ӽ��� 				�Ӽ���(byte[]�� ������)
		mav.addObject("imageFile", shelter.getS_uploadfile());
		mav.addObject("filename", shelter.getS_filename());

		return mav;
	}
}
