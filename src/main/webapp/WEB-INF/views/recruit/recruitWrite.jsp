<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/recruit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<div class="container jumbotron">

 <hr class="my-4">	
	<!-- 1. 모집인원 디폴트는 0으로 만들기 
	2. 라디오 신청에 따라 다른 보기 보여주기 막기
	-->
	<!--한글 주석  -->
	<form:form commandName="recruit" action="recruitWrite.do" id="recruitWrite_form" enctype="multipart/form-data">
		<form:hidden path="r_id" />
		<form:errors element="div" cssClass="error-color" />
		<table>
			<tr>
				<td><label>모집 기간 설정</label></td>
				<td>
					<div class="form-check-radio">
						<label class="form-check-label"> <input type="radio"
							name="r_status" id="r_status1" class="form-check-input"
							value="1">상시 모집<span class="form-check-sign"></span>
						</label>
					</div>
					</td>
				<td>
					<div class="form-check-radio">
						<label class="form-check-label"> <input type="radio"
							name="r_status" id="r_status2" class="form-check-input"
							value="2" checked>날짜 설정 <span class="form-check-sign"></span>
						</label>
					</div>
				</td>
				<td>
				<input type="date" name="r_start_date" id="r_start_date" class="form-control">
				</td>
				<td> ~ </td>
				<td>
				<input type="date" name="r_end_date" id="r_end_date" class="form-control">
				</td>
			</tr>			
			<tr>
			<td><label>(일일)모집 인원 </label></td>
				<td><input type="number" name="r_people" id="r_people" min="0" class="form-control">
				</td>
			</tr>	
			</table>	
			 <div style="height:20px;"></div>	
				<label for="title">제목</label>
				<input type="text" name="r_title" id="r_title" maxlength="50" size="10" class="form-control" >			
			<div style="height:20px;"></div>	
				
			<textarea rows="15" cols="30" name="r_content" id="r_content" class="form-control" placeholder="봉사활동에 관한 안내글을 써주세요."></textarea>
			<div style="height:5px;"></div>
				<label>이미지 첨부</label>
				<input type="file" name="r_upload" id="r_upload">	

		<div style="text-align:center;">
			<input type="submit" value="전송" class="btn btn-warning btn-lg" > 
			<input type="button" value="목록" class="btn btn-primary btn-lg" onclick="location.href='recruitList.do'">
		</div>
	</form:form>
</div>