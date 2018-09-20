<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<div class="container">
<br><br>

<div style="text-align:center;">
<br><br>
<!-- 검색창 -->
<form action="reviewList.do" method="get">
		<ul style="list-style:none; margin:0;padding:0;text-align:center">
			<li style="display:inline-block;">
				<select name="keyfield">
					<option value="re_asname">보호소명</option>
					<option value="re_title">제목</option>
					<option value="all" selected>전체</option>
				</select>
			</li>
			<li style="display:inline-block;">
				<input type="text" name="keyword" id="keyword">
			</li>
			<li style="display:inline-block;">
				<input type="submit" class="btn btn-warning"value="search">
			</li>
		</ul>
	</form>
<c:if test="${user_id!=null}">
<div style="float:right;">
<button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/review/reviewWrite.do'">후기쓰기</button>
</div>
</c:if>

<br><br><br>
<c:if test="${count == 0}">
<br><br>
	<div class="align-center"><h1>등록된 게시물이 없습니다.</h1></div>
</c:if>
<c:if test="${count> 0}">
<div style="width:100%">
<table class="table" style="width:90%;margin-left:5%">
		<tr>
			<th>글번호</th>
			<th><a style="color:green">[ 보호소 ]</a></th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="review" items="${review}">
		<tr>
			<td>${review.re_num}</td>
			<td><a style="color:green;" href="#">[${review.re_asname}]</a></td>
			<td><a href="${pageContext.request.contextPath}/review/reviewDetail.do?re_num=${review.re_num}">${review.re_title}</a></td>
			<td>${review.re_id}</td>
			<td>${review.re_date}</td>
			<td>${review.re_hit}</td>
		</tr>
		</c:forEach>
	</table>
</div>
</c:if>
</div>
<br><br>
	<div style="text-align:center;margin-left:47%">${pagingHtml}</div>
	<br><br>
</div>