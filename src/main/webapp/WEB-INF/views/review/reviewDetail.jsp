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
	var result=confirm('���� ���� �Ͻðڽ��ϱ�?');
	if(result){
		alert('���� �����Ǿ����ϴ�.');
		location.href='reviewDelete.do?re_num=${review.re_num}';
	}else{
		alert('�� ������ ����Ͽ����ϴ�.');
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
<p style="float:right;">��ȸ�� : ${review.re_hit}</p>
<br><hr><br>
<span>${review.re_content}</span>
<br><br><hr>

<c:if test="${user_id.equals(review.re_id)}">
<div  style="float:right;">
<button class="btn btn-dange" id="delete">�����ϱ�</button>
<button class="btn btn-warning">�����ϱ�</button>
</div></c:if>
<div style="float:left;color:gray">�ۼ���: ${review.re_id} / �ۼ��� : ${review.re_date} </div>
<br><br>
</div>
<c:if test="${user_id==null}">
	<div id="replyNo">
	<div style="float:left;width:80%">
			<textarea class="form-control" style="width:100%; padding-top:5%; margin-left:4%"placeholder="�α��� �� �̿��� �ֽʽÿ�." disabled="disabled"></textarea>
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
<!-- ���â -->
<div id="output">

</div>
<div style="text-align:center;" class="paging-button">${pagingHtml}</div>
<hr>
</div>