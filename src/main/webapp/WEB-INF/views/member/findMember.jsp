<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberFind.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">

<div class="container">
	<div align="center">
	<br>
		<div text-align="center">
			<h2>아이디/비밀번호 찾기</h2>
		</div>
		<form:form commandName="command" action="findMember.do" id="findMember_form">
		<hr>
		<div class="horizontal"></div><!-- 가로 여백 -->
			<div class="align-center">
				<label for="m_email">이메일</label> 
				<input type="text" name="m_email" id="m_email" size="50px" value="${command.m_email}" placeholder="khfinaltest@gmail.com"/><br>
				<div id="message_email"></div>
				<form:errors path="m_email" cssClass="error-color"/>
			</div>
			<br><br>
			<div class="align-center">
				<input type="button" id="find_Id" value="아이디 찾기" class="btn btn-primary"> 
				<input type="submit" id="find_passwd" value="비밀번호 찾기" class="btn btn-primary">
			</div>
			<div class="horizontal"></div><!-- 가로 여백 -->
		</form:form>
	</div>
</div>