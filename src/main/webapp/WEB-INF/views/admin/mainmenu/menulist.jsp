<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
selectbox#select_id {
	position: relative;
	width: 200px; /* 너비설정 */
	z-index: 1;
}

</style>

<section class="content-header">
	<h1>
		메인 메뉴 <small> 메뉴 편집</small>
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
			<h3 class="box-title">메뉴 목록</h3>
		</div>
		<div class="box-body">

			<div class="input-group">
				<select id="select_id" name=s1 size=15>
					<option selected="selected" value=0>-메뉴 추가-</option>
					<c:if test="${count>0}">
						<c:forEach var="menu" items="${list}">
							<c:if test="${menu.menu_depth==1}">
								<option value="${menu.menu_num}">${menu.menu_name}</option>
							</c:if>
							<c:if test="${menu.menu_depth>1}"> 
								<option value="${menu.menu_num}"> ㅡ ${menu.menu_name}</option>  
							</c:if>
						</c:forEach>
					</c:if>
				</select>
			</div>

			<br>

			<div class="input-group">
				<input type="text" class="form-control"> <span
					class="input-group-addon">.00</span>
			</div>
			<br>

			<div class="input-group">
				<span class="input-group-addon">$</span> <input type="text"
					class="form-control"> <span class="input-group-addon">.00</span>
			</div>

			<h4>With icons</h4>

			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
				<input type="email" class="form-control" placeholder="Email">
			</div>
			<br>

			<div class="input-group">
				<input type="text" class="form-control"> <span
					class="input-group-addon"><i class="fa fa-check"></i></span>
			</div>
			<br>

			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
				<input type="text" class="form-control"> <span
					class="input-group-addon"><i class="fa fa-ambulance"></i></span>
			</div>

			<h4>With checkbox and radio inputs</h4>

			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<span class="input-group-addon"> <input type="checkbox">
						</span> <input type="text" class="form-control">
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
				<div class="col-lg-6">
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio">
						</span> <input type="text" class="form-control">
					</div>
					<!-- /input-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->

			<h4>With buttons</h4>

			<p class="margin">
				Large:
				<code>.input-group.input-group-lg</code>
			</p>

			<div class="input-group input-group-lg">
				<div class="input-group-btn">
					<button type="button" class="btn btn-warning dropdown-toggle"
						data-toggle="dropdown">
						Action <span class="fa fa-caret-down"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul>
				</div>
				<!-- /btn-group -->
				<input type="text" class="form-control">
			</div>
			<!-- /input-group -->
			<p class="margin">Normal</p>

			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="btn btn-danger">Action</button>
				</div>
				<!-- /btn-group -->
				<input type="text" class="form-control">
			</div>
			<!-- /input-group -->
			<p class="margin">
				Small
				<code>.input-group.input-group-sm</code>
			</p>

			<div class="input-group input-group-sm">
				<input type="text" class="form-control"> <span
					class="input-group-btn">
					<button type="button" class="btn btn-info btn-flat">Go!</button>
				</span>
			</div>
			<!-- /input-group -->
		</div>
	</div>
</section>
<!-- /.content -->
<script src="${pageContext.request.contextPath}/resources/admin/js/jquery-3.3.1.min.js"></script>
<script>
$("#select_id").change(function() {
	var str = "";
    $( "select option:selected" ).each(function() {
      str += $( this ).text() + " "+$( this ).val();
    });
    alert(str);
}).trigger("change");  
</script> 