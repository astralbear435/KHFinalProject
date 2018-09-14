<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- banner -->
	<div class="banner">
		<!-- menu -->
			<div id="toggle_m_nav">
			  <div id="m_nav_menu" class="m_nav">
				<div class="m_nav_ham" id="m_ham_1"></div>
				<div class="m_nav_ham" id="m_ham_2"></div>
				<div class="m_nav_ham" id="m_ham_3"></div>
			  </div>
			</div>

			<div id="m_nav_container" class="m_nav">
			  <ul id="m_nav_list" class="m_nav">
				<li> <a href="${pageContext.request.contextPath}/main/main.do">Home</a> </li>
				<li> <a href="${pageContext.request.contextPath}/main/gallery.do">Gallery</a> </li>
				<li> <a href="${pageContext.request.contextPath}/main/icons.do">Icons</a> </li>
				<li> <a href="${pageContext.request.contextPath}/ap/apMain.do">임시보호</a> </li>
				<li> <a href="${pageContext.request.contextPath}/shelter/shelterList.do">보호소</a> </li>
				<c:if test="${empty user_id}">
				<li><a href="${pageContext.request.contextPath}/member/provision.do">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath}/member/selectLogin.do">로그인</a></li>
				</c:if>
				<c:if test="${!empty user_id}">
				<li>${user_id}님 로그인 중</li>
				<li><a href="${pageContext.request.contextPath}/shelter/shelterConfirm.do">회원정보</a></li>
				<li><a href="${pageContext.request.contextPath}/note/receivedList.do">쪽지</a></li>
				<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
				
				
				</c:if>
				<li><a href="${pageContext.request.contextPath}/recruit/recruitList.do">봉사활동 모집</a></li>
				<li> <a href="${pageContext.request.contextPath}/mypage/mypage.do?v_id=${user_id}">마이페이지</a> </li>
		
				<c:if test="${!empty user_id && user_auth == 3}">
				<li> <a href="${pageContext.request.contextPath}/mypage/mypageShelter.do?r_id=${user_id}">보호소 마이페이지</a> </li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/goods/list.do">후원하기</a></li>
			  </ul>
			</div>
		<!-- menu -->
		<div class="container">
			<!-- wthree-top-grid -->
			<div class="wthree-top-grid">
				<h1><a href="${pageContext.request.contextPath}/main/main.do">Njoy Trip</a></h1>
				<div class="w3-agileits-border"> </div>
				<h2>About Us</h2>
			</div>
			<!-- //wthree-top-grid -->
		</div>
	</div>
	<!-- //banner -->