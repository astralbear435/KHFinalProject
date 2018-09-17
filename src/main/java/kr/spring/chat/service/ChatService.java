package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import kr.spring.chat.domain.ChatCommand;

public interface ChatService {
	
	List<ChatCommand> getChatListById(Map<String,Object> chatList);
}
