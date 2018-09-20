<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- DataTables -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">

<!-- Content Header (Page header) -->

<section class="content-header">
	<h1>
		회원 관리 <small>일반 회원</small> 
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>보호소</a></li>
		<li class="active">목록</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">보호소 리스트</h3> 
		</div>
		<!-- /.box-header -->
		<div class="box-body">
		<c:if test="${count == 0}">
	<div class="align-center">등록된 회원이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
			<table id="shelterList" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>보호소 번호</th>
						<th>보호소 아이디</th>
						<th>보호소 이름</th>
						<th>보호소 승인</th>
						<th> <div><input type="checkbox" id="allCheck"></div></th> 
					</tr>
				</thead>
				<tbody>
				<c:forEach var="shelter" items="${list}">
					<tr>
						<td>${snum=snum+1}</td>
						<td>${shelter.s_id}</td>
						<td>${shelter.s_name}</td>
						<td>
						<input type="hidden" value="${shelter.auth}">
						<c:if test="${shelter.auth==3}"><select class="agreement" id="${shelter.s_id}" name="agree_${shelter.s_id}"><option selected="selected" value="3">비승인</option><option value="4">승인</option></select></c:if>
						<c:if test="${shelter.auth==4}"><select class="agreement" id="${shelter.s_id}" name="agree_${shelter.s_id}"><option value="3">비승인</option><option value="4" selected="selected">승인</option></select></c:if>
						</td> 
						<td><input type="checkbox" name="checkNum" id="${member.m_num}"></td> 
					</tr>
				</c:forEach>
				
				</tbody>
				<tfoot>
					<tr>
						<th>보호소 번호</th>
						<th>보호소 아이디</th>
						<th>보호소 이름</th>
						<th>보호소 승인</th>
						<th>X</th>
					</tr>
				</tfoot>
			</table>
			</c:if>
		</div>
		<!-- /.box-body -->
		<button id="menu_delete" class="btn btn-info pull-right">삭제</button>
	</div>

</section>
<!-- /.content -->
<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/admin/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script
	src="${pageContext.request.contextPath}/resources/admin/dist/js/demo.js"></script>
<!-- page script -->
<script>
$(function () {
  $('#shelterList').DataTable({
    'paging'      : true,
    'lengthChange': true,
    'searching'   : true,
    'ordering'    : true,
    'info'        : true,
    'autoWidth'   : false
  })
})  
</script>
<script	src="${pageContext.request.contextPath}/resources/admin/js/shelteragree.js"></script>