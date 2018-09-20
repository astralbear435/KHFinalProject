<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<!-- bootstrap-css -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap2.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
<!--// css -->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	//삭제 안됨.
	function detailClose(){
		self.close();
		opener.document.location.reload();
	}
	
	// 삭제 시 확인
	function detailDelete(){
		var note_num = $('#note_num').text();
		
		if(confirm("쪽지를 삭제하시겠습니까?")){
			location.replace('delete.do?note_num=' + note_num);
		}else{
			return;
		}
	}
	
	// 답장
	function reply(){
		$('#noteReplyForm').submit();
	}
</script>
<body>
<div style="container"><img src="${pageContext.request.contextPath}/upload/goods/message.jpg" alt="" style="width:100%"></div>
	<div style="width:540px; height:600px;">
	
		<div style="margin:20px;">
			<div id="note_num" style="display: none;">${note.note_num}</div>
			<form action="reply.do" method="post" id="noteReplyForm">
				<input type="hidden" name="recipient" value="${note.sender}">
			</form>
			<div class="form-group recipient" style="height: 60px;">
				<c:if test="${user_id == note.sender}">
				받는 사람 : ${note.recipient}<br>
				받은 날짜 : ${note.write_date}<br><br>
				</c:if>
				<c:if test="${user_id == note.recipient}">
					보낸 사람 : ${note.sender}<br>
					보낸 날짜 : ${note.write_date}<br><br>
				</c:if>	
			</div>
			<hr size="1" width="100%"><br>
			<textarea rows="15" cols="85" style="border: 1.5px solid #333333; resize:none;">
				
${note.note_content}</textarea><br>
			<div class="horizontal"></div><!-- 가로 여백 -->
			<p align="center" style="width: 540px;">
				<c:if test="${user_id == note.recipient}">
					<input type="button" value="답장" class="btn btn-primary" onclick="reply();">
				</c:if>
				<input type="button" value="삭제" class="btn btn-danger" onclick="detailDelete();">
				<input type="button" value="닫기" class="btn btn-primary" onclick="detailClose();">
			</p>
		</div>
	</div>
</body>
</html>