<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
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
</script>
<body>
	<div style="width:400px; height:500px;">
		<div style="margin:20px;">
			<div id="note_num" style="display: none;">${note.note_num}</div>
			보낸 사람 : ${note.sender}<br>
			보낸 날짜 : ${note.write_date}<br><br>
			<hr size="1" width="100%"><br>
			<textarea rows="22" cols="48" style="resize:none;">${note.note_content}</textarea><br>
			<div></div>
			<p align="center">
				<input type="button" value="삭제" onclick="detailDelete()">
				<input type="button" value="닫기" onclick="detailClose();">
			</p>
		</div>
	</div>
</body>
</html>