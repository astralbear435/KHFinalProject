<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap2.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/fullcalendar.min.css'
	rel='stylesheet' />
<link
	href='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/lib/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0/lib/jquery.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar-3.9.0//fullcalendar.min.js'></script>

<script>
$(document).ready(function(){
	  ajax(); 
});

	function ajax() {
	var event = [];
	$.ajax({
	 type:'post',
	 data:{r_id:'${user_id}'},
	 url:'${pageContext.request.contextPath}/mypage/recruitMyCalendar.do',
	 dataType:'json',
	 cache:false,
	 timeout:30000,
	 success:function(data){
		 	var recruit = data.recruit; //유기견 보호소 봉사활동 신청 현황을 보여주는 캘린더
		 	
		 	console.log(recruit);
					
				//유기견 봉사활동 일정 리스트
				if(recruit.length>0){ 
					for(var i = 0; i<recruit.length; i++){
		 	 			event.push({ 
		 	 				title: '[봉사활동]' + recruit[i].v_id +','+ recruit[i].v_status
		 					,start: recruit[i].v_date
		 	 			});
		 	 			console.log(event[i].title,event[i].start);
		 			}
				} 
			
		 	
			//캘린더
			  $('#calendar').fullCalendar({ 
					header: {
				    	left: '',
				        center: 'title', 
				        right:'prev,next today'				
			  				},
			  	    defaultDate : new Date(),
				 	 navLinks: false,
				  	selectable: true,
				    selectHelper: true,
			
				      editable: false,//화면에서 직접 날짜 이동 불가능  
				      eventLimit: true,				
					events :  event,
					eventColor: '#378006',	
				  eventClick: function(event) {
					 	var title = $(this).find('.fc-title').text();
					 	var sub_title = title.split(',');
					 	//수정해야함
						window.open("${pageContext.request.contextPath}/volunteer/volunteerDetail.do?v_num="+sub_title[1],"volunteer","width=400, height=300, left=100, top=50");

						    }
						  	  				
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
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}

#container {
	
}
</style>



<div class="container">

	
		<h2 class="hdg">${user_id}'s  MYPAGE</h2>

	<hr class="my-4">

	<div id='calendar'></div>
<div>
후원내역
<div class="col-md-12">
<table>
<tr>
<td scope="col">후원번호</td>
<td scope="col">후원자</td>
<td scope="col">후원내액</td>
<td scope="col">후원금액</td>
</tr>
<td></td>
<td></td>
<td></td>
<td></td>

</table>
</div>

우리 보호소를 후원해주신 분들의 후원메시지입니다.
<p></p>
<table>
<tr>
<td>ID</td>
<td>message</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>

</table>
</div>



</div>



