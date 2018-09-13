package kr.spring.goods.domain;

public class GoodsCommand {
	private String as_name;
	private String as_id;
	private String as_location;
	private int pad;
	private int dogfood;
	private int catfood;
	private int shampoo;
	private int catsand;
	private int as_auth;
	private int as_did;
	
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
	public int getCatfood() {
		return catfood;
	}
	public void setCatfood(int catfood) {
		this.catfood = catfood;
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
	public int getAs_did() {
		return as_did;
	}
	public void setAs_did(int as_did) {
		this.as_did = as_did;
	}
	@Override
	public String toString() {
		return "GoodsCommand [as_name=" + as_name + ", as_id=" + as_id + ", as_location=" + as_location + ", pad=" + pad
				+ ", dogfood=" + dogfood + ", catfood=" + catfood + ", shampoo=" + shampoo + ", catsand=" + catsand
				+ ", as_auth=" + as_auth + ", as_did=" + as_did + "]";
	}
	
	
}
