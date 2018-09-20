<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ap.css">
<div class="container">
	<br><br>
	<div class="floatL">&nbsp;&nbsp;</div>
	
	<div class="col-md-12">
		<c:if test="${count == 0}">
			<div class="align-center">등록된 게시물이 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>글 제목</th>
						<th>조회수</th>
						<th>작성자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="notice" items="${list}">
						<tr class="table-light">
							<td>${notice.n_idx}</td>
							<td><a href="detail.do?n_idx=${notice.n_idx}" class="card-link">${notice.n_subject}</a></td>
							<td>${notice.n_hit}</td>
							<td>${notice.n_id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="col-md-12" id="container">
				<div id="block">${pagingHtml}</div>
			</div>
		</c:if>
	</div>
</div>

