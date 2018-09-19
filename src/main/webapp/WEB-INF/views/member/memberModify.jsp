<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/memberConfirm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<div class="page-main-style">
	<br>
	<div id="margin0a">
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<br>
	<div>
		<div class="titleText">회원정보 수정</div>
		<div style="text-align: center;"><span style="color: red;">*빨간부분만 수정가능</span></div><!-- 
		<div class="horizontal"></div>가로 여백 -->
		<div class="w500 margin0a">
			<form:form commandName="command" action="memberUpdate.do" id="modify_form" enctype="multipart/form-data">
				<fieldset class="clearB">
					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="m_id">아이디</label><br>
							<input type="text" name="m_id" id="m_id" class="form-control" style="width: 278px;" value="${command.m_id}" readonly>
						</div>
					</div>
					<br>
					
					<div class="form-group clearB">
						<label for="m_name">이름</label>
						<input type="text" name="m_name" id="m_name" class="form-control is-invalid" value="${command.m_name}">
						<div id="message_name"></div>
					</div>
					
					<div class="form-group clearB">
						<label for="m_gender">성별</label><br>
						<input type="text" name="m_gender" id="m_gender" class="form-control" value="${command.m_gender}" readonly>
					</div>
					
					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="m_nickname">닉네임</label>
							<input type="text" name="m_nickname" value="${command.m_nickname}" class="form-control" readonly>
						</div>
					</div>
					<br>
					
					<div class="form-group clearB">
						<label for="m_passwd">비밀번호</label>
						<input type="password" name="m_passwd" id="m_passwd" class="form-control is-invalid" placeholder="비밀번호 입력">
						<div id="message_pw"></div>
					</div>
					
					<div class="form-group clearE">
						<label for="checkPw">비밀번호 확인</label>
						<input type="password" name="checkPw" id="checkPw" class="form-control is-invalid" placeholder="비밀번호확인 입력">
						<div id="message_checkPw"></div>
					</div>
					
					<div class="form-group clearF">
						<label for="m_phone">전화번호</label>
						<input type="text" name="m_phone" id="m_phone" class="form-control is-invalid" value="${command.m_phone}">
						<div id="message_phone"></div>
					</div>
				
					<div class="form-group floatL">
						<label for="m_zipcode">우편번호</label><br>
						<input type="text" name="m_zipcode" id="m_zipcode" class="form-control is-invalid" style="width:200px;" value="${command.m_zipcode}" readonly>
					</div>
					
					<div class="form-group floatL h72 lineh95">
						<input type="button" id="selectZipcode" class="btn btn-primary" value="우편번호 찾기">
					</div>
					<div id="message_zipcode"></div>
					
					<div class="form-group clearB">
						<label for="m_address">주소</label>
						<input type="text" name="m_address" id="m_address" class="form-control" value="${command.m_address}" readonly>
					</div>
					
					<div class="form-group clearB">
						<label for="m_address_detail">상세주소</label>
						<input type="text" name="m_address_detail" id="m_address_detail" class="form-control is-invalid" value="${command.m_address_detail}">
						<div id="message_address_detail"></div>
					</div>
					
					<c:if test="${command.auth != 2}">
						<div class="w500 h72">
							<div class="form-group floatL">
								<label for="m_email">이메일</label><br>
								<input type="email" name="m_email" id="m_email" style="width: 290px;" value="${command.m_email}" placeholder="${command.m_email}" class="form-control is-invalid"/>
							</div>
							<div class="floatL h72" style="width: 10px;"></div>
							<div class="form-group floatL h72 lineh95">
								<input type="button" id="confirmEmail" class="btn btn-primary" value="이메일 중복체크">
							</div>
						</div>
						<div id="message_email"></div>
						<br>
					</c:if>
					<c:if test="${command.auth == 2}">
						<div class="w500 h72">
							<div class="form-group floatL">
								<label for="m_email">이메일</label><br>
								<input type="email" name="m_email" id="m_email" style="width: 290px;" value="${command.m_email}" placeholder="${command.m_email}" class="form-control" readonly/>
							</div>
							<div class="floatL h72" style="width: 10px;"></div>
						</div>
					</c:if>
					
					<div class="form-group clearB">
						<label for="upload">프로필사진</label>
						<input type="file" class="form-control-file" name="upload" id="upload" value="${command.profileFilename}"/>
						<small id="fileHelp" class="form-text text-muted">
							프로필 사진 이미지를 첨부해주세요. 미첨부시 기본 이미지로 표시됩니다!
						</small>
					</div>
					
					<div class="form-group">
						<label for="m_birth">생년월일</label>
						<input type="text" name="m_birth" id="m_birth" class="form-control is-invalid" value="${command.m_birth}">
						<div id="message_birth"></div>
					</div>
		
					<hr size="1" width="100%">
		
					<div class="w500 clearB" style="text-align:center; margin: 0;">
						<input type="submit" class="btn btn-primary" value="수정하기">&nbsp;&nbsp;
						<input type="button" value="뒤로" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/memberDetail.do'">&nbsp;&nbsp;
					</div>
					
					<div class="horizontal"></div><!-- 가로 여백 -->
		
				</fieldset>
			</form:form>
		</div>
	</div>
	<br>
</div>
