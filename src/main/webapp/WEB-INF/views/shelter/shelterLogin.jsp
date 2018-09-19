<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<div class="page-main-style">
	<div class="horizontal"></div>
	<!-- 가로 여백 -->
	<div class="titleText">보호소 로그인</div>
	<br>
	<div style="width: 500px; margin: 0 auto;" id="shelterLogin">

		<form action="shelterLogin.do" id="loginForm" method="post">
			<fieldset>
				<div class="form-group" style="width: 280px; margin: 0 auto;">
					<label for="s_id">아이디</label> <input type="text"
						class="form-control" id="s_id" name="s_id" style="width: 280px;"
						placeholder="아이디 입력">
				</div>
				<br>
				<div class="form-group" style="width: 280px; margin: 0 auto;">
					<label for="s_passwd">비밀번호</label> <input type="password"
						class="form-control" id="s_passwd" name="s_passwd"
						style="width: 280px;" placeholder="비밀번호 입력">
				</div>
				<div class="horizontal"></div>
				<!-- 가로 여백 -->
				<div
					style="width: 500px; text-align: center; clear: both; margin: 0;">
					<input type="submit" class="btn btn-warning btn-lg" value="로그인">

				</div>
				<div class="horizontal"></div>
				<!-- 가로 여백 -->
			</fieldset>
		</form>
	</div>
</div>







