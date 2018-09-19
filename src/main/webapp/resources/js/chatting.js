$(document).ready(function() {
	//X버튼 클릭시 채팅창 숨김
	$('#button_close').on('click',function() {
		$('#m_chat_container').hide();
	});
	
	var wsocket;
	
	//WebSocket 연결
	function connect() {
		wsocket = new WebSocket("ws://192.168.10.27:8080/ProjectCAN/chat-ws.do");
		wsocket.onopen = function(evt) {
			alert('채팅 입장');
		};
		
		//서버로부터 메시지를 받으면 호출되는 함수 지정
		wsocket.onmessage = function(evt) {
			var data = evt.data;
			if(data.substring(0,6) == 'admin:') {
				console.log('----------->' + data.substring(6));
				appendMessage(data.substring(6),data.substring(0,6));
			}
			else {
				appendMessage(data.substring(4),data.substring(0,4));
			}
		};
		
		//채팅 종료시 호출되는 함수 지정
		wsocket.onclose = function(evt) {
			alert('채팅종료');
		};
	}
	
	//채팅 종료()
	function disconnect() {
		//브라우저 종료시 채팅 종료
		wsocket.onclose();
	}
	
	//서버에 메시지 전송
	function send() {
		var msg = $('#message_input').val();
		var user_id = $('#user_id').val();
		console.log(user_id);
		//메시지를 서버로 전송
		if(user_id.indexOf('admin') != -1) {	//관리자일 때
			wsocket.send('admin:' + msg);
		}
		else {	//고객일 때
			wsocket.send('msg:' + msg);
		}
		//메시지 초기화
		$('#message_input').val('');
	}
	
	
	//서버에서 전송된 메시지를 UI에 표시
	function appendMessage(msg,user) {
		console.log('~~~~'+msg);
		//관리자 메세지
		if(user.indexOf('admin') != -1) {
			var userMsg = '<br><br>';
			userMsg += '<li class="message left appeared">';
			userMsg += '<div class="avatar">';
			userMsg += '	<img src="../resources/images/member/chat_admin.jpg">';
			userMsg += '</div>';
			userMsg += '<div class="text_wrapper">';
			userMsg += '	<div class="text">';
			userMsg += msg;
			userMsg += '	</div>';
			userMsg += '</div>';
			userMsg += '</li>';
		}
		
		//클라이언트 메세지
		else {
			var userMsg = '<br><br>';
			userMsg += '<li class="message right appeared">';
			userMsg += '	<div class="avatar">';
			userMsg += '		<img src="../resources/images/member/chat_default.png">';
			userMsg += '	</div>';
			userMsg += '	<div class="text_wrapper">';
			userMsg += '		<div class="text">';
			userMsg += 				msg;
			userMsg += '		</div>';
			userMsg += '	</div>';
			userMsg += '</li>';
		}
		
		$('#message_appear').append(userMsg);
		var chatAreaHeight = $('.message').height();
		var maxScroll = $('.text_wrapper').height() - chatAreaHeight;
		$('.message').scrollTop(maxScroll);
	}
	
	//이벤트 연결
	$(document).ready(function() {
		var onOff = 0;
		
		//메세지 입력 후 Enter
		$('#message_input').keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);	//인터넷익스플로러면 event.code 아니면 event.which
			
			if(keycode == '13') {
				send();
			}
			event.stopPropagation();	//(부모로)이벤트 전파 막음
		});
		
		//메세지 전송 버튼 클릭
		$('#send_message').click(function() {
			if($('#message_input').val() != '') {
				send();
			}
		});
		
		//서버접속 버튼 클릭
		$('#m_nav_chat').click(function() {
			if(onOff == 0) {
				onOff = 1;
				connect();
			}
		});
		
		
	});
	
});
   
   