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
	 data:{v_id:'${user_id}'},
	 url:'${pageContext.request.contextPath}/mypage/volunteerMyCalendar.do',
	 dataType:'json',
	 cache:false,
	 timeout:30000,
	 success:function(data){
		 	var volunteer = data.volunteer; //유기견 보호소 봉사활동 신청 현황을 보여주는 캘린더
					
				//유기견 봉사활동 일정 리스트
				if(volunteer.length>0){ 
					for(var i = 0; i<volunteer.length; i++){
		 	 			event.push({ 
		 	 				title: '[봉사활동]' + volunteer[i].r_num +','+ volunteer[i].v_num
		 					,start: volunteer[i].v_date
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
				    /*  select: function(start, end) {
				        var title = prompt('Event Title:');
				        var eventData;
				        if (title) {
				          eventData = {
				            title: title,
				            start: start,
				            end: end
				          };
				          $('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
				        }
				        $('#calendar').fullCalendar('unselect');
				      }, */
				      editable: false,//화면에서 직접 날짜 이동 불가능  
				      eventLimit: true,				
					events :  event,
					eventColor: '#378006',	
				  eventClick: function(event) {
					 	var title = $(this).find('.fc-title').text();
					 	var sub_title = title.split(',');
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

	<div var="command">
		<h2 class="hdg">${command.m_id}'s   MYPAGE</h2>
	</div>
	<hr class="my-4">

	<div id='calendar'></div>


나의 후원내역
<c:forEach var="donation" items="${list}">
<table>
<tr>
<td>후원자 번호</td>
<td>${donation.dona_num}</td>
</tr>
<tr>
<td>후원자 닉네임</td>
<td>${donation.dona_username}</td>
</tr>
<tr>
<td>후원한 보호소</td>
<td>${donation.dona_asname}</td>
</tr>
<tr>
<td>후원 날짜</td>
<td>${donation.dona_date}</td>
</tr>
<tr>
<td>후원 메시지</td>
<td>${donation.dona_message}</td>
</tr>
<tr>
<td>후원액</td>
<td>${donation.dona_price}</td>
</tr>
<tr>
<td>후원 상품번호</td>
<td>${donation.dona_goodsnum}</td>
</tr>
<tr>
<td>후원 상품 갯수</td>
<td>${donation.dona_goodamount}</td>
</tr>
</table>

</c:forEach>
</div>



