<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/apCall.js"></script>
<!-- jQuery -->
<script>
	$(document).ready(function() {
		var date = new Date();

		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;

		var today = year + "-" + month + "-" + day;
		$("#call_re_date").attr("value", "0000-00-00");
		
	});

</script>
<div class="container">
	<form:form commandName="command" action="write.do" id="call_form" enctype="multipart/form-data">
		<form:hidden path="call_num"/>
		<form:errors element="div" cssClass="error-color"/>	
			<table class="table table-hover" style="margin:20px 0 0 0;">
				<tr>
					<th>이름</th>
					<td>
						<form:input path="call_name" class="form-control" readonly="true"/>
						<form:errors path="call_name" cssClass="error-color"/>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<form:input path="call_phone" class="form-control"/>
						<form:errors path="call_phone" cssClass="error-color"/>
					</td>
				</tr>
				<tr>
					<th>기간</th>
					<td>
						시작 날짜 : <form:input type="date" path="call_start"  min="2013-01-01"/>
								<form:errors path="call_start" cssClass="error-color"/>
						마침 날짜 : <input type="date" name="call_end" id="call_end" min="2013-01-01">
								<form:errors path="call_end" cssClass="error-color"/>
						<script>
							var today = new Date().toISOString().split('T')[0];
							document.getElementsByName("call_start")[0].setAttribute('min', today);
							document.getElementsByName("call_end")[0].setAttribute('min', today);
						</script> 
					</td>
				</tr>
				<tr>
					<th>반려동물 무게</th>
					<td>
						<input type="radio" value="1" id="call_wei" name="call_wei">15kg미만
						<form:errors path="call_wei" cssClass="error-color"/>
						<br>
						<input type="radio" value="2" id="call_wei" name="call_wei">15kg이상
						<form:errors path="call_wei" cssClass="error-color"/>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">사전 만남 신청 여부</th>
					<td colspan="2">
						<input type="radio" value="1" name="call_re" id="call_re">신청함 /
						<form:errors path="call_re" cssClass="error-color"/>
						만남 가능 날짜 : <input type="date" name="call_re_date" id="call_re_date" min="2013-01-01">
						<script>
							var today = new Date().toISOString().split('T')[0];
							document.getElementsByName("call_re_date")[0].setAttribute('min', today);
						</script>
						만남 가능 시간 : <select class="form-control" style="width:80px;" name="call_re_hour" id="call_re_hour">
										<option value="0">0시</option>
										<option value="1">1시</option>
										<option value="2">2시</option>
									</select> 시
									<select class="form-control" style="width:80px;" name="call_re_min" id="call_re_min">
										<option value="00">00분</option>
										<option value="15">15분</option>
										<option value="30">30분</option>
									</select> 분
						<br>
						<input type="radio" value="2" name="call_re" id="call_re">신청안함
						<form:errors path="call_re" cssClass="error-color"/>
						
					</td>
				</tr>
				<tr>
					<th style="text-align: center;" rowspan="2">나의 반려동물 소개</th>
					<td><textarea rows="10" cols="100" name="call_intro"></textarea>
						<form:errors path="call_intro" cssClass="error-color"/></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" class="btn btn-warning" style="float:center; margin:0 0 10px 0;" value="등록하기">
						<input type="button" class="btn btn-secondary" style="float:center; margin:0 0 10px 0;" value="목록" onclick="location.href='apCallList.do'">
					</td>
				</tr>
			</table>
	</form:form>
</div>







