package kr.spring.goods.domain;

public class CartListCommand {
	private int p_cartnum;//īƮ ���� ��ȣ
	private String p_id;//���� ���̵�
	private String p_name;//��ȣ�� �̸�
	private int p_price;//�� ����
	private int p_num;//��ǰ��ȣ
	private int p_amount;//����
	private String p_goodsname;//���Ǹ�
	private String p_goodsphoto;//���ǻ���
	
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
