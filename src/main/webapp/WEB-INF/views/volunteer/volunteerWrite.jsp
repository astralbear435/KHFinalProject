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

<script>
	$(document).ready(function() {

    $('#calendar').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listMonth'
      },
      defaultDate : new Date(),
      navLinks: true,
      businessHours: true,
      
      dayClick: function(date, jsEvent, view) {
    	 alert( date.format()+' 봉사활동을 신청합니다.');   	   
  	    document.getElementById("date").innerHTML = date.format();
      },
      selectable: true,
      selectHelper: true,
      editable: false,
      events: [
/*         {
          title: 'Business Lunch',
          start: '2018-9-03T13:00:00',
          constraint: 'businessHours'
        },
        {
          title: 'Meeting',
          start: '2018-09-13T11:00:00',
          constraint: 'availableForMeeting', // defined below
          color: '#257e4a'
        },
        {
          title: 'Conference',
          start: '2018-09-18',
          end: '2018-09-20'
        },
        {
          title: 'Party',
          start: '2018-09-29T20:00:00'
        },

        // areas where "Meeting" must be dropped
        {
          id: 'availableForMeeting',
          start: '2018-09-11T10:00:00',
          end: '2018-09-11T16:00:00',
          rendering: 'background'
        },
        {
          id: 'availableForMeeting',
          start: '2018-09-13T10:00:00',
          end: '2018-09-13T16:00:00',
          rendering: 'background'
        },

        // red areas where no events can be dropped
        {
          start: '2018-09-24',
          end: '2018-09-28',
          overlap: false,
          selectable: false,
          rendering: 'background',
          color: '#ff9f89'
        },
        {
          start: '2018-09-06',
          end: '2018-09-08',
          overlap: false,
          rendering: 'background',
          color: '#ff9f89'
        } */
      ]
    });

  	/* var sample = document.getElementsByName('v_status');
  	for(var i=0;i<sample.length;i++){
  		if(sample[i].checked==true){
  			alert(sample[i].value);
  			
  		}
  	} */
  	
  	
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
  	      	    		alert('네트워크 오류');
  	      	    	}
  	      	    });  	      	 
  	      });       
  });
	
	

</script>

<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 450px;
    margin: 0 auto;
  }

</style>
	
<title>봉사 활동 신청서</title>
	<div>
		<h2>${shelterName.s_name} 봉사 활동 신청</h2>
		<a href="보호소 소개 페이지로 이동링크">${recruit.r_id} 보호소를 소개페이지 바로가기.</a>
		<p>제목 ${recruit.r_title}</p>
		<p>${recruit.r_content}</p>
		<p>모집 인원 : 일일  ${recruit.r_people} 명</p>
		<c:if test="${recruit.r_status==1}">
		<p>상시 모집 중</p>
		</c:if>
		<c:if test="${recruit.r_status==2}">
		<p>모집 날짜   ${recruit.r_start_date}~${recruit.r_end_date}</p>
		</c:if>
		<form <%-- action="${pageContext.request.contextPath}/volunteer/volunteerWrite.do" --%> id="volunteerWrite_form" method="post">
			<input type="hidden" name="r_num" value="${recruit.r_num}" id="r_num"/>
			<table>
				<tr>
					<td>봉사활동 신청 날짜 : </td> <td> <p id='date'>달력에서 원하시는 날짜를 선택하세요</p> </td>
					<%-- <c:if test="${recruit.r_status} == '1'">
					
					</c:if>
					<c:if test="${recruit.r_status} == '2'">
					<td><input type="date" name="v_date" id="v_date" min=${recruit.r_start_date} max=${recruit.r_end_date}>
					</td>			</c:if> --%>		
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
				<input type="submit" value="신청하기" id="date_submit">
			</div>
	
		</form>
		<div id="test"></div>
</div>

  <div id='calendar'></div>
  

