package kr.spring.chat.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.chat.domain.ChatCommand;
import kr.spring.chat.service.ChatService;


@Controller
public class ChatController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ChatService chatService;
	
	//자바빈 초기화
	@ModelAttribute("command")
	public ChatCommand initCommand() {
		return new ChatCommand();
	}
	
	
	
}
