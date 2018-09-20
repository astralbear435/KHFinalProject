<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberFind.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">

<div class="w500 margin0a">
	<br>
	<div id="margin0a">
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<br>
	<div class="titleText">아이디/비밀번호 찾기</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
	<form action="findMember.do" id="findMember_form" method="post">
		<div align="center">
			<input type="text" name="m_email" id="m_email" class="form-control" value="${command.m_email}" placeholder="이메일을 입력하세요."/><br>
			<br>
			<div id="message_email"></div>
			<form:errors path="m_email" cssClass="error-color"/>
		</div><hr>
		
		<div align="center">
			<input type="button" id="find_Id" value="아이디 찾기" class="btn btn-warning">&nbsp;&nbsp;&nbsp;
			<input type="submit" id="find_passwd" value="비밀번호 찾기" class="btn btn-warning">
		</div><br>
	</form>
</div>
	
