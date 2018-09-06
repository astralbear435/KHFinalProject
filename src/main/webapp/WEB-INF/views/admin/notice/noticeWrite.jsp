<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/dist/css/skins/_all-skins.min.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Page Header <small>Optional description</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>공지사항</a></li>
		<li class="active">작성</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<div class="box box-info">
		<div class="box-header with-border">
			<h3 class="box-title">공지사항 작성</h3>
		</div>
		<div class="box-body">
			<form:form commandName="command" action="write.do" id="register_form"
				enctype="multipart/form-data">
				<form:hidden path="n_id" />
				<form:errors element="div" cssClass="error-color" />
				<ul>
					<li><label for="n_subject">제목</label> <form:input path="n_subject" />
						<form:errors path="n_subject" cssClass="error-color" /></li>
					<li><div class="box-body pad">
					<form:textarea id="editor1" path="n_content"/>
							
					
					</div>
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="전송"> <input type="button"
						value="목록" onclick="location.href='list.do'">
				</div>
			</form:form>
		</div>
	</div>

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
<!-- Bootstrap WYSIHTML5 -->
<script
	src="${pageContext.request.contextPath}/resources/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script>
  $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace('editor1')
    //bootstrap WYSIHTML5 - text editor
    $('.textarea').wysihtml5()
  })
</script>