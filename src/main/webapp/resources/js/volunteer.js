$(document).ready(function(){

	//
	$('#re_form').submit(function(event){
		if($('#re_content').val()==''){
			alert('내용을 입력하세요');
			$('#re_content').focus();
			return false;
		}
		//날짜입력 유효성 체크
		$('#volunteerWrite_form').submit(function(){
			if($('#v_date').val()==''){
				alert('봉사활동을 신청할 날짜를 선택해주세요');
				$('#v_date').focus();
				return false;
			}
		});	
	});
	
	


});
