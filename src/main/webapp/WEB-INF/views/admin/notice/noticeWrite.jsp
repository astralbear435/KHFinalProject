<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/dist/css/skins/_all-skins.min.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<!-- Content Header (Page header) -->

<section class="content-header">
	<h1>
		공지사항 <small> 공지사항 작성</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>공지사항</a></li>
		<li class="active">작성</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<form:form commandName="command" action="noticeWrite.do"
		id="register_form" enctype="multipart/form-data">
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">
					<label for="n_subject">제목</label>
					<form:input path="n_subject" />
					<form:errors path="n_subject" cssClass="error-color" />
				</h3>
			</div>
			<div class="box-body">

				<form:hidden path="n_id" />
				<form:errors element="div" cssClass="error-color" />


				<div class="box-body pad">
					<form:textarea path="n_content" onKeyup="var m=50;var s=this.scrollHeight;if(s>=m)this.style.pixelHeight=s+4"></form:textarea>
					

				</div>

				<div class="align-center">
					<input type="submit" value="전송"> <input type="button"
						value="목록" onclick="location.href='noticeList.do'">
				</div>

			</div>
		</div>
	</form:form>
</section>
<!-- /.content -->


<!-- jQuery 3 -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script
	src="${pageContext.request.contextPath}/resources/admin/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script
	src="${pageContext.request.contextPath}/resources/admin/dist/js/demo.js"></script>
<!-- CK Editor -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/ckeditor/ckeditor.js"></script>
<script>
CKEDITOR.replace( 'n_content', {
    extraPlugins: 'easyimage',
    cloudServices_tokenUrl: 'https://34815.cke-cs.com/token/dev/iFqU8CDd4fi9zhRMafJediRsggcVol5iYV6xOOkwYiwN00nlKyRC5YN9xDzv',
    cloudServices_uploadUrl: 'https://34815.cke-cs.com/easyimage/upload/'
} );
</script>
