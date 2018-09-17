<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/recruit.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>

<div class="container jumbotron">

 <hr class="my-4">
	<form:form commandName="recruit" action="recruitUpdate.do"
		id="recruitModify_form" enctype="multipart/form-data">
		<form:hidden path="r_id" />
		<form:hidden path="r_num" />
		<form:hidden path="r_filename" />
		<form:errors element="div" cssClass="error-color" />
		
		<table>
		<tr>
		<td><label for="r_status">���� �Ⱓ ����</label></td>
		<td><div class="form-check-radio">
					<label class="form-check-label"> <input type="radio"
						name="r_status" id="r_status1" class="form-check-input" value="1">���
						����<span class="form-check-sign"></span>
					</label>
				</div>
		</td>
		<td>
		<div class="form-check-radio">
					<label class="form-check-label"> <input type="radio"
						name="r_status" id="r_status2" class="form-check-input" value="2"
						checked> ��¥ ���� <span class="form-check-sign"></span>
					</label>
		</div> <form:errors path="r_status" cssClass="error-color" />
		</td>
		<td>
		 <form:input type="date" path="r_start_date" class="form-control"/> <form:errors path="r_start_date"
					cssClass="error-color" />
		</td>
		<td>~</td>
		<td>
		 <form:input type="date" path="r_end_date" class="form-control"/> <form:errors path="r_end_date"
					cssClass="error-color" />
		</td>
		</tr>
		<tr>
		<td> <label for="r_people">���� �ο�</label>
		</td>
		<td> <form:input path="r_people" class="form-control"/> <form:errors path="r_people"
					cssClass="error-color" />
		</td>
		</tr>
		
		</table>
		<label for="r_title">����</label> <form:input path="r_title" class="form-control"/>
				<form:errors path="r_title" cssClass="error-color" />
				
				<div style="height:20px;"></div>	
				
		<form:textarea path="r_content" class="form-control" rows="15"/> <form:errors path="r_content"
					cssClass="error-color" />	
							
		<div style="height:20px;"></div>	
			
		<label for="r_upload">���Ͼ��ε�</label> <input type="file" name="r_upload" id="r_upload" /> 
				<c:if test="${!empty recruit.r_filename}">
					<span>(${recruit.r_filename})������ ��ϵǾ� �ֽ��ϴ�. �ٽ� ���ε��ϸ� ���� ������ �����˴ϴ�.</span>
				</c:if>
				
		<div style="text-align:center;">
			<input type="submit" class="btn btn-primary btn-lg"  value="����"> 
			<input type="button" class="btn btn-primary btn-lg" 
				value="���" onclick="location.href='recruitList.do'">
		</div>
	</form:form>

</div>