package kr.spring.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.domain.BoardReplyCommand;
import kr.spring.board.service.BoardService;
import kr.spring.util.PagingUtil;

@Controller
public class BoardReplyAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
		
	@Resource
	private BoardService boardService;
	
	//댓글 등록
	@RequestMapping("/dog_board/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(
			BoardReplyCommand boardReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()) {
			log.debug("<<boardReplyCommand>> : " + 
		                      boardReplyCommand);
		}
		
		Map<String,String> map = 
				new HashMap<String,String>();
		
		String user_id = 
			(String)session.getAttribute("user_id");
		if(user_id==null) {
			//로그인 안 됨
			map.put("result", "logout");
		}else {
			
			//댓글 등록
			boardService.insertReply(boardReplyCommand);
			map.put("result", "success");
		}
		
		return map;
	}
	//댓글 목록
	@RequestMapping("/dog_board/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("num") int num){
		
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<num>> : " + num);
		}
		
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("num", num);
		
		//총 글의 갯수
		int count = boardService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,
				    rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<BoardReplyCommand> list = null;
		if(count > 0) {
			list = boardService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = 
				new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	//댓글 삭제
	@RequestMapping("/dog_board/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(
			@RequestParam("re_num") int re_num,
			@RequestParam("id") String id,
			HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<re_num>> : " + re_num);
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = 
				new HashMap<String,String>();
		
		String user_id = 
			(String)session.getAttribute("user_id");
		if(user_id==null) {
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)) {
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			boardService.deleteReply(re_num);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}		
		return map;
	}
	//댓글 수정
	@RequestMapping("/dog_board/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(
			BoardReplyCommand boardReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()) {
			log.debug("<<boardReplyCommand>> : " + 
		                boardReplyCommand);
		}
		
		Map<String,String> map = 
				new HashMap<String,String>();
		
		String user_id = 
			(String)session.getAttribute("user_id");
		if(user_id==null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(
				        boardReplyCommand.getId())){
			//로그인 아이디와 작성자 아이디 일치
			
			
			//댓글 수정
			boardService.updateReply(boardReplyCommand);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
    //댓글의 댓글
	@RequestMapping("/dog_board/answerReply.do")
	@ResponseBody
	public Map<String,String> answerReply(
			BoardReplyCommand boardReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()) {
			log.debug("<<boardReplyCommand>> : " + 
		                      boardReplyCommand);
		}
		
		Map<String,String> map = 
				new HashMap<String,String>();
		
		String user_id = 
			(String)session.getAttribute("user_id");
		
		int pt_num = 0, depth = 0;
		
		if(request.getParameter("parent_num")!=null) {//답글
			pt_num = Integer.parseInt(request.getParameter("parent_num"));
			depth = Integer.parseInt(request.getParameter("depth"));
		}
		request.setAttribute("pt_num", pt_num);
		request.setAttribute("depth", depth);
		
		if(user_id==null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
			
		}else{
			//댓글 등록
			boardService.insertReply(boardReplyCommand);
			map.put("result", "success");
		}
		
		return map;
	}

}












