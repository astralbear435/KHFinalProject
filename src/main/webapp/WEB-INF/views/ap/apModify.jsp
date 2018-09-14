<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
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
		$("#call_re_date").attr("value", today);
	});

	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("call_re_date")[0].setAttribute('min', today);
</script>
<div class="container">
	<form:form commandName="apCommand" action="apUpdate.do" enctype="multipart/form-data">
		<form:hidden path="ap_num"/>
		<form:hidden path="id"/>
		<form:errors element="div" cssClass="error-color"/>	
			<table class="table table-hover" style="margin:50px 0 50px 0;">
				<tr>
					<th class="table-primary" style="text-align: center;" colspan="2"><font size="5em">기본사항</font></th>
				</tr>
				<tr>
					<th style="text-align: center;">직업</th>
					<td>
						<input type="radio" id="ap_job" name="ap_job" value="주부" <c:if test="${apCommand.ap_job=='주부'}">checked</c:if>/>주부
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" id="ap_job" name="ap_job" value="직장인" <c:if test="${apCommand.ap_job=='직장인'}">checked</c:if>/>직장인
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" id="ap_job" name="ap_job" value="프리랜서" <c:if test="${apCommand.ap_job=='프리랜서'}">checked</c:if>/>프리랜서
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" id="ap_job" name="ap_job" value="무직" <c:if test="${apCommand.ap_job=='무직'}">checked</c:if>/>무직
					</td>
				</tr>
		<!-- ----------------------------------------------------------------- -->
				<tr>
					<th class="table-primary" style="text-align: center;" colspan="2"><font size="5em">반려동물 관련 경력</font></th>
				</tr>
				<tr>
					<th style="text-align: center;">자격증 보유시 자격증 내역</th>
					<td>
						<input type="checkbox" id="ap_cer" name="ap_cer" value="1" <c:if test="${apCommand.ap_cer==1}">checked</c:if>/>반려동물 관리사
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" id="ap_cer" name="ap_cer" value="2" <c:if test="${apCommand.ap_cer==2}">checked</c:if>/>반려동물 행동교정사
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">펫시터 활동 경력</th>
					<td>
						<input type="radio" value="1" id="ap_act" name="ap_act" <c:if test="${apCommand.ap_act==1}">checked</c:if>/>없음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" id="ap_act" name="ap_act" <c:if test="${apCommand.ap_act==2}">checked</c:if>/>1년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="3" id="ap_act" name="ap_act" <c:if test="${apCommand.ap_act==3}">checked</c:if>/>1년~2년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="4" id="ap_act" name="ap_act" <c:if test="${apCommand.ap_act==4}">checked</c:if>/>2년~3년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="5" id="ap_act" name="ap_act" <c:if test="${apCommand.ap_act==5}">checked</c:if>/>3년이상
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">반려동물 키워 본 경력</th>
					<td>
						<input type="radio" value="1" id="ap_pet" name="ap_pet" <c:if test="${apCommand.ap_pet==1}">checked</c:if>/>없음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" id="ap_pet" name="ap_pet" <c:if test="${apCommand.ap_pet==2}">checked</c:if>/>1년~5년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="3" id="ap_pet" name="ap_pet" <c:if test="${apCommand.ap_pet==3}">checked</c:if>/>5년~10년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="4" id="ap_pet" name="ap_pet" <c:if test="${apCommand.ap_pet==4}">checked</c:if>/>10년이상
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">유기견 보호센터 및<br>반려동물 봉사경험</th>
					<td>
						<div class="form-group">
						<textarea class="form-control" id="ap_ser" name="ap_ser" rows="4" cols="30">${apCommand.ap_ser}</textarea>
						</div>
					</td>
				</tr>
		<!-- ----------------------------------------------------------------- -->
				<tr>
					<th class="table-primary" style="text-align: center;" colspan="2"><font size="5em">임시보호 환경</font></th>
				</tr>
				<tr>
					<th style="text-align: center;">거주지(중복체크)</th>
					<td>
						선택 사항 : &nbsp;&nbsp;&nbsp;&nbsp;
						<c:forTokens var="list" items="${apCommand.ap_home}" delims=",">
						<c:choose>
								<c:when test="${list==1}">
							       	단독주택
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==2}">
							       	아파트
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==3}">
							       	빌라
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==4}">
							       	오피스텔
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==5}">
							       	마당보유
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==6}">
							       	주변산책로
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							</c:choose>
						</c:forTokens>
						<br>
						-------------------------------------------------------------------------------------------------
						<br>
						다시 선택 : &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="1" id="ap_home" name="ap_home">단독주택
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_home" name="ap_home">아파트
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_home" name="ap_home">빌라
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="4" id="ap_home" name="ap_home">오피스텔
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="5" id="ap_home" name="ap_home">마당보유
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="6" id="ap_home" name="ap_home">주변산책로 보유
						
					</td>
				</tr>
			<!-- ----------------------------------------------------------------- -->
				<tr>
					<th class="table-primary" style="text-align: center;" colspan="2"><font size="5em">임시보호 활동 유형</font></th>
				</tr>
				<tr>
					<th style="text-align: center;">임시보호 유형</th>
					<td>
						<input type="checkbox" value="1" id="ap_sel" name="ap_sel" <c:if test="${apCommand.ap_sel==1}">checked</c:if>/>위탁/자신집
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_sel" name="ap_sel" <c:if test="${apCommand.ap_sel==2}">checked</c:if>/>방문/고객의 집
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_sel" name="ap_sel" <c:if test="${apCommand.ap_sel==3}">checked</c:if>/>위탁 & 방문 모두
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">케어불가 반려동물</th>
					<td>
						선택 사항 : &nbsp;&nbsp;&nbsp;&nbsp;
						<c:forTokens var="list" items="${apCommand.ap_nopet}" delims=",">
						<c:choose>
								<c:when test="${list==1}">
							       	중성화 하지 않음
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==2}">
							       	하울링심함
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==3}">
							       	가구,물건 심하게 물어뜯음
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==4}">
							       	마킹 심함
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==5}">
							       	자주 짖음
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==6}">
							       	노령견
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==7}">
							       	퍼피(2살미만)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==8}">
							       	대형견(15kg이상)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							</c:choose>
						</c:forTokens>
						<br>
						-------------------------------------------------------------------------------------------------
						<br>
						다시 선택 : &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="1" id="ap_nopet" name="ap_nopet">중성화 하지 않음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_nopet" name="ap_nopet">하울링심함
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_nopet" name="ap_nopet">가구,물건 심하게 물어뜯음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="4" id="ap_nopet" name="ap_nopet">마킹 심함
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="5" id="ap_nopet" name="ap_nopet">자주 짖음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="6" id="ap_nopet" name="ap_nopet">노령견
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="7" id="ap_nopet" name="ap_nopet">퍼피(2살미만)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="8" id="ap_nopet" name="ap_nopet">대형견(15kg이상)
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">제공 가능 서비스</th>
					<td>선택 사항 : &nbsp;&nbsp;&nbsp;&nbsp;
						<c:forTokens var="list" items="${apCommand.ap_nopet}" delims=",">
						<c:choose>
								<c:when test="${list==1}">
							       	집 앞 픽업
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==2}">
							       	모발관리(눈물관리, 빗질)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==3}">
							       	약물복용(약바르거나 복용하는 법 숙지)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==4}">
							       	응급처치(응급시 행하는 조치를 아는 경우)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==5}">
							       	목욕가능
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==6}">
							       	야외산책
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==7}">
							       	실내놀이(노즈워킹 등 다양한 놀이)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==8}">
							       	장기관리(14일 이상)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==9}">
							       	노령견케어(만 8세이상 노령견 간호 교육 수료)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							    <c:when test="${list==10}">
							       	퍼피케어(2년미만 퍼피 교육 수료)
							       	&nbsp;&nbsp;&nbsp;&nbsp;
							    </c:when>
							</c:choose>
						</c:forTokens>
						<br>
						-------------------------------------------------------------------------------------------------
						<br>
						다시 선택 : &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="1" id="ap_service" name="ap_service">집 앞 픽업
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_service" name="ap_service">모발관리(눈물관리, 빗질)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_service" name="ap_service">약물복용(약바르거나 복용하는 법 숙지)
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="4" id="ap_service" name="ap_service">응급처치(응급시 행하는 조치를 아는 경우)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="5" id="ap_service" name="ap_service">목욕가능
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="6" id="ap_service" name="ap_service">야외산책
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="7" id="ap_service" name="ap_service">실내놀이(노즈워킹 등 다양한 놀이)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="8" id="ap_service" name="ap_service">장기관리(14일 이상)
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="9" id="ap_service" name="ap_service">노령견케어(만 8세이상 노령견 간호 교육 수료)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="10" id="ap_service" name="ap_service">퍼피케어(2년미만 퍼피 교육 수료)
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">임시보호 활동 가능 일 수<br>(한달기준)</th>
					<td>
						<input type="radio" value="1" id="ap_mon" name="ap_mon" <c:if test="${apCommand.ap_mon==1}">checked</c:if>/>10일 이내
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" id="ap_mon" name="ap_mon" <c:if test="${apCommand.ap_mon==2}">checked</c:if>/>10일 ~ 20일 미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="3" id="ap_mon" name="ap_mon" <c:if test="${apCommand.ap_mon==3}">checked</c:if>/>20일 이상
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="center">
						<input type="submit" class="btn btn-outline-warning" value="등록하기">
						<input type="button" class="btn btn-primary" value="목록" onclick="location.href='apMain.do'">
					</td>
				</tr>
			</table>
	</form:form>
</div>







