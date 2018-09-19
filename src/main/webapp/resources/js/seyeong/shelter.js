$(document).ready(function(){
	
	/* shelterWrite */
	var checkId = 0;
	
	/* 아이디 */
	// 중복 체크
	$('#confirmId').click(function(){
		
		if($('#s_id').val() == '') {
			
			$('#message_id').css('color','red').text('아이디를 입력하세요.');
			$('#s_id').focus();
			
			return;
		}
		
		for (i = 0; i < $('#s_id').val().length; i++) {
            ch = $('#s_id').val().charAt(i)
            if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
                
            	$('#message_id').css('color','red').text('아이디는 대소문자, 숫자만 입력가능합니다.');
                $('#s_id').focus();
                
                return false;
            }
        }
		
		if($('#s_id').val().length < 6 || $('#s_id').val().length > 20) {
			
			$('#message_id').css('color','red').text('아이디는 6~20자로 입력하세요.');
			$('#s_id').focus();
			
			return false;
		}

		// 중복 확인
		var id = $('#s_id').val();
		$('#message_id').text(''); //메세지 초기화
		$.ajax({
			type:'post',
			data:{id:id},
			url:'confirmId.do',
			dataType:'json',
			timeout:30000,
			success:function(data){
				if(data.result == 'idNotFound'){
					$('#message_id').css('color','blue').text('훌륭한 아이디입니다!');
					checkId = 1;
				}else if(data.result == 'idDuplicated'){
					$('#message_id').css('color','red').text('더 멋진 아이디를 입력하세요!');
					$('#s_id').val('').focus();
					checkId = 0;
				}else{
					alert('아이디 중복 체크 오류');
				}
			},
			error:function(){
				alert(id);
				alert('네트워크 오류 발생!!');
			}
		});
	});
	
	// 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#shelterRegisterForm #s_id').keyup(function(){
		checkId=0;
		$('#message_id').text('');
	});
	
	// submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#shelterRegisterForm').submit(function(){
		if(checkId==0){
			alert('아이디 중복 체크 필수!');
			if($('#s_id').val()==''){
				$('#s_id').focus();
			}
			return false;
		}
	});
	
	/* 이메일 */
	// 중복 체크
	$('#confirmEmail').click(function(){
		var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		
		//이메일 입력 여부 판단
		if($('#s_email').val() == '') {
			
			$('#message_email').css('color','red').text('이메일을 입력하세요.');
			$('#s_email').focus();
			
			return;
		}
		
		if(regex.test($('#s_email').val()) == false) {
			
			$('#message_email').css('color','red').text('이메일 형식이 올바르지 않습니다.');
		    
		    return false;
		}
		
		var email=$('#s_email').val();
		$('#message_email').text(''); //메세지 초기화
		$.ajax({
			type:'post',
			data:{email:email},
			url:'confirmEmail.do',
			dataType:'json',
			timeout:30000,
			success:function(data){
				if(data.result == 'emailNotFound'){
					$('#message_email').css('color','blue').text('사용 가능한 이메일입니다!');
					checkId = 1;
				}else if(data.result == 'emailDuplicated'){
					$('#message_email').css('color','red').text('사용 중인 이메일입니다!');
					$('#s_email').val('').focus();
					checkId = 0;
				}else{
					alert('이메일 중복 체크 오류');
				}
			},
			error:function(){
				alert(id);
				alert('네트워크 오류 발생!!');
			}
		});
	});
	
	// 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#shelterRegisterForm #s_email').keyup(function(){
		checkId=0;
		$('#message_email').text('');
	});
	
	// submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#shelterRegisterForm').submit(function(){
		if(checkId==0){
			alert('이메일 중복 체크 필수!');
			if($('#s_email').val()==''){
				$('#s_email').focus();
			}
			return false;
		}
	});
	
	// 회원 정보 유효성 체크
	$('#shelterModifyForm').submit(function(){
		if($('#s_passwd').val()==''){
			alert('비밀번호를 입력하세요!');
			$('#s_passwd').focus();
			return;
		}
		
		if($('#s_name').val()==''){
			alert('이름을 입력하세요!');
			$('#s_name').focus();
			return;
		}
		
		if($('#s_license_name').val()==''){
			alert('사업자 번호를 입력하세요!');
			$('#s_id').focus();
			return;
		}
		
		if($('#s_phone').val()==''){
			alert('전화번호를 입력하세요!');
			$('#s_id').focus();
			return;
		}
		
		if($('#s_address1').val()==''){
			alert('주소를 입력하세요!');
			$('#s_id').focus();
			return;
		}
	});
	
	
	/* shelterInfo */
	// 회원 정보 확인하려면 비밀번호 확인 해야 함
	$('#confirmBtn').click(function(){
		if($('#s_passwd').val() == $('#s_passwdInput').val()){
			document.location.href = 'shelterInfo.do';
		}else{
			alert('비밀번호가 일치하지 않습니다');
			return;
		}
	});
	
	// 엔터키 치면 확인 버튼 클릭과 동일하게 처리됨
	$('#s_passwdInput').keydown(function(key){
		if(key.keyCode == 13){
			key.preventDefault();
			$('#confirmBtn').click();
		}
	});
	
	// 수정 시 확인
	$('#shelterUpdateBtn').click(function(){
		var id = $('#s_id').val();
		
		if(confirm("회원 정보를 수정 하시겠습니까?")){
			$('#shelterUpdateForm').submit();
		}else{}
	});
	
	// 탈퇴 시 확인
	$('#shelterDeleteBtn').click(function(){
		var id = $('#s_id').val();
		
		if(confirm("탈퇴하시겠습니까?")){
			$('#shelterDeleteForm').submit();
		}else{}
	});
});