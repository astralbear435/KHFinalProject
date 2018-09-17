<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/recruit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<div class="container jumbotron">

 <hr class="my-4">	
	<!-- 1. �����ο� ����Ʈ�� 0���� ����� 
	2. ���� ��û�� ���� �ٸ� ���� �����ֱ� ����
	-->
	<!--�ѱ� �ּ�  -->
	<form:form commandName="recruit" action="recruitWrite.do" id="recruitWrite_form" enctype="multipart/form-data">
		<form:hidden path="r_id" />
		<form:errors element="div" cssClass="error-color" />
		<table>
			<tr>
				<td><label>���� �Ⱓ ����</label></td>
				<td>
					<div class="form-check-radio">
						<label class="form-check-label"> <input type="radio"
							name="r_status" id="r_status1" class="form-check-input"
							value="1">��� ����<span class="form-check-sign"></span>
						</label>
					</div>
					</td>
				<td>
					<div class="form-check-radio">
						<label class="form-check-label"> <input type="radio"
							name="r_status" id="r_status2" class="form-check-input"
							value="2" checked>��¥ ���� <span class="form-check-sign"></span>
						</label>
					</div>
				</td>
				<td>
				<input type="date" name="r_start_date" id="r_start_date" class="form-control">
				</td>
				<td> ~ </td>
				<td>
				<input type="date" name="r_end_date" id="r_end_date" class="form-control">
				</td>
			</tr>			
			<tr>
			<td><label>(����)���� �ο� </label></td>
				<td><input type="number" name="r_people" id="r_people" min="0" class="form-control">
				</td>
			</tr>	
			</table>	
			 <div style="height:20px;"></div>	
				<label for="title">����</label>
				<input type="text" name="r_title" id="r_title" maxlength="50" size="10" class="form-control" >			
			<div style="height:20px;"></div>	
				
			<textarea rows="15" cols="30" name="r_content" id="r_content" class="form-control" placeholder="����Ȱ���� ���� �ȳ����� ���ּ���."></textarea>
			<div style="height:5px;"></div>
				<label>�̹��� ÷��</label>
				<input type="file" name="r_upload" id="r_upload">	

		<div style="text-align:center;">
			<input type="submit" value="����" class="btn btn-primary btn-lg"> 
			<input type="button" value="���" class="btn btn-primary btn-lg" onclick="location.href='recruitList.do'">
		</div>
	</form:form>
</div>