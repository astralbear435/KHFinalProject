package kr.spring.goods.domain;

public class GoodsListCommand {
	private String g_name;
	private int g_price;
	private String g_photo1;
	private String g_photo2;
	private String g_photo3;
	private String g_id;
	private String g_content;
	private String g_origin;
	private int g_num;
	private int g_amount;
	
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public int getG_price() {
		return g_price;
	}
	public void setG_price(int g_price) {
		this.g_price = g_price;
	}
	public String getG_photo1() {
		return g_photo1;
	}
	public void setG_photo1(String g_photo1) {
		this.g_photo1 = g_photo1;
	}
	public String getG_photo2() {
		return g_photo2;
	}
	public void setG_photo2(String g_photo2) {
		this.g_photo2 = g_photo2;
	}
	public String getG_photo3() {
		return g_photo3;
	}
	public void setG_photo3(String g_photo3) {
		this.g_photo3 = g_photo3;
	}
	public String getG_id() {
		return g_id;
	}
	public void setG_id(String g_id) {
		this.g_id = g_id;
	}
	public String getG_content() {
		return g_content;
	}
	public void setG_content(String g_content) {
		this.g_content = g_content;
	}
	public String getG_origin() {
		return g_origin;
	}
	public void setG_origin(String g_origin) {
		this.g_origin = g_origin;
	}
	public int getG_num() {
		return g_num;
	}
	public void setG_num(int g_num) {
		this.g_num = g_num;
	}
	
	public int getG_amount() {
		return g_amount;
	}
	public void setG_amount(int g_amount) {
		this.g_amount = g_amount;
	}
	@Override
	public String toString() {
		return "GoodsListCommand [g_name=" + g_name + ", g_price=" + g_price + ", g_photo1=" + g_photo1 + ", g_photo2="
				+ g_photo2 + ", g_photo3=" + g_photo3 + ", g_id=" + g_id + ", g_content=" + g_content + ", g_origin="
				+ g_origin + ", g_num=" + g_num + ", g_amount=" + g_amount + "]";
	}

	
}
