$(document).ready(function(){
	//봉사모집글 작성 유효성 체크
	$('#recruitWrite_form').submit(function(){
		if($('#r_upload').val()==''){
			alert('보호소를 소개할 이미지를 반드시 첨부해주세요');
			$('#r_upload').focus();
			return false;
		}
		if($('#r_title').val()==''){
			alert('글 제목을 작성해주세요');
			$('#r_title').focus();
			return false;
		}
		if($('#r_status').val()==''){
			alert('모집 기간을 설정해주세요');
			$('#r_status').focus();
			return false;
		}
		//적용 안됨
		if($('#r_status').val()=='1'){
			document.getElementById("r_start_date").disabled = true;
			document.getElementById("r_end_date").disabled = true;
		}
		
		//적용 안됨
		if($('#r_status').val()=='2'){
			if($('#r_start_date').val()==''||$('#r_end_date').val()==''){
				$('#r_end_date').val('');
				$('#r_start_date').val('').focus();
				alert('모집 날짜를 지정해주세요');
			}			
			return false;
		}
		
		if(new Date($('#r_start_date').val()) > new Date($('#r_end_date').val())){
			alert('모집 시작 날짜 뒤로 마감 날짜를 지정해야 합니다.');
			$('#r_end_date').val('');
			$('#r_start_date').val('').focus();
			return false;
		}	
		if($('#r_people').val()==''){
			alert('일일 모집 인원을 설정 해주세요');
			$('#r_people').focus();
			return false;
		}
		if($('#r_content').val()==''){
			alert('보호소 봉사활동 안내글을 작성해주세요');
			$('#r_content').focus();
			return false;
		}	
		alert('봉사참여자가 존재하면 향후 글을 수정하는데 제약이 있을 수 있습니다. 이대로 글을 올리시겠습니까?');
		return true;
	});



	$('#recruitMidify_form').click(function(){
		 //적용 안됨
		if($("#r_status").val()=='1'){
			 $("#r_status1").prop("checked", true);
		 }
		 if($("#r_status").val()=='2'){
			 $("#r_status2").prop("checked", true);
		 }
		 alert('이대로 글을 올리시겠습니까?');
	});
});