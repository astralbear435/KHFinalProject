<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- bootstrap-css -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap2.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
<!--// css -->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/seyeong/note.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//textarea에 내용 입력시 글자수 체크
		$(document).on('keyup', 'textarea', function(){
			//입력한 글자수를 구함
			var inputLength = $(this).val().length;
			
			if(inputLength > 1000){
				//1000자를 넘어선 경우 1000자 까지로 잘라버림
				$(this).val($(this).val().substring(0, 1000));
			}else{
				//1000자 이하인 경우
				var remain = inputLength;
				remain += '/1000';
			}
			$('.letter-count').text(remain);
		});
		
		var index = -1;
		
		// 쪽지 받을 사람 확인하기
		$('#recipient').click(function(){
			index = -1;
			$('#recipient').val('');
			$('#result').text('');
		});

		$('#search').click(function(){
			var idArray = $('#idArray').val();
			var recipient = $('#recipient').val();
			index = idArray.indexOf(recipient);
			var searchResult = index > 0;
			
			if(recipient == 'admin'){ // 관리자에게 쪽지를 보낼 수 없음
				alert('쪽지를 보낼 수 없는 아이디입니다. 다시 입력해주세요.');
				$('#recipient').val('').focus();
				return;
			}
			
			if(!searchResult){ // 가입한 아이디 중에 받을 사람 아이디가 없음
				alert('없는 아이디입니다. 다시 입력해주세요.');
				$('#recipient').val('').focus();
				return;
			}else{
				$('#result').text('확인');
				$('#note_content').focus();
			}
		});
		
		$('#noteWriteFormSubmit').click(function(){
			if(index < 0){ // 받을 사람 아이디를 확인하지 않았음
				alert('받을 사람의 아이디를 확인해주세요!');
				return;
			}
			
			if($('#note_content').val().length < 10){
				alert('10자 이상부터 쪽지 발송이 가능합니다.');
				return;
			}
			
			$('#noteWriteForm').submit();
		});
		
		// 엔터키 이벤트 막기
		$('input[type="text"]').keydown(function() {
		    if (event.keyCode == 13) {
		        event.preventDefault();
		    }
		});
	});
</script>

<body>
	<div id="noteWrite" style="width:540px; height:600px;">
		<div style="margin:20px;">
			<input type="hidden" id="idArray" value="${idArray}"/>
			<form id="noteWriteForm" style="width:540px; height:600px;">
				<input type="hidden" name="sender" value="${command.sender}"/>
				<div class="form-group recipient" style="width: 500px;">
					<label class="condition floatL" for="recipient" style="margin-top: 13px;">받을 사람</label>
					<input type="text" name="recipient" id="recipient" style="width: 280px;"
							class="form-control condition floatL"/>
					<div class="floatL" style="width: 5px; height: 30px;"></div>
					<input type="button" class="floatL btn btn-primary" value="확인" style="margin-top: 3px;" id="search">
					<div class="floatL" style="width: 30px; height: 30px;"></div>
					<div class="floatL" style="width: 35px;height: 25px;margin-top: 13px;" id="result"></div>
				</div>
				<hr size="1" width="100%"><br>
				<label for="note_content"></label>
				<textarea rows="15" cols="85" style="border: 1.5px solid #333333; resize:none;"
								name="note_content" id="note_content">
				
</textarea><br>
				<div class="letter-count" style="text-align:right">0 / 1000</div>
				<div class="horizontal"></div><!-- 가로 여백 -->
				<p align="center">
					<input type="button" value="보내기" id="noteWriteFormSubmit" class="btn btn-primary">
					<input type="button" value="취소" onclick="self.close()" class="btn btn-primary"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>