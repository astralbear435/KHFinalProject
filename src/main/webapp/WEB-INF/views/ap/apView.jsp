<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ap.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ap.css">
<!-- jQuery -->
<script>
	$(document).ready(function() {
		var date = new Date();
		
		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;

		var today = year + "-" + month + "-" + day;
	});
</script>

<div class="container">
	<div class="col-md-2" style="float:right; margin:100px 0 50px 0;">
		<c:if test="${!empty user_id && user_id == apcommand.id}">
			<input type="button" class="btn btn-warning" value="수정" onclick="location.href='apModify.do?ap_num=${apcommand.ap_num}'">
			<input type="button" class="btn btn-danger" value="삭제" id="apdelete" data-num="${apcommand.ap_num}">
		</c:if>
		<input type="button" class="btn btn-secondary" value="목록" onclick="location.href='apList.do'"><br><br>
	</div>
	<div class="border-styles col-md-4"style="float:left; margin:100px 0 50px 0;">
		<div style="margin:10px 0 10px 0;">
			[${apcommand.id}] ${apcommand.ap_job}	
		</div>
		<hr>
		<div>
			<p style="margin: 50px 0 50px 0;">
				<img src="${pageContext.request.contextPath}/resources/images/ap/dog.png"/>
				관련 자격증 :
				<c:if test="${apcommand.ap_cer==null}">
							없습니다.
						</c:if>
				<c:if test="${apcommand.ap_cer!=null}">
					<c:forTokens var="list" items="${apcommand.ap_cer}" delims=",">
						<c:if test="${list == 1}">반려동물 관리사</c:if>
						<c:if test="${list == 2}">반려동물 행동 교정사</c:if>
					</c:forTokens>
				</c:if>
			</p>
			<p style="margin: 50px 0 50px 0;">
				<img src="${pageContext.request.contextPath}/resources/images/ap/clock.png"
					style="float: left; margin: 20px 0 0 50px;"/><br> 
					체크인, 체크아웃 시간<br>
					체 크 인 : 09:00 AM ~ 09:00 PM<br>
					체 크 아 웃 : 09:00 AM ~ 09:00 PM<br>
					
			</p>
		</div>
		<hr>
		<div>
			<dl>
				<dt>돌봄 환경 : 
					<c:forTokens var="list" items="${apcommand.ap_home}" delims=",">
						<c:if test="${list == 1}"><span class="badge badge-secondary">단독 주택</span></c:if>
						<c:if test="${list == 2}"><span class="badge badge-secondary">아파트</span></c:if>
						<c:if test="${list == 3}"><span class="badge badge-secondary">빌라</span></c:if>
						<c:if test="${list == 4}"><span class="badge badge-secondary">오피스텔</span></c:if>
						<c:if test="${list == 5}"><span class="badge badge-secondary">마당보유</span></c:if>
						<c:if test="${list == 6}"><span class="badge badge-secondary">주변 산책로 보유</span></c:if>
					</c:forTokens>
				</dt>
			</dl>
		</div>
		<hr>
		<div>
			<label>임시보호자 활동 경력</label>
			<dl>
				<dt>펫시터 활동 : 
					<c:if test="${apcommand.ap_act == 1}">없음</c:if>
					<c:if test="${apcommand.ap_act == 2}">1년미만</c:if>
					<c:if test="${apcommand.ap_act == 3}">1년~2년미만</c:if>
					<c:if test="${apcommand.ap_act == 4}">2년~3년미만</c:if>
					<c:if test="${apcommand.ap_act == 5}">3년이상</c:if>
				</dt>
			</dl>
			<dl>
				<dt>반려동물 경험 : 
					<c:if test="${apcommand.ap_pet == 1}">없음</c:if>
					<c:if test="${apcommand.ap_pet == 2}">1년~5년미만</c:if>
					<c:if test="${apcommand.ap_pet == 3}">5년~10년미만</c:if>
					<c:if test="${apcommand.ap_pet == 4}">10년이상</c:if>
				</dt>
			</dl>
		</div>
		<hr>
		<div>
			<label><i class="fa fa-frown-o" style="font-size: 25px;"></i>&nbsp;저는 케어 해드릴 수 없습니다</label>
			<br>
			<c:forTokens var="list" items="${apcommand.ap_nopet}" delims=",">
				<c:if test="${list == 1}"><span class="badge badge-secondary">중성화 안함</span></c:if>
				<c:if test="${list == 2}"><span class="badge badge-secondary">하울링 안함</span></c:if>
				<c:if test="${list == 3}"><span class="badge badge-secondary">가구/물건 파손 심함</span></c:if>
				<c:if test="${list == 4}"><span class="badge badge-secondary">마킹 심함</span></c:if>
				<c:if test="${list == 5}"><span class="badge badge-secondary">자주 짖음</span></c:if>
				<c:if test="${list == 6}"><span class="badge badge-secondary">노령견</span></c:if>
				<c:if test="${list == 7}"><span class="badge badge-secondary">퍼피(2살미만)</span></c:if>
				<c:if test="${list == 8}"><span class="badge badge-secondary">대형견(15kg이상)</span></c:if>
			</c:forTokens>
		</div>
		<hr>
		<div style="margin:10px 0 20px 0;">
			<label><i class="fa fa-smile-o" style="font-size: 25px;"></i>&nbsp;제가 해드릴 수 있어요!</label>
			<br>
			<c:forTokens var="list" items="${apcommand.ap_service}" delims=",">
				<c:if test="${list == 1}"><span class="badge badge-secondary">집앞 픽업</span></c:if>
				<c:if test="${list == 2}"><span class="badge badge-secondary">모발관리(눈물관리,빗질관리)</span></c:if>
				<c:if test="${list == 3}"><span class="badge badge-secondary">약물복용</span></c:if>
				<c:if test="${list == 4}"><span class="badge badge-secondary">응급처치</span></c:if>
				<c:if test="${list == 5}"><span class="badge badge-secondary">목욕가능</span></c:if>
				<c:if test="${list == 6}"><span class="badge badge-secondary">야외산책</span></c:if>
				<c:if test="${list == 7}"><span class="badge badge-secondary">실내놀이</span></c:if>
				<c:if test="${list == 8}"><span class="badge badge-secondary">장기관리(14일이상)</span></c:if>
				<c:if test="${list == 9}"><span class="badge badge-secondary">노령견 케어</span></c:if>
				<c:if test="${list == 10}"><span class="badge badge-secondary">대형견(15kg이상)</span></c:if>
			</c:forTokens>
		</div>
	</div>

	<div class="border-styles col-md-4">
		<form:form commandName="command" action="apBoWrite.do" enctype="multipart/form-data">
			<input type="hidden" id="bo_id" name="bo_id" value="${user_id}">
			<input type="hidden" id="id" name="id" value="${apcommand.id}">
			<input type="hidden" id="ap_num" name="ap_num" value="${apcommand.ap_num}">
			<label>예약을 원하는 날짜를 선택해주세요.</label><br>
			시작 날짜 : <input type="date" id="bo_date_start" name="bo_date_start" min="2013-01-01">
			<br><br>
			<select class="form-control" style="width: 80px;" name="bo_start_hour" id="bo_start_hour">
				<option value="0">0시</option>
				<option value="1">1시</option>
				<option value="2">2시</option>
			</select> 시
			<select class="form-control" style="width: 80px;" name="bo_start_min" id="bo_start_min">
				<option value="00">00분</option>
				<option value="15">15분</option>
				<option value="30">30분</option>
			</select> 분
			<br><br>
			마침 날짜 : <input type="date" id="bo_date_end" name="bo_date_end" min="2013-01-01"><br>
			<br><br>
			<select class="form-control" style="width: 80px;" name="bo_end_hour" id="bo_end_hour">
				<option value="0">0시</option>
				<option value="1">1시</option>
				<option value="2">2시</option>
			</select> 시
			<select class="form-control" style="width: 80px;" name="bo_end_min" id="bo_end_min">
				<option value="00">00분</option>
				<option value="15">15분</option>
				<option value="30">30분</option>
			</select> 분
			<br><br>
			<script>
				var today = new Date().toISOString().split('T')[0];
				document.getElementsByName("bo_date_start")[0].setAttribute('min', today);
				document.getElementsByName("bo_date_end")[0].setAttribute('min', today);
			</script> 
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