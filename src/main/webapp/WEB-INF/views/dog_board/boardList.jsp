<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/css/layout.css"></script>
<style>
ul{
   list-style:none;
   text-align:center;
   align:center;
   }
form{
   width:50%;
   height:10%;
   align:center;
}
</style>
<div class="page-main-style">
	<br>
	<h2 align="center">입양페이지</h2>
	<br>
	<br>
		<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="id">보호소명칭</option>
					<option value="an_name">동물이름</option>
					<option value="all">전체</option>
				</select>
				<input type="text" name="keyword" id="keyword">
				<input type="submit" value="찾기" class="btn btn-outline-info">
				<input type="button" value="새로고침" class="btn btn-outline-info" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<br><br>
	<c:if test="${count == 0}"><!--  게시물이 없을때 -->
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}"><!-- 게시물이 존재할때 -->
		<c:forEach var="board" items="${list}">
		<div style="display:inline-block;">
			<a href="detail.do?num=${board.num}"><img src="imageView.do?num=${board.num}" width="230" height="200">
			<br><font color="#17BEFF" size="3">${board.id}</font>&nbsp;&nbsp;${board.an_species}&nbsp;${board.an_name}<br><br>
			<img id="message" src="../resources/images/message.png"style="max-width:10px">&nbsp;(${board.re_cnt})<br>
			${board.reg_date} &nbsp;<font size="1">조회 ${board.an_hit}</font><br><br><br></a>
		</div>
	</c:forEach> 
	<div align="right">
		<c:if test="${!empty user_id}">
		<input type="button" value="글쓰기"  class="btn btn-info"
		       onclick="location.href='write.do'">
		</c:if>
	</div>
	<div align="center">${pagingHtml}</div>
	</c:if>
	
</div>