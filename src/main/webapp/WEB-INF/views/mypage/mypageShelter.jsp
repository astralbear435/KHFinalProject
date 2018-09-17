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
		 	var recruit = data.recruit; //����� ��ȣ�� ����Ȱ�� ��û ��Ȳ�� �����ִ� Ķ����
		 	
		 	console.log(recruit);
					
				//����� ����Ȱ�� ���� ����Ʈ
				if(recruit.length>0){ 
					for(var i = 0; i<recruit.length; i++){
		 	 			event.push({ 
		 	 				title: '[����Ȱ��]' + recruit[i].v_id +','+ recruit[i].v_status
		 					,start: recruit[i].v_date
		 	 			});
		 	 			console.log(event[i].title,event[i].start);
		 			}
				} 
			
		 	
			//Ķ����
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
			
				      editable: false,//ȭ�鿡�� ���� ��¥ �̵� �Ұ���  
				      eventLimit: true,				
					events :  event,
					eventColor: '#378006',	
				  eventClick: function(event) {
					 	var title = $(this).find('.fc-title').text();
					 	var sub_title = title.split(',');
					 	//�����ؾ���
						window.open("${pageContext.request.contextPath}/volunteer/volunteerDetail.do?v_num="+sub_title[1],"volunteer","width=400, height=300, left=100, top=50");

						    }
						  	  				
					});
	 },
	 
	 error:function(){
		 alert('��Ʈ��ũ ���� �߻�');
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
�Ŀ�����
<div class="col-md-12">
<table>
<tr>
<td scope="col">�Ŀ���ȣ</td>
<td scope="col">�Ŀ���</td>
<td scope="col">�Ŀ�����</td>
<td scope="col">�Ŀ��ݾ�</td>
</tr>
<td></td>
<td></td>
<td></td>
<td></td>

</table>
</div>

�츮 ��ȣ�Ҹ� �Ŀ����ֽ� �е��� �Ŀ��޽����Դϴ�.
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



