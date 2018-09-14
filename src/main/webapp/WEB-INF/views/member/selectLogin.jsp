<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<style>
	.modal {
		display: none; /* Hidden by default */
		position: fixed; /* Stay in place */
		z-index: 1; /* Sit on top */
		left: 0;
		top: 0;
		width: 100%; /* Full width */
		height: 100%; /* Full height */
		overflow: auto; /* Enable scroll if needed */
		background-color: rgb(0, 0, 0); /* Fallback color */
		background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	}
	
	/* Modal Content/Box */
	.modal-content {
		background-color: #fefefe;
		margin: 15% auto; /* 15% from the top and centered */
		padding: 20px;
		border: 1px solid #888;
		width: 30%; /* Could be more or less, depending on screen size */
	}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		/* 일반 */
		//로그인 모달창		
		$('#memberLogin').click(function() {
			
			$('#myModal').show();
		});
		
		$('#loginButton').click(function() {
			
			$.ajax({
				url:'memberLogin.do',
				type:'post',
				data:{m_id:$('#m_id').val(),m_passwd:$('#m_passwd').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data) {
					
					if(data.result == 'success') {	//로그인 성공
						
						$('#myModal').hide();
						location.href='${pageContext.request.contextPath}/main/main.do';
					
					} else if(data.result == 'false') {
						
						$('#errorLogin').css('color','red').text('아이디 또는 비밀번호 불일치!');
						$('#m_id').focus();
						$('#m_passwd').val('');
						
					} else {
						
						alert('네트워크 오류 발생!');
					}
				}
			});
			
		});
		
		//닫기
		$('#closeMemberLogin').click(function() {
			
			$('#myModal').hide();
		});
		
		/* 보호소 */
		//로그인 모달창		
		$('#shelterLogin').click(function() {
			
			$('#shelterModal').show();
		});
		
		$('#shelterLoginButton').click(function() {
			
			$.ajax({
				url:'shelterLogin.do',
				type:'post',
				data:{s_id:$('#s_id').val(),s_passwd:$('#s_passwd').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data) {
					if(data.result == 'success') {	//로그인 성공
						$('#shelterModal').hide();
						location.href='${pageContext.request.contextPath}/main/main.do';
					} else if(data.result == 'false') {
						
						$('#errorLogin').css('color','red').text('아이디 또는 비밀번호 불일치!');
						$('#s_id').focus();
						$('#s_passwd').val('');
						
					} else {
						alert('네트워크 오류 발생!');
					}
				}
			});
			
		});
		
		//닫기
		$('#closeShelterLogin').click(function() {
			
			$('#shelterModal').hide();
		});
	});
</script>

<body>
	<!-- 모달창 열기 -->
	<div id="myModal" class="modal">
		<div class="modal-content">
		<span style="font-size:10pt; text-align:right;">
					<a href="#" id="closeMemberLogin">X</a>
				</span>
			<p style="text-align: center;">
				<span style="font-size: 14pt;">
					<b><span style="font-size: 20pt;">일반회원 로그인</span></b>
				</span>
				
			</p>
			<div style="text-align: center;"><hr color="black">
				
					<label for="m_id">아이디</label>
					<br>
					<input type="text" name="m_id" id="m_id" class="form-control" style="width: 280px;" placeholder="아이디 입력" />
					<br><br>					
					<label for="m_passwd">비밀번호</label>
					<br>
					<input type="password" name="m_passwd" id="m_passwd" class="form-control" style="width: 280px;" placeholder="비밀번호 입력" />
					<br>
					<div id="errorLogin"></div>
					<br>
					<input type="button" class="btn btn-warning btn-lg" id="loginButton" value="로그인">
					<br><br>&nbsp;
					<a class="agile-icon" href="${pageContext.request.contextPath}/member/write.do"> 
						<i class="fa fa-user">회원가입</i>
					</a>&nbsp;&nbsp;&nbsp;
					<a class="agile-icon" href="${pageContext.request.contextPath}/member/findMember.do"> 
						<i class="fa fa-unlock-alt">아이디/비밀번호 찾기</i>
					</a>
					<br><br>
				
			</div>
		</div>
	</div>
	<!-- 모달창 닫기 -->
	
	<!-- 보호소 모달창 -->
	<div id="shelterModal" class="modal">
		<div class="modal-content">
		<span style="font-size:10pt; text-align:right;">
					<a href="#" id="closeShelterLogin">X</a>
				</span>
			<p style="text-align: center;">
				<span style="font-size: 14pt;">
					<b><span style="font-size: 20pt;">보호소 회원 로그인</span></b>
				</span>
				
			</p>
			<div style="text-align: center;"><hr color="black">
				
					<label for="s_id">아이디</label>
					<br>
					<input type="text" name="s_id" id="s_id" class="form-control" style="width: 280px;" placeholder="아이디 입력" />
					<br><br>					
					<label for="s_passwd">비밀번호</label>
					<br>
					<input type="password" name="s_passwd" id="s_passwd" class="form-control" style="width: 280px;" placeholder="비밀번호 입력" />
					<br>
					<div id="errorLogin"></div>
					<br>
					<input type="button" class="btn btn-warning btn-lg" id="shelterLoginButton" value="로그인">
					<br><br>&nbsp;
					<a class="agile-icon" href="${pageContext.request.contextPath}/shelter/write.do"> 
						<i class="fa fa-user">회원가입</i>
					</a>&nbsp;&nbsp;&nbsp;
					<a class="agile-icon" href="${pageContext.request.contextPath}/member/findMember.do"> 
						<i class="fa fa-unlock-alt">아이디/비밀번호 찾기</i>
					</a>
					<br><br>
				
			</div>
		</div>
	</div>
	<!-- 보호소 모달창 -->
	
	<div style="width: 100%; height: 380px;">
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div>
		<div style="width: 450px; margin: 0 auto;">
			<img src="${pageContext.request.contextPath}\resources\images\sy\보호소 줄 그림.png">
		</div>
	</div>
	<div class="horizontal"></div><!-- 가로 여백 -->
	<div style="width: 25%; height: 150px; margin: 0 auto;">
		<input type="button" id="memberLogin" class="btn btn-warning btn-lg btn-block" value="일반 로그인">
		<div class="horizontal"></div><!-- 가로 여백 -->
		<input type="button" id="shelterLogin" class="btn btn-warning btn-lg btn-block" value="보호소 로그인">
	</div>
	</div>
</body>









