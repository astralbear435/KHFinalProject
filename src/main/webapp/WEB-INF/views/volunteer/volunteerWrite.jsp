<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap2.css" 	rel="stylesheet" type="text/css" media="all"/>
<link
	href='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/fullcalendar.min.css' rel='stylesheet'/>
<link
	href='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/fullcalendar.print.min.css' rel='stylesheet' media='print'/>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/lib/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/lib/jquery.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/fullcalendar.min.js'></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/volunteer.js"></script>

<script type="text/javascript" >
$(document).ready(function(){

	  ajax(); 
	  
	  $('#volunteerWrite_form').submit(function(){
	  		var vdate = $('#date').text();
	  		$('#test').text(vdate);
	  		var v_status = document.querySelector('input[name="v_status"]:checked').value;
	  		var r_num = $('#r_num').val();
	  		
	      	  $.ajax({
	      	    	type:'post',
	      	    	data:{v_date:vdate, v_status:v_status, r_num:r_num},
	      	    	url:'volunteerWrite.do',
	      	    	dataType:'json',
	      	    	cache:false,
	      	    	timeout:10000,
	      	    	success: function(data){
	      	    		if(data.result == 'logout'){
	      	    			alert('로그인이 필요합니다.');
	      	    		}else if(data.result == 'success'){
	      	    			alert('봉사활동 신청이 완료되었습니다.');
	      	    		}
	      	    	},
	      	    	error: function(){
	      	    		alert('전송 네트워크 오류');
	      	    	}
	      	    });  	      	 
	      });       
});

function ajax() {
	var event = [];
	$.ajax({
	 type:'post',
	 data:{r_id:'${user_id}'},
	 url:'${pageContext.request.contextPath}/volunteer/recruitCalendar.do',//컨트롤러 만들어야 함
	 dataType:'json',
	 cache:false,
	 timeout:30000,
	 success:function(data){
		 var recruit = data.recruit;
		 console.log(recruit);
		//유기견 봉사활동 일정 리스트
			if(recruit.length>0){ 
				for(var i = 0; i<recruit.length; i++){
	 	 			event.push({ 
	 	 				title: '[신청자]'
	 	 				,start: recruit[i].v_date
	 	 				,color: 'yellow'
	 	 			});
	 	 			console.log(event[i].title,event[i].start);
	 			}
			} 	
				
		    $('#calendar').fullCalendar({
		        header: {
		      		left: '',
		  	        center: 'title', 
		  	        right:'prev,next today'		
		        },
		        defaultDate : new Date(),
		        navLinks: false,
		        businessHours: true,
		        
		        dayClick: function(date, jsEvent, view) {
		        	var r_status = $('#r_status').val();
		        	var r_start = $('#startDate').text();
		        	var r_end = $('#endDate').text();
		        	
		        	var r_start_date = Date.parse(r_start);
		        	var r_end_date = Date.parse(r_end);
		        		//r_start_date r_end_date를 불러오기
		        		if(r_status == 2){
		        		if(date > r_start_date){
		        			alert( date.format()+' 봉사활동을 신청합니다.');   	   
				    	    document.getElementById("date").innerHTML = date.format();		
		        		}else{
		        			alert( date.format() + '은 모집기간이 아닙니다. 모집기간을 다시 확인해주세요');
		        		}
		        }
		        		
					 var myDate = new Date(); 
					 if(r_status == 1){
				       if (date < myDate) { //이미 지난 날짜
				    	   alert( date.format()+' 봉사활동을 신청할 수 없습니다.');  
				       } 
				     
				       if(date > myDate){ 
				    	   alert( date.format()+' 봉사활동을 신청합니다.');   	   
				    	    document.getElementById("date").innerHTML = date.format();				       
				    	} }		      	 	
		        },
		        selectable: true,
		        selectHelper: true,
		        editable: false,
		        events: event
		      });
	 },
	 error:function(){
		 alert('네트워크 오류 발생');
	 }
	});
} 	 
  	

</script>

<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 500px;
    margin: 0 auto;
  }

</style>

	<div>
		<h2 style="text-align: center;">${shelterName.s_name} 봉사 활동 신청 안내</h2>
		
		<br><span class="badge badge-pill badge-danger">제목</span> ${recruit.r_title}</p>
		<span class="badge badge-pill badge-warning">안내</span> <p>${recruit.r_content}</p>
		모집 인원 : <span class="badge badge-pill badge-dark">일일</span>  ${recruit.r_people} 명</p>
		<input type="hidden" name="r_status" value="${recruit.r_status}" id="r_status"/>
		<c:if test="${recruit.r_status==1}">
		<span class="badge badge-pill badge-success">상시 모집 중</span>
		
		</c:if>
		<c:if test="${recruit.r_status==2}">
		<span class="badge badge-pill badge-success">모집 날짜</span> <span id="startDate">${recruit.r_start_date}</span>  ~ <span id="endDate"> ${recruit.r_end_date}</span> 
		</c:if>
		
		<div id="test"></div>
		<div style="height: 35px;"></div>
</div>


  <div id='calendar'></div>
  	<div style="height: 35px;"></div>
	<div style="text-align:center;">
	<form <%-- action="${pageContext.request.contextPath}/volunteer/volunteerWrite.do" --%> id="volunteerWrite_form" method="post">
			<input type="hidden" name="r_num" value="${recruit.r_num}" id="r_num"/>
			<table >
				<tr>
					<td><span class="badge  badge-pill badge-info">신청 날짜</span> </td> <td> <p id='date'><small class="form-text text-muted">달력에서 원하시는 날짜를 선택하세요</small></p> </td>					
				</tr>
				<tr>
					<td><span class="badge  badge-pill badge-info">신청 시간</span></td>
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
			<input type="submit" value="신청하기" id="date_submit" class="btn btn-warning">	
		</form>
				
			</div>
