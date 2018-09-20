<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.fileDrop {
	border: 1px dotted blue;;
	height: 400px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/admin/js/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/admin/js/mainpage.js"></script>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		메인페이지 셋업 <small>메인 이미지 변경</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>메인 이미지 설정</a></li>
		<li class="active">이미지 설정</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
<div class="col-md-6">
	<div class="box box-info">
		<div class="box box-solid">
			<div class="box-header with-border">
				<i class="fa fa-text-width"></i>
				<h3 class="box-title">이미지 등록</h3>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<!-- 파일을 업로드할 영역 -->
				<div class="fileDrop">
					<!-- 업로드된 파일 목록 -->
					<div class="uploadedList">
						<c:if test="${count>0 }">
							<c:forEach var="image" items="${list}">
								<div class='col-md-3 col-sm-4'>
									<a href='displayFile.do?fileName=mainImage/${image.main_img_name}'>
										<img src='displayFile.do?fileName=mainImage/${image.main_s_img_name}'> 
									</a><br> <span data-src="${image.main_s_img_name}"><i class='fa fa-fw fa-close'></i></span>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>

			</div>
			<!-- /.box-body -->
		</div>
	</div>
</div>
</section>
<!-- /.content -->