$(document).ready(function(){
	//��������� �ۼ� ��ȿ�� üũ
	$('#recruitWrite_form').submit(function(){
		if($('#r_upload').val()==''){
			alert('��ȣ�Ҹ� �Ұ��� �̹����� �ݵ�� ÷�����ּ���');
			$('#r_upload').focus();
			return false;
		}
		if($('#r_title').val()==''){
			alert('�� ������ �ۼ����ּ���');
			$('#r_title').focus();
			return false;
		}
		if($('#r_status').val()==''){
			alert('���� �Ⱓ�� �������ּ���');
			$('#r_status').focus();
			return false;
		}
		//���� �ȵ�
		if($('#r_status').val()=='1'){
			document.getElementById("r_start_date").disabled = true;
			document.getElementById("r_end_date").disabled = true;
		}
		
		//���� �ȵ�
		if($('#r_status').val()=='2'){
			if($('#r_start_date').val()==''||$('#r_end_date').val()==''){
				$('#r_end_date').val('');
				$('#r_start_date').val('').focus();
				alert('���� ��¥�� �������ּ���');
			}			
			return false;
		}
		
		if(new Date($('#r_start_date').val()) > new Date($('#r_end_date').val())){
			alert('���� ���� ��¥ �ڷ� ���� ��¥�� �����ؾ� �մϴ�.');
			$('#r_end_date').val('');
			$('#r_start_date').val('').focus();
			return false;
		}	
		if($('#r_people').val()==''){
			alert('���� ���� �ο��� ���� ���ּ���');
			$('#r_people').focus();
			return false;
		}
		if($('#r_content').val()==''){
			alert('��ȣ�� ����Ȱ�� �ȳ����� �ۼ����ּ���');
			$('#r_content').focus();
			return false;
		}	
		alert('���������ڰ� �����ϸ� ���� ���� �����ϴµ� ������ ���� �� �ֽ��ϴ�. �̴�� ���� �ø��ðڽ��ϱ�?');
		return true;
	});



	$('#recruitMidify_form').click(function(){
		 //���� �ȵ�
		if($("#r_status").val()=='1'){
			 $("#r_status1").prop("checked", true);
		 }
		 if($("#r_status").val()=='2'){
			 $("#r_status2").prop("checked", true);
		 }
		 alert('�̴�� ���� �ø��ðڽ��ϱ�?');
	});
	

	
	


});