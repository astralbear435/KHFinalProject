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
	
//==============��� ���====================
@RequestMapping("/review/insertReply.do")
@ResponseBody
public Map<String,Object> insertReply(ReviewReplyCommand reviewReplyCommand,HttpSession session,HttpServletRequest request){
	
	Map<String,Object> map=new HashMap<String,Object>();
	String user_id=(String)session.getAttribute("user_id");
	if(log.isDebugEnabled()) {
		 log.debug("<<��� ����>> : "+reviewReplyCommand);
		
		 }
	if(user_id==null) {
		//�α��� �ȵ�.
		map.put("result","logout");
	}else {
		//��� ���.
		reviewService.insertReply(reviewReplyCommand);
		
		map.put("result","success");
	}
	return map;
}
//=====================��� ��� ======================
//��� ���
@RequestMapping("/review/listReply.do")
@ResponseBody
public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,@RequestParam("num") int reply_num){
Map<String,Object> map=new HashMap<String, Object>();
map.put("reply_num",reply_num);
	 //�� ����� ����
int count=reviewService.selectReplyCount(reply_num);
PagingUtil page=new PagingUtil(currentPage,count,rowCount,pageCount,null);
	List<ReviewReplyCommand> list=null;
	//����� 1���� �ִٸ�
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
//=======================��� ����===================
@RequestMapping("/review/deleteReply.do")
@ResponseBody
public Map<String,String> deleteReply(@RequestParam("reply_mynum")int reply_mynum,@RequestParam("reply_id") String reply_id,HttpSession session){

	System.out.println("------------�� ��� ��ȣ : "+reply_mynum);
	 Map<String,String> map=new HashMap<String, String>();
	 String user_id=(String)session.getAttribute("user_id");
	 if(user_id==null) {
		 //�̷α��� ����
		 map.put("result","logout");//���׾ƿ����¶�� �˸���
		 
	 }else if(user_id!=null&&user_id.equals(reply_id)) {
		 //�α��� ���� �� �ۼ��ھ��̵�� ��ġ�ϴ°��
		 reviewService.deleteReply(reply_mynum);
		 map.put("result","success");
	 }else {
		 //�α��ξ��̵�� �ۼ��� ����ġ
		 map.put("result","wrongAccess");
	 }
	 return map;
}
//----------------------------��� ����--------------------------

@RequestMapping("/review/updateReply.do")
@ResponseBody
public Map<String,String> modifyReply(ReviewReplyCommand reviewReplyCommand,HttpSession session,HttpServletRequest request){
	 if(log.isDebugEnabled()) {
		 log.debug("<<������ ��� ����>> : "+reviewReplyCommand);
		 }
	 Map<String,String> map=new HashMap<String, String>();
	 String user_id=(String)session.getAttribute("user_id");
	 
	 if(user_id==null) {
		 //�̷α��� ����
		 map.put("result","logout");//���׾ƿ����¶�� �˸���
		 
	 }else if(user_id!=null&&user_id.equals(reviewReplyCommand.getReply_id())) {
		 //�α��ξ��̵�� �ۼ��� ���̵���ġ
		 
		 //��� ����
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
