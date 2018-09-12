<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- DataTables -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/plugins/iCheck/all.css">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Page Header <small>Optional description</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>공지사항</a></li>
		<li class="active">목록</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">공지사항 리스트</h3> 
		</div>
		<!-- /.box-header -->
		<div class="box-body">
		<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
			<table id="example1" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>글 제목</th>
						<th>조회수</th>
						<th>작성자</th>
						<th>X</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="notice" items="${list}">
					<tr>
						<td>${notice.n_idx}</td>
						<td>${notice.n_subject}</td>
						<td>${notice.n_hit}</td>
						<td>${notice.n_id}</td>
						<td><div class="icheckbox_minimal-blue" aria-checked="false" aria-disabled="false" style="position: relative;"><input type="checkbox" id="${notice.n_idx}" class="minimal" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div></td>
					</tr>
				</c:forEach>
				
				</tbody>
				<tfoot>
					<tr>
						<th>글 번호</th>
						<th>글 제목</th>
						<th>조회수</th>
						<th>작성자</th>
						<th>X</th>
					</tr>
				</tfoot>
			</table>
			</c:if>
		</div>
		<!-- /.box-body -->
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
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath}/resources/admin/plugins/iCheck/icheck.min.js"></script>	
	
<!-- page script -->
<script>
$(function () {
  $('#example1').DataTable({
    'paging'      : true,
    'lengthChange': true,
    'searching'   : true,
    'ordering'    : true,
    'info'        : true,
    'autoWidth'   : false
  })
})
 //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
</script>