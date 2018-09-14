package kr.spring.note.domain;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class NoteCommand {
	private int note_num;
	@NotEmpty
	private String sender;
	@NotEmpty
	private String recipient;
	@NotEmpty
	private String note_content;
	private Date write_date;
	private String read_status;
	private String delete_status;
	
	public int getNote_num() {
		return note_num;
	}
	public void setNote_num(int note_num) {
		this.note_num = note_num;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public String getRead_status() {
		return read_status;
	}
	public void setRead_status(String read_status) {
		this.read_status = read_status;
	}
	public String getDelete_status() {
		return delete_status;
	}
	public void setDelete_status(String delete_status) {
		this.delete_status = delete_status;
	}
	
	@Override
	public String toString() {
		return "NoteCommand [note_num=" + note_num + ", sender=" + sender + ", recipient=" + recipient
				+ ", note_content=" + note_content + ", write_date=" + write_date + ", read_status=" + read_status
				+ ", delete_status=" + delete_status + "]";
	}
}
