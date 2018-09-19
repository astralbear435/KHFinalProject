<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	$(document).ready(function(){
		//textarea에 내용 입력시 글자수 체크
		$(document).on('keyup', 'textarea', function(){
			//입력한 글자수를 구함
			var inputLength = $(this).val().length;
			
			if(inputLength > 1000){
				//1000자를 넘어선 경우 1000자 까지로 잘라버림
				$(this).val($(this).val().substring(0, 1000));
			}else{
				//1000자 이하인 경우
				var remain = inputLength;
				remain += '/1000';
			}
			$('.letter-count').text(remain);
		});
	});
</script>
<body>
	<div id="noteReply" style="width:540px; height:600px;">
		<div style="margin:20px;">
			<form id="noteReplyForm" style="width:540px; height:600px;">
				<div class="form-group recipient" style="width: 500px; line-height: 40px;">
					<label class="condition floatL" for="recipient" style="margin-top: 8px;"></label>
					<input type="hidden" value="${command.recipient}"/>
					받는 사람 : ${command.recipient}
				</div>
				<hr size="1" width="100%"><br>
				<label for="note_content"></label>
				<textarea rows="15" cols="85" style="border: 2px solid #333333; resize:none;" name="note_content">
				
</textarea><br>
				<div class="letter-count" style="text-align:right">0 / 1000</div>
				<div class="horizontal"></div><!-- 가로 여백 -->
				<p align="center">
					<input type="submit" value="보내기" class="btn btn-warning">
					<input type="button" value="취소" onclick="self.close()"
							 class="btn btn-primary"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>