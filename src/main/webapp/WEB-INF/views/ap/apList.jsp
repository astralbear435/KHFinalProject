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
					<option selected="">Open this select menu</option>
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="id">ID</option>
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
					value="글쓰기" onclick="location.href='apForm.do'">
		</c:if>
	</div>
	<c:if test="${count == 0}">
	<div style="text-align:center;">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<c:forEach var="article" items="${list}">
		<div class="card border-primary col-md-12">
			<div class="card-header">
				<p class="card-text">
					${article.ap_num}
					[ ${article.id} ]<br>
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
	<div class="col-md-12" style=" text-align:center; margin:50px 0 50px 0;">${pagingHtml}</div>
	</c:if>
</div>

<!-- 
<p class="card-text">봉사 : ${article.ap_ser}</p>
					<p class="card-text">
						임보유형 : 
						<c:if test="${article.ap_sel == 1}">위탁/자신의 집</c:if>
						<c:if test="${article.ap_sel == 2}">방문/고객님의 집</c:if>
						<c:if test="${article.ap_sel == 3}">위탁&방문</c:if>
					</p>
					<p class="card-text">
						케어 불가 : 
						<c:forTokens var="list" items="${article.ap_nopet}" delims=",">
							<c:if test="${list == 1}">중성화 안함</c:if>
							<c:if test="${list == 2}">하울링 안함</c:if>
							<c:if test="${list == 3}">가구/물건 파손 심함</c:if>
							<c:if test="${list == 4}">마킹 심함</c:if>
							<c:if test="${list == 5}">자주 짖음</c:if>
							<c:if test="${list == 6}">노령견</c:if>
							<c:if test="${list == 7}">퍼피(2살미만)</c:if>
							<c:if test="${list == 8}">대형견(15kg이상)</c:if>
						</c:forTokens>
					</p>					
					<p class="card-text">
						직업 : ${article.ap_job}
					</p>
					<p class="card-text">
						한달 기준 가능 한 일 수 : 
						<c:if test="${article.ap_mon == 1}">10일 이내</c:if>
						<c:if test="${article.ap_mon == 2}">10일 ~ 20일미만</c:if>
						<c:if test="${article.ap_mon == 3}">20일 이상</c:if>
					</p>
 -->





