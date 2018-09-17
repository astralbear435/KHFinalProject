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
	
	
	  /* //댓글 수정 버튼 클릭 시 수정폼 노출
	   $(document).on('click', '.modify-btn', function(){
	      //댓글 글 번호
	      var re_num = $(this).attr('data-num');
	      //작성자 아이디
	      var id = $(this).attr('data-id');
	      //댓글 내용(부모 영역 p태그 안의 텍스트를 읽어옴)
	      var content = $(this).parent().find('p').text();
	      
	      //댓글 수정폼 UI
	      var modifyUI = '<form id = "mre_form">';
	         modifyUI +=   '   <input type="hidden" name="re_num" id="mre_num" value="'+re_num+'">';       
	         modifyUI +=   '   <input type="hidden" name="id" id="id" value="'+id+'">';       
	         modifyUI +=   '   <textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content">'+content+'</textarea>';       
	         modifyUI +=   '   <div id="mre_first"><span class="letter-count">300/300</span></div>';       
	         modifyUI +=   '   <div id="mre_second" class="align-right">';
	         modifyUI +=   '      <input type="submit" value="수정">';
	         modifyUI +=   '      <input type="button" value="취소" class="re-reset>';
	         modifyUI +=   '   </div>';
	         modifyUI +=   '   <hr size="1" noshade width="96%">';
	         modifyUI +=   '</form>';
	         
	         //이전에 이미 수정하는 댓글이 있을 경우 수정 버튼을 클릭하면 숨김 sub-item을 환원시키고 수정폼을 초기화함
	         initModifyForm();
	         
	         //지금 클릭해서 수정하고자 하는 데이터는 감추기, 수정버튼을 감싸고 있는 div
	         $(this).parent().hide();
	         
	         //수정폼을 수정하고자 하는 데이터가 있는 div에 노출
	         $(this).parents('.item').append(modifyUI);
	         
	         //입력한 글자 수 셋팅
	         var inputLength = $('#mre_content').val().length;
	         var remain = 300 - inputLength;
	         remain += '/300';
	         
	         //문서 객체에 반영
	         $('#mre_first .letter-count').text(remain);
	   });
	   
	   //댓글 수정 폼 취소 버튼 클릭 시 수정폼 초기화
	   $(document).on('click', '.re-reset', function(){
	      initModifyForm();
	   });
	   
	   //댓글 수정폼 초기화
	   function initModifyForm(){
	      $('.sub-item').show();
	      $('#mre_form').remove();
	   }
	   
	   //댓글 수정: 미래의 태그, 기본 이벤트 제거
	   $(document).on('submit', '#mre_form', function(event){
	      if($('#mre_content').val() == ''){
	         alert('내용을 입력하세요!');
	         $('#mre_content').focus();
	         return false();
	      }
	      
	      //폼에 입력한 데이터 반환
	      var data = $(this).serialize();
	      console.log(data);
	      //수정 작업
	      $.ajax({
	         url: 'updateReply.do',
	         type: 'post',
	         data: data,
	         dataType: 'json',
	         cache: false,
	         timeout: 10000,
	         success: function(data){
	            if(data.result == 'logout'){
	               alert('로그인이 필요합니다!');
	            }else if(data.result == 'success'){
	               $('#mre_form').parent().find('p').text($('#mre_content').val());
	               //수정폼 초기화
	               initModifyForm();
	            }else if(data.result == 'wrongAccess'){
	               alert('타인의 댓글은 수정할 수 없습니다!');
	            }
	         },
	         error: function(){
	            alert('댓글 수정 네트워크 오류!');
	         }
	      });
	      //기본 이벤트 제거
	      event.preventDefault();
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
					alert('로그인 해야 작성할 수 있습니다.')
				}else if(data.result =='success'){
					//폼 초기화
					initForm();
					//목록 호출
					selectData(1,$('#num').val());
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
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	
	
	
	
	//댓글 삭제
	$('.delete input').click(function(){
		$.ajax({
			type:'post',
			url:'deleteUpdate.do',
			data:{re_num:re_num,id:id},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result == 'success'){
					alert('삭제 완료');
					selectData(1,$('#num').val());
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('삭제 시 오류 발생');
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
*/
});
