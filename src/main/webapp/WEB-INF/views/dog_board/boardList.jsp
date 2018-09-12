<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/css/layout.css"></script>
<div class="page-main-style">
	<h2>게시판 목록</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="id">보호소명칭</option>
					<option value="name">동물이름</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="새로고침" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<br>
	<br>
	<div class="align-right">
		<c:if test="${!empty user_id}">
		<input type="button" value="글쓰기"
		       onclick="location.href='write.do'">
		</c:if>
	</div>
	<br><br>
	<c:if test="${count == 0}"><!--  게시물이 없을때 -->
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}"><!-- 게시물이 존재할때 -->
		<c:forEach var="board" items="${list}">
		<div style="display:inline-block;">
			<a href="detail.do?num=${board.num}"><img src="imageView.do?num=${board.num}" width="230" height="150">
			<br><font color="#17BEFF" size="3">${board.id}</font>&nbsp;&nbsp;${board.an_species}&nbsp;${board.an_name}<br><br>
			<img id="message" src="../resources/images/message.png"style="max-width:10px">&nbsp;(${board.re_cnt})<br>
			${board.reg_date} &nbsp;<font size="1">조회 ${board.an_hit}</font><br><br><br></a>
		</div>
	</c:forEach> 
	<div class="align-center">${pagingHtml}</div>
	</c:if>
	
</div>