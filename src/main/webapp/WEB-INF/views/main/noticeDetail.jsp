<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ap.css">
<div class="container">
	<br><br>
	<div class="floatL">&nbsp;&nbsp;</div>
	<input type="button" class="btn btn-secondary" style="float:right; margin:20px 0 20px 0;" 
			value="목록으로" onclick="location.href='noticeList.do'"> 
	<div class="card border-primary col-md-12" style="margin:10px 0 50px 0;">
			<div class="card-header">
				<div style="float:right;">
					<i class="fa fa-eye"></i>조회수 : ${noticeCommend.n_hit}
				</div>
				<p>
					[${noticeCommend.n_idx}] ${noticeCommend.n_id}
					
				</p>
				
			</div>
			<div class="card-body">
				<p class="card-text">
					${noticeCommend.n_subject}
				</p>
					${noticeCommend.n_content}
			</div>
	</div>
</div>

