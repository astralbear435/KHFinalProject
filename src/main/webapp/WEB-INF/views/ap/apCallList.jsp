<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/apCall.js"></script>
<div class="container">
	<br><br>
	<div class="col-md-12">
		<form class="form-inline my-2 my-lg-0" action="list.do" style="float:center;" id="search_form" method="get">
			<div class="form-group">
				<select class="custom-select" name="keyfield">
					<option selected>Open this select menu</option>
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
			</div>
	    	<input class="form-control mr-sm-2" type="text" name="keyword" id="keyword" placeholder="Search">
	    	<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
	    </form>
		<input type="button" class="btn btn-secondary" style="float:right; margin:0 0 10px 0;" 
					value="임시보호자 메인으로" onclick="location.href='apMain.do'">
		<c:if test="${!empty user_id}">
			<input type="button" class="btn btn-outline-warning" style="float:right; margin:0 10px 10px 0;" 
					value="글쓰기" onclick="location.href='apCallForm.do'">
		</c:if>
	</div>
	<c:if test="${count == 0}">
	<div style="text-align:center;">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<c:forEach var="article" items="${list}">
		<div class="card border-primary col-md-3" style="max-width: 250px; margin:0 10px 10px 15px;">
			<div class="card-header">
				<p class="card-text">
					${article.call_num}
				</p>
				신청기간 : ${article.call_start} ~ ${article.call_end}</div>
			<div class="card-body">
				<h4 class="card-title">작성자 : ${article.call_name}</h4>
				<p class="card-text">
					<c:if test="${article.call_wei == 1}"> 15kg 미만</c:if>
					<c:if test="${article.call_wei == 2}"> 15kg 이상</c:if>
				</p>
				<p class="card-text">
					<c:if test="${article.call_re == 1}">신청함</c:if>
					<c:if test="${article.call_re == 2}">신청하지 않음</c:if>
				</p>
				<a href="apCalldetail.do?call_num=${article.call_num}" class="card-link">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<div class="col-md-12" style=" text-align:center; margin:50px 0 50px 0;">${pagingHtml}</div>
	</c:if>
</div>







