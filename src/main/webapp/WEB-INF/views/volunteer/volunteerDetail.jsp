<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>

<div class="container">
	<p>${volunteer.r_title}</p>
	</h4>
	<c:if test="${volunteer.r_status==2}">
		<p class="card-text">${recruit.r_start_date}~${recruit.r_end_date}</p>
	</c:if>
	<c:if test="${volunteer.r_status==1}">
		<p class="card-text">상시 모집 중</p>
	</c:if>
	<h5>봉사활동 내용 안내</h5>
	<h6>${volunteer.r_content}</h6>
	<h5>모집 인원</h5>
	<h6>일일 ${volunteer.r_people} 명</h6>
	모집 날짜 ${volunteer.r_start_date}~${volunteer.r_end_date} 
	
	봉사활동 신청 날짜
	${volunteer.v_date} 
	봉사활동 신청 시간 ${volunteer.v_status}

<script type="text/javascript">	
	$(document).ready(function(){
    $("#modify input").click(function(){
      alert("수정 버튼 클릭");
    });
});
	$(document).ready(function(){
	    $("#delete input").click(function(){
	     confirm("봉사활동 일정을 취소하시겠습니까?");

	     window.close();  
	    
	     alert("봉사활동 일정이 취소되었습니다.");
	    });
	}); 
	</script>
		
	<div>
	 <input
		type="button" value="봉사활동 일정  변경" class="btn btn-success" id="modify"
		onclick="${pageContext.request.contextPath}/volunteer/volunteerUpdate.do?v_num=${volunteer.v_num}">
		</div>
		<div class="delete">
	<input type="button" value="봉사활동 일정 취소" class="btn btn-success" id="delete"
		onclick="${pageContext.request.contextPath}/volunteer/volunteerDelete.do?v_num=${volunteer.v_num}">
</div>
</div>