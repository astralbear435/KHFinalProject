<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/idOrEmailLogin.js"></script> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<div class="page-main-style">
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div style="width: 500px; margin: 0 auto;" id="memberLogin">
		<form:form commandName="command" action="login.do" id="login_form">
			<form:errors element="div" cssClass="error-color"/>	
			<ul>
				<li id="eid">
					<label>아이디</label>
					<form:input path="m_id"/> 
					<form:errors path="m_id" cssClass="error-color"/>
				</li>
				
				<li>
					<label for="m_passwd">비밀번호</label>
					<form:password path="m_passwd"/> 
					<form:errors path="m_passwd" cssClass="error-color"/>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="로그인"> <br>
				<input type="button" value="아이디/비밀번호 찾기" onclick="location.href='${pageContext.request.contextPath}/member/findMember.do'">
				<input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/member/write.do'">
			</div>
		</form:form>
	</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
</div>







