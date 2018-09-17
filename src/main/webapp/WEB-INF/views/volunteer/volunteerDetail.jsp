<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

	//ajax�� �����ؾ� ��
	function deleteVNum(){
		var v_num = $('#v_num').text();
		
		if(confirm("������ �����Ͻðڽ��ϱ�?")){
			location.replace('volunteer/volunteerDelete.do?v_num=' + v_num);
		}else{
			return;
		}
		
		self.close();
		opener.document.location.reload();
	}
	
	

</script>
<div class="container">
	<div id="v_num" style="display: none;">${volunteer.v_num}</div>	
	<p>${volunteer.r_title}</p>

	<h5>����Ȱ�� ���� �ȳ�</h5>
	<h6>${volunteer.r_content}</h6>
	
	<h5>���� �ο�</h5>
	
	<h6>���� ${volunteer.r_people} ��</h6>
	
	<c:if test="${volunteer.r_status ==2}">
	<p class="card-text">���� ��¥  : ${volunteer.r_start_date}~${volunteer.r_end_date}</p>
	</c:if>
	<c:if test="${volunteer.r_status ==1}">
		<p class="card-text">��� ���� ��</p>
	</c:if>
	
	<div id="before">	
	����Ȱ�� ��û ��¥ : ${volunteer.v_date} 	
	<c:if test="${volunteer.v_status==1}">
	 ����Ȱ�� ��û �ð� : 1~3��
	</c:if>
	<c:if test="${volunteer.v_status==2}">
	 ����Ȱ�� ��û �ð� : 3~5��
	</c:if>

	</div>
	<div id="output"></div>	
	
<div id="before2">
<input type="button" value="����Ȱ�� ����  ����" class="modify-btn btn-success" id="modify-btn" onclick="location.href='${pageContext.request.contextPath}/volunteer/volunteerUpdate.do?v_num=${volunteer.v_num}'">
</div>
	
	<input type="button" value="����Ȱ�� ���� ���" class="btn btn-success" id="delete"  onclick="deleteVNum();">
				
</div>