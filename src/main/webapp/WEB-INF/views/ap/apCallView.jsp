<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/apCall.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ap.css">

<div class="container">

	<div style="float:right; margin:100px 0 50px 0;">
		<c:if test="${!empty user_id && user_id == apcall.call_name}">
			<input type="button" class="btn btn-warning" value="수정" onclick="location.href='apModify.do?ap_num=${apcommand.ap_num}'">
			<input type="button" class="btn btn-danger" value="삭제" id="apdelete" data-num="${apcommand.ap_num}">
		</c:if>
		<input type="button" class="btn btn-secondary" value="목록" onclick="location.href='apList.do'">
	</div>
	
	<div class="border-styles col-md-8"style="float:left; margin:100px 0 50px 0;">
		<div style="margin:10px 0 10px 0;">
			[${apcall.call_num}] ${apcall.call_name} 
		</div>
		<hr>
		
		<div>
			<p>
				<i class="fa fa-calendar"></i>기간 : ${apcall.call_start} ~ ${apcall.call_end}
			</p>
		</div>
		<hr>
		
		<div>
			<p>
				<img src="${pageContext.request.contextPath}/resources/images/ap/dog.png"/>
				반려동물 무게 :
				<c:if test="${apcall.call_wei == 1}">
					15kg 미만
				</c:if>
				<c:if test="${apcall.call_wei == 2}">
					15kg 이상
				</c:if>
			</p>
		</div>
		<hr>
		
		<div>
			<div style="width: 450px; margin: 0 auto;">
				
				<img src="${pageContext.request.contextPath}\resources\images\ap\intro.png">
				
			</div>
				<p>나의 반려동물 소개<br></p>
				<p>${apcall.call_intro}</p>
		</div>
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