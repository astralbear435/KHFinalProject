<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/apCall.js"></script>
<div class="container">
	<table class="table table-hover" style="margin:100px 0 50px 300px; width:500px;">
		<tbody>
			<tr>
				<th>번호 : </th>
				<td>${apcall.call_num}</td>
			</tr>
			<tr>
				<th>작성자 : </th>
				<td>${apcall.call_name}</td>
			</tr>
			<tr>
				<th><i class="fa fa-calendar"></i>기간 : </th>
				<td>${apcall.call_start} ~ ${apcall.call_end}</td>
			</tr>
			<tr>
				<th>반려동물 무게 : </th>
				<td>
					<c:if test="${apcall.call_wei == 1}">
						15kg 미만
					</c:if>
					<c:if test="${apcall.call_wei == 2}">
						15kg 이상
					</c:if>
				</td>
			</tr>
			<tr>
				<th>나의 반려동물 소개 : </th>
				<td>${apcall.call_intro}</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<div style="float:right; margin:0 0 10px 0;">
		<c:if test="${!empty user_id && user_id == apcall.call_name}">
			<input type="button" class="btn btn-outline-warning" value="수정" onclick="location.href='apCallupdate.do?call_num=${apcall.call_num}'">
			<input type="button" class="btn btn-outline-warning" value="삭제" id="apCalldelete" data-num="${apcall.call_num}">
		</c:if>
		<input type="button" class="btn btn-secondary" value="목록" onclick="location.href='apCallList.do'">
	</div>
		
	<!-- 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
</div>