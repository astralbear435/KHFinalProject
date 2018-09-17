package kr.spring.chat.domain;

import java.sql.Date;

public class ChatCommand {
	
	private int chatId;
	private String fromId;
	private String toId;
	private String chatContent;
	private Date chatTime;
	
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Date getChatTime() {
		return chatTime;
	}
	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
	
	@Override
	public String toString() {
		return "ChatCommand [chatId=" + chatId + ", fromId=" + fromId + ", toId=" + toId + ", chatContent="
				+ chatContent + ", chatTime=" + chatTime + "]";
	}
	
}
