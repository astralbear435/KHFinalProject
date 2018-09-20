<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<style>
	.w500{width: 500px;}
	.h72{height: 72px;}
	.lineh95{line-height: 95px;}
	.margin0a{margin: 0 auto;}
	.clearB{clear: both;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/seyeong/shelter.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#s_passwdInput').keydown(function(key){
		if(key.keyCode == 13){
			key.preventDefault();
			$('#confirmBtn').click();
		}
	});
});
</script>
<div class="page-main-style">
	<div>
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div id="margin0a">
			<div style="width: 450px; margin: 0 auto;">
				<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
			</div>
		</div>
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div class="titleText">비밀번호 확인</div><br>
		<div style="text-align: center;">
			정확한 확인을 위해 비밀번호를 한번 더 입력해 주세요!
		</div><br>
		<div class="w500 margin0a">
			<form id="shelterConfirmForm" method="post" action="shelterConfirm.do">
				<fieldset class="clearB">
						<div class="form-group clearB">
							<input type="hidden" id="s_passwd" value="${shelter.s_passwd}">
							<input type="password" class="form-control" id="s_passwdInput" placeholder="비밀번호 입력">
						</div>
						<div class="horizontal"></div><!-- 가로 여백 -->
						<div class="w500 clearB" style="text-align:center; margin: 0;">
							<input type="button" id="confirmBtn" class="btn btn-warning" value="확인">
							<input type="button"  class="btn btn-primary" value="취소"
							      onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
						</div>
						<div class="horizontal"></div><!-- 가로 여백 -->
					</fieldset>
			</form>
		</div>
	</div>
	<br>
</div>