$(document).ready(function() {
	
	//처음 설문 작성시 유효성 체크
	$('#ap_form').submit(function () {
		
		if(!($("input:radio[name='ap_job']").is(":checked"))) {
			
			$('#message_ap_job').css('color','red').text('현재 직업을 선택해주세요!');
			$('#ap_job').val('').focus();

			return false;
		}
		
		if(!($("input:checkbox[name='ap_cer']").is(":checked"))) {
			
			$('#message_ap_cer').css('color','red').text('보유한 자격증을 선택하세요. 없으면 [없음]을 선택해주세요!');
			$('#ap_cer').val('').focus();
			
			return false;
		}
		
		if(!($("input:radio[name='ap_act']").is(":checked"))) {
			
			$('#message_ap_act').css('color','red').text('펫시터 활동 경력을 선택해주세요!');
			$('#ap_act').val('').focus();
			
			return false;
		}
		
		if(!($("input:radio[name='ap_pet']").is(":checked"))) {
			
			$('#message_ap_pet').css('color','red').text('반려동물 키워본 경력을 선택해주세요!');
			$('#ap_pet').val('').focus();
			
			return false;
		}
		
		if($('#ap_ser').val() == '') {
			
			$('#message_ap_ser').css('color','red').text('반려동물 소개를 해주세요!');
			$('#ap_ser').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_home']").is(":checked"))) {
			
			$('#message_ap_home').css('color','red').text('거주지를 선택하세요.');
			$('#ap_home').val('').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_sel']").is(":checked"))) {
			
			$('#message_ap_sel').css('color','red').text('임시보호 유형을 선택하세요.');
			$('#ap_sel').val('').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_nopet']").is(":checked"))) {
			
			$('#message_ap_nopet').css('color','red').text('케어 불가한 유형을 선택하세요.');
			$('#ap_nopet').val('').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_service']").is(":checked"))) {
			
			$('#message_ap_service').css('color','red').text('제공 가능한 서비스 유형을 선택하세요.');
			$('#ap_service').val('').focus();
			
			return false;
		}
		
		if(!($("input:radio[name='ap_mon']").is(":checked"))) {
			
			$('#message_ap_mon').css('color','red').text('임시보호 활동 가능 일 수를 선택해주세요!');
			$('#ap_mon').val('').focus();

			return false;
		}
		
	});

	//글 수정시 유효성 체크
	$('#ap_update_form').submit(function () {
		
		if(!($("input:checkbox[name='ap_cer']").is(":checked"))) {
			
			$('#message_ap_cer').css('color','red').text('보유한 자격증을 선택하세요. 없으면 [없음]을 선택해주세요.');
			$('#ap_cer').val('').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_home']").is(":checked"))) {
			
			$('#message_ap_home').css('color','red').text('거주지를 선택하세요.');
			$('#ap_home').val('').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_nopet']").is(":checked"))) {
			
			$('#message_ap_nopet').css('color','red').text('케어 불가한 유형을 선택하세요.');
			$('#ap_nopet').val('').focus();
			
			return false;
		}
		
		if(!($("input:checkbox[name='ap_service']").is(":checked"))) {
			
			$('#message_ap_service').css('color','red').text('제공 가능한 서비스 유형을 선택하세요.');
			$('#ap_service').val('').focus();
			
			return false;
		}
		
	});
	
	//글 삭제 여부
	$('#apdelete').click(function(){
		var ap_num=$(this).attr('data-num');
		if (confirm("정말 삭제하시겠습니까??") == true){//확인
			document.location.href='apdelete.do?ap_num='+ap_num;
		}else{//취소
			return;
		}
	});
	
	//예약 확인 여부
	$('#apbooking').click(function(){
		var ap_num=$(this).attr('data-num');
		
		if (confirm("정말 예약하시겠습니까??") == true){//확인
			document.location.href='apdelete.do?ap_num='+ap_num;
		}else{//취소
			return false;
		}
	});
	
	
});
