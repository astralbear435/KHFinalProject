$(document).ready(function() {
	
//-----------사전신청 여부 설정 시작--------------
	//라디오 버튼 변경시 이벤트
	$("input[name='call_re']:radio").change(function (){
		//라디오 버튼 값을 가져온다.
		var call_re = this.value;
		
		if(call_re=="1"){//신청함
			$("input#call_re_date").attr("readonly", false);
			$("input#call_re_date").css("background","none");
			$("select#call_re_hour").attr("readonly", false);
			$("select#call_re_hour").css("background","none");
			$("select#call_re_min").attr("readonly", false);
			$("select#call_re_min").css("background","none");
		}else if(call_re=="2"){//신청안함
			$("input#call_re_date").attr("readonly", true);
			$("input#call_re_date").css("background","#ccc");
			$("select#call_re_hour").attr("readonly", true);
			$("select#call_re_hour").css("background","#ccc");
			$("select#call_re_min").attr("readonly", true);
			$("select#call_re_min").css("background","#ccc");
		}
	});
//-----------사전신청 여부 설정 끝--------------

	//글 삭제 여부
	$('#apCalldelete').click(function(){
		var call_num=$(this).attr('data-num');
		if (confirm("정말 삭제하시겠습니까??") == true){//확인
			document.location.href='apCalldelete.do?call_num='+call_num;
		}else{//취소
			return;
		}
	});
	

	
});
