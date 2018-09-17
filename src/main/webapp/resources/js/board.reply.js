$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount;
	
	//댓글 목록
	function selectData(pageNum,num){
		currentPage = pageNum;
		
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('#output').empty();
		}
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,num:num},
			url:'listReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출 오류 발생!');
				}else {
					$(list).each(function(index,item){
						var output = '<div class="item">';
						output += item.id+'<br>';
						output += ' <font size="0.3">'+item.re_date+'</font>';
						output += ' <div class="sub-item">';
						output += '   <p>' + item.re_content + '</p>';
			
						if($('#user_id').val()==item.id){
							//로그인 한 id가 댓글 작성자 id와 같으면
						    output += '  <input type="button" data-num="'+item.re_num+'" data-id="'+item.id+'" value="댓글" class="reply-btn">';
							output += '  <input type="button" data-num="'+item.re_num+'" data-id="'+item.id+'" value="수정" class="modify-btn">';
							output += '  <input type="button" data-num="'+item.re_num+'" data-id="'+item.id+'" value="지우기" class="delete-btn">';
						}
						output += '   <hr width="70%" noshade>';
						output += ' </div>';
						output += '</div>';
						
						//문서 객체에 추가
						$('#output').append(output);
					});
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음 페이지가 없음
						$('.paging-button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging-button').show();
					}
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류');
			}
		});
		
	}
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging-button input').click(function(){
		var pageNum = currentPage + 1;
		selectData(pageNum,$('#num').val());
	});
	//댓글 등록
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
			url:'writeReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(data.result == 'success'){
					//폼 초기화
					initForm();
					//목록 호출
					selectData(1,$('#num').val());
				}else{
					alert('등록시 오류 발생');
				}
			},
			error:function(){
				alert('등록시 네트워크 오류 발생!');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수를 수함
		var inputLength = $(this).val().length;
		
		if(inputLength>300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			var remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id')=='re_content'){
				//등록폼 글자수
				$('#re_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
		}
		
	});
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글 글번호
		var re_num = $(this).attr('data-num');
		//작성자 아이디
		var id = $(this).attr('data-id');
		//댓글 내용
		var re_content = $(this).parent().find('p').text();
		
		//댓글 수정폼 UI
		var modifyUI = '<form id="mre_form">';
		   modifyUI += ' <input type="hidden" name="re_num" id="mre_num" value="'+re_num+'">';
		   modifyUI += ' <input type="hidden" name="id" id="id" value="'+id+'">';
		   modifyUI += ' <textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content">'+re_content+'</textarea>';
		   modifyUI += ' <div id="mre_first"><span class="letter-count">300/300</span></div>';
		   modifyUI += ' <div id="mre_second" class="align-right">';
		   modifyUI += ' <input type="submit" value="수정">';
		   modifyUI += ' <input type="button" value="취소" class="re-reset">';
		   modifyUI += ' </div>';
		   modifyUI += ' <hr size="1" noshade width="70%">';
		   modifyUI += '</form>';
		   
		   //이전에 이미 수정하는 댓글이 있을 경우 
		   //수정버튼을 클릭하면 숨김 sub-item을
		   //환원시키고 수정폼을 초기화함
		   initModifyForm();
		   
		   //지금 클릭해서 수정하고자 하는 데이터는 
		   //감추기
		   //수정버튼을 감싸고 있는 div
		   $(this).parent().hide();
		   
		   //수정폼을 수정하고자하는 데이티가 있는
		   //div에 노출
		   $(this).parents('.item').append(modifyUI);
		   
		   //입력한 글자수 셋팅
		   var inputLength = $('#mre_content').val().length;
		   var remain = 300 - inputLength;
		   remain += '/300';
		   
		   //문서 객체에 반영
		   $('#mre_first .letter-count').text(remain);
		
	});
	//댓글 수정 폼 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	//댓글 수정 폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	//댓글 수정
	$(document).on('submit','#mre_form',function(event){
		if($('#mre_content').val()==''){
			alert('내용을 입력하세요!');
			$('#mre_content').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//수정
		$.ajax({
			type:'post',
			url:'updateReply.do',
			data:data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(data.result=='success'){
					$('#mre_form').parent().find('p').text($('#mre_content').val());
					//수정폼 초기화
					initModifyForm();
				}else if(data.result=='wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}
			},
			error:function(){
				alert('네트워크 오류이다.');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//대댓글 버튼 클릭시 수정폼 노출
	$(document).on('click','.reply-btn',function(){
		//댓글 글번호
		var re_num = $(this).attr('data-num');
		//작성자 아이디
		var id = $(this).attr('data-id');
		//댓글 내용
		var re_content = $(this).parent().find('p').text();
		
		//대댓글UI
		var replyUI = '<form id="are_form">';
		   replyUI += ' <input type="hidden" name="pt_num" id="pt_num" value="'+pt_num+'">';
		   replyUI += ' <input type="hidden" name="depth" id="depth" value="'+depth+'">';
		   replyUI += ' <textarea rows="3" cols="50" name="re_content" id="are_content" class="rep-content">'+re_content+'</textarea>';
		   replyUI += ' <div id="are_first"><span class="letter-count">300/300</span></div>';
		   replyUI += ' <div id="are_second" class="align-right">';
		   replyUI += ' <input type="submit" value="댓글">';
		   replyUI += ' <input type="button" value="취소" class="re-reset">';
		   replyUI += ' </div>';
		   replyUI += ' <hr size="1" noshade width="70%">';
		   replyUI += '</form>';
		   
		   //이전에 이미 댓글을 작성하는 댓글이 있을 경우 
		   //대댓버튼을 클릭하면 숨김 sub-item을
		   //환원시키고 댓글폼을 초기화함
		   initReplyForm();
		   
		   //지금 클릭해서 댓글을 하고자 하는 데이터는 
		   //감추기
		   //댓글버튼을 감싸고 있는 div
		   $(this).parent().hide();
		   
		   //댓글폼을 수정하고자하는 데이터가 있는
		   //div에 노출
		   $(this).parents('.item').append(replyUI);
		   
		   //입력한 글자수 셋팅
		   var inputLength = $('#are_content').val().length;
		   var remain = 300 - inputLength;
		   remain += '/300';
		   
		   //문서 객체에 반영
		   $('#mre_first .letter-count').text(remain);
		
	});
	
	//댓댓글 
	$(document).on('submit','#are_form',function(event){
		if($('#are_content').val()==''){
			alert('내용을 입력하세요!');
			$('#are_content').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//대댓글
		$.ajax({
			type:'post',
			url:'answerReply.do',
			data:data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(data.result=='success'){
					$('#are_form').parent().find('p').text($('#are_content').val());
					//댓글폼 초기화
					initReplyForm();
				}else if(data.result=='wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}
			},
			error:function(){
				alert('네트워크 오류이다.');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	//댓글 폼 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initReplyForm();
	});
	//댓글  폼 초기화
	function initReplyForm(){
		$('.sub-item').show();
		$('#are_form').remove();
	}
	
	
	//댓글 삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		var re_num = $(this).attr('data-num');
		//작성자 아이디
		var id = $(this).attr('data-id');
		
		$.ajax({
			type:'post',
			url:'deleteReply.do',
			data:{re_num:re_num,id:id},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result == 'success'){
					alert('삭제 완료!');
					selectData(1,$('#num').val());
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
	});
	//초기 데이터(목록) 호출
	selectData(1,$('#num').val());
});




