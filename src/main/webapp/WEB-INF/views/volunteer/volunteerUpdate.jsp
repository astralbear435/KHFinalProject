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
</script>
<div class="container jumbotron">

 <hr class="my-4">
	<form:form commandName="volunteer" action="volunteerUpdate.do" id="volunteerUpdate_form" name="myForm">
		<form:hidden path="v_id"/>
		<form:hidden path="v_num"/>
		<form:errors element="div" cssClass="error-color"/>
		
	<div>봉사활동 날짜<input type="date" name="v_date" id="v_date" min=${recruit.r_start_date} max=${recruit.r_end_date}>
		</div>
		<div>		<div class="form-check-radio">
							<label class="form-check-label"> <input type="radio"
								name="v_status" id="v_status1" class="form-check-input"
								value="1">1~3시<span class="form-check-sign"></span>
							</label>
						</div>
						<div class="form-check-radio">
							<label class="form-check-label"> <input type="radio"
								name="v_status" id="v_status2" class="form-check-input"
								value="2" checked>3~5시 <span class="form-check-sign"></span>
							</label>
						</div>
			</div>	
		
		<div class="align-center">
			<input type="button" class="btn btn-primary btn-lg" id="v_btn"  value="전송"> 
			<input type="button" class="btn btn-primary btn-lg" id="del_btn" 
				value="목록" onclick="${pageContext.request.contextPath}/mypage/mypage.do'">
		</div>
	</form:form>

</div>