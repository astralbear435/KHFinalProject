package kr.spring.goods.domain;

public class AdminCheck {
	private String ch_id;
	private String ch_name;
	private String ch_message;
	private String ch_url;
	private int ch_amount;
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public String getCh_message() {
		return ch_message;
	}
	public void setCh_message(String ch_message) {
		this.ch_message = ch_message;
	}
	public String getCh_url() {
		return ch_url;
	}
	public void setCh_url(String ch_url) {
		this.ch_url = ch_url;
	}
	public int getCh_amount() {
		return ch_amount;
	}
	public void setCh_amount(int ch_amount) {
		this.ch_amount = ch_amount;
	}
	
	@Override
	public String toString() {
		return "adminCheck [ch_id=" + ch_id + ", ch_name=" + ch_name + ", ch_message=" + ch_message + ", ch_url="
				+ ch_url + ", ch_amount=" + ch_amount + "]";
	}	
	

}
