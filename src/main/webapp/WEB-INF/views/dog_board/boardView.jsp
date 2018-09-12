<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.reply.js"></script>
<script type="text/javascript">
//삭제시 확인
function delUrl(){
	var Del = confirm("글을 삭제하시겠습니까?")
	if(Del == true)
	{
	alert("삭제 되었습니다.")
		location.href='delete.do?num=${board.num}'
	}else{
		alert("취소 되었습니다.");
	}
}
</script>
<style>
ul{
   float:right;
   list-style:none;
   text-align:center;
   }
img{
   
}
</style>
<div class="page-main-style">
	<br><br>
	<font color="#17BEFF" size="3">${board.id}</font>&nbsp;
	<span><b><a style="font-size:20px">[${board.species}&nbsp;&nbsp;${board.reg_date}]</a><b style="float:right;">조회수 : ${board.hit}</b></b></span>
	<hr size="1" width="100%">
	<br>
	<div style="display:inline-block; font-size:15px; width:740px;">
	<a href="detail.do?num=${board.num}"><img src="imageView.do?num=${board.num}" width="400" height="250">
	</a>
	<ul>
		<li><b>보호소 : ${board.id}</b></li>
		<li>견종   :  ${board.species}</li>
		<li>색상   :  ${board.color}</li>
		<li>성별   :  ${board.gender}</li>
		<li>접종여부  : ${board.review}</li>
		<li>중성화  :  ${board.operate}</li>
	</ul>
		</div>
<%-- 	<div style="displayinline-block; font-size:20px; width:48%">
	<ul>
		<li><b>보호소 : ${board.id}</b></li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;조회수 : ${board.hit}</li>
		<li>견종:${board.species}</li>
		<li>색상:${board.color}</li>
		<li>성별:${board.gender}</li>
		<li>접종여부 :${board.review}</li>
		<li>중성화:${board.operate}</li>
	</ul>
	<br>
	</div> --%>
	<br>
	<br>
	<hr size="1" width="100%">
	<br><br><br><br><br><br><br>
	<p>
		${board.an_content}
	</p>
	<br>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user_id && user_id == board.id}">
		<input type="button" value="수정"
		  onclick="location.href='update.do?num=${board.num}'">	
		<input type="button" value="삭제"
		  onclick="delUrl()">	  
		</c:if>
		<input type="button" value="목록"
		         onclick="location.href='list.do'">	  
	</div>
	
		<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="num"
			       value="${board.num}" id="num">
			<input type="hidden" name="id"
			       value="${user_id}" id="user_id">
			<textarea rows="3" cols="50"
			  name="re_content" id="re_content"
			  class="rep-content"
			  <c:if test="${empty user_id}">disabled="disabled"</c:if>
			  >
			  <c:if test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea>              
			<c:if test="${!empty user_id}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			
			<div id="re_second" class="align-right">
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
	
</div>







