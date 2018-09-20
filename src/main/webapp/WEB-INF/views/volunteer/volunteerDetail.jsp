<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap2.css" rel="stylesheet" type="text/css" media="all"/>

<script type="text/javascript">
	//ajax로 삭제해야 함
/* 	function deleteVNum(){
		var v_num = $('#v_num').val();
		
		if(confirm("일정을 취소하시겠습니까?")){	
			
			location.replace('volunteer/volunteerDelete.do?v_num=' + v_num);
		}else{
			return;
		}		
		self.close();
		opener.document.location.reload();
	} */
	//도큐먼트 레디 펑션 안으로 옮기기
	
	
	
	$(document).ready(function(){		
			$('#afterTime').hide();
			$('#afterButton').hide();
			$('#afterDate').hide();
			$('#v_date').hide();
			$('#status').hide();
			
			
		 $(document).on('click', '#modify-btn', function(){
			 $('#afterTime').show();
				$('#afterButton').show();
				$('#afterDate').show();
				$('#v_date').show();
				$('#status').show();
			
		 });
		 		  
		 $('#volunteerUpdate_form').submit(function(){
			  var v_num = $('#v_num').val();
			  var v_date = $('#v_date').val();
			  var v_status = document.querySelector('input[name="v_status"]:checked').value;
					
				$.ajax({
				 type:'post',
				 data:{v_num:v_num, v_date:v_date, v_status:v_status},
				 url:'${pageContext.request.contextPath}/volunteer/volunteerUpdate.do',				 
				 dataType:'json',
				 cache:false,
				 timeout:30000,
				 success:function(data){
					 if(data.result == 'logout'){
						 alert('로그인 후 이용가능');
					 }else if(data.result == 'success'){
						 alert('전송 성공');
					 }
				 },
				 error:function(){					 
					 alert('네트워크 오류 발생');
				 }			
			}); 
		 }); 
		 
		 $('#delete').click(function(){
				var v_num = $('#v_num').val();
				$.ajax({
					type:'post',
					url:'${pageContext.request.contextPath}/volunteer/volunteerDelete.do',
					data:{v_num:v_num},
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						if(data.result == 'logout'){
							alert('로그인해야 삭제할 수 있습니다.');
						}else if(data.result == 'success'){
							alert('일정이 취소되었습니다.');
						}else{
							alert('삭제 시 오류 발생');
						}
						self.close();
						opener.document.location.reload();
					},
					error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			});
		 
	});
	
</script>
<div class="container">
	<%-- <h2 style="text-align: center;">${shelterName.s_name} 봉사 활동 신청 안내</h2> --%>
	<span class="badge badge-pill badge-danger">제목</span> <p>${volunteer.r_title}</p>

	<span class="badge badge-pill badge-warning">안내</span>
	<p>${volunteer.r_content}</p>
	
	모집 인원 : <span class="badge badge-pill badge-dark">일일</span> ${volunteer.r_people} 명
	
	<c:if test="${volunteer.r_status ==1}">
		<p><span class="badge badge-pill badge-success">상시 모집 중</span></p>
	</c:if>
	<c:if test="${volunteer.r_status ==2}">
	<p><span class="badge badge-pill badge-success">모집 날짜</span>  ${volunteer.r_start_date}~${volunteer.r_end_date}</p>
	</c:if>	

	<table style="float:left;">
	<tr>
	<td>
	<span class="badge  badge-pill badge-info">신청 날짜</span>
	</td>
	<td>
	${volunteer.v_date}
	</td>
	</tr>
	<tr>
	<td>
	<span class="badge  badge-pill badge-info">신청 시간</span>
	</td>
	<td>
	<c:if test="${volunteer.v_status==1}">	 1~3시	 </c:if>
	 <c:if test="${volunteer.v_status==2}">	  3~5시	  </c:if>
	</td>	
	</tr>
	</table>

	<form id="volunteerUpdate_form">
	<input type="hidden" id="v_num" name="v_num" value="${volunteer.v_num}">
	 	<table>
	 	<tr>
	 	<td rowspan="2">
	 	<input type="submit" class="btn btn-outline-danger" id="afterButton" value="-▶">
	 	</td>
	 	<td>
		<span class="badge  badge-pill badge-info" id="afterDate">변경 날짜</span>
		</td>
		<td>
		<input type="date" name="v_date" id="v_date" min=${recruit.r_start_date } max=${recruit.r_end_date}> </td>
		</tr>
		<tr>
		<td>
		<span class="badge  badge-pill badge-info" id="afterTime">변경 시간</span>
		</td>
		<td>
			<div id="status">
		<div class="form-check-radio">
			<label class="form-check-label"> <input type="radio"
				name="v_status" id="v_status1" class="form-check-input" value="1">1~3시<span
				class="form-check-sign"></span>
			</label>
		</div>
		<div class="form-check-radio">
			<label class="form-check-label"> <input type="radio"
				name="v_status" id="v_status2" class="form-check-input" value="2"
				checked>3~5시 <span class="form-check-sign"></span>
			</label>
		</div>
		</div>
		</td>
		</tr>		
		</table>
	</form>

	<div id="after">
	<input type="hidden" value="${volunteer.v_id}"><input type="hidden" value="${volunteer.v_num}"> 
	</div> 	
		
	<div style="clear:both;">
<div id="before2" >
	<input type="button" value="봉사활동 일정  변경" style="float:left;" class="btn btn-warning" id="modify-btn">
</div>
	<input type="button" value="봉사활동 일정 취소" style="float:left;" class="btn btn-danger" id="delete" >				
</div>
</div>	