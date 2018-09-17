<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<form:form commandName="command" action="apWrite.do" id="register_form" enctype="multipart/form-data">
	    <form:hidden path="id"/>
		<form:errors element="div" cssClass="error-color"/>	
			<table class="table table-hover" style="margin:50px 0 50px 0;">
				<tr>
					<th class="table-primary" style="text-align: center;" colspan="2"><font size="5em">기본사항</font></th>
				</tr>
				<tr>
					<th style="text-align: center;">직업</th>
					<td>
						<input type="radio" value="주부" id="ap_job" name="ap_job">주부
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="직장인" id="ap_job" name="ap_job">직장인
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="프리랜서" id="ap_job" name="ap_job">프리랜서
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="무직" id="ap_job" name="ap_job">무직
					</td>
				</tr>
		<!-- ----------------------------------------------------------------- -->
				<tr>
					<th class="table-primary" style="text-align: center;" colspan="2"><font size="5em">반려동물 관련 경력</font></th>
				</tr>
				<tr>
					<th style="text-align: center;">자격증 보유시 자격증 내역</th>
					<td>
						<input type="checkbox" value="1" id="ap_cer" name="ap_cer">없음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_cer" name="ap_cer">반려동물 관리사
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_cer" name="ap_cer">반려동물 행동교정사
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">펫시터 활동 경력</th>
					<td>
						<input type="radio" value="1" id="ap_act" name="ap_act">없음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" id="ap_act" name="ap_act">1년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="3" id="ap_act" name="ap_act">1년~2년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="4" id="ap_act" name="ap_act">2년~3년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="5" id="ap_act" name="ap_act">3년이상
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">반려동물 키워 본 경력</th>
					<td>
						<input type="radio" value="1" id="ap_pet" name="ap_pet">없음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" id="ap_pet" name="ap_pet">1년~5년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="3" id="ap_pet" name="ap_pet">5년~10년미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="4" id="ap_pet" name="ap_pet">10년이상
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">유기견 보호센터 및<br>반려동물 봉사경험</th>
					<td>
						<div class="form-group">
						<textarea class="form-control" id="ap_ser" name="ap_ser" rows="4" cols="30"></textarea>
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
						<input type="checkbox" value="1" id="ap_sel" name="ap_sel">위탁/자신집
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_sel" name="ap_sel">방문/고객의 집
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_sel" name="ap_sel">위탁 & 방문 모두
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">케어불가 반려동물</th>
					<td>
						<input type="checkbox" value="1" id="ap_nopet" name="ap_nopet">중성화 하지 않음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_nopet" name="ap_nopet">하울링심함
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_nopet" name="ap_nopet">가구,물건 심하게 물어뜯음
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="4" id="ap_nopet" name="ap_nopet">마킹 심함
						<br>
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
					<td>
						<input type="checkbox" value="1" id="ap_service" name="ap_service">집 앞 픽업
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="2" id="ap_service" name="ap_service">모발관리(눈물관리, 빗질)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="3" id="ap_service" name="ap_service">약물복용(약바르거나 복용하는 법 숙지)
						<br>
						<input type="checkbox" value="4" id="ap_service" name="ap_service">응급처치(응급시 행하는 조치를 아는 경우)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="5" id="ap_service" name="ap_service">목욕가능
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="6" id="ap_service" name="ap_service">야외산책
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="7" id="ap_service" name="ap_service">실내놀이(노즈워킹 등 다양한 놀이)
						<br>
						<input type="checkbox" value="8" id="ap_service" name="ap_service">장기관리(14일 이상)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="9" id="ap_service" name="ap_service">노령견케어(만 8세이상 노령견 간호 교육 수료)
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="checkbox" value="10" id="ap_service" name="ap_service">퍼피케어(2년미만 퍼피 교육 수료)
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">임시보호 활동 가능 일 수<br>(한달기준)</th>
					<td>
						<input type="radio" value="1" id="ap_mon" name="ap_mon">10일 이내
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="2" id="ap_mon" name="ap_mon">10일 ~ 20일 미만
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" value="3" id="ap_mon" name="ap_mon">20일 이상
					</td>
				</tr>
				
				<tr>
					<td colspan="3" align="center">
						<input type="submit" class="btn btn-warning" value="등록하기">
						<input type="button" class="btn btn-primary" value="목록" onclick="location.href='apMain.do'">
					</td>
				</tr>
			</table>
	</form:form>
</div>







