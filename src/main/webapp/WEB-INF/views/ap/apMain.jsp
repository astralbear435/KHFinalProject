<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
	<div style="margin:50px 0 0 0;">
		<span>
			<img src="${pageContext.request.contextPath}/resources/images/apmain.jpg" style="height:574px; width:1058px;" alt="임시보호자 메인" class="img-responsive" />
		</span>
	</div>

	<div class="jumbotron col-md-6" style="margin:50px 50px 50px 0; width:500px; text-align:center;">
		<p class="lead" style="font-weight:bold;">임시 보호자 집으로 부르기</p>
		<hr class="my-4">
		<p>임시보호자가 자신의 집으로<br>오길 원해요!</p>
		<p class="lead">
			<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/ap/apCallList.do" role="button">목록보기</a>
		</p>
	</div>
	
	<div class="jumbotron col-md-6" style="margin:50px 50px 50px 0; width:500px; text-align:center;">
		<p class="lead" style="font-weight:bold;">임시 보호자 집에 맡기기</p>
		<hr class="my-4">
		<p>임시보호자의 집에<br>반려동물 맡기기를 원해요!</p>
		<p class="lead">
			<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/ap/apList.do" role="button">목록보기</a>
		</p>
	</div>
	
	<div class="jumbotron col-md-6" style="margin:50px 50px 50px 0; width:500px; text-align:center;">
		<p class="lead" style="font-weight:bold;">임시 보호자 지원 신청하기</p>
		<hr class="my-4">
		<p>자원봉사로 다른 사람의 동물을 대신<br>돌봐주는 시스템</p>
		<p class="lead">
			<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/ap/apForm.do" role="button">신청하기</a>
		</p>
	</div>
	
</div>