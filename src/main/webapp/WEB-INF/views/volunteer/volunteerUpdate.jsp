<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
	
	
<script type="text/javascript">
	$(document).ready(function(){
		$('#v_btn').click(function(){
			document.myForm.target=opener.name;
			document.myForm.submit();
			self.close();
			
		});
	
	});
	
	$(document).ready(function(){
		  ajax(); 
	});


</script>
<div class="container jumbotron">

 <hr class="my-4">
	<form:form commandName="volunteer" action="volunteerUpdate.do" id="volunteerUpdate_form" name="myForm">
		<form:hidden path="v_id"/>
		<form:hidden path="v_num"/>
		<form:errors element="div" cssClass="error-color"/>
		변경 전 날짜 -> 변경 후 날짜 입력 폼에 같이 있고 감추기
		

		
		<div class="align-center">
			<input type="button" class="btn btn-warning btn-lg" id="v_btn"  value="전송"> 
			<input type="button" class="btn btn-primary btn-lg" id="del_btn" value="목록" onclick="${pageContext.request.contextPath}/mypage/mypage.do'">
		</div>
	</form:form>

</div>