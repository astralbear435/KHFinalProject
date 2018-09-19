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
	  ajax2();

 
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
 
 function ajax2(){
	 var dona_asname = $('#sname').text();
	  $.ajax({
		 type:'post',
		 data:{s_id:'${user_id}', dona_asname:dona_asname},//보호소이름 출력
		 url:'${pageContext.request.contextPath}/mypage/donaShelterPage.do',
		 dataType:'json',
		 cache:false,
		 timeout:30000,
		 success:function(data){
			 var list = data.list;
			 	
			 	console.log(list);
			 	
					 $(list).each(function(index,donaS){
						 		var output = '<tr class="table-info">'; 
								output += ' <th scope="row">' + donaS.dona_num + '</th>';
								output += ' <th>' + donaS.dona_username+ donaS.dona_id + '</th>';
								output += ' <th>' + donaS.dona_date + '</th>';
								
								var dona_asnames = donaS.dona_asname.split(',');
								var dona_num;
								for(var i=0;i<dona_asnames.length;i++){
									if(dona_asname == dona_asnames[i]){
										dona_num = i;
										console.log('dona_num : ' + i);
									}
								}								
								var goodsnums = donaS.dona_goodsnum.split(',');
								
								var g_name,g_price;
								$.ajax({
									 type:'post',
									 data:{g_num:goodsnums[dona_num]},
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

								output += ' <th>' + goodsnums[dona_num] + '</th>';
								
								output += ' <th>' + g_name + '</th>';
								
								var goodsamounts = donaS.dona_goodsamount.split(',');
								
								output += ' <th>' + goodsamounts[dona_num] + '</th>';
								output += ' <th>' + donaS.dona_message + '</th>';
								output += ' <th>' + g_price + '</th>';
								output += ' </tr>';	
						 
								//문서 객체에 추가
								$('#output').append(output);
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


	
		<h2 class="hdg"><span id="sname">${shelter.s_name}</span> MYPAGE</h2>
<div style="text-align:right;">
<input type="button" class="btn btn-outline-primary" onclick="location.href='${pageContext.request.contextPath}/shelter/shelterDetail.do?id=${user_id}'" value="${shelter.s_name} 페이지 바로 가기">
	<input type="button" class="btn btn-outline-primary" onclick="location.href='${pageContext.request.contextPath}/member/detail.do?${user_id}'" value="${user_id}님 로그인">
</div>
	<hr class="my-4">
	
	<div id='calendar'></div>
	
<div style="height:35px;"></div>
	
<div class="col-md-12">
<h3 class="hdg">후원받은 목록</h3>
							<table class="table table-hover">
							  <thead>
							    <tr>
							      <th scope="col">후원자 번호</th>
							      <th scope="col">후원자 닉네임</th>
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
						
						
</div>









