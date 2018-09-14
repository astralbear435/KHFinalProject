<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/memberView.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberConfirm.js"></script>

<div class="page-main-style">
	<br>
	<div id="margin0a">
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<div class="titleText">${member.m_id} 프로필</div>
	<div class="horizontal"></div>
	<div class="w600 margin0a">
		<div id="margin0a">
			<div class="photoArea">
				<div>
				<c:if test="${member.profileFilename eq null and member.m_gender eq '남'}">
					<img src="${pageContext.request.contextPath}/resources/images/member/manDefault.png">
				</c:if>
				<c:if test="${member.profileFilename eq null and member.m_gender eq '여'}">
					<img src="${pageContext.request.contextPath}/resources/images/member/womanDefault.png">
				</c:if>
				<c:if test="${fn:endsWith(member.profileFilename,'.jpg')||
						  	fn:endsWith(member.profileFilename,'.JPG')||
						  	fn:endsWith(member.profileFilename,'.gif')||
						  	fn:endsWith(member.profileFilename,'.GIF')||
						  	fn:endsWith(member.profileFilename,'.png')||
						  	fn:endsWith(member.profileFilename,'.PNG')}">
					<img src="imageView.do?m_id=${member.m_id}">
				</c:if>
				</div>
			</div>
		</div><hr>
		<div>
			<div class="m_text">아이디 : ${member.m_id}</div><hr>
			<div class="m_text">이름 : ${member.m_name}</div><hr>
			<div class="m_text">성별 : ${member.m_gender}</div><hr>
			<div class="m_text">닉네임 : ${member.m_nickname}</div><hr>
			<div class="m_text">전화번호 : ${fn:substring(member.m_phone,0,3)}-${fn:substring(member.m_phone,3,7)}-${fn:substring(member.m_phone,7,11)}</div><hr>
			<div class="m_text">우편번호 : ${member.m_zipcode}</div><hr>
			<div class="m_text">주소 : ${member.m_address}</div><hr>
			<div class="m_text">
				이메일 : ${member.m_email} &nbsp;
				<c:if test="${member.auth != 2 }">
					<input type="button" class="btn btn-outline-warning" value="인증메일 재전송" onclick="location.href='${pageContext.request.contextPath}/member/resend_e.do'">
				</c:if>
			</div><hr>
			<div class="m_text">생년월일 : ${fn:substring(member.m_birth,0,4)}년 ${fn:substring(member.m_birth,4,6)}월 ${fn:substring(member.m_birth,6,8)}일</div><hr>
			<div class="m_text">가입날짜 : ${member.m_reg_date}</div><hr>
		</div>
		<div align="center">
			<input type="button" value="수정" class="btn btn-primary" onclick="location.href='memberUpdate.do'">&nbsp;&nbsp;&nbsp;
			<input type="button" value="탈퇴" class="btn btn-primary" onclick="location.href='memberDelete.do'">
		</div><br>
	</div>
</div>











