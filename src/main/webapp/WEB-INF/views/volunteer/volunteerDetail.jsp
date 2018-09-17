<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

	//ajax로 삭제해야 함
	function deleteVNum(){
		var v_num = $('#v_num').text();
		
		if(confirm("일정을 삭제하시겠습니까?")){
			location.replace('volunteer/volunteerDelete.do?v_num=' + v_num);
		}else{
			return;
		}
		
		self.close();
		opener.document.location.reload();
	}
	
	

</script>
<div class="container">
	<div id="v_num" style="display: none;">${volunteer.v_num}</div>	
	<p>${volunteer.r_title}</p>

	<h5>봉사활동 내용 안내</h5>
	<h6>${volunteer.r_content}</h6>
	
	<h5>모집 인원</h5>
	
	<h6>일일 ${volunteer.r_people} 명</h6>
	
	<c:if test="${volunteer.r_status ==2}">
	<p class="card-text">모집 날짜  : ${volunteer.r_start_date}~${volunteer.r_end_date}</p>
	</c:if>
	<c:if test="${volunteer.r_status ==1}">
		<p class="card-text">상시 모집 중</p>
	</c:if>
	
	<div id="before">	
	봉사활동 신청 날짜 : ${volunteer.v_date} 	
	<c:if test="${volunteer.v_status==1}">
	 봉사활동 신청 시간 : 1~3시
	</c:if>
	<c:if test="${volunteer.v_status==2}">
	 봉사활동 신청 시간 : 3~5시
	</c:if>

	</div>
	<div id="output"></div>	
	
<div id="before2">
<input type="button" value="봉사활동 일정  변경" class="modify-btn btn-success" id="modify-btn" onclick="location.href='${pageContext.request.contextPath}/volunteer/volunteerUpdate.do?v_num=${volunteer.v_num}'">
</div>
	
	<input type="button" value="봉사활동 일정 취소" class="btn btn-success" id="delete"  onclick="deleteVNum();">
				
</div>