<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/recruit.js"></script>
<style>
ul{
   list-style:none;
   }   
</style>

<div class="container">

	<div class="w3l-heading">
		<h3>����Ȱ�� ���� �ȳ�</h3>
	</div>

	<p>
		����� ��ȣ ���Ϳ����� ������ ������ ���� �����ںе��� ������ ��ٸ��ϴ�. <br> �ڿ����� Ȱ���ð� : ���� 1��
		~ ���� 5��(1�� : 1��~3�� / 2�� : 3~5��, 1,2�� ���� ���� ����) <br> ����Ȱ�� : ���л�
		�̻���� ��û �����մϴ�. ��ü���縦 ���Ͻô� ��� ������ȣ����(042-825-1118)�� ���� ��û�Ͻñ� �ٶ��ϴ�. (��,
		��ü����� �ֿϵ������� ������ �Ǵ� ���� ����ڸ� ����)<br> �����û : ������ 15����(0��) ~ 2��������
		������ȣ���� Ȩ���������� ��û �����մϴ�. <br> - 1�� �����ο��� 5�� �����̸�, �ָ� �� �����Ͽ��� ��û�ڰ�
		���� ��û�� ����� �� ������ ���عٶ��ϴ�. <br> Ȯ�μ� �߱� :
		1365�ڿ���������(www.1365.go.kr)����Ʈ�� ���� ����ð� Ȯ�� �� ��°��� <br> 1365�ڿ���������
		ȸ������ : ȸ������ �� �е鿡 ���� ����ð� �Է��� �����ϸ�, ����Ȱ�� �� ���߻� �� ���غ����� �����ϹǷ� �ݵ�� ����
		�����ϼž� �մϴ�. <br>
	</p>
 
<c:if test="${!empty user_id}">
<div style="text-align: right;">
	<input type="button" value="����Ȱ�� ���� �� ����"
		class="btn btn-primary btn-lg"
		onclick="location.href='${pageContext.request.contextPath}/recruit/recruitWrite.do'">
</div>
</c:if>
	<form action="recruitList.do" id="search_form" method="get">
		<ul class="search">
			<li><select name="keyfield">
					<option value="r_title">����Ȱ�� ����</option>
					<option value="r_id">ID</option>
					<option value="r_content">����</option>
			</select></li>
			<li><input type="text" name="keyword" id="keyword"></li>
			<li><input type="submit" value="�˻�"></li>
		</ul>
	</form>

	<hr class="my-4">
	<script type="text/javascript">
function showPopup(r_num){
	window.open("${pageContext.request.contextPath}/volunteer/volunteerWrite2.do?r_num="+r_num,"����Ȱ�� ��û","width=400, height=800, left=100, top=50");
}

</script>


	<div class="row ">
		<c:forEach var="recruit" items="${list}">
			<div class="card border-primary col-sm-3.5 agile-choose-grid"
				style="max-width: 45rem; margin:10px;">
				<div class="card-header choose-info">${recruit.r_id}</div>
				<div class="card-body choose-info">
					<c:if
						test="${fn:endsWith(recruit.r_filename,'.jpg') ||
					fn:endsWith(recruit.r_filename,'.JPG') ||
					fn:endsWith(recruit.r_filename,'.gif') ||
					fn:endsWith(recruit.r_filename,'.GIF') ||
					fn:endsWith(recruit.r_filename,'.png') ||
					fn:endsWith(recruit.r_filename,'.PNG')}">
						<div class="align-center">
							<img src="imageView.do?r_num=${recruit.r_num}"
								style="max-width: 300px">
						</div>
					</c:if>

					<h4 class="card-title">
						<p>${recruit.r_title}</p>
					</h4>
					<c:if test="${recruit.r_status==2}">
						<p class="card-text">${recruit.r_start_date}~${recruit.r_end_date}</p>
					</c:if>
					<c:if test="${recruit.r_status==1}">
						<p class="card-text">��� ���� ��</p>
					</c:if>


				</div>
				<div class="col-md-6">


					<c:if test="${!empty user_id}">
						<input type="button" value="����Ȱ�� ��û�ϱ�" class="btn btn-warning" onclick="showPopup(${recruit.r_num});">
					</c:if>
					<c:if test="${empty user_id}">
						<input type="button" value="����Ȱ�� ��û�ϱ�" class="btn btn-warning"
							onclick="alert('�α����� �ʿ��� �����Դϴ�.');">
					</c:if>
					<c:if test="${!empty user_id && user_id == recruit.r_id}">		
						<input type="button" value="����" class="btn btn-success"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitUpdate.do?r_num=${recruit.r_num}'">
				
						<input type="button" value="����" class="btn btn-danger"
							onclick="location.href='${pageContext.request.contextPath}/recruit/recruitDelete.do?r_num=${recruit.r_num}'">
					</c:if>
					
				</div>
			</div>
		</c:forEach>
	</div>

</div>










