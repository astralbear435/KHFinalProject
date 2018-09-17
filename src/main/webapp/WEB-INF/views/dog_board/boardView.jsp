<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
   }
#backdiv{
	  float:right;
	  border:15px solid blue; 
	  text-align:center;
	  border-width:3px;
	  border-color:#e0f7ff;
	  background-color:#e0f7ff; 
	  width:30%;
	  border-style:double;
}
</style>
<div class="container">
	<br><br>
	&nbsp;&nbsp;<font style="font-size:1em;" color="#17BEFF"><b>${s_name}</b></font>&nbsp;
	<span><b style="font-size:1em;"><a>[${board.an_species}&nbsp;${board.an_name}&nbsp;${board.reg_date}]</a><b style="float:right;">조회수 : ${board.an_hit}</b></b></span>
	<hr size="1" width="100%">
	<br>
	<div style="padding-right: 22%; margin-left:8%; display:inline-block; font-size:15px; ">
	<a href="detail.do?num=${board.num}"><img src="imageView.do?num=${board.num}" width=500, height=500>
	</a>
	<ul style="font-size:20px; /* padding-right:5%; */ padding-top:8%;">
		<li>&nbsp;&nbsp;&nbsp;&nbsp;보호소 :${s_name}</li>
		<br>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;이름 : ${board.an_name}</li>
		<br>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;묘종/견종   :  ${board.an_species}</li>
		<br>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;모색   :  ${board.an_color}</li>
		<br>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;성별   :  ${board.an_gender}</li>
		<br>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;접종여부  : ${board.an_review}</li>
		<br>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;중성화  :  ${board.an_operate}</li>
	</ul>
		<div id="backdiv" style="width:350px; height:150px;" align="right">
			<br>
            <span class="banner_txt01">CUSTOMER CENTER</span>
            <br>
            <span class="banner_txt02">대표전화</span>
            <br>
            <span class="banner_txt03">010-4497-0401</span>
            <br>
            <span class="banner_txt04">365일 연중무휴/24시간 상담가능</span>
            <br>
        </div>
		</div>
	<br>
	<br>
	<hr size="1" width="100%">
	<div style="width: 450px; margin: 0 auto;">
	<br><br>
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
	</div>
	<br><br><br><br><br>
	<p>
		${board.an_content}
	</p>
	<br>
	<hr size="1" width="100%">
	<div align="right">
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
		<br>
		<span class="reply-title"></span>
		<form id="re_form">
			<input type="hidden" name="num"
			       value="${board.num}" id="num">
			<input type="hidden" name="id"
			       value="${user_id}" id="user_id">
			<textarea style="width:100%;"
			  name="re_content" id="re_content"
			  class="re-content" <c:if test="${empty user_id}">disabled="disabled"</c:if>
			  ><c:if test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea>              
			<c:if test="${!empty user_id}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
				<input type="submit" value="전송">
			</div>
			<br>
			</c:if>
		</form>
	</div>
	<!-- 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button"  style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
</div>







