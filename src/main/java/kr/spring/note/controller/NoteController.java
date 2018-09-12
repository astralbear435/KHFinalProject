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

	/* 받은 쪽지함 received / 보낸 쪽지함 send */
	@RequestMapping("/note/receivedList.do")
	public ModelAndView received(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
						   HttpSession session) {
		String recipient = (String)session.getAttribute("user_id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", "recipient");
		map.put("keyword", recipient);
		
		//총 글의 갯수 또는 검색 된 글의 갯수
		int count = noteService.selectNoteRowCount(map);
		
		PagingUtil page = new PagingUtil("recipient", recipient, currentPage, count, rowCount, pageCount, "note/receivedList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<NoteCommand> list = null;
		if(count > 0) {
			list = noteService.selectNoteList(map);
			
			// 쪽지 미리보기에 ...처리
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
		
		//총 글의 갯수 또는 검색 된 글의 갯수
		int count = noteService.selectNoteRowCount(map);
		
		PagingUtil page = new PagingUtil("sender", sender, currentPage, count, rowCount, pageCount, "note/sendList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<NoteCommand> list = null;
		if(count > 0) {
			list = noteService.selectNoteList(map);
			
			// 쪽지 미리보기에 ...처리
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

	/* 쪽지 상세 */
	@RequestMapping("/note/detail.do")
	public ModelAndView detail(@RequestParam("note_num") int note_num,
							   HttpSession session) {
		String id = (String)session.getAttribute("user_id");
		
		if(log.isDebugEnabled()) {
			log.debug("<<note_num>> : " + note_num);
		}
		
		NoteCommand note = noteService.selectNote(note_num);
		
		if(id.equals(note.getRecipient())) {
			// 접속한 아이디가 받는 사람과 같은 사람이 읽으면 쪽지 상태 변경
			noteService.updateNoteStatusToOpen(note_num);
		}
		
		//enter에 대한 줄바꿈처리
		note.setNote_content(StringUtil.useBrNoHtml(note.getNote_content()));
		
		return new ModelAndView("note/noteDetail", "note", note);
	}

	/* 쪽지 작성 */
	@RequestMapping(value="/note/write.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");
		
		NoteCommand command = new NoteCommand();
		command.setSender(id);
		
		model.addAttribute("command",command);
		
		return "note/noteWrite";
	}

	//전송된 데이터 처리
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

		//글쓰기
		noteService.insert(noteCommand);

		map.put("result", "success");
		
		return map;
	}
	
	/* 쪽지 삭제 */
	
	// 개별 삭제
	@RequestMapping("/note/delete.do")
	public String submitDelete(@RequestParam("note_num") int note_num, HttpSession session) {
		
		// 접속 아이디랑 삭제할 쪽지 정보 가져옴
		String id = (String)session.getAttribute("user_id");
		NoteCommand note = noteService.selectNote(note_num);
		
		// 한 쪽만 볼 수 없게 처리
		if(note.getDelete_status().equals("both") && id.equals(note.getSender())) { // 둘 다 삭제하지 않은 상태에서 보낸 사람이 쪽지를 삭제
			noteService.deleteNoteStatusToRecipient(note_num); // 받은 사람만 볼 수 있음
		}else if(note.getDelete_status().equals("both") && id.equals(note.getRecipient())) { // 둘 다 삭제하지 않은 상태에서 받은 사람이 쪽지를 삭제
			noteService.deleteNoteStatusToSender(note_num); // 보낸 사람만 볼 수 있음
		}
		
		// DB에서 삭제
		// 보낸 사람만 볼 수 있는 상태에서 보낸 사람이 쪽지 삭제		  // 받은 사람만 볼수 있는 상태에서 받은 사람이 쪽지 삭제
		if((note.getDelete_status().equals("sender") && id.equals(note.getSender())) || (note.getDelete_status().equals("recipient") && id.equals(note.getRecipient()))) {
			noteService.delete(note_num);
		}
		
		return "note/noteDelete";
	}
	
	// 체크박스로 삭제
	@RequestMapping("/note/checkDelete.do")
	public String submitCheckDelete(@RequestParam("checkList") String checkList, HttpSession session) {

		int note_num = 0;
		String id = (String)session.getAttribute("user_id");
		String[] array = checkList.toString().split(",");
		
		for(int i=0; i<array.length; i++) {
			note_num = Integer.parseInt(array[i]);
			
			// 삭제할 쪽지 정보 가져옴
			NoteCommand note = noteService.selectNote(note_num);
			
			// 한 쪽만 볼 수 없게 처리
			if(note.getDelete_status().equals("both") && id.equals(note.getSender())) { // 둘 다 삭제하지 않은 상태에서 보낸 사람이 쪽지를 삭제
				noteService.deleteNoteStatusToRecipient(note_num); // 받은 사람만 볼 수 있음
			}else if(note.getDelete_status().equals("both") && id.equals(note.getRecipient())) { // 둘 다 삭제하지 않은 상태에서 받은 사람이 쪽지를 삭제
				noteService.deleteNoteStatusToSender(note_num); // 보낸 사람만 볼 수 있음
			}
			
			// DB에서 삭제
			// 보낸 사람만 볼 수 있는 상태에서 보낸 사람이 쪽지 삭제		  // 받은 사람만 볼수 있는 상태에서 받은 사람이 쪽지 삭제
			if((note.getDelete_status().equals("sender") && id.equals(note.getSender())) || (note.getDelete_status().equals("recipient") && id.equals(note.getRecipient()))) {
				noteService.delete(note_num);
			}
		}
		
		return "note/noteCheckDelete";
	}
}
