<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- DataTables -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
	
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		공지사항 <small>목록</small>
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
						<th><input type="checkbox" id="allCheck"></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="notice" items="${list}">
					<tr>
						<td><a href="${pageContext.request.contextPath}/admin/notice/noticeModify.do?n_idx=${notice.n_idx}">${notice.n_idx}</a></td> 
						<td><a href="${pageContext.request.contextPath}/admin/notice/noticeDetail.do?n_idx=${notice.n_idx}" onclick="window.open(this.href,'상세보기','width=600,height=700');return false;">${notice.n_subject}</a></td>
						<td>${notice.n_hit}</td> 
						<td>${notice.n_id}</td>
						<td><input type="checkbox" name=checkNum id="${notice.n_idx}" class="minimal"></td>
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
		<button id="notice_delete" class="btn btn-info pull-right">삭제</button>
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
  $('#example1').DataTable({
    'paging'      : true,
    'lengthChange': true,
    'searching'   : true,
    'ordering'    : true,
    'info'        : true,
    'autoWidth'   : false
  })
})
$(document).ready(function(){
    //최상단 체크박스 클릭
    $("#allCheck").click(function(){
        //클릭되었으면
        if($("#allCheck").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=checkNum]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=checkNum]").prop("checked",false);
        }
    });
})
</script>