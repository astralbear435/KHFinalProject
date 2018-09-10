$(document).ready(function() {
	
	var checkId = 0;	//중복체크 수행 여부를 판단하기 위해 변수 생성 (0이면 미수행, 1이면 수행)
	var checkNickname = 0;
	var checkEmail = 0;
	
	function clearInput() {
		
		//회원가입시
		//아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
		$('#register_form #m_id').keyup(function () {
			
			checkId = 0;
			$('#message_id').text('');
		});
		
		//닉네임 중복 안내 메시지 초기화 및 닉네임 중복값 초기화
		$('#register_form #m_nickname').keyup(function () {
			
			checkNickname = 0;
			$('#message_nickname').text('');
		});
		
		//이메일 중복 안내 메시지 초기화 및 아이디 중복값 초기화
		$('#register_form #m_email').keyup(function () {
			
			checkEmail = 0;
			$('#message_email').text('');
		});
		
		//이름 미입력 안내 메시지 초기화
		$('#register_form #m_name').keyup(function () {

			$('#message_name').text('');
		});
		//비밀번호 미입력 안내 메시지 초기화
		$('#register_form #m_passwd').keyup(function () {

			$('#message_pw').text('');
		});
		//비밀번호 확인 미입력 안내 메시지 초기화
		$('#register_form #checkPw').keyup(function () {

			$('#message_checkPw').text('');
		});
		//전화번호 미입력 안내 메시지 초기화
		$('#register_form #m_phone').keyup(function () {

			$('#message_phone').text('');
		});
		//우편번호 미입력 안내 메시지 초기화
		$('#register_form #selectZipcode').click(function () {

			$('#message_zipcode').text('');
		});
		//상세주소 미입력 안내 메시지 초기화
		$('#register_form #m_address_detail').keyup(function () {

			$('#message_address_detail').text('');
		});
		//생년월일 미입력 안내 메시지 초기화
		$('#register_form #m_birth').keyup(function () {

			$('#message_birth').text('');
		});
		
		//----------------------수정시----------------------
		//이름 미입력 안내 메시지 초기화
		$('#modify_form #m_name').keyup(function () {

			$('#message_name').text('');
		});
		//비밀번호 미입력 안내 메시지 초기화
		$('#modify_form #m_passwd').keyup(function () {

			$('#message_pw').text('');
		});
		//비밀번호 확인 미입력 안내 메시지 초기화
		$('#modify_form #checkPw').keyup(function () {

			$('#message_checkPw').text('');
		});
		//전화번호 미입력 안내 메시지 초기화
		$('#modify_form #m_phone').keyup(function () {

			$('#message_phone').text('');
		});
		//우편번호 미입력 안내 메시지 초기화
		$('#modify_form #selectZipcode').click(function () {

			$('#message_zipcode').text('');
		});
		//상세주소 미입력 안내 메시지 초기화
		$('#modify_form #m_address_detail').keyup(function () {

			$('#message_address_detail').text('');
		});
		//생년월일 미입력 안내 메시지 초기화
		$('#modify_form #m_birth').keyup(function () {

			$('#message_birth').text('');
		});
	}

	//아이디 중복 체크
	$('#confirmId').click(function () {
		
		//아이디 입력 여부 판단
		if($('#m_id').val() == '') {
			
			$('#message_id').css('color','red').text('아이디를 입력하세요.');
			$('#m_id').focus();
			
			return;
		}
		
		for (i = 0; i < $('#m_id').val().length; i++) {
            ch = $('#m_id').val().charAt(i)
            if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
                
            	$('#message_id').css('color','red').text('아이디는 대소문자, 숫자만 입력가능합니다.');
                $('#m_id').focus();
                
                return false;
            }
        }
		
		if($('#m_id').val().length < 6 || $('#m_id').val().length > 20) {
			
			$('#message_id').css('color','red').text('아이디는 6~20자로 입력하세요.');
			$('#m_id').focus();
			
			return false;
		}
		
		if($('#m_id').val().indexOf(" ") >= 0) {
			
			$('#message_id').css('color','red').text('공백이 포함되어있습니다.');
			$('#m_id').focus();
			
			return false;
		}
		
		$('#message_id').text('');	//메세지 초기화
		$('#loading').show();	//로딩 이미지 노출
		
		$.ajax({
			
			url:'confirmId.do',
			type:'post',
			data:{m_id:$('#m_id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				
				$('#loading').hide();	//로딩 이미지 감추기
				
				if(data.result == 'idNotFound') {	//아이디가 없는 경우
					
					$('#message_id').css('color','blue').text('훌륭한 아이디입니다!');
					checkId = 1;
					
				} else if(data.result == 'idDuplicated') {	//아이디가 중복된 경우
					
					$('#message_id').css('color','red').text('더 멋진 아이디를 입력하세요!');
					$('#m_id').val('').focus();
					checkId = 0;
					
				} else {
					
					alert('아이디 중복체크 오류!!');
				}
			},
			error:function() {
				
				$('#loading').hide();	//로딩 이미지 감추기
				alert('네트워크 오류 발생!!');
			}
			
		});
		
	});
	
	
//----------------------------------------------------
	
	
	//닉네임 중복체크
	$('#confirmNickname').click(function () {
		
		var pattern = /^[가-힣|a-z|A-Z|0-9|\*]+$/;
		
		//닉네임 입력 여부 판단
		if($('#m_nickname').val() == '') {
			
			$('#message_nickname').css('color','red').text('닉네임을 입력하세요.');
			$('#m_nickname').focus();
			
			return;
		}
		
		if($('#m_nickname').val().length < 2 || $('#m_nickname').val().length > 10) {
			
			$('#message_nickname').css('color','red').text('닉네임은 2~10자로 입력하세요.');
			$('#m_nickname').focus();
			
			return false;
		}
		
		if(pattern.test($('#m_nickname').val()) === false) {
			
			$('#message_nickname').css('color','red').text('한글 또는 영문의 2~10자로 입력하세요.');
			$('#m_nickname').focus();
			
			return false;
		}
		
		if($('#m_nickname').val().indexOf(" ") >= 0) {

			$('#message_nickname').css('color','red').text('공백을 지워주세요.');
			$('#m_id').focus();

			return false;
		}
		
		$('#message_nickname').text('');	//메세지 초기화
		$('#loading').show();	//로딩 이미지 노출
		
		$.ajax({
			
			url:'confirmNickname.do',
			type:'post',
			data:{m_nickname:$('#m_nickname').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				
				$('#loading').hide();	//로딩 이미지 감추기
				
				if(data.result == 'nicknameNotFound') {	//닉네임이 없는 경우
					
					$('#message_nickname').css('color','blue').text('훌륭한 닉네임입니다!');
					checkNickname = 1;
					
				} else if(data.result == 'nicknameDuplicated') {	//닉네임이 중복된 경우
					
					$('#message_nickname').css('color','red').text('더 멋진 닉네임을 입력하세요!');
					$('#m_nickname').val('').focus();
					checkNickname = 0;
					
				} else {
					
					alert('닉네임 중복체크 오류!!');
				}
			},
			error:function() {
				
				$('#loading').hide();	//로딩 이미지 감추기
				alert('네트워크 오류 발생!!');
			}
			
		});
		
	});
	
	
	//이메일 중복 체크
	$('#confirmEmail').click(function () {
		
		var regex= /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		
		//이메일 입력 여부 판단
		if($('#m_email').val() == '') {
			
			$('#message_email').css('color','red').text('이메일을 입력하세요.');
			$('#m_email').focus();
			
			return;
		}
		
		if(regex.test($('#m_email').val()) === false) {
			
			$('#message_email').css('color','red').text('이메일 형식이 올바르지 않습니다.');
		    
		    return false;
		}
		
		
		$('#message_email').text('');	//메세지 초기화
		$('#loading').show();	//로딩 이미지 노출
		
		$.ajax({
			
			url:'confirmEmail.do',
			type:'post',
			data:{m_email:$('#m_email').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				
				$('#loading').hide();	//로딩 이미지 감추기
				
				if(data.result == 'emailNotFound') {	//이메일이 없는 경우
					
					$('#message_email').css('color','blue').text('사용가능한 이메일입니다!');
					checkEmail = 1;
					
				} else if(data.result == 'emailDuplicated') {	//이메일이 중복된 경우
					
					$('#message_email').css('color','red').text('사용중인 이메일입니다!');
					$('#m_email').val('').focus();
					checkEmail = 0;
					
				} else {
					
					alert('이메일 중복체크 오류!!');
				}
			},
			error:function() {
				
				$('#loading').hide();	//로딩 이미지 감추기
				alert('네트워크 오류 발생!!');
			}
			
		});
		
	});
	
	
	//submit 이벤트 발생시 아이디, 닉네임 , 이메일 중복 체크 여부 , 비빌번호 유효성 체크
	$('#register_form').submit(function () {
		
		//핸드폰번호 체크
		var regExp = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
		//생년월일 체크
		var regBirth = /^(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])$/;
		//이름 체크
		var regName = /^[가-힣a-zA-Z]+$/;
		
		if(checkId == 0) {

			$('#message_id').css('color','red').text('아이디 중복체크 필수');
			$('#m_id').focus();

			return false;
			
		} 
		
		if($('#m_name').val() == '') {
			
			$('#message_name').css('color','red').text('이름을 입력하세요.');
			$('#m_name').focus();

			return false;

		} else if (regName.test($('#m_name').val()) === false) {
			
			$('#message_name').css('color','red').text('세상에 이런 이름이 있나요?');
			$('#m_name').focus();

			return false;
		}
		
		if(checkNickname == 0) {

			$('#message_nickname').css('color','red').text('닉네임 중복 체크 필수.');
			$('#m_nickname').focus();

			return false;

		}

		if($('#m_passwd').val() == '') {
			
			$('#message_pw').css('color','red').text('비밀번호를 입력하세요.');
			$('#m_passwd').val('').focus();
			
			return false;
			
		} else if(!$('#m_passwd').val().match(/([a-zA-Z0-9].*[`,~,!,@,#,$,%,^,&,*,(,),_,-,+,=])|([`,~,!,@,#,$,%,^,&,*,(,),_,-,+,=].*[a-zA-Z0-9])/)) {
			
			$('#message_pw').css('color','red').text('비밀번호는 영문(대소문자구분), 숫자, 특수문자를 혼용하여 10자 이상을 입력하세요.');
			$('#m_passwd').val('').focus();
			
			return false;
		
		}
		
		if($('#checkPw').val() == '') {
			
			$('#message_checkPw').css('color','red').text('비밀번호 확인을 입력하세요.');
			$('#checkPw').focus();
			
			return false;
			
		} else if($('#m_passwd').val() != $('#checkPw').val()) {
			
			$('#message_checkPw').css('color','red').text('비밀번호와 비밀번호 확인이 불일치힙니다.');
			$('#checkPw').val('').focus();
			
			return false;
			
		}
		
		if($('#m_phone').val() == '') {
			
			$('#message_phone').css('color','red').text('전화번호를 입력하세요.');
			$('#m_phone').val('').focus();
			
			return false;
			
		} else if(regExp.test($('#m_phone').val()) === false) {
			
			$('#message_phone').css('color','red').text('-을 제외한 숫자로 정확한 핸드폰 번호를 입력하세요.');
			$('#m_phone').val('').focus();
			
			return false;
			
		}
		
		if($('#m_zipcode').val() == '' || $('#m_address').val() == '') {
			
			$('#message_zipcode').css('color','red').text('우편번호를 입력하세요.');
			
			return false;
			
		}
		
		if($('#m_address_detail').val() == '') {
			
			$('#message_address_detail').css('color','red').text('상세주소를 입력하세요.');
			$('#m_address_detail').val('').focus();
			
			return false;
			
		}
		
		if(checkEmail == 0) {

			$('#message_email').css('color','red').text('이메일 중복 체크 필수.');
			$('#m_email').focus();

			return false;

		}

		if($('#m_birth').val() == '') {
			
			$('#message_birth').css('color','red').text('생년월일을 입력하세요.');
			$('#m_birth').focus();
			
			return false;
			
		} else if(regBirth.test($('#m_birth').val()) === false) {
			
			$('#message_birth').css('color','red').text('"19920816" 형태로 입력하세요.');
			$('#m_birth').val('').focus();
			
			return false;
		}
	});
		
	
	//수정
	$('#modify_form').submit(function () {

		//핸드폰번호 체크
		var regExp = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
		//생년월일 체크
		var regBirth = /^(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])$/;
		//이름 체크
		var regName = /^[가-힣a-zA-Z]+$/;
		
		if($('#m_name').val() == '') {

			$('#message_name').css('color','red').text('이름을 입력하세요.');
			$('#m_name').focus();

			return false;

		} else if (regName.test($('#m_name').val()) === false) {
			
			$('#message_name').css('color','red').text('세상에 이런 이름이 있나요?');
			$('#m_name').focus();

			return false;
			
		} 
		
		if($('#m_passwd').val() == '') {

			$('#message_pw').css('color','red').text('비밀번호를 입력하세요.');
			$('#m_passwd').val('').focus();

			return false;

		} else if(!$('#m_passwd').val().match(/([a-zA-Z0-9].*[`,~,!,@,#,$,%,^,&,*,(,),_,-,+,=])|([`,~,!,@,#,$,%,^,&,*,(,),_,-,+,=].*[a-zA-Z0-9])/)) {

			$('#message_pw').css('color','red').text('비밀번호는 영문(대소문자구분), 숫자, 특수문자를 혼용하여 10자 이상을 입력하세요.');
			$('#m_passwd').val('').focus();

			return false;

		}
		
		if($('#checkPw').val() == '') {

			$('#message_checkPw').css('color','red').text('비밀번호 확인을 입력하세요.');
			$('#checkPw').focus();

			return false;

		} else if($('#m_passwd').val() != $('#checkPw').val()) {

			$('#message_checkPw').css('color','red').text('비밀번호와 비밀번호 확인이 불일치힙니다.');
			$('#checkPw').val('').focus();

			return false;

		}
		
		if($('#m_phone').val() == '') {

			$('#message_phone').css('color','red').text('전화번호를 입력하세요.');
			$('#m_phone').val('').focus();

			return false;

		} else if(regExp.test($('#m_phone').val()) === false) {

			$('#message_phone').css('color','red').text('-을 제외한 숫자로 정확한 핸드폰 번호를 입력하세요.');
			$('#m_phone').val('').focus();

			return false;

		}

		if($('#m_zipcode').val() == '' || $('#m_address').val() == '') {

			$('#message_zipcode').css('color','red').text('우편번호를 입력하세요.');

			return false;

		}

		if($('#m_address_detail').val() == '') {

			$('#message_address_detail').css('color','red').text('상세주소를 입력하세요.');
			$('#m_address_detail').val('').focus();

			return false;

		}

		if($('#m_birth').val() == '') {

			$('#message_birth').css('color','red').text('생년월일을 입력하세요.');
			$('#m_birth').focus();

			return false;

		} else if(regBirth.test($('#m_birth').val()) === false) {

			$('#message_birth').css('color','red').text('"19920816" 형태로 입력하세요.');
			$('#m_birth').val('').focus();

			return false;
		}
	});
	
	clearInput();
	
//--------------------------------------------------
	
	//이메일 도메인 onclick 이벤트
	$('#selectZipcode').click(function() {
		
		new daum.Postcode({
			oncomplete: function(data) {

				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

				var fullAddr = ''; // 최종 주소 변수
				var extraAddr = ''; // 조합형 주소 변수

				// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					
					fullAddr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					
					fullAddr = data.jibunAddress;
				}
				
				// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
				if(data.userSelectedType === 'R'){
					//법정동명이 있을 경우 추가한다.
					if(data.bname !== ''){
						extraAddr += data.bname;
					}
					// 건물명이 있을 경우 추가한다.
					if(data.buildingName !== ''){
						extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}

					// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
					fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				$('#m_zipcode').val(data.zonecode); //5자리 새우편번호 사용
				$('#m_address').val(fullAddr);

				// 커서를 상세주소 필드로 이동한다.
				$('#m_address_detail').focus();
			}
		}).open();
	});
	
});




