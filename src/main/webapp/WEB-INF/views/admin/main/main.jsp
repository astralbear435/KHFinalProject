<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		메인페이지 <small>메인페이지</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> admin</a></li>
		<li class="active">메인 페이지</li>
	</ol>
</section>
<%
	String user_id = (String)session.getAttribute("user_id");
%>
<!-- Main content -->
<section class="content container-fluid">
	<input type="hidden" id="user_id" value="${user_id}">
	<div class="row">
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-aqua">
				<div class="inner">
					<h4>오늘 후원금 : ${todayP}</h4>

					<p>총 후원금 : ${totalP}</p>
				</div>
				<div class="icon">
					<i class="ion ion-bag"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-green">
				<div class="inner">
					<h4>오늘 등록한 보호소 : ${todayS}</h4>

					<p>총 보호소수 : ${totalS}</p>
				</div>
				<div class="icon">
					<i class="fa fa-fw fa-heartbeat"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-yellow">
				<div class="inner">
					<h4>오늘 가입자 : ${todayM}</h4>

					<p>총 회원수 : ${totalM}</p>
				</div>
				<div class="icon">
					<i class="ion ion-person-add"></i>
				</div>
				<a
					href="${pageContext.request.contextPath}/admin/member/normalMember.do"
					class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
		<div class="col-lg-3 col-xs-6">
			<!-- small box -->
			<div class="small-box bg-red">
				<div class="inner">
					<h4>오늘 방문자수 : ${todayV}</h4>

					<p>누적 방문자수 : ${totalV}</p>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a href="#" class="small-box-footer">More info <i
					class="fa fa-arrow-circle-right"></i></a>
			</div>
		</div>
		<!-- ./col -->
	</div>
	<div class="col-lg-4 col-xs-6">
		<!-- DIRECT CHAT PRIMARY -->
		<div class="box box-primary direct-chat direct-chat-primary">
			<div class="box-header with-border">
				<h3 class="box-title">Direct Chat</h3>

				<div class="box-tools pull-right">
					<!-- <span data-toggle="tooltip" title="3 New Messages"
						class="badge bg-light-blue">3</span> --> 
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool"
						data-toggle="tooltip" title="Contacts"
						data-widget="chat-pane-toggle">
						<i class="fa fa-comments"></i>
					</button>
					<button type="button" id="exitButton" class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
			<div id="message_appear" class="direct-chat-messages">
			</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<form action="#" method="post">
					<div class="input-group">
						<input type="text" name="message" id="message_input"
							placeholder="Type Message ..." class="form-control"> <span
							class="input-group-btn">
							<button type="button" id="send_message"
								class="btn btn-primary btn-flat">Send</button>
						</span>
					</div>
				</form>
			</div>
			<!-- /.box-footer-->
		</div>
		<!--/.direct-chat -->
	</div>
</section>
<!-- /.content -->

<!-- jQuery 3 -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script
	src="${pageContext.request.contextPath}/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script
	src="${pageContext.request.contextPath}/resources/admin/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script
	src="${pageContext.request.contextPath}/resources/admin/dist/js/demo.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/admin/js/chatting.js"></script>