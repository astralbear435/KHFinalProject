package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.chat.dao.ChatMapper;
import kr.spring.chat.domain.ChatCommand;


@Service("chatService")
public class ChatServiceImpl implements ChatService {
	
	@Resource
	private ChatMapper chatMapper;
	
	@Override
	public List<ChatCommand> getChatListById(Map<String, Object> chatList) {
		
		return chatMapper.getChatListById(chatList);
	}
	
	
}
