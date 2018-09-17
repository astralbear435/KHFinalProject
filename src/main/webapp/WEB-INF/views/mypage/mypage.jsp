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

window.name = 'mypage';

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
			var boCallList = data.boCallList;	
		 	var bohoCallList = data.bohoCallList;
				//유기견 봉사활동 일정 리스트
				if(volunteer.length>0){ 
					for(var i = 0; i<volunteer.length; i++){
		 	 			event.push({ 
		 	 				title: '[봉사활동]' + volunteer[i].r_num +','+ volunteer[i].v_num
		 					,start: volunteer[i].v_date,
		 					color: 'lightgreen'
		 	 			});
		 	 			console.log(event[i].title,event[i].start);
		 			}
				} 
		 	
				if(boCallList.length>0){ 
					for(var i = 0; i<boCallList.length; i++){
		 	 			event.push({ 
		 	 				title: '[임시보호 신청]'+ '임시보호자'+ boCallList[i].id +'시작일'+ boCallList[i].bo_start_hour +'시'+ boCallList[i].bo_start_min+'분'+'종료일'+ boCallList[i].bo_end_hour+'시'+boCallList[i].bo_end_min+'분',
		 	 				start: boCallList[i].bo_date_start,
		 					end: boCallList[i].bo_date_end,
		 					color: 'pink'
		 	 			});
		 	 			
		 			}
				}	
				if(bohoCallList.length>0){ 
					for(var i = 0; i<bohoCallList.length; i++){
		 	 			event.push({ 
		 	 				title: '[임시보호 신청]'+ '신청자'+ bohoCallList[i].bo_id +'시작일'+ bohoCallList[i].bo_start_hour +'시'+ bohoCallList[i].bo_start_min+'분'+'종료일'+ bohoCallList[i].bo_end_hour+'시'+bohoCallList[i].bo_end_min+'분',
		 	 				start: bohoCallList[i].bo_date_start,
		 					end: bohoCallList[i].bo_date_end,
		 					color: 'skyblue'
		 	 			});
		 	 			
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

<h2 class="hdg">${command.m_id}'s   MYPAGE</h2>

	<hr class="my-4">

	<div id='calendar'></div>








<br><br><br>


<c:forEach var="callHome" items="${callList}">
<table>
<tr>
<td><a href="${pageContext.request.contextPath}/ap/apCalldetail.do?call_num=${callHome.call_num}">내가 쓴 글로 바로가기</a></td>
<td>${callHome.call_start}</td>
</tr>

</table>
펫시터가 신청받으면 캘린더에 표시하게 하고, 상세정보페이지 링크로 보내주기
</c:forEach>

<p> ${donation.dona_asname} </p>


<h3 class="hdg">나의 후원내역</h3>


						<div class="col-md-12">
							<table class="table table-hover">
							  <thead>
							    <tr>
							      <th scope="col">후원자 번호</th>
							      <th scope="col">후원자 닉네임</th>
							      <th scope="col">후원한 보호소</th>
							      <th scope="col">후원 날짜</th>
							      <th scope="col">후원 메시지</th>
							      <th scope="col">후원 금액</th>
							      <th scope="col">후원 상품번호</th>
							      <th scope="col">후원 상품명</th>
							      <th scope="col">후원 상품 갯수</th>
							    </tr>
							  </thead>
	<c:forEach var="donation" items="${donaList}">
	<c:if test="${dona_count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${dona_count > 0}">  
							  <tbody>							
							    <tr class="table-info">
							      <th scope="row">${donation.dona_num}</th>
							      <td>${donation.dona_username}</td>
							      <td>${donation.dona_asname}</td>
							      <td>${donation.dona_date}</td>
							       <td>${donation.dona_message}</td>
							      <td>${donation.dona_price}</td>
							      <td>${donation.dona_goodsnum}</td>
							      <td></td>
							      <td>${donation.dona_goodsamount}</td>
							    </tr>
														 
							  </tbody>
	 </c:if>	
</c:forEach>
							</table> 
						</div>




<c:forEach var="boCall" items="${boCallList}">
<h3 class="hdg">임시 보호자 집으로 부르기</h3>
		<div class="col-md-12">
							<table class="table table-hover">
							  <thead>
							    <tr>
							      <th scope="col">회원이 신청글 쓰고 펫시터가 온다.  </th>     
							http://localhost:8080/ProjectCAN/ap/apCalldetail.do?call_num=5	 
							    </tr>
							  </thead>
							  <tbody>							
							    <tr class="table-info">
							      <th scope="row">
					     
							 ${boCall.bo_num} ${boCall.ap_num}  ${boCall.id} ${boCall.bo_end_min} ${boCall.bo_end_hour} ${boCall.bo_date_end} 
${boCall.bo_start_min} ${boCall.bo_start_hour}  ${boCall.bo_date_start}  ${boCall.bo_id}
							 </th>
							    </tr>
														 
							  </tbody>
							</table> 
						</div>


</c:forEach>
<h3 class="hdg">임시 보호자 집에 맡기기</h3>




</div>

	

