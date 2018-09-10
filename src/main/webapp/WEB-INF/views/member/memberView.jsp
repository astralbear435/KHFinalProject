<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-main-style">
	<h2>회원상세정보</h2>
	<ul>
		<li>아이디 : ${member.m_id}</li>
		<li>이름 : ${member.m_name}</li>
		<li>닉네임 : ${member.m_nickname}</li>
		<li>전화번호 : ${member.m_phone}</li>
		<li>우편번호 : ${member.m_zipcode}</li>
		<li>주소 : ${member.m_address}</li>
		<li>상세주소 : ${member.m_address_detail}</li>
		<li>이메일 : ${member.m_email} 
			<c:if test="${member.auth != 2 }">
				<input type="button" value="인증메일 재전송" onclick="location.href='${pageContext.request.contextPath}/member/resend_e.do'">
			</c:if>
		</li>
		<li>생년월일 : ${member.m_birth}</li>
		<li>가입날짜 : ${member.m_reg_date}</li>
		<li>프로필 사진 : <div>
			<c:if test="${fn:endsWith(member.profileFilename,'.jpg')||
					  	fn:endsWith(member.profileFilename,'.JPG')||
					  	fn:endsWith(member.profileFilename,'.gif')||
					  	fn:endsWith(member.profileFilename,'.GIF')||
					  	fn:endsWith(member.profileFilename,'.png')||
					  	fn:endsWith(member.profileFilename,'.PNG')}">
				<img src="imageView.do?m_id=${member.m_id}" style="max-width:500px">
			</c:if></div>
		</li>
	</ul>
	
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='update.do'">
		<input type="button" value="탈퇴" onclick="location.href='delete.do'">
	</p>
	
</div>








