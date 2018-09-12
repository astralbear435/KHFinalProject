package kr.spring.goods.domain;

public class GoodsCommand {
	private String as_name;
	private String as_id;
	private String as_location;
	private int pad;
	private int dogfood;
	private int cattoy;
	private int shampoo;
	private int catsand;
	private int as_auth;
	public String getAs_name() {
		return as_name;
	}
	public void setAs_name(String as_name) {
		this.as_name = as_name;
	}
	public String getAs_id() {
		return as_id;
	}
	public void setAs_id(String as_id) {
		this.as_id = as_id;
	}
	public String getAs_location() {
		return as_location;
	}
	public void setAs_location(String as_location) {
		this.as_location = as_location;
	}
	public int getPad() {
		return pad;
	}
	public void setPad(int pad) {
		this.pad = pad;
	}
	public int getDogfood() {
		return dogfood;
	}
	public void setDogfood(int dogfood) {
		this.dogfood = dogfood;
	}
	public int getCattoy() {
		return cattoy;
	}
	public void setCattoy(int cattoy) {
		this.cattoy = cattoy;
	}
	public int getShampoo() {
		return shampoo;
	}
	public void setShampoo(int shampoo) {
		this.shampoo = shampoo;
	}
	public int getCatsand() {
		return catsand;
	}
	public void setCatsand(int catsand) {
		this.catsand = catsand;
	}
	public int getAs_auth() {
		return as_auth;
	}
	public void setAs_auth(int as_auth) {
		this.as_auth = as_auth;
	}
	@Override
	public String toString() {
		return "GoodsCommand [as_name=" + as_name + ", as_id=" + as_id + ", as_location=" + as_location + ", pad=" + pad
				+ ", dogfood=" + dogfood + ", cattoy=" + cattoy + ", shampoo=" + shampoo + ", catsand=" + catsand
				+ ", as_auth=" + as_auth + "]";
	}
	
	
}
