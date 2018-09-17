<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/apCall.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ap.css">

<div class="container">

	<div style="float:right; margin:100px 0 50px 0;">
		<c:if test="${!empty user_id && user_id == apcall.call_name}">
			<input type="button" class="btn btn-warning" value="수정" onclick="location.href='apModify.do?ap_num=${apcommand.ap_num}'">
			<input type="button" class="btn btn-danger" value="삭제" id="apdelete" data-num="${apcommand.ap_num}">
		</c:if>
		<input type="button" class="btn btn-secondary" value="목록" onclick="location.href='apCallList.do'">
	</div>
	
	<div class="border-styles col-md-7"style="float:left; margin:100px 0 50px 0;">
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
				<i class="fa fa-calendar"></i>사전만남 신청 : 
				<c:if test="${apcall.call_re == 1}">
					신청함 <br>
					사전 만남 날짜 : ${apcall.call_re_date} <br> 
					사전 만남 시간 : ${apcall.call_re_hour}시 ${apcall.call_re_min}분
				</c:if>
				<c:if test="${apcall.call_re == 2}">
					신청하지 않음
				</c:if>
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
	
	<div class="border-styles col-md-4">
		<form:form commandName="command" action="apBoCallWrite.do" enctype="multipart/form-data">
			<input type="hidden" id="bo_call_id" name="bo_call_id" value="${user_id}">
			<input type="hidden" id="bo_call_date_start" name="bo_call_date_start" value="${apcall.call_start}">
			<input type="hidden" id="bo_call_date_end" name="bo_call_date_end" value="${apcall.call_end}">
			<input type="hidden" id="bo_call_re" name="bo_call_re" value="${apcall.call_re}">
			<c:if test="${apcall.call_re == 1}">
				<input type="hidden" id="bo_call_re_date" name="bo_call_re_date" value="${apcall.call_re_date}">
				<input type="hidden" id="bo_call_re_hour" name="bo_call_re_hour" value="${apcall.call_re_hour}">
				<input type="hidden" id="bo_call_re_min" name="bo_call_re_min" value="${apcall.call_re_min}">
			</c:if>
			<input type="hidden" id="call_name" name="call_name" value="${apcall.call_name}">
			<input type="hidden" id="call_num" name="call_num" value="${apcall.call_num}">
			<input type="submit" class="btn btn-warning btn-lg btn-block" 
					style="margin:10px 0 10px 0;" value="예약하기">
		</form:form>
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