$(document).ready(function(){

	//
	$('#re_form').submit(function(event){
		if($('#re_content').val()==''){
			alert('������ �Է��ϼ���');
			$('#re_content').focus();
			return false;
		}
		//��¥�Է� ��ȿ�� üũ
		$('#volunteerWrite_form').submit(function(){
			if($('#v_date').val()==''){
				alert('����Ȱ���� ��û�� ��¥�� �������ּ���');
				$('#v_date').focus();
				return false;
			}
		});	
	});
	
	
	  /* //��� ���� ��ư Ŭ�� �� ������ ����
	   $(document).on('click', '.modify-btn', function(){
	      //��� �� ��ȣ
	      var re_num = $(this).attr('data-num');
	      //�ۼ��� ���̵�
	      var id = $(this).attr('data-id');
	      //��� ����(�θ� ���� p�±� ���� �ؽ�Ʈ�� �о��)
	      var content = $(this).parent().find('p').text();
	      
	      //��� ������ UI
	      var modifyUI = '<form id = "mre_form">';
	         modifyUI +=   '   <input type="hidden" name="re_num" id="mre_num" value="'+re_num+'">';       
	         modifyUI +=   '   <input type="hidden" name="id" id="id" value="'+id+'">';       
	         modifyUI +=   '   <textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content">'+content+'</textarea>';       
	         modifyUI +=   '   <div id="mre_first"><span class="letter-count">300/300</span></div>';       
	         modifyUI +=   '   <div id="mre_second" class="align-right">';
	         modifyUI +=   '      <input type="submit" value="����">';
	         modifyUI +=   '      <input type="button" value="���" class="re-reset>';
	         modifyUI +=   '   </div>';
	         modifyUI +=   '   <hr size="1" noshade width="96%">';
	         modifyUI +=   '</form>';
	         
	         //������ �̹� �����ϴ� ����� ���� ��� ���� ��ư�� Ŭ���ϸ� ���� sub-item�� ȯ����Ű�� �������� �ʱ�ȭ��
	         initModifyForm();
	         
	         //���� Ŭ���ؼ� �����ϰ��� �ϴ� �����ʹ� ���߱�, ������ư�� ���ΰ� �ִ� div
	         $(this).parent().hide();
	         
	         //�������� �����ϰ��� �ϴ� �����Ͱ� �ִ� div�� ����
	         $(this).parents('.item').append(modifyUI);
	         
	         //�Է��� ���� �� ����
	         var inputLength = $('#mre_content').val().length;
	         var remain = 300 - inputLength;
	         remain += '/300';
	         
	         //���� ��ü�� �ݿ�
	         $('#mre_first .letter-count').text(remain);
	   });
	   
	   //��� ���� �� ��� ��ư Ŭ�� �� ������ �ʱ�ȭ
	   $(document).on('click', '.re-reset', function(){
	      initModifyForm();
	   });
	   
	   //��� ������ �ʱ�ȭ
	   function initModifyForm(){
	      $('.sub-item').show();
	      $('#mre_form').remove();
	   }
	   
	   //��� ����: �̷��� �±�, �⺻ �̺�Ʈ ����
	   $(document).on('submit', '#mre_form', function(event){
	      if($('#mre_content').val() == ''){
	         alert('������ �Է��ϼ���!');
	         $('#mre_content').focus();
	         return false();
	      }
	      
	      //���� �Է��� ������ ��ȯ
	      var data = $(this).serialize();
	      console.log(data);
	      //���� �۾�
	      $.ajax({
	         url: 'updateReply.do',
	         type: 'post',
	         data: data,
	         dataType: 'json',
	         cache: false,
	         timeout: 10000,
	         success: function(data){
	            if(data.result == 'logout'){
	               alert('�α����� �ʿ��մϴ�!');
	            }else if(data.result == 'success'){
	               $('#mre_form').parent().find('p').text($('#mre_content').val());
	               //������ �ʱ�ȭ
	               initModifyForm();
	            }else if(data.result == 'wrongAccess'){
	               alert('Ÿ���� ����� ������ �� �����ϴ�!');
	            }
	         },
	         error: function(){
	            alert('��� ���� ��Ʈ��ũ ����!');
	         }
	      });
	      //�⺻ �̺�Ʈ ����
	      event.preventDefault();
	   });

	//��� ���
	$('#re_form').submit(function(event){
		if($('#re_content').val()==''){
			alert('������ �Է��ϼ���');
			$('#re_content').focus();
			return false;
		}
		var data = $(this).serialize();
		
		//���
		$.ajax({
			type:'post',
			data:data,
			url:'writeReply.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('�α��� �ؾ� �ۼ��� �� �ֽ��ϴ�.')
				}else if(data.result =='success'){
					//�� �ʱ�ȭ
					initForm();
					//��� ȣ��
					selectData(1,$('#num').val());
				}else{
					alert('��� �� ���� �߻�');
				}
			},
			error:function(){
				alert('��� �� ��Ʈ��ũ ���� �߻�');
			}
		});
		//�⺻ �̺�Ʈ ����
		 event.preventDefault();
	});
	
	//��� �ۼ� �� �ʱ�ȭ
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	
	
	
	
	//��� ����
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
					alert('�α����ؾ� ������ �� �ֽ��ϴ�.');
				}else if(data.result == 'success'){
					alert('���� �Ϸ�');
					selectData(1,$('#num').val());
				}else if(data.result == 'wrongAccess'){
					alert('Ÿ���� ���� ������ �� �����ϴ�.');
				}else{
					alert('���� �� ���� �߻�');
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
*/
});
