<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberConfirm.js"></script>

<div class="w500 margin0a">
	<br>
	<div id="margin0a">
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<br>
	<div class="titleText">회원탈퇴</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div align="center">
		<form:form commandName="command" action="memberDelete.do" id="login_form">
	   		<form:hidden path="m_id"/>
			<form:password path="m_passwd" class="form-control" placeholder="비밀번호를 입력하세요."/><br><br>
			<span style="color:red;"><form:errors path="m_passwd" cssClass="error-color"/></span>
			<br><hr>		
			<div align="center">
				<input type="submit" value="탈퇴하기" class="btn btn-primary">&nbsp;&nbsp;&nbsp;
				<input type="button" value="뒤로" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/memberDetail.do'">
			</div><br>
		</form:form>
	</div><br>
</div>


