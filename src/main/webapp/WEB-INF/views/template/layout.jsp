<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<title><tiles:getAsString name="title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Njoy Trip Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap2.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
<!--// css -->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- font -->
<!-- <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Rokkitt:100,200,300,400,500,600,700,800,900" rel="stylesheet"> -->

<!-- //font -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<!-- menu -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<!--

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

 -->
<!-- chatting style -->
<style>
	@media screen and (max-width: 1000px) {
		#m_nav_chat {
			display:none;
		}
	}
      #m_nav_chat {
         display:none;
      }
   }

	#m_nav_chat {
		width: 120px;
		position: fixed;
		bottom: 80px;
		left: 30px;
		z-index: 9991;
		cursor: pointer;
		padding: .5em;
		text-align: center;
		color: #0093ffc7;
	}
	
	#chat_icon{
		color:#b7339a;
	}
	
	#chat_icon img{
		width:80%;
		border-radius: 40%;
	}
	
	#m_chat_container {
		height: 500px;
		width: 470px;
		z-index: 9999;
		bottom: 175px;
		position: fixed;
		display: none;
	}
	
	#chatMessageArea{
		padding-bottom:60px;
	}
	
	#messageBox {
	    width: 130px;
	    height: auto;
	    background-color: #808080b0;
	}
	
	* {
	  box-sizing: border-box;
	}
	
	body {
	  /* background-color: #edeff2; */
	  font-family: "Calibri", "Roboto", sans-serif;
	}
	
	.chat_window {
	  position: absolute;
	  width: calc(100% - 20px);
	  max-width: 800px;
	  height: 500px;
	  border-radius: 10px;
	  background-color: #fff;
	  left: 50%;
	  top: 50%;
	  transform: translateX(-50%) translateY(-50%);
	  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
	  background-color: #f8f8f8;
	  overflow: hidden;
	}
	
	.top_menu {
	  background-color: #fff;
	  width: 100%;
	  padding: 20px 0 15px;
	  box-shadow: 0 1px 30px rgba(0, 0, 0, 0.1);
	}
	.top_menu .buttons {
	  margin: 3px 0 0 20px;
	  position: absolute;
	}
	.top_menu .buttons .button {
	  width: 16px;
	  height: 16px;
	  border-radius: 50%;
	  display: inline-block;
	  margin-right: 10px;
	  position: relative;
	}
	.top_menu .buttons .button.close {
	  background-color: #f5886e;
	}
	.top_menu .buttons .button.minimize {
	  background-color: #fdbf68;
	}
	.top_menu .buttons .button.maximize {
	  background-color: #a3d063;
	}
	.top_menu .title {
	  text-align: center;
	  color: #00bcd4;
	  font-size: 20px;
	}
	
	.messages {
	  position: relative;
	  list-style: none;
	  padding: 20px 10px 0 10px;
	  margin: 0;
	  height: 347px;
	  overflow: scroll;
	}
	.messages .message {
	  clear: both;
	  overflow: hidden;
	  margin-bottom: 20px;
	  transition: all 0.5s linear;
	  opacity: 0;
	}
	.messages .message.left .avatar {
	  float: left;
	}
	.messages .message.left .avatar img{
	  width:60px;
	  border-radius: 50%;
	}
	.messages .message.left > .text_wrapper {
	  background-color: #ffe6cb;
	  margin-left: 20px;
	}
	.messages .message.left > .text_wrapper::after, .messages .message.left > .text_wrapper::before {
	  right: 100%;
	  border-right-color: #ffe6cb;
	}
	.messages .message.left .text {
	  color: #c48843;
	}
	.messages .message.right .avatar {
	  float: right;
	  border-radius: 50%;
	}
	.messages .message.right .avatar img{
	  width:60px;
	}
	.messages .message.right > .text_wrapper {
	  background-color: #c7eafc;
	}
	.messages .message.right > .text_wrapper::after, .messages .message.right > .text_wrapper::before {
	  left: 100%;
	  border-left-color: #c7eafc;
	}
	.messages .message.right .text {
	  color: #45829b;
	}
	.messages .message.appeared {
	  opacity: 1;
	}
	.messages .message .avatar {
	  width: 60px;
	  height: 60px;
	  border-radius: 50%;
	  display: inline-block;
	}
	.messages .message .text_wrapper {
	  display: inline-block;
	  padding: 20px;
	  border-radius: 6px;
	  width: calc(100% - 85px);
	  min-width: 100px;
	  position: relative;
	}
	.messages .message .text_wrapper::after, .messages .message .text_wrapper:before {
	  top: 18px;
	  border: solid transparent;
	  content: " ";
	  height: 0;
	  width: 0;
	  position: absolute;
	  pointer-events: none;
	}
	.messages .message .text_wrapper::after {
	  border-width: 13px;
	  margin-top: 0px;
	}
	.messages .message .text_wrapper::before {
	  border-width: 15px;
	  margin-top: -2px;
	}
	.messages .message .text_wrapper .text {
	  font-size: 18px;
	  font-weight: 300;
	}
	
	.bottom_wrapper {
	  position: relative;
	  width: 100%;
	  background-color: #fff;
	  padding: 20px 20px;
	  position: absolute;
	  bottom: 0;
	}
	.bottom_wrapper .message_input_wrapper {
	  display: inline-block;
	  height: 50px;
	  border-radius: 25px;
	  border: 1px solid #bcbdc0;
	  width: calc(100% - 160px);
	  position: relative;
	  padding: 0 20px;
	}
	.bottom_wrapper .message_input_wrapper .message_input {
	  border: none;
	  height: 100%;
	  box-sizing: border-box;
	  width: calc(100% - 40px);
	  position: absolute;
	  outline-width: 0;
	  color: gray;
	}
	.bottom_wrapper .send_message {
	  width: 140px;
	  height: 50px;
	  display: inline-block;
	  border-radius: 50px;
	  background-color: #a3d063;
	  border: 2px solid #a3d063;
	  color: #fff;
	  cursor: pointer;
	  transition: all 0.2s linear;
	  text-align: center;
	  float: right;
	}
	.bottom_wrapper .send_message:hover {
	  color: #a3d063;
	  background-color: #fff;
	}
	.bottom_wrapper .send_message .text {
	  font-size: 18px;
	  font-weight: 300;
	  display: inline-block;
	  line-height: 48px;
	}
	
	.message_template {
	  display: none;
	}
</style>

<!-- //menu -->
</head>
	<body>
		<div id="main">
			<div id="main_header">
				<tiles:insertAttribute name="header"/>
			</div>
			<div id="main_menu">
				<tiles:insertAttribute name="menu"/>
			</div>
			<div id="main_body">
				<tiles:insertAttribute name="body"/>
			</div>
			<div id="main_footer">
				<tiles:insertAttribute name="footer"/>
			</div>
		</div>
		
		<script src="${pageContext.request.contextPath}/resources/js/responsiveslides.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jarallax.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/SmoothScroll.min.js"></script>
		<script type="text/javascript">
	        /* init Jarallax */
	        $('.jarallax').jarallax({
	            speed: 0.5,
	            imgWidth: 1366,
	            imgHeight: 768
	        })
	    </script>
		<script type="text/javascript">
				jQuery(document).ready(function($) {
					$(".scroll").click(function(event){		
						event.preventDefault();
						$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
					});
				});
		</script>
		
		<script src="${pageContext.request.contextPath}/resources/js/chatting.js"></script>
		<!-- chat -->
		<div id="toggle_chat">
			<div id="m_nav_chat">
				<div id="chat_icon">
					<img src="${pageContext.request.contextPath}/resources/images/member/chatting_icon.png">
				</div>
			</div>
		</div>
		<!-- open chatting -->
		<!------ Include the above in your HEAD tag ---------->
		<div id="m_chat_container">
			<%
				String user_id = (String)session.getAttribute("user_id");
			%>
			<input type="hidden" id="user_id" value="${user_id}">
			<div class="chat_window">
				<div class="top_menu">
					<div class="button close" id="button_close" style="margin-top: 0px; margin-right: 0px;"></div>
					<div class="buttons">
						<div class="button minimize"></div>
						<div class="button maximize"></div>
					</div>
					<div class="title">고객상담 톡</div>
				</div>
				<ul class="messages">
					<li class="message left appeared" id="message_appear">
						<div class="avatar">
							<img src="${pageContext.request.contextPath}/resources/images/member/chat_admin.jpg">
						</div>
						<div class="text_wrapper">
							<div class="text">안녕하세요. CAN 고객상담 톡입니다. 24시간 언제나 빠르게 안내해드리겠습니다.<br> 무엇을 도와드릴까요?</div>
						</div>
					</li>
				</ul>
				<div class="bottom_wrapper clearfix">
					<div class="message_input_wrapper">
						<input class="message_input" id="message_input" placeholder="메시지를 입력하세요..." />
					</div>
					<div class="send_message" id="send_message">
						<div class="icon"></div>
						<div class="text">전송</div>
					</div>
				</div>
			</div>
			<div class="message_template">
				<ul>
				<li class="message">
					<div class="avatar"></div>
					<div class="text_wrapper">
						<div class="text"></div>
					</div>
				</li>
				</ul>
			</div>
		</div>
	</body>
</html>











