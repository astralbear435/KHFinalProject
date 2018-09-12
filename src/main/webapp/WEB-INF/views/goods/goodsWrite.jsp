<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>


<div class="page-main-style">
	<h1>임시등록</h1>
	<form:form commandName="command" action="write.do"
	    id="register_form">
		<form:errors element="div" cssClass="error-color" id="error_id"/>	
		<ul>
			<li>
				<label for="as_id">보호소 아이디</label>
				<form:input path="as_id"/>
				<form:errors path="as_id" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="as_name">보호소명</label>
				<form:input path="as_name"/>
				<form:errors path="as_name" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="as_location">위치</label>
				<form:input path="as_location"/>
				<form:errors path="as_location" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="pad">배변패드</label>
				<form:input type="number" path="pad"/>
				<form:errors path="pad" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="dogfood">사료(전연령용)</label>
				<form:input type="number" path="dogfood"/>
				<form:errors path="dogfood" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="cattoy">장난감</label>
				<form:input type="number" path="cattoy"/>
				<form:errors path="cattoy" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="shampoo">샴푸(장모용)</label>
				<form:input type="number" path="shampoo"/>
				<form:errors path="shampoo" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="catsand">고양이 모래</label>
				<form:input type="number" path="catsand"/>
				<form:errors path="catsand" 
				             cssClass="error-color"/>
			</li>			
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="홈으로"
			      onclick="location.href='${pageContext.request.contextPath}/main/main.do'">	    
		</div>
	</form:form>
 
</div>
