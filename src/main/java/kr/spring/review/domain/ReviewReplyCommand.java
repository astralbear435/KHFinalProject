package kr.spring.review.domain;

import java.sql.Date;

public class ReviewReplyCommand {
	private int reply_mynum;
	private int reply_num;
	private Date reply_date;
	private String reply_id;
	private String reply_content;
	public int getReply_mynum() {
		return reply_mynum;
	}
	public void setReply_mynum(int reply_mynum) {
		this.reply_mynum = reply_mynum;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	@Override
	public String toString() {
		return "ReviewReplyCommand [reply_mynum=" + reply_mynum + ", reply_num=" + reply_num + ", reply_date="
				+ reply_date + ", reply_id=" + reply_id + ", reply_content=" + reply_content + "]";
	}	
	
}
