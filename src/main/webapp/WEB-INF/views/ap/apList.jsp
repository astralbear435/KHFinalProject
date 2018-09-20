<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<div class="container">
	<br><br>
	<div class="card border-primary col-md-12 col-sm-12">
		<form action="apList.do"  id="search_form" method="get">
			<div class="condition floatL">
				<div class="search col-md-12 col-sm-12">
					<label class="condition floatL" for="ap_service"> 서비스 |&nbsp;</label>
					<div class="floatL" style="width:20px; height: 24px;"></div>
					<div class="condition">
						<fieldset class="form-group">
							<input type="radio" class="form-check-input" name="ap_service" value="1">집앞픽업&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="2">모발관리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="3">약물복용&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="4">응급처치&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="6">야외산책&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="7">실내놀이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="8">장기관리(14일이상)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="9">노령견 케어&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_service" value="10">퍼피케어
						</fieldset>
					</div>
				</div>
				
				<div class="search col-md-12 col-sm-12">
					<label class="condition floatL" for="ap_home"> 거주환경 |&nbsp;</label>
					<div class="floatL" style="width:20px; height: 24px;"></div>
					<div class="condition">
						<fieldset class="form-group">
							<input type="radio" class="form-check-input" name="ap_home" value="1"> 단독주택&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_home" value="2"> 아파트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_home" value="3"> 빌라&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_home" value="4"> 오피스텔&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_home" value="5"> 마당보유&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" class="form-check-input" name="ap_home" value="6"> 주변산책로보유
						</fieldset>
					</div>
				</div>
				
				<div class="col-md-12" id="container">
					<div id="block-btn">
						<input type="submit" class="btn btn-primary btn-lg" value="검색" onclick="location:href='#'">
					</div>
				</div>
				
			</div>
	    </form>
	</div>
	
	<div class="floatL">&nbsp;&nbsp;</div>
	<input type="button" class="btn btn-primary" style="float:right; margin:20px 0 20px 0;" 
			value="임시보호자 메인으로" onclick="location.href='apMain.do'">
	<c:if test="${!empty user_id && user_auth==5}">
		<input type="button" class="btn btn-warning" style="float:right; margin:20px 10px 20px 0;" 
				value="글쓰기" onclick="location.href='apForm.do'">
	</c:if>
	<div class="floatL">&nbsp;&nbsp;</div>
	
	<c:if test="${count == 0}">
		<div style="text-align:center; margin: 50px 0 50px 0;">등록된 게시물이 없습니다.</div>
	</c:if>
	
	<c:if test="${count > 0}">
	<c:forEach var="article" items="${list}">
		<div class="card border-primary col-md-12" style="margin:10px 0 10px 0;">
			<div class="card-header">
				<p class="card-text">
					${article.ap_num}
					[ ${article.id} ]
					<br>
					<i class="fa fa-github"></i>반려동물 경험 : 
					<c:if test="${article.ap_pet == 1}">없음</c:if>
					<c:if test="${article.ap_pet == 2}">1년~5년미만</c:if>
					<c:if test="${article.ap_pet == 3}">5년~10년미만</c:if>
					<c:if test="${article.ap_pet == 4}">10년이상</c:if>
				</p>
				
			</div>
				<div class="card-body">
					<p class="card-text">
						<i class="fa fa-ticket"></i>
						자격증 보유 : 
						<c:forTokens var="list" items="${article.ap_cer}" delims=",">
							<c:if test="${list == 1}">반려동물 관리사</c:if>
							<c:if test="${list == 2}">반려동물 행동 교정사</c:if>
						</c:forTokens>
					</p>
					<p class="card-text">
						<i class="fa fa-github-alt"></i>
						펫시터 경험 : 
						<c:if test="${article.ap_act == 1}">없음</c:if>
						<c:if test="${article.ap_act == 2}">1년미만</c:if>
						<c:if test="${article.ap_act == 3}">1년~2년미만</c:if>
						<c:if test="${article.ap_act == 4}">2년~3년미만</c:if>
						<c:if test="${article.ap_act == 5}">3년이상</c:if>
					</p>
					<p class="card-text">
						<c:forTokens var="list" items="${article.ap_home}" delims=",">
							<c:if test="${list == 1}"><span class="badge badge-secondary">단독 주택</span></c:if>
							<c:if test="${list == 2}"><span class="badge badge-secondary">아파트</span></c:if>
							<c:if test="${list == 3}"><span class="badge badge-secondary">빌라</span></c:if>
							<c:if test="${list == 4}"><span class="badge badge-secondary">오피스텔</span></c:if>
							<c:if test="${list == 5}"><span class="badge badge-secondary">마당보유</span></c:if>
							<c:if test="${list == 6}"><span class="badge badge-secondary">주변 산책로 보유</span></c:if>
						</c:forTokens>
						<c:forTokens var="list" items="${article.ap_service}" delims=",">
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
					</p>
					<a href="apdetail.do?ap_num=${article.ap_num}" class="card-link">상세보기</a>
				</div>
		</div>
	</c:forEach>
	<div class="col-md-12" id="container">
		<div id="block">${pagingHtml}</div>
	</div>
	
	</c:if>
</div>

