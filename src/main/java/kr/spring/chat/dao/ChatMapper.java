package kr.spring.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.chat.domain.ChatCommand;


public interface ChatMapper {
	
	@Select("SELECT * FROM chat WHERE ((fromId = #{fromId} AND toId = #{toId}) OR (fromId = #{fromId} AND toId = #{toId})) AND chatId > #{chatId} ORDER BY chatTime")
	public List<ChatCommand> getChatListById(Map<String,Object> chatList);
}
