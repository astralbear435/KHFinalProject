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
	  ajax3();


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
			var boList = data.boList;	//임시보호자 집으로 부르기 일정
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
		 	//임시보호자 집에 맡기기
				if(boList.length>0){ 
					for(var i = 0; i<boList.length; i++){
		 	 			event.push({ 
		 	 				title: '[임시보호 신청]'+ '임시보호자'+ boList[i].id +'시작일'+ boList[i].bo_start_hour +'시'+ boList[i].bo_start_min+'분'+'종료일'+ boList[i].bo_end_hour+'시'+boList[i].bo_end_min+'분',
		 	 				start: boList[i].bo_date_start,
		 					end: boList[i].bo_date_end,
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
			      editable: true,//화면에서 직접 날짜 이동 불가능  
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

	function ajax3(){
		var g_name,g_price;
		$.ajax({
			type:'post',
			data:{dona_id:'${user_id}'},
			url:'${pageContext.request.contextPath}/mypage/donaMyPage.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var list= data.list;
				console.log(list);
				$(list).each(function(index,donalist){
					
						var dona_asnames = donalist.dona_asname.split(',');
						var goodsnums = donalist.dona_goodsnum.split(',');
						var goodsamounts = donalist.dona_goodsamount.split(',');
						if(dona_asnames.length>0){
							for(var i=0;i<dona_asnames.length;i++){
								var output = '<tr class="table-info">'; 
								output += ' <th scope="row">' + donalist.dona_num + '</th>';
								output += ' <th>' + donalist.dona_username + '</th>';
								output += ' <th>' + dona_asnames[i] + '</th>';
								output += ' <th>' + donalist.dona_date + '</th>';
								output += ' <th>' + goodsnums[i] + '</th>';
								
								$.ajax({
									 type:'post',
									 data:{g_num:goodsnums[i]},
									 url:'${pageContext.request.contextPath}/mypage/getProductNameNPrice.do',
									 dataType:'json',
									 cache:false,
									 async:false,
									 timeout:30000,
									 success:function(data){
										 g_name = data.goods.g_name;
										 g_price = data.goods.g_price;
									 },
									 error:function(){
										 alert('네트워크 오류 발생');
									 }
								 });
								
								output += ' <th>' + g_name + '</th>';
								
								output += ' <th>' + goodsamounts[i] + '</th>';
								output += ' <th>' + donalist.dona_message + '</th>';
								output += ' <th>' + g_price + '</th>';
								output += ' </tr>';							 
								//문서 객체에 추가
								$('#output').append(output);
							}
						}else{
							var output = '<tr class="table-info">'; 
							output += ' <th scope="row">' + donalist.dona_num + '</th>';
							output += ' <th>' + donalist.dona_username + '</th>';
							output += ' <th>' + donalist.dona_asname + '</th>';
							output += ' <th>' + donalist.dona_date + '</th>';
							output += ' <th>' +donalist.dona_goodsnum + '</th>';
							
							
							$.ajax({
								 type:'post',
								 data:{g_num:donalist.dona_goodsnum},
								 url:'${pageContext.request.contextPath}/mypage/getProductNameNPrice.do',
								 dataType:'json',
								 cache:false,
								 async:false,
								 timeout:30000,
								 success:function(data){
									 g_name = data.goods.g_name;
									 g_price = data.goods.g_price;
								 },
								 error:function(){
									 alert('네트워크 오류 발생');
								 }
							 });

							output += ' <th>' + g_name + '</th>';
							
							output += ' <th>' + donalist.dona_goodsamount + '</th>';
							output += ' <th>' + donalist.dona_message + '</th>';
							output += ' <th>' + g_price + '</th>';
							output += ' </tr>';							 
							//문서 객체에 추가
							$('#output').append(output);
						}
					 });
				
		
			},
			error:function(data){
				alert('네트워크 오류 발생');
			}
		});
	}
});
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

	<h2 class="hdg">${command.m_id}'s  MYPAGE</h2>
	<div style="text-align:right;">
	<input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/note/receivedList.do'" value="받은 쪽지함">
	<input type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/detail.do?${user_id}'" value="${user_id}님 로그인">
	</div>
	<hr class="my-4">
	

	<div id='calendar'></div>

	<br>
	<br>
	<br>

	<h3 class="hdg">나의 후원내역</h3>
	<input type="hidden">

	<div class="col-md-12">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">후원자 번호</th>
					<th scope="col">후원자 닉네임</th>
					<th scope="col">후원한 보호소</th>
					<th scope="col">후원 날짜</th>
					<th scope="col">후원 상품번호</th>
					<th scope="col">후원 상품명</th>
					<th scope="col">후원 상품 수량</th>
					<th scope="col">후원 메시지</th>
					<th scope="col">후원 금액</th>
				</tr>
			</thead>
			<tbody id="output">
			</tbody>

		</table>

	</div>

	
		<h3 class="hdg">임시 보호자 집에 맡기기</h3>
		<div class="col-md-12">
			<table class="table table-hover">
				<thead>
				<tr>
						<th scope="col"> 임시보호자</th> <th> 시작일</th> <th>마감일</th> 
				</tr>
				</thead>
				<tbody>
		 		<c:forEach var="bo" items="${boList}">
					<tr class="table-info">					
						<td scope="row">${bo.id}</td>
						<td> ${bo.bo_date_start} ${bo.bo_start_hour}시 ${bo.bo_start_min}분 </td><td> ${bo.bo_date_end} ${bo.bo_end_hour}시 ${bo.bo_end_min}분</td>	
							
					</tr>
					</c:forEach> 

				</tbody>
			</table>
		</div>



	
		<h3 class="hdg">임시 보호자 집으로 부르기</h3>
		<div class="col-md-12">
		<table class="table table-hover">
			<thead>
				<tr>
				<th scope="col">기간</th>
				<th>예약 현황</th>
				<th></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="callHome" items="${callList}">
					<tr class="table-info">
					<td>${callHome.call_start}~${callHome.call_end}</td>
					<td>신청 중</td>
					<td><button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/ap/apCalldetail.do?call_num=${callHome.call_num}'" >GO</button></td>		
					</tr>
			</c:forEach>
		<%-- 	 <c:forEach var="complete" items="${callList2}">
					<tr class="table-info">
					<td>{complete.bo_call_date_start} ~ {complete.bo_call_date_end}</td>
					<td>신청 완료 </td>
					<td>임시보호자 ${complete.bo_call_id} 님이 신청을 수락했습니다.</td>		
					</tr>
			</c:forEach>  --%>
			</tbody>
		</table>			
		</div>

	
<c:if test="${user_auth == 5}">
	<h3 class="hdg">임시보호자 집에 맡기기</h3>
		<div class="col-md-12">
		<table class="table table-hover">
			<thead>
				<tr>
				<th scope="col"> 신청자 아이디</th> <th> 시작일</th> <th>마감일</th> 
				</tr>
			</thead>
			<tbody>
			<c:forEach var="boho" items="${bohoCallList}">
					<tr class="table-info">
					<td> ${boho.bo_id}</td> <td> ${boho.bo_date_start} ${boho.bo_start_hour}시 ${boho.bo_start_min}분</td> <td> ${boho.bo_date_end} ${boho.bo_end_hour}시 ${boho.bo_end_min}분</td> 
					
				</c:forEach>
			</tbody>
		</table>			
		</div>
		
	<h3 class="hdg">임시보호자 집으로 부르기</h3>
	<div class="col-md-12" >
		<table class="table table-hover">
			<thead>
				<tr>
				<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="callHome" items="${callList}">
					<tr>				
					</tr>
			</c:forEach>
			</tbody>
		</table>			
		</div>
		</c:if>
		
		</div>


