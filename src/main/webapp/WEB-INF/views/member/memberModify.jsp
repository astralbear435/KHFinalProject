<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberConfirm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<div class="page-main-style">
	<h1>회원정보수정</h1>
	<form:form commandName="command" action="update.do" id="modify_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li>
				<label for="m_id">아이디</label>
				<input type="text" name="m_id" value="${command.m_id}" readonly>
			</li>
			<li>
				<label for="m_name">이름</label>
				<input type="text" name="m_name" id="m_name" value="${command.m_name}">
				<div id="message_name"></div>
			</li>
			<li>
				<label for="m_nickname">닉네임</label>
				<input type="text" name="m_nickname" value="${command.m_nickname}" readonly>
			</li>
			<li>
				<label for="m_passwd">비밀번호</label>
				<input type="password" name="m_passwd" id="m_passwd">
				<div id="message_pw"></div>
			</li>
			<li>
				<label for="checkPw">비밀번호 확인</label>
				<input type="password" name="checkPw" id="checkPw">
				<div id="message_checkPw"></div>
			</li>
			<li>
				<label for="m_phone">전화번호</label>
				<input type="text" name="m_phone" id="m_phone" value="${command.m_phone}">
				<div id="message_phone"></div>
			</li>
			<li>
				<label for="m_zipcode">우편번호</label>
				<input type="text" name="m_zipcode" id="m_zipcode" value="${command.m_zipcode}" readonly>
				<input type="button" id="selectZipcode" value="우편번호 찾기">
				<div id="message_zipcode"></div>
			</li>
			<li>
				<label for="m_address">주소</label>
				<input type="text" name="m_address" id="m_address" value="${command.m_address}" readonly>
			</li>
			<li>
				<label for="m_address_detail">상세주소</label>
				<input type="text" name="m_address_detail" id="m_address_detail" value="${command.m_address_detail}">
				<div id="message_address_detail"></div>
			</li>
			<li>
				<label for="m_email">이메일</label>
				<input type="email" name="m_email" id="m_email" value="${command.m_email}" readonly>
			</li>
			<li>
				<label for="upload">프로필사진</label>
				<input type="file" name="upload" id="upload"><br>
			</li>
			<li>
				<label for="m_birth">생년월일</label>
				<input type="text" name="m_birth" id="m_birth" value="${command.m_birth}">
				<div id="message_birth"></div>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="뒤로" onclick="location.href='${pageContext.request.contextPath}/member/detail.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>







