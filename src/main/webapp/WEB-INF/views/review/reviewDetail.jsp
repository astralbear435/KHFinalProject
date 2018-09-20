<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/soeun/reviewReply.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 $('#cursor').mouseenter(function(){
	 $('#cursor').css('color','white');  
	 $('#cursor').css('background-color','black');  
 });
$('#cursor').mouseleave(function(){
	 $('#cursor').css('color','black');  
	 $('#cursor').css('background-color','white');  
});
$('#delete').click(function(){
	var result=confirm('글을 삭제 하시겠습니까?');
	if(result){
		alert('글이 삭제되었습니다.');
		location.href='reviewDelete.do?re_num=${review.re_num}';
	}else{
		alert('글 삭제를 취소하였습니다.');
	}
});
 });

</script>
<div class="container">
<br><br>
<h4> REVIEW / ${review.re_asname}</h4>
<div style="text-align:center;">
<br><br>
<h1>${review.re_title}</h1>
<p style="float:right;">조회수 : ${review.re_hit}</p>
<br><hr><br>
<span>${review.re_content}</span>
<br><br><hr>

<c:if test="${user_id.equals(review.re_id)}">
<div  style="float:right;">
<button class="btn btn-dange" id="delete">삭제하기</button>
<button class="btn btn-warning">수정하기</button>
</div></c:if>
<div style="float:left;color:gray">작성자: ${review.re_id} / 작성일 : ${review.re_date} </div>
<br><br>
</div>
<c:if test="${user_id==null}">
	<div id="replyNo">
	<div style="float:left;width:80%">
			<textarea class="form-control" style="width:100%; padding-top:5%; margin-left:4%"placeholder="로그인 후 이용해 주십시오." disabled="disabled"></textarea>
			</div>
			<div style="float:right; width:18%;margin-top: 1.5%;padding-left: 2%;">
			<br>
			<span style="cursor: pointer; border: 3px solid black;padding: 22%;" id="cursor">SAND</span>
		</div>
	</div>
</c:if>
<c:if test="${user_id!=null}">
<div id="reply">
		<form id="re_form">
			<input type="hidden" name="reply_num" value="${review.re_num}" id="reply_num">
			<input type="hidden" name="reply_id" value="${user_id}" id="reply_id">
			<div style="float:left;width:80%">
			<textarea class="form-control" style="width:100%; padding-top:5%; margin-left:4%"id="reply_content" name="reply_content"></textarea>
			</div>
			<div style="float:right; width:18%;margin-top: 1.5%;padding-left: 2%;">
			<br>
			<span style="cursor: pointer; border: 3px solid black;padding: 22%;" id="cursor">SAND</span>
		</div>
		</form>
	</div>
	<br><br><br><br><br>
</c:if>
<!-- 댓글창 -->
<div id="output">

</div>
<div style="text-align:center;" class="paging-button">${pagingHtml}</div>
<hr>
</div>