$(document).ready(function() {

	//submit 이벤트 발생시 아이디, 닉네임 , 이메일 중복 체크 여부 , 비빌번호 유효성 체크
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
	
});
