// 일괄 체크/해제
function checkAll(){
	if($('.checkAll').is(':checked')){
		$('input[name=checkRow]').prop("checked",true);
	}else{
		$('input[name=checkRow]').prop("checked",false);
	}
}

// 체크박스 삭제처리
function deleteAction(){
	var checkList = "";
	
	$('input[name=checkRow]:checked').each(function(){
		checkList += $(this).attr('data-num') + ',';
	});
	
	checkList = checkList.substring(0,checkList.lastIndexOf(",")); // 맨끝 콤마는 지움
	
	if(checkList == ''){
		alert('삭제할 대상을 선택하세요');
		return false;
	}
	
	console.log('### checkList > {}' + checkList);
	
	if(confirm('체크한 쪽지를 모두 삭제합니다. 삭제하시겠습니까?')){
		location.replace('checkDelete.do?checkList=' + checkList);
	}else{
		return;
	}
}