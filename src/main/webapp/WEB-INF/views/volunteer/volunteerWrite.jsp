<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/volunteer.js"></script>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/lib/jquery.min.js'></script>
<title>보호소 봉사 활동 신청</title>


<script>
if("1"){
	document.getElementById("v_date").valueAsDate = new Date();	
}

</script>



	<div>
		<h2>${recruit.r_id}보호소봉사 활동 신청</h2>
		<a href="보호소 소개 페이지로 이동링크">${recruit.r_id} 보호소를 소개페이지 바로가기.</a>
		<h5>${recruit.r_title}</h5>
		<h5>봉사활동 내용 안내</h5>
		<h6>${recruit.r_content}</h6>
		<h5>모집 인원</h5>
		<h6>일일 ${recruit.r_people} 명</h6>
		모집 날짜
		 ${recruit.r_start_date}~${recruit.r_end_date}
		 <form
			action="${pageContext.request.contextPath}/volunteer/volunteerWrite.do"
			id="volunteerWrite_form" method="post">
			<input type="hidden" name="r_num" value="${recruit.r_num}" />
			<table>
				<tr>
					<td>봉사활동 신청 날짜</td>
					<c:if test="${recruit.r_status} == '1'">
					
					</c:if>
					<c:if test="${recruit.r_status} == '2'">
					
					</c:if>
					<td><input type="date" name="v_date" id="v_date" min=${recruit.r_start_date} max=${recruit.r_end_date}>
					</td>	
					
				</tr>
				<tr>
					<td>봉사활동 신청 시간</td>
					<td>
						<div class="form-check-radio">
							<label class="form-check-label"> <input type="radio"
								name="v_status" id="v_status1" class="form-check-input"
								value="1">1~3시<span class="form-check-sign"></span>
							</label>
						</div>
						<div class="form-check-radio">
							<label class="form-check-label"> <input type="radio"
								name="v_status" id="v_status2" class="form-check-input"
								value="2" checked>3~5시 <span class="form-check-sign"></span>
							</label>
						</div>
					</td>
				</tr>
			</table>

			<div class="align-center">
				<input type="submit" value="신청하기">
			</div>
				<c:if test="${!empty user_id && user_id == recruit.r_id}">		
						<input type="button" value="수정" class="btn btn-success"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitUpdate.do?r_num=${recruit.r_num}'">
				
						<input type="button" value="삭제" class="btn btn-danger"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitDelete.do?r_num=${recruit.r_num}'">
					</c:if>
		</form>
	</div>


