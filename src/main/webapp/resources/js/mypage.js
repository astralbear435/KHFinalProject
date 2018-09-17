$(document).ready(function(){
	//����Ȱ�� ���� ����
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
			url:'volunteerUpdate.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('�α��� �ؾ� �ۼ��� �� �ֽ��ϴ�.')
				}else if(data.result =='success'){
					//�� �ʱ�ȭ
					initForm();
			
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
	//����Ȱ�� ���� ����
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
					alert('����Ȱ�� ������ ��ҵǾ����ϴ�.');
				}else if(data.result == 'wrongAccess'){
					alert('Ÿ���� ������ ����� �� �����ϴ�.');
				}else{
					alert('������ �߻��߽��ϴ�. �ٽ� �õ����ּ���');
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});

});

