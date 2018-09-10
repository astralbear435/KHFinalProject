<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/note.js"></script>
<body>
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div style="text-align: center;">
		<div class="titleText">받은 쪽지함</div><br><br>

		<c:if test="${count == 0}">
			<div>받은 쪽지가 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
			<div style="width: 950px; margin: 0 auto">
				<div style="text-align: left; margin-bottom: 10px;">
					<input type="button" value="삭제" id="deleteBtn" onclick="deleteAction();">
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" style="width: 90px;">
								<input type="checkbox" class="checkAll" onclick="checkAll();">
							</th>
							<th scope="col" style="width: 140px;">보낸 사람</th>
							<th scope="col" style="width: 390px;">내용</th>
							<th scope="col">보낸 날짜</th>
							<th scope="col">차단</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="note" items="${list}">
							<c:if test="${note.delete_status != 'sender'}">
							
								<!-- 읽지 않았을 때 -->
								<c:if test="${note.read_status.equals('open_not')}">
									<tr class="table-warning">
										<td><input type="checkbox" name="checkRow" data-num="${note.note_num}"></td>
										<th scope="row">${note.recipient}</th>
										<td>
											<a href="Javascript:;"
												onClick="window.open('${pageContext.request.contextPath}/note/detail.do?note_num=${note.note_num}','쪽지','width=450, height=550, scrollbars=no')">
												${note.note_content}</a>
										</td>
										<td>${note.write_date}</td>
										<td>차단</td>
									</tr>
								</c:if>

								<!-- 읽었을 때 -->
								<c:if test="${!note.read_status.equals('open_not')}">
									<tr>
										<td><input type="checkbox" name="checkRow" data-num="${note.note_num}"></td>
										<th scope="row">${note.recipient}</th>
										<td>
											<a href="Javascript:;"
												onClick="window.open('${pageContext.request.contextPath}/note/detail.do?note_num=${note.note_num}','쪽지','width=450, height=550, scrollbars=no')">
												${note.note_content}</a>
										</td>
										<td>${note.write_date}</td>
										<td>차단</td>
									</tr>
								</c:if>
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