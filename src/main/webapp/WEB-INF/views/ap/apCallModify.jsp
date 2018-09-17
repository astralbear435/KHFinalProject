<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ap/apCall.js"></script>
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
		$("#call_re_date").attr("value", today);
	});

	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("call_re_date")[0].setAttribute('min', today);
</script>
<div class="container">
	<form:form commandName="command" action="update.do" id="call_form" enctype="multipart/form-data">
		<form:hidden path="call_num"/>
		<form:errors element="div" cssClass="error-color"/>	
			<table class="table table-hover" style="margin:50px 0 0 0;">
				<tr>
					<th style="text-align: center;">이름</th>
					<td>
						<form:input path="call_name" readonly="true"/>
						<form:errors path="call_name" cssClass="error-color"/>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">연락처</th>
					<td>
						<form:input path="call_phone"/>
						<form:errors path="call_phone" cssClass="error-color"/>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">기간</th>
					<td>
						시작 날짜 : <form:input type="date" path="call_start" min="2013-01-01"/>
								<form:errors path="call_start" cssClass="error-color"/>
						마침 날짜 : <form:input type="date" path="call_end" min="2013-01-01"/>
								<form:errors path="call_end" cssClass="error-color"/>

					</td>
				</tr>
				<tr>
					<th style="text-align: center;">반려동물 무게</th>
					<td>
					 	<input type="radio" id="call_wei" name="call_wei" value="1" <c:if test="${command.call_wei==1}">checked</c:if>/>15kg미만
					 	<br>
					 	<input type="radio"  id="call_wei" name="call_wei"value="2" <c:if test="${command.call_wei==2}">checked</c:if>/>15kg이상									
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">사전 만남 신청 여부</th>
					<td colspan="2">
						<input type="radio" value="1" name="call_re" id="call_re" <c:if test="${command.call_re==1}">checked</c:if>>신청함 /
						<form:errors path="call_re" cssClass="error-color"/>
						<div id="call_re_date">
						만남 가능 날짜 : <form:input type="date" path="call_re_date" min="2013-01-01"/>
						만남 가능 시간 : <select class="form-control" style="width:80px;" id="call_re_hour" name="call_re_hour">
										<option value="0" <c:if test="${command.call_re_hour==0}">selected</c:if>>0시</option>
										<option value="1"<c:if test="${command.call_re_hour==1}">selected</c:if>>1시</option>
										<option value="2"<c:if test="${command.call_re_hour==2}">selected</c:if>>2시</option>
									</select> 시
									<select class="form-control" style="width:80px;" id="call_re_min" name="call_re_min">
										<option value="00" <c:if test="${command.call_re_min==00}">selected</c:if>>00분</option>
										<option value="15" <c:if test="${command.call_re_min==15}">selected</c:if>>15분</option>
										<option value="30" <c:if test="${command.call_re_min==30}">selected</c:if>>30분</option>
									</select> 분
						</div>
						<br>
						<input type="radio" value="2" name="call_re" id="call_re" <c:if test="${command.call_re==2}">checked</c:if>>신청안함
						<form:errors path="call_re" cssClass="error-color"/>
						
					</td>
				</tr>
				<tr>
					<th style="text-align: center;" rowspan="2">나의 반려동물 소개</th>
					<td><form:textarea path="call_intro" rows="10" cols="100" />
						<form:errors path="call_intro" cssClass="error-color"/></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" class="btn btn-warning" value="등록하기">
						<input type="button" class="btn btn-secondary" value="목록" onclick="location.href='apCallList.do'">
					</td>
				</tr>
			</table>
	</form:form>
</div>







