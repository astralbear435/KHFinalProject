$(document).ready(function(){
    //최상단 체크박스 클릭
    $("#allCheck").click(function(){
        //클릭되었으면
        if($("#allCheck").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=checkNum]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=checkNum]").prop("checked",false);
        }
    });
    //
    $('.agreement').change(function() {
		var id=$(this).attr('id');
		var auth=$(this).val();
		
		$.ajax({
			type:'post',
			data:{auth:auth,id:id},
			url:'shelterAgreement.do',
			cache:false,
			timeout:30000,
			success:function(){
				if(auth=='3'){
					alert("승인처리가 해제되었습니다.")
				}else if(auth=='4'){
					alert("승인처리가 되었습니다.")
				}
			},error:function(){
				alert('네트워크 오류');
			}
		});
    });  
})