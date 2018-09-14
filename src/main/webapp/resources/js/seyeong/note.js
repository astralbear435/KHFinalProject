$(document).ready(function(){
	/* 쪽지 발송 시 팝업 꺼짐 */
	// 발송
	$('#noteWriteForm').submit(function(event){
		var data = $(this).serialize();
		$.ajax({
			type:'post',
			data:data,
			url:'writeAjax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'success'){
					self.close();
					opener.document.location.reload();
				}else if(data.result == 'noData'){
					alert('누락된 내용이 있습니다.');
				}else{
					alert('오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		event.preventDefault();
	});
	
	// 답장
	$('#noteReplyForm').submit(function(event){
		var data = $(this).serialize();
		$.ajax({
			type:'post',
			data:data,
			url:'writeAjax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'success'){
					self.close();
					opener.document.location.reload();
				}else if(data.result == 'noData'){
					alert('누락된 내용이 있습니다.');
				}else{
					alert('오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		event.preventDefault();
	});
});