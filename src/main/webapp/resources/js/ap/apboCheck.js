$(document).ready(function(){
	
	//새로고침해도 상태를 유지하기위해
	function roadData(){
		var bo_num=$('#bookcheck').attr('data-num');//글 번호	
		$.ajax({
			type:'post',
			data:{call_num:bo_num},
			url:'boCheck.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var count=data.count;
				var callList = data.callList;
				if(count>=1){
					//예약 완료
					var str="";
					str+='<p>[예약 완료]</p>';
					$('#bookcheck').empty();
					$('#bookcheck').append(str);
				}else{
					//예약 미완료
					var str="";
					str+='<p>[예약 가능]</p>';
					$('#bookcheck').empty();
					$('#bookcheck').append(str);	
				}
						
			},error:function(){
					alert('로드시 오류 ');
				}
			});
	}

	 roadData();
});