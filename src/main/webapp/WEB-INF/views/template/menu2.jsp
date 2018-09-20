<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/modal.js"></script>

<!-- 모달창 열기 -->
<div id="loginModal" class="modalLogin">
   <div class="modal-contentLogin">
      <span style="font-size: 10pt; text-align: right;"> <a href="#"
         id="closeMemberLogin">X</a>
      </span>
      <p style="text-align: center;">
         <span style="font-size: 14pt;"> <b><span
               style="font-size: 20pt;">로그인</span></b>
         </span>
      </p>
      <div style="text-align: center;">
         <hr color="black">
         <label for="l_id">아이디</label> <br> <input type="text" name="m_id" id="l_id" class="form-control" style="max-width: 280px;" placeholder="아이디 입력" /> <br>
         <br> 
         <label for="l_passwd">비밀번호</label> <br> 
         <input type="password" name="m_passwd" id="l_passwd" class="form-control" style="max-width: 280px;" placeholder="비밀번호 입력" /> <br>
         <br>
         <div id="errorLogin"></div>
         <br> 
         <input type="button" class="btn btn-warning btn-lg" id="loginButton" value="로그인"> <br>
         <br>&nbsp; 
         <a class="agile-icon" href="${pageContext.request.contextPath}/member/provision.do"> 
            <i class="fa fa-user">회원가입</i>
         </a>&nbsp;&nbsp;&nbsp; 
         <a class="agile-icon" href="${pageContext.request.contextPath}/member/findMember.do">
            <i class="fa fa-unlock-alt">아이디/비밀번호 찾기</i>
         </a> <br>
         <br>
      </div>
   </div>
</div>
<!-- 모달창 닫기 -->

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
		<c:if test="${mcount==0}">
			</c:if>
			<c:if test="${mcount>0}">
				<c:forEach var="menu" items="${mlist}">
					<c:if test="${menu.menu_order==1}">
						<li><a href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order==2}">
						<li><a href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order==3}">
						<li><a href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order==4}">
						<li><a href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order==5}">
						<li><a
							href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order==6}">
						<li><a
							href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order==7}">
						<li><a
							href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${menu.menu_order>8}">
						<li><a
							href="${pageContext.request.contextPath}${menu.menu_url}">${menu.menu_name}</a></li>
					</c:if>
					<c:if test="${empty user_id}">
						<c:if test="${menu.menu_order==8}">
							<li><a href="${menu.menu_url}" id="memberLogin">${menu.menu_name}</a></li>
						</c:if>
					</c:if>
					</c:forEach>
					<c:if test="${!empty user_id}">
						<c:if test="${user_auth==1 || user_auth==2 || user_auth==5}">
							<li><a href="#">${user_id}님 로그인 중</a></li>
							<li><a
								href="${pageContext.request.contextPath}/mypage/mypage.do?v_id=${user_id}">마이페이지</a>
							</li>
						</c:if>
						<c:if test="${user_auth==3 || user_auth==4}">
							<li><a
								href="${pageContext.request.contextPath}/shelter/shelterConfirm.do">${user_id}님
									로그인 중</a></li>
							<li><a
								href="${pageContext.request.contextPath}/mypage/mypageShelter.do?r_id=${user_id}">마이페이지</a>
							</li>
						</c:if>
						<li><a
							href="${pageContext.request.contextPath}/note/receivedList.do">쪽지</a></li>
						<li><a
							href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
					</c:if>
				
			</c:if>
		</ul>
	</div>
	<!-- menu -->
	<div class="container">
		<!-- wthree-top-grid -->
		<div class="wthree-top-grid">
			<h1>
				<a href="${pageContext.request.contextPath}/main/main.do">Njoy
					Trip</a>
			</h1>
			<div class="w3-agileits-border"></div>
			<h2>About Us</h2>
		</div>
		<!-- //wthree-top-grid -->
	</div>
</div>
<!-- //banner -->