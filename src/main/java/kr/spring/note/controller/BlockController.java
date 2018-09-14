package kr.spring.note.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.note.domain.NoteCommand;
import kr.spring.note.service.NoteService;

public class BlockController {
	@Resource
	private NoteService noteService;
	
	// 차단하기
	@RequestMapping("/note/block.do")
	public String submitCheckDelete(@RequestParam("id") String block_id,
									HttpSession session) {

		String user_id = (String)session.getAttribute("user_id");
		
		noteService.block(user_id,block_id);
		
		return "note/block";
	}
}
