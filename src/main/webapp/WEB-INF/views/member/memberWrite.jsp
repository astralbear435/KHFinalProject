<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/memberConfirm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<div class="500 margin0a">
<%-- 	<br>
	<div>
		<div class="titleText">회원가입</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
		<div id="margin0a">
			<div style="width: 450px; margin: 0 auto;">
				<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
			</div>
		</div>
		<br>

		<div class="titleText">회원가입</div><br> --%>
	<br>
	<div id="margin0a">
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<br>
	<div>
		<div class="titleText">회원가입</div>
		<div class="horizontal"></div><!-- 가로 여백 -->
		<div class="w500 margin0a">
			<form:form commandName="command" action="sendEmail.do"
				id="register_form" enctype="multipart/form-data">
				<fieldset class="clearB">
					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="m_id">아이디</label><br> <input type="text"
								name="m_id" id="m_id" class="form-control" style="width: 278px;"
								placeholder="아이디 입력">
						</div>
						<div class="floatL h72" style="width: 5px;"></div>
						<div class="form-group floatL h72 lineh95">
							<input type="button" id="confirmId" class="btn btn-primary"
								value="아이디 중복체크">
						</div>

					</div><br>
					<div id="message_id"></div><br>
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
					
>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
>>>>>>> d656dce3edc76ffd09ff45d4470a68df1679bd2e
					<div class="form-group clearB">
						<label for="m_name">이름</label> <input type="text" name="m_name"
							id="m_name" class="form-control" placeholder="이름 입력">
						<div id="message_name"></div>
					</div><br>
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> d656dce3edc76ffd09ff45d4470a68df1679bd2e
					
					<div class="form-group clearB">
						<label for="m_gender">성별</label><br>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-warning active">
							<input type="radio" name="m_gender" id="man" value="남" autocomplete="off"> 남
						</label>
						<label class="btn btn-info active">
							<input type="radio" name="m_gender" id="woman" value="여" autocomplete="off"> 여
						</label><br>
						</div>						
						<div id="message_gender"></div>
					</div>
<<<<<<< HEAD
=======
>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
					
					<div class="form-group clearB">
						<label for="m_gender">성별</label><br>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-info active">
							<input type="radio" name="m_gender" id="man" value="남" autocomplete="off"> 남
						</label>
						<label class="btn btn-info active">
							<input type="radio" name="m_gender" id="woman" value="여" autocomplete="off"> 여
						</label><br>
						</div>						
						<div id="message_gender"></div>
					</div>
>>>>>>> d656dce3edc76ffd09ff45d4470a68df1679bd2e

					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="m_nickname">닉네임</label><br> <input type="text"
								name="m_nickname" id="m_nickname" class="form-control"
								style="width: 278px;" placeholder="아이디 입력">
						</div>
						<div class="floatL h72" style="width: 5px;"></div>
						<div class="form-group floatL h72 lineh95">
							<input type="button" id="confirmNickname" class="btn btn-primary"
								value="닉네임 중복체크">
						</div>
					</div>
					<br>
					<div id="message_nickname"></div>
					<br>

					<div class="form-group clearB">
						<label for="m_passwd">비밀번호</label> <input type="password"
							name="m_passwd" id="m_passwd" class="form-control"
							placeholder="비밀번호 입력">
						<div id="message_pw"></div>
					</div>

					<div class="form-group clearE">
						<label for="checkPw">비밀번호 확인</label> <input type="password"
							name="checkPw" id="checkPw" class="form-control"
							placeholder="비밀번호확인 입력">
						<div id="message_checkPw"></div>
					</div>

					<div class="form-group clearF">
						<label for="m_phone">전화번호</label> <input type="text"
							name="m_phone" id="m_phone" class="form-control"
							placeholder="'-'을 제외한 숫자입력">
						<div id="message_phone"></div>
					</div>

					<div class="form-group floatL">
						<label for="m_zipcode">우편번호</label><br> <input type="text"
							name="m_zipcode" id="m_zipcode" class="form-control"
							style="width: 200px;" placeholder="우편번호 찾기 버튼 클릭" readonly>
					</div>
<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
					<div class="floatL h72" style="width: 5px;"></div>
>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
>>>>>>> d656dce3edc76ffd09ff45d4470a68df1679bd2e
					<div class="form-group floatL h72 lineh95">
						<input type="button" id="selectZipcode" class="btn btn-primary" value="우편번호 찾기">
					</div><br><br>				
					<div id="message_zipcode"></div>
					<div class="form-group clearB">
						<label for="m_address">주소</label> <input type="text"
							name="m_address" id="m_address" class="form-control" readonly>
					</div>

					<div class="form-group clearB">
						<label for="m_address_detail">상세주소</label> <input type="text"
							name="m_address_detail" id="m_address_detail"
							class="form-control">
						<div id="message_address_detail"></div>
					</div>

					<div class="w500 h72">
						<div class="form-group floatL">
							<label for="m_email">이메일</label><br> <input type="email"
								name="m_email" id="m_email" style="width: 290px;"
								value="${command.m_email}" class="form-control"
								placeholder="test@test.com" />
						</div>
						<div class="floatL h72" style="width: 10px;"></div>
						<div class="form-group floatL h72 lineh95">
							<input type="button" id="confirmEmail" class="btn btn-primary"
								value="이메일 중복체크">
						</div>
					</div>
					<br>
					<div id="message_email"></div>
					<br>

					<div class="form-group clearB">
						<label for="upload">프로필사진</label> <input type="file"
							class="form-control-file" name="upload" id="upload" /> <small
							id="fileHelp" class="form-text text-muted"> 프로필 사진 이미지를
							첨부해주세요. 미첨부시 기본 이미지로 표시됩니다! </small>
					</div>

					<div class="form-group">
						<label for="m_birth">생년월일</label> <input type="text"
							name="m_birth" id="m_birth" class="form-control"
							placeholder="19920816">
						<div id="message_birth"></div>
					</div>
		
					<br><hr size="1" width="100%">
		
					<div class="w500 clearB" style="text-align:center; margin: 0;">
<<<<<<< HEAD
						<input type="submit" value="회원가입" class="btn btn-warning">&nbsp;&nbsp;&nbsp;
=======
<<<<<<< HEAD
						<input type="submit" value="회원가입" class="btn btn-primary">&nbsp;&nbsp;&nbsp;
=======
						<input type="submit" value="회원가입" class="btn btn-warning">&nbsp;&nbsp;&nbsp;
>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
>>>>>>> d656dce3edc76ffd09ff45d4470a68df1679bd2e
						<input type="button" value="홈으로" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
					</div>

					<div class="horizontal"></div>
					<!-- 가로 여백 -->

				</fieldset>
			</form:form>
		</div>
	</div>
	<br>
</div>