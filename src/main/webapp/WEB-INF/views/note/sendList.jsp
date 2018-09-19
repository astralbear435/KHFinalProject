<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/seyeong/noteList.js"></script>
<body>
	<div class="horizontal"></div> <!-- 가로 여백 -->
	<div style="text-align: center;">
		<div class="titleText">보낸 쪽지함</div><br><br>

		<c:if test="${count == 0}">
			<div>보낸 쪽지가 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
			<div style="width: 80%; margin: 0 auto">
				<div style="text-align: left; margin-bottom: 10px;">
					<input type="button" value="삭제" class="btn btn-danger" id="deleteBtn" onclick="deleteAction();">
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" style="width: 10%;">
								<input type="checkbox" class="checkAll" onclick="checkAll();">
							</th>
							<th scope="col" style="width: 20%;">받는 사람</th>
							<th scope="col">내용</th>
							<th scope="col" style="width: 15%;">보낸 날짜</th>
							<th scope="col" style="width: 15%;">수신 여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="note" items="${list}">
							<c:if test="${note.delete_status != 'recipient'}">
								<tr>
									<td><input type="checkbox" name="checkRow" data-num="${note.note_num}"></td>
									<th scope="row">${note.recipient}</th>
									<td><a href="Javascript:;"
										onClick="window.open('${pageContext.request.contextPath}/note/detail.do?note_num=${note.note_num}','쪽지','width=485, height=540, scrollbars=no')">
											${note.note_content} </a></td>
									<td>${note.write_date}</td>
									<td><c:if test="${note.read_status != 'open_not'}">읽음</c:if>
										<c:if test="${note.read_status == 'open_not'}">읽지않음</c:if></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<div class="horizontal"></div>
		<!-- 가로 여백 -->
	</div>
</body>
</html>