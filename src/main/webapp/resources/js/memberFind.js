$(document).ready(function() {
	
	var regex= /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	var check = true;
	
	//아이디 찾기
	$('#find_Id').click(function() {
		
		$('#message_email').empty();
		$('#m_email.errors').empty();
		
		check = true;

		//이메일 입력 여부 판단
		if($('#m_email').val() == '') {

			$('#message_email').css('color','red').text('이메일을 입력하세요.');
			$('#m_email').focus();
			
			check = false;
			
			return false;
		}
		
		if(regex.test($('#m_email').val()) === false) {
			
			$('#message_email').css('color','red').text('이메일 형식이 올바르지 않습니다.');
		    
			check = false;
			
			return false;
		}
		
		if(check) {
			
			$('#message_id').text('');	//메세지 초기화
			
			$.ajax({
				
				url:'find_id.do',
				type:'post',
				data:{m_email:$('#m_email').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data) {
					
					$('#loading').hide();	//로딩 이미지 감추기
	
					if(data.result == 'emailNotFound') {	//이메일이 없는 경우
	
						$('#message_email').css('color','red').text('등록되지 않은 이메일입니다.');
	
					} else if(data.result == 'emailFound') {	//이메일이 존재하는 경우
						
						$('#message_email').css('color','blue').text('아이디 : ' + data.member);
						
					} else {
	
						alert('이메일 체크 오류!!');
					}
				},
				error:function() {
					
					$('#loading').hide();	//로딩 이미지 감추기
					alert('네트워크 오류 발생!!');
				}
	
			});
		}
	});
	
	//비밀번호 찾기
	$('#findMember_form').submit(function() {
		
		$('#message_email').empty();
		$('#m_email.errors').empty();
		
		check = true;
		//이메일 입력 여부 판단
		if($('#m_email').val() == '') {

			$('#message_email').css('color','red').text('이메일을 입력하세요.');
			$('#m_email').focus();
			
			check = false;
			
			return false;
		}
		
		if(regex.test($('#m_email').val()) === false) {
			
			$('#message_email').css('color','red').text('이메일 형식이 올바르지 않습니다.');
		    
			check = false;
			
			return false;
		}

		/*if(check) {
			
			$('#message_id').text('');	//메세지 초기화
			
			$.ajax({

				url:'findMember.do',
				type:'post',
				data:{m_email:$('#m_email').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data) {

					$('#loading').hide();	//로딩 이미지 감추기

					if(data.result == 'emailNotFound') {	//이메일이 없는 경우
						
						$('#message_email').css('color','red').text('등록되지 않은 이메일입니다.');

					} else if(data.result == 'success') {	//이메일이 존재하는 경우
						
						var output = '';
						output += '등록된 이메일로 임시비밀번호가 전송되었습니다.';
						output += '<br>';
						output += '로그인 후 비밀번호를 변경하세요.';
						//이메일로 임시비밀번호 발송되었다고 알림
						$('#message_email').css('color','blue').append(output);
						
					} else {

						alert('이메일 체크 오류!!');
					}
				},
				error:function() {

					$('#loading').hide();	//로딩 이미지 감추기
					alert('네트워크 오류 발생!!');
				}

			});
		}*/
		
	});
	
	//이메일 검색 오류 안내 메시지 초기화
	$('#m_email').keyup(function () {
		
		$('#message_email').text('');
	});
	
});




