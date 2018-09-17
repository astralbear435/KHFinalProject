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
			
			<img style="float: left;width: 13%;margin:1%;" src="${pageContext.request.contextPath}/upload/goods/title.png" alt="">
			
			  <ul id="m_nav_list" class="m_nav">
				<li> <a href="${pageContext.request.contextPath}/main/main.do">Home</a> </li>
				<li> <a href="${pageContext.request.contextPath}/main/gallery.do">Gallery</a> </li>
				<li> <a href="${pageContext.request.contextPath}/main/icons.do">Icons</a> </li>
<<<<<<< HEAD
				<li> <a href="${pageContext.request.contextPath}/main/codes2.do">Codes</a> </li>
				<li><a href="${pageContext.request.contextPath}/recruit/recruitList.do">봉사활동 모집</a></li>
=======
					    <li><a href="${pageContext.request.contextPath}/main/codes2.do">codes</a> </li>	
>>>>>>> eaeb9531a013948ad2744e7904076067244fd02f
				<li> <a href="${pageContext.request.contextPath}/ap/apMain.do">임시보호</a> </li>
				<li> <a href="${pageContext.request.contextPath}/shelter/shelterList.do">보호소</a> </li>
				<c:if test="${empty user_id}">
				<li><a href="${pageContext.request.contextPath}/member/provision.do">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath}/member/selectLogin.do">로그인</a></li>
				</c:if>
				<c:if test="${!empty user_id}">
<<<<<<< HEAD
				<li><a href="#">${user_id}님 로그인</a></li>
				<c:if test="${user_auth==3 || user_auth==4}">
=======
				<li><a href="${pageContext.request.contextPath}/member/memberDetail.do">${user_id}님 로그인</a></li>
>>>>>>> 55bfb18f117dda57dd48c11c72c466fd62efa098
				<li><a href="${pageContext.request.contextPath}/shelter/shelterConfirm.do">회원정보</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/note/receivedList.do">쪽지</a></li>
				<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/goods/list.do">후원하기</a></li>
			  </ul>
			</div>
			
		<!-- menu -->
		<div class="container">
			<div class="w3l-banner-grids">
				<h1><a href="${pageContext.request.contextPath}/main/main.do">CAN</a></h1>
				<div class="w3layouts-border"> </div>
				<p></p>
				<div class="w3-button">
					<ul>
						<li><a href="${pageContext.request.contextPath}/main/about.do" class="button1">About</a></li>
						<li><a href="${pageContext.request.contextPath}/main/single.do" class="button2">More</a></li>
					</ul>
				</div>
				<div class="agileinfo-social-grids">
					<ul>
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-rss"></i></a></li>
						<li><a href="#"><i class="fa fa-vk"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- //banner -->
<%-- <ul class="menu">
	<c:if test="${empty user_id}">
	<li><a href="${pageContext.request.contextPath}/member/write.do">회원가입</a></li>
	<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
	</c:if>
	
	<c:if test="${!empty user_id}">
	<li><a href="${pageContext.request.contextPath}/member/detail.do">회원정보</a></li>
	<li>${user_id}님 로그인 중</li>
	<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
	</c:if>
</ul> --%>