<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/idOrEmailLogin.js"></script> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script>
	$(document).ready(function(){
		$('#shelterLoginSubmit').click(function(){
			$('#shelterLoginForm').submit();
		});
	});
</script>
<div class="page-main-style">
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div style="width: 130px; height: 24px; margin: 0 auto;">
		<div class="floatL"><input type="radio" class="form-check-input" id="member" name="login" value="일반" checked>일반</div>
		<div class="floatL" style="width: 30px; height: 24px;"></div>
		<div class="floatL"><input type="radio" class="form-check-input" id="shelter" name="login" value="보호소">보호소</div>
	</div>
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
	<div style="width: 500px; margin: 0 auto;" id="shelterLogin">
		<form action="shelterLogin.do" id="shelterLoginForm" method="post">
			<fieldset>
				<div class="form-group" style="width: 280px; margin: 0 auto;">
					<label for="s_id">아이디</label>
					<input type="text" class="form-control" id="s_id" name="s_id" style="width: 280px;" placeholder="아이디 입력">
				</div>
				<br>
				<div class="form-group" style="width: 280px; margin: 0 auto;">
					<label for="s_passwd">비밀번호</label>
					<input type="password" class="form-control" id="s_passwd" name="s_passwd" style="width: 280px;" placeholder="비밀번호 입력">
				</div>
				<div class="horizontal"></div><!-- 가로 여백 -->
				<div style="width: 500px; text-align:center; clear: both; margin: 0;">
					<input type="button"  id="shelterLoginSubmit" class="btn btn-primary" value="로그인">
				</div>
				<div class="horizontal"></div><!-- 가로 여백 -->
			</fieldset>
		</form>
	</div>
</div>







