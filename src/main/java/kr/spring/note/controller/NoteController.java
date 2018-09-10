package kr.spring.note.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.note.domain.NoteCommand;
import kr.spring.note.service.NoteService;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class NoteController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 20;
	private int pageCount = 20;

	@Resource
	private NoteService noteService;

	/* ���� ������ received / ���� ������ send */
	@RequestMapping("/note/receivedList.do")
	public ModelAndView received(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
						   HttpSession session) {
		String recipient = (String)session.getAttribute("user_id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", "recipient");
		map.put("keyword", recipient);
		
		//�� ���� ���� �Ǵ� �˻� �� ���� ����
		int count = noteService.selectNoteRowCount(map);
		
		PagingUtil page = new PagingUtil("recipient", recipient, currentPage, count, rowCount, pageCount, "note/receivedList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<NoteCommand> list = null;
		if(count > 0) {
			list = noteService.selectNoteList(map);
			
			// ���� �̸����⿡ ...ó��
			for(NoteCommand note :  list) {
				if(note.getNote_content().length() >= 20) note.setNote_content(note.getNote_content().substring(0,20) + "...");
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("receivedList");
		mav.addObject("count", count);
		
		if(list != null) {
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
		}
		
		return mav;
	}

	@RequestMapping("/note/sendList.do")
	public ModelAndView send(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
							 HttpSession session) {
		String sender = (String)session.getAttribute("user_id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", "sender");
		map.put("keyword", sender);
		
		//�� ���� ���� �Ǵ� �˻� �� ���� ����
		int count = noteService.selectNoteRowCount(map);
		
		PagingUtil page = new PagingUtil("sender", sender, currentPage, count, rowCount, pageCount, "note/sendList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<NoteCommand> list = null;
		if(count > 0) {
			list = noteService.selectNoteList(map);
			
			// ���� �̸����⿡ ...ó��
			for(NoteCommand note :  list) {
				if(note.getNote_content().length() >= 20) note.setNote_content(note.getNote_content().substring(0,20) + "...");
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("sendList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}

	/* ���� �� */
	@RequestMapping("/note/detail.do")
	public ModelAndView detail(@RequestParam("note_num") int note_num,
							   HttpSession session) {
		String id = (String)session.getAttribute("user_id");
		
		if(log.isDebugEnabled()) {
			log.debug("<<note_num>> : " + note_num);
		}
		
		NoteCommand note = noteService.selectNote(note_num);
		
		if(id.equals(note.getRecipient())) {
			// ������ ���̵� �޴� ����� ���� ����� ������ ���� ���� ����
			noteService.updateNoteStatusToOpen(note_num);
		}
		
		//enter�� ���� �ٹٲ�ó��
		note.setNote_content(StringUtil.useBrNoHtml(note.getNote_content()));
		
		return new ModelAndView("note/noteDetail", "note", note);
	}

	/* ���� �ۼ� */
	@RequestMapping(value="/note/write.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");
		
		NoteCommand command = new NoteCommand();
		command.setSender(id);
		
		model.addAttribute("command",command);
		
		return "note/noteWrite";
	}

	//���۵� ������ ó��
	@RequestMapping(value="/note/writeAjax.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> submit(@ModelAttribute("command") @Valid NoteCommand noteCommand, 
										BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<NoteCommand>> : " + noteCommand);
		}

		Map<String,String> map = new HashMap<String,String>(); 
		if(result.hasErrors()) {
			map.put("result", "notData");
		}

		//�۾���
		noteService.insert(noteCommand);

		map.put("result", "success");
		
		return map;
	}
	
	/* ���� ���� */
	
	// ���� ����
	@RequestMapping("/note/delete.do")
	public String submitDelete(@RequestParam("note_num") int note_num, HttpSession session) {
		
		// ���� ���̵�� ������ ���� ���� ������
		String id = (String)session.getAttribute("user_id");
		NoteCommand note = noteService.selectNote(note_num);
		
		// �� �ʸ� �� �� ���� ó��
		if(note.getDelete_status().equals("both") && id.equals(note.getSender())) { // �� �� �������� ���� ���¿��� ���� ����� ������ ����
			noteService.deleteNoteStatusToRecipient(note_num); // ���� ����� �� �� ����
		}else if(note.getDelete_status().equals("both") && id.equals(note.getRecipient())) { // �� �� �������� ���� ���¿��� ���� ����� ������ ����
			noteService.deleteNoteStatusToSender(note_num); // ���� ����� �� �� ����
		}
		
		// DB���� ����
		// ���� ����� �� �� �ִ� ���¿��� ���� ����� ���� ����		  // ���� ����� ���� �ִ� ���¿��� ���� ����� ���� ����
		if((note.getDelete_status().equals("sender") && id.equals(note.getSender())) || (note.getDelete_status().equals("recipient") && id.equals(note.getRecipient()))) {
			noteService.delete(note_num);
		}
		
		return "note/noteDelete";
	}
	
	// üũ�ڽ��� ����
	@RequestMapping("/note/checkDelete.do")
	public String submitCheckDelete(@RequestParam("checkList") String checkList, HttpSession session) {

		int note_num = 0;
		String id = (String)session.getAttribute("user_id");
		String[] array = checkList.toString().split(",");
		
		for(int i=0; i<array.length; i++) {
			note_num = Integer.parseInt(array[i]);
			
			// ������ ���� ���� ������
			NoteCommand note = noteService.selectNote(note_num);
			
			// �� �ʸ� �� �� ���� ó��
			if(note.getDelete_status().equals("both") && id.equals(note.getSender())) { // �� �� �������� ���� ���¿��� ���� ����� ������ ����
				noteService.deleteNoteStatusToRecipient(note_num); // ���� ����� �� �� ����
			}else if(note.getDelete_status().equals("both") && id.equals(note.getRecipient())) { // �� �� �������� ���� ���¿��� ���� ����� ������ ����
				noteService.deleteNoteStatusToSender(note_num); // ���� ����� �� �� ����
			}
			
			// DB���� ����
			// ���� ����� �� �� �ִ� ���¿��� ���� ����� ���� ����		  // ���� ����� ���� �ִ� ���¿��� ���� ����� ���� ����
			if((note.getDelete_status().equals("sender") && id.equals(note.getSender())) || (note.getDelete_status().equals("recipient") && id.equals(note.getRecipient()))) {
				noteService.delete(note_num);
			}
		}
		
		return "note/noteCheckDelete";
	}
}