<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.fileDrop {
	width: 600px;
	height: 200px;
	border: 1px dotted blue;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin/js/mainpage.js"></script>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		메인페이지 셋업 <small>메인 이미지 변경</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>pageSetup</a></li>
		<li class="active">Main Image</li>
	</ol>
</section>

<!-- Main content -->
<section czlass="content container-fluid">
	<div class="box box-solid">
		<div class="box-header with-border">
			<i class="fa fa-text-width"></i>

			<h3 class="box-title">파일 이미지 등록</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<!-- 파일을 업로드할 영역 -->
			<div class="fileDrop"></div>
			<!-- 업로드된 파일 목록 -->
			<div class="uploadedList"></div>
		</div>
		<!-- /.box-body -->
	</div>
</section>
<!-- /.content -->