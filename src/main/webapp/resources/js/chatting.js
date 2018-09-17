$(document).ready(function() {
	
	//X버튼 클릭시 채팅창 숨김
	$('#button_close').on('click',function() {
		$('#m_chat_container').hide();
	});
	
	var wsocket;
	
	//WebSocket 연결
	function connect() {
		wsocket = new WebSocket("ws://localhost:8080/ProjectCAN/chat-ws.do");
		wsocket.onopen = function(evt) {
			var msg = '';
		};
		
		//서버로부터 메시지를 받으면 호출되는 함수 지정
		wsocket.onmessage = function(evt) {
			var data = evt.data;
			if(data.substring(0,4) == 'msg:') {
				appendMessage(data.substring(4));
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
		//메시지를 서버로 전송
		wsocket.send('msg:' + msg);
		//메시지 초기화
		$('#message_input').val('');
	}
	
	
	//서버에서 전송된 메시지를 UI에 표시
	function appendMessage(msg) {
		var a = 0;
		//클라이언트 메세지
		if(a==1) {
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
		
		//관리자 메세지
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
	
	
    /*var Message;
    Message = function (arg) {
        this.text = arg.text, this.message_side = arg.message_side;
        this.draw = function (_this) {
            return function () {
                var $message;
                $message = $($('.message_template').clone().html());
                $message.addClass(_this.message_side).find('.text').html(_this.text);
                $('.messages').append($message);
                return setTimeout(function () {
                    return $message.addClass('appeared');
                }, 0);
            };
        }(this);
        return this;
    };
    $(function () {
        var getMessageText, message_side, sendMessage;
        message_side = 'right';
        getMessageText = function () {
            var $message_input;
            $message_input = $('.message_input');
            return $message_input.val();
        };
        sendMessage = function (text) {
            var $messages, message;
            if (text.trim() === '') {
                return;
            }
            $('.message_input').val('');
            $messages = $('.messages');
            message_side = message_side === 'left' ? 'right' : 'left';
            message = new Message({
                text: text,
                message_side: message_side
            });
            message.draw();
            return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 300);
        };
        $('.send_message').click(function (e) {
            return sendMessage(getMessageText());
        });
        $('.message_input').keyup(function (e) {
            if (e.which === 13) {
                return sendMessage(getMessageText());
            }
        });
        sendMessage('Hello Philip! :)');
        setTimeout(function () {
            return sendMessage('Hi Sandy! How are you?');
        }, 1000);
        return setTimeout(function () {
            return sendMessage('I\'m fine, thank you!');
        }, 2000);
    });*/
});
	
	