$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount;
	//초기데이터 호출(목록)
	selectData(1,$('#reply_num').val());
	//댓글 목록
	function selectData(pageNum,num){
		currentPage=pageNum;
		
		if(pageNum==1){
			//처음호출시엔 해당 아이디는 div내부 내용물을 비운다.
			$('#output').empty();
		}
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,num:num},
			url:'listReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count=data.count;
				rowCount=data.rowCount;
				var list=data.list;
				
				if(count<0||list==null){
					alert('댓글 호출 오류');
				}else{
					$(list).each(function(index,item){
						var output='<div class="item" style="background-color: #DDDDDD;border-radius: 20px;padding: 2%;">';
						output+='<h3 style="color:black">'+item.reply_id+'</h3>';
						output+='<div class="sub-item">';
						output+='<p style="margin-left: 2%; font-size: 119%;">'+item.reply_content+'</p>';
						if($('#reply_id').val()==item.reply_id){
							//로그인한 아이디가 댓글 작성자 아이디와 같으면
							output+='<input type="button" class="modify-btn btn-warning " data-num="'+item.reply_mynum+'" data-id="'+item.reply_id+'" value="수정" style="float:right;">&nbsp;';
							output+='<input type="button" data-num="'+item.reply_mynum+'" data-id="'+item.reply_id+'" value="삭제" class="btn-warning delete-btn" style="float:right;">';
						};
						output+=item.reply_date;
						output+='</div>';
						output+='</div><br>';						
						//문서객체에 추가
						$('#output').append(output);
					});
				//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음페이지가 없음
						$('.paging-button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging-button').show();
					}
				}
			},
			error:function(){
			}
		});
	}	
	
	// 댓글 등록
	$('#cursor').click(function(event){
		if($('#reply_content').val()==''){
			alert('내용을 입력 하세요');
			$('#reply_content').focus();
			return false;
		}
		var data=$('#re_form').serialize();
		//등록
		$.ajax({
			type:'post',
			data:data,
			url:'insertReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(data.result=='success'){
					//폼 초기화
					initForm();
					//목록호출
					selectData(1,$('#reply_num').val());
				}else{
					alert('댓글 등록 오류!');
					}
			},
			error:function(){
				alert('댓글 등록 오류-2');
			}
		});
		// 기본이벤트 제거
		event.preventDefault();		
	});
//폼 초기화	
function initForm(){
		$('#reply_content').val('');
	};	
//댓글삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		var reply_mynum=$(this).attr('data-num');
		//작성자 아이디
		var reply_id=$(this).attr('data-id');
		$.ajax({
			type:'post',
			url:'deleteReply.do',
			data:{reply_mynum:reply_mynum,reply_id:reply_id},
			dataType:'json',
			timeout:30000,
			success:function(data){
				if(data.result=='logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result=='success'){
					alert('해당 댓글 삭제를 완료했습니다');
					selectData(1,$('#reply_num').val());
				}else if(data.result=='wrongAccess'){
					alert('타인의 댓글은 삭제할 수 없습니다.');
				}else{
					alert('삭제 중 오류발생.');
				}
			},error:function(){
				alert('삭제 중 오류발생2');
			}
		});
	});
	
//댓글 수정 버튼 클릭시 수정 폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글 글 번호
		var reply_mynum=$(this).attr('data-num');
		//작성자 아이디
		var reply_id=$(this).attr('data-id');
		//댓글 내용
		var reply_content=$(this).parent().find('p').text();
		//댓글 수정 폼 ui
		var modifyUI='<form id="mre_form">';
		modifyUI+='<input type="hidden" name="reply_mynum" id="reply_mynum" value="'+reply_mynum+'">';
		modifyUI+='<input type="hidden" name="reply_id" id="reply_id" value="'+reply_id+'">';
		modifyUI+='<textarea style="width:80%;display: inline-block;" name="reply_content" id="reply_content" class="rep-content">'+reply_content+'</textarea>';
		modifyUI+='<div id="mre_second" class="align-right" style="display: inline-block;margin-left: 2%;width: 18%;">';
		modifyUI+='<input type="submit" class="btn-warning" style="display: inline-block;" value="수정" >';
		modifyUI+='<input type="button" class="re-reset btn-warning" style="display: inline-block;" value="취소" >';
		modifyUI+='</div>';
		modifyUI+='</form>';
		//수정버튼을 클릭하면 숨김 sub-item을 환원시키고 수정폼을 초기화함
	 initModifyForm();
	 //지금 클릭해서 수정코자 하는 데이터는 감춤,수정버튼을 감싸고있는 div 
	$(this).parent().hide();
	//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
	$(this).parents('.item').append(modifyUI);
	});
//댓글 수정 폼 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	//댓글수정 폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
//댓글 수정
		$(document).on('submit','#mre_form',function(event){
			if($('#reply_content').val==''){
				alert('내용을 입력하세요!');
				$('#reply_content').focus();
				return false;
			};
		
			var data=$(this).serialize();
			$.ajax({
				type:'post',
				url:'updateReply.do',
				data:data,
				cash:false,
				dataType:'json',
				timeout:30000,
				success:function(data){
					if(data.result=='logout'){
						alert('로그인해야 작성 가능합니다.');
					}else if(data.result=='success'){
						$('#mre_form').parent().find('p').text(data.content);
						//수정폼 초기화
						initModifyForm();
					}else if(data.result=='wrongAccess'){
						alert('타인의 글은 수정 불가합니다.');
					}
				},error:function(){
					alert('댓글 수정중 오류');
				}
			});
			//기본이벤트 제거
			event.preventDefault();		
		});	
});