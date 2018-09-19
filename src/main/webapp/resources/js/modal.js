$(document).ready(function() {
	
	// 로그인 모달창		
	$('#memberLogin').click(function() {
		
		$('#loginModal').show();
	});
	
	$('#loginButton').click(function() {
		
		$.ajax({
			url:'../member/memberLogin.do',
			type:'post',
			data:{m_id:$('#l_id').val(),m_passwd:$('#l_passwd').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				
				if(data.result == 'success') {	//로그인 성공
					
					alert('환영합니다!');

					$('#loginModal').hide();
					location.href='../main/main.do';
				
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
	
	// 닫기
	$('#closeMemberLogin').click(function() {
		
		$('#loginModal').hide();
	});
	
	// 엔터하면 로그인 처리
	$('input[type="text"]').keydown(function(event) {
	    if (event.keyCode == 13) {
	    	$('#loginButton').click();
	    }
	});
	
	$('input[type="password"]').keydown(function(event) {
	    if (event.keyCode == 13) {
	    	$('#loginButton').click();
	    }
	});
});
