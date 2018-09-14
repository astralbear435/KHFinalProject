<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/recruit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>

<div class="container">
	<h3>봉사활동 모집 글 작성</h3>
 <hr class="my-4">	
	<!-- 1. 모집인원 디폴트는 0으로 만들기 
	2. 라디오 신청에 따라 다른 보기 보여주기 막기
	-->
	<form:form commandName="recruit" action="recruitWrite.do" id="recruitWrite_form" enctype="multipart/form-data">
		<form:hidden path="r_id" />
		<form:errors element="div" cssClass="error-color" />
		<table>
			<tr>
				<td><label>섬네일에 띄울 이미지</label></td>
				<td><input type="file" name="r_upload" id="r_upload"></td>
			</tr>
			
			<tr>
				<td><label for="title">봉사활동 모집 글 제목</label></td>
				<td><input type="text" name="r_title" id="r_title" maxlength="50"></td>
			</tr>

			<tr>
				<td><label>봉사활동 모집 기간 설정</label></td>
				<td>
					<div class="form-check-radio">
						<label class="form-check-label"> <input type="radio"
							name="r_status" id="r_status1" class="form-check-input"
							value="1">상시 모집<span class="form-check-sign"></span>
						</label>
					</div>
					<div class="form-check-radio">
						<label class="form-check-label"> <input type="radio"
							name="r_status" id="r_status2" class="form-check-input"
							value="2" checked>모집 기간 설정 <span class="form-check-sign"></span>
						</label>
					</div>
				</td>
			</tr>
			
			<tr>
				<td><label for="date">봉사활동 모집 시작 날짜</label></td>
				<td>
				<input type="date" name="r_start_date" id="r_start_date">
				</td>
			</tr>				
			<tr>
				<td><label for="date">봉사활동 모집 마감 날짜</label></td>
				<td>
				<input type="date" name="r_end_date" id="r_end_date">
				</td>
			</tr>
			
			<tr>
			<td><label>봉사활동 모집 인원(일일)</label></td>
				<td><input type="number" name="r_people" id="r_people" min="0">
				</td>
			</tr>
			
			<tr>
				<td><label for="detail">보호소 봉사활동에 대한 안내</label></td>
				<td><textarea rows="5" cols="30" name="r_content" id="r_content"></textarea>
				</td>
			</tr>			
		</table>

		<div class="align-center">
			<input type="submit" value="전송"> 
			<input type="button" value="목록" onclick="location.href='recruitList.do'">
		</div>
	</form:form>
</div>