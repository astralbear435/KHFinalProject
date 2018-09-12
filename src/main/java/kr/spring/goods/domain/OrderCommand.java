package kr.spring.goods.domain;

import java.sql.Date;

public class OrderCommand {
	private int dona_num; //주문번호
	private String dona_id;
	private String dona_asname;
	private String dona_username;
	private Date dona_date;
	private String dona_message;
	private String dona_goodsamount;//문자열로 저장할 예정(갯수)
	private String dona_goodsnum;//문자열로 저장할 예정(상품번호)
	private int dona_price;
	public int getDona_num() {
		return dona_num;
	}
	public void setDona_num(int dona_num) {
		this.dona_num = dona_num;
	}
	public String getDona_id() {
		return dona_id;
	}
	public void setDona_id(String dona_id) {
		this.dona_id = dona_id;
	}
	public String getDona_asname() {
		return dona_asname;
	}
	public void setDona_asname(String dona_asname) {
		this.dona_asname = dona_asname;
	}
	public String getDona_username() {
		return dona_username;
	}
	public void setDona_username(String dona_username) {
		this.dona_username = dona_username;
	}
	public Date getDona_date() {
		return dona_date;
	}
	public void setDona_date(Date dona_date) {
		this.dona_date = dona_date;
	}
	public String getDona_message() {
		return dona_message;
	}
	public void setDona_message(String dona_message) {
		this.dona_message = dona_message;
	}
	public String getDona_goodsamount() {
		return dona_goodsamount;
	}
	public void setDona_goodsamount(String dona_goodsamount) {
		this.dona_goodsamount = dona_goodsamount;
	}
	public String getDona_goodsnum() {
		return dona_goodsnum;
	}
	public void setDona_goodsnum(String dona_goodsnum) {
		this.dona_goodsnum = dona_goodsnum;
	}
	public int getDona_price() {
		return dona_price;
	}
	public void setDona_price(int dona_price) {
		this.dona_price = dona_price;
	}
	@Override
	public String toString() {
		return "OrderCommand [dona_num=" + dona_num + ", dona_id=" + dona_id + ", dona_asname=" + dona_asname
				+ ", dona_username=" + dona_username + ", dona_date=" + dona_date + ", dona_message=" + dona_message
				+ ", dona_goodsamount=" + dona_goodsamount + ", dona_goodsnum=" + dona_goodsnum + ", dona_price="
				+ dona_price + "]";
	}	
	

}
