$(document).ready(function(){
	//봉사활동 일정 변경
	$('#re_form').submit(function(event){
		if($('#re_content').val()==''){
			alert('내용을 입력하세요');
			$('#re_content').focus();
			return false;
		}
		var data = $(this).serialize();
		
		//등록
		$.ajax({
			type:'post',
			data:data,
			url:'volunteerUpdate.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인 해야 작성할 수 있습니다.')
				}else if(data.result =='success'){
					//폼 초기화
					initForm();
			
				}else{
					alert('등록 시 오류 발생');
				}
			},
			error:function(){
				alert('등록 시 네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		 event.preventDefault();
	});
	//봉사활동 일정 삭제
	$('.paging-button input').click(function(){
			
		$.ajax({
			type:'post',
			url:'volunteerDelete.do',
			data:{v_num:'${v_num}'},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'success'){
					alert('봉사활동 일정이 취소되었습니다.');
				}else if(data.result == 'wrongAccess'){
					alert('타인의 일정을 취소할 수 없습니다.');
				}else{
					alert('오류가 발생했습니다. 다시 시도해주세요');
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});

});

