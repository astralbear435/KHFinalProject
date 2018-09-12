package kr.spring.goods.domain;

public class CartListCommand {
	private int p_cartnum;//카트 고유 번호
	private String p_id;//유저 아이디
	private String p_name;//보호소 이름
	private int p_price;//총 가격
	private int p_num;//물품번호
	private int p_amount;//갯수
	private String p_goodsname;//물건명
	private String p_goodsphoto;//물건사진
	
	public int getP_cartnum() {
		return p_cartnum;
	}
	public void setP_cartnum(int p_cartnum) {
		this.p_cartnum = p_cartnum;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getP_amount() {
		return p_amount;
	}
	public void setP_amount(int p_amount) {
		this.p_amount = p_amount;
	}
	public String getP_goodsname() {
		return p_goodsname;
	}
	public void setP_goodsname(String p_goodsname) {
		this.p_goodsname = p_goodsname;
	}
	public String getP_goodsphoto() {
		return p_goodsphoto;
	}
	public void setP_goodsphoto(String p_goodsphoto) {
		this.p_goodsphoto = p_goodsphoto;
	}
	@Override
	public String toString() {
		return "CartListCommand [p_cartnum=" + p_cartnum + ", p_id=" + p_id + ", p_name=" + p_name + ", p_price="
				+ p_price + ", p_num=" + p_num + ", p_amount=" + p_amount + ", p_goodsname=" + p_goodsname
				+ ", p_goodsphoto=" + p_goodsphoto + "]";
	}	
}
