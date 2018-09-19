package kr.spring.review.controller;

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

import kr.spring.review.domain.ReviewReplyCommand;
import kr.spring.review.service.ReviewService;
import kr.spring.util.PagingUtil;

@Controller
public class ReviweReplyController {
	private Logger log=Logger.getLogger(this.getClass());
	private int rowCount =10;
	private int pageCount=10;
	
	@Resource
	private ReviewService reviewService;
	
//==============댓글 등록====================
@RequestMapping("/review/insertReply.do")
@ResponseBody
public Map<String,Object> insertReply(ReviewReplyCommand reviewReplyCommand,HttpSession session,HttpServletRequest request){
	
	Map<String,Object> map=new HashMap<String,Object>();
	String user_id=(String)session.getAttribute("user_id");
	if(log.isDebugEnabled()) {
		 log.debug("<<댓글 내용>> : "+reviewReplyCommand);
		
		 }
	if(user_id==null) {
		//로그인 안됨.
		map.put("result","logout");
	}else {
		//댓글 등록.
		reviewService.insertReply(reviewReplyCommand);
		
		map.put("result","success");
	}
	return map;
}
//=====================댓글 목록 ======================
//댓글 목록
@RequestMapping("/review/listReply.do")
@ResponseBody
public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,@RequestParam("num") int reply_num){
Map<String,Object> map=new HashMap<String, Object>();
map.put("reply_num",reply_num);
	 //총 댓글의 갯수
int count=reviewService.selectReplyCount(reply_num);
PagingUtil page=new PagingUtil(currentPage,count,rowCount,pageCount,null);
	List<ReviewReplyCommand> list=null;
	//댓글이 1개라도 있다면
	if(count>0) {
		list=reviewService.selecReplyList(map);
	}if(count<0) {
		list=Collections.emptyList();
	}
	 Map<String,Object> mapJson=new HashMap<String,Object>();
	 mapJson.put("count",count);
	 mapJson.put("rowCount",rowCount);
	 mapJson.put("list",list);
	 return mapJson;	
}
//=======================댓글 삭제===================
@RequestMapping("/review/deleteReply.do")
@ResponseBody
public Map<String,String> deleteReply(@RequestParam("reply_mynum")int reply_mynum,@RequestParam("reply_id") String reply_id,HttpSession session){

	System.out.println("------------내 댓글 번호 : "+reply_mynum);
	 Map<String,String> map=new HashMap<String, String>();
	 String user_id=(String)session.getAttribute("user_id");
	 if(user_id==null) {
		 //미로그인 상태
		 map.put("result","logout");//러그아웃상태라고 알리기
		 
	 }else if(user_id!=null&&user_id.equals(reply_id)) {
		 //로그인 상태 및 작성자아이디와 일치하는경우
		 reviewService.deleteReply(reply_mynum);
		 map.put("result","success");
	 }else {
		 //로그인아이디와 작성자 불일치
		 map.put("result","wrongAccess");
	 }
	 return map;
}
//----------------------------댓글 수정--------------------------

@RequestMapping("/review/updateReply.do")
@ResponseBody
public Map<String,String> modifyReply(ReviewReplyCommand reviewReplyCommand,HttpSession session,HttpServletRequest request){
	 if(log.isDebugEnabled()) {
		 log.debug("<<수정된 댓글 내용>> : "+reviewReplyCommand);
		 }
	 Map<String,String> map=new HashMap<String, String>();
	 String user_id=(String)session.getAttribute("user_id");
	 
	 if(user_id==null) {
		 //미로그인 상태
		 map.put("result","logout");//러그아웃상태라고 알리기
		 
	 }else if(user_id!=null&&user_id.equals(reviewReplyCommand.getReply_id())) {
		 //로그인아이디와 작성자 아이딛일치
		 
		 //댓글 수정
		 reviewService.updateReply(reviewReplyCommand);
		 String content=reviewReplyCommand.getReply_content();
		 map.put("result","success");
		 map.put("content",content);
		
	 }else {
		 map.put("result","worngAccess");	
	 }	 
	 return map;
}
}
