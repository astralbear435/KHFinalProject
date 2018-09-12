<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// 쪽지 발송 시 팝업 꺼짐
		$('#noteWriteForm').submit(function(event){
			var data = $(this).serialize();
			$.ajax({
				type:'post',
				data:data,
				url:'writeAjax.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'success'){
						self.close();
						opener.document.location.reload();
					}else if(data.result == 'noData'){
						alert('누락된 내용이 있습니다.');
					}else{
						alert('오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
			event.preventDefault();
		});
		
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
	});
</script>
<body>
	<div id="noteWrite" style="width:400px; height:500px;">
		<div style="margin:20px;">
			<form id="noteWriteForm">
				<input type="hidden" name="sender" value="${command.sender}"/>
				<div class="recipient">
					<label class="condition floatL" for="recipient">받을 사람</label>
					<input type="text" name="recipient" class="condition floatL" size="10"/>
				</div>
				<hr size="1" width="100%"><br>
				<label for="note_content"></label>
				<textarea rows="20" cols="48" style="resize:none;" name="note_content"></textarea><br>
				<div class="letter-count" style="text-align:right">0 / 1000</div>
				<p align="center">
					<input type="submit" value="보내기">
					<input type="button" value="취소" onclick="self.close()"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>