$(document).ready(function(){
	//선택삭제 삭제눌렀을때
	$('#delete_cart').click(function(){
	 var selectNum="";
		  $( "input[name='select_me']:checked" ).each(function (){
		      var selectNum=""+$(this).val()+","; //여러개선택으로 배열만들기
		  });
		  selectNum=selectNum.substring(0,selectNum.lastIndexOf(","));
		  var id=$('#id').val();
					$.ajax({
						type:'post',
						data:{selectNum:selectNum,id:id},
						url:'deleteCart.do',
						dataType:'json',
						cache:false,
						timeout:30000,
						success:function(data){
							if(data.result=='logout'){
								alert('로그인 후 이용해 주세요.');
							}else if(data.result=='success'){
								location.reload();
							}else{
								alert('세션이 만료되었습니다. 다시 시도해주세요.');
							}							
						},error:function(){
							location.reload();
						}
				     });	  
		  });
	

	$('#order').click(function(){
		alert('전체결제~~~~');
	});
});