<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<body>
	<div style="width: 100%; height: 380px;">
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div id="margin0a">
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div style="width: 25%; height: 150px; margin: 0 auto;">
		<input type="button" id="memberWriteBtn" class="btn btn-warning btn-lg btn-block" value="일반 로그인"
				onclick="location.href='${pageContext.request.contextPath}/member/login.do'">
		<div class="horizontal"></div><!-- 가로 여백 -->
		<input type="button" id="shelterWriteBtn" class="btn btn-warning btn-lg btn-block" value="보호소 로그인"
				onclick="location.href='${pageContext.request.contextPath}/shelter/shelterLogin.do'">
	</div>
	</div>
</body>