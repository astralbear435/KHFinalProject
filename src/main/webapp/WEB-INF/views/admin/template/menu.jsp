<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/admin/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(
			function() {
				$(function() {
					var sBtn = $("ul > li"); //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)
					sBtn.find("a").click(function() { // sBtn에 속해 있는  a 찾아 클릭 하면.
						sBtn.removeClass("active"); // sBtn 속에 (active) 클래스를 삭제 한다.
						$(this).parent().addClass("active"); // 클릭한 a에 (active)클래스를 넣는다.
					})
				})
				
				//전체주소
				console.log("url : " + $(location).attr('href'));

				//http:, localhost:port번호, index.html ?test=tttt 순으로 나누어져 있습니다.
				console.log("url : " + $(location).attr('pathname')); 
				var str=($(location).attr('pathname')).split('/');
				console.log(str[4]);
			})
</script>
<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">

	<!-- Sidebar user panel (optional) -->
	<div class="user-panel">
		<div class="pull-left image">
			<img
				src="${pageContext.request.contextPath}/member/imageView.do?m_id=${member.m_id}"
				class="img-circle" alt="User Image">

		</div>
		<div class="pull-left info">
			<p>${member.m_name}</p>
			<!-- Status -->
			<a href="#"><i class="fa fa-circle text-success"></i>
				${member.m_id}</a>
		</div>
	</div>

	<!-- search form (Optional) -->
	<form action="#" method="get" class="sidebar-form">
		<div class="input-group">
			<input type="text" name="q" class="form-control"
				placeholder="Search..."> <span class="input-group-btn">
				<button type="submit" name="search" id="search-btn"
					class="btn btn-flat">
					<i class="fa fa-search"></i>
				</button>
			</span>
		</div>
	</form>
	<!-- /.search form -->

	<!-- Sidebar Menu -->
	<ul class="sidebar-menu" data-widget="tree">
		<li class="header">메인 페이지</li>
		<!-- Optionally, you can add icons to the links -->
		<li class=""><a href="${pageContext.request.contextPath}/admin/pageSetup/mainPage.do"><i class="fa fa-fw fa-object-ungroup"></i> <span>메인
					페이지 이미지</span></a></li>
		<li><a href="${pageContext.request.contextPath}/admin/mainmenu/menulist.do"><i class="fa fa-fw fa-cog"></i> <span>메뉴 설정</span></a></li>
		
		<li class="header">회원 관리</li>
		<li class=""><a href="${pageContext.request.contextPath}/admin/member/normalMember.do"><i class="glyphicon glyphicon-user"></i> 
		<span>일반 회원</span></a></li>
		<li class=""><a href="${pageContext.request.contextPath}/admin/member/shelterMember.do"><i class="fa fa-fw fa-heartbeat"></i>  
		<span>보호소</span></a></li>
		<li class="header">게시물 관리</li>
		<li class="treeview"><a href="#"><i class="fa fa-link"></i> <span>공지사항</span> <span class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
			</span> </a>
			<ul class="treeview-menu">
				<li><a
					href="${pageContext.request.contextPath}/admin/notice/noticeWrite.do">공지사항 작성</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/notice/noticeList.do">공지사항 리스트</a></li>
			</ul></li>
			<li><a href="#"><i class="fa fa-link"></i> <span>팝업 관리</span></a></li> 
		<li class="header">후원 관리</li>
	</ul>
	<!-- /.sidebar-menu -->
</section>
<!-- /.sidebar -->

