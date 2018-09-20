<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 보기</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin/dist/css/skins/skin-blue.min.css">
</head>
<body>
<div class="col-md-6">
	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">${notice.n_subject}</h3> <small> ${notice.n_id} </small><div class="text-right"><small>${notice.n_last_modified}</small></div>
		</div>
		<!-- /.box-header --> 
		<div class="box-body">
		${notice.n_content}
		</div>
	</div>
	</div>
</body>

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
</html>