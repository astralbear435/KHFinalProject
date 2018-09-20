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
		<li><a href="#"><i class="fa fa-dashboard"></i>메인메뉴</a></li>
		<li class="active">설정</li>
	</ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
	<section class="content">
		<div class="row">
			<div class="col-md-4">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">메뉴 목록</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
					<div id="menu_box">
						<select id="select_id1" name=s1 size=15
							class="form-control select_id1" style="font-size: 20px;">
							<option selected="selected" value=0>-메뉴 추가-</option>
							<c:if test="${count>0}">
								<c:forEach var="menu" items="${list}">
									<c:if test="${menu.menu_depth==1}">
										<option value="${menu.menu_num}">${menu.menu_name}</option>
									</c:if>
									<c:if test="${menu.menu_depth>1}">
										<option value="${menu.menu_num}">ㅡ ${menu.menu_name}</option>
									</c:if>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<br>
						<button id="menu_up" class="btn btn-info">▲</button>
						<button id="menu_down" class="btn btn-info">▼</button>
						<button id="menu_delete" class="btn btn-info pull-right">삭제</button>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">메뉴 상세</h3>
					</div>
					<!-- /.box-header -->
					<form id="writeMenu" method="post" action="writeMenu.do">
					<div id="menu_detail" class="box-body">
						<div class="form-group">
							<label for="menu_use">메뉴 활성화</label><br> 
							<input type="radio"	name="menu_use" id="menu_use1" value="Y" checked="checked"> 표시
							<input type="radio" name="menu_use" id="menu_use2" value="N"> 미표시
						</div>
						<div class="form-group">
							<label for="menu_name">메뉴 명</label> <input type="text" id="menu_name" name="menu_name" class="form-control" placeholder="메뉴명">
							<span class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="menu_url">메뉴 주소</label> <input type="text" id="menu_url" name="menu_url" class="form-control" placeholder="/main/main.do">
							<span class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="menu_order">메뉴 순서</label> <input type="number" value="${count+1}" id="menu_order" name="menu_order" class="form-control" placeholder="0">
							<span class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="menu_dd">드롭다운 메뉴 사용</label><br> 
							<input type="radio"	name="menu_dd" id="menu_dd1" value="Y"> 사용 
							<input type="radio" name="menu_dd" id="menu_dd2" value="N" checked="checked"> 미사용
						</div>
						<div class="form-group">
							<label>상위 메뉴</label> 
							<select id="select_id2" name="parent_num" class="form-control">
							<option selected="selected" value="0" disabled="disabled">-선택-</option>
							</select>
						</div> 
						<input class="btn btn-info" type="submit" value="추가"> 
					</div>
					</form>
				</div>
			</div>
		</div>
	</section>
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
<script	src="${pageContext.request.contextPath}/resources/admin/js/mainmenu.js"></script>
