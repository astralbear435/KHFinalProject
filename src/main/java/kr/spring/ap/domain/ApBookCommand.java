package kr.spring.ap.domain;

public class ApBookCommand {
	private int ap_book_num;
	private String ap_book_id;
	
	public int getAp_book_num() {
		return ap_book_num;
	}
	public void setAp_book_num(int ap_book_num) {
		this.ap_book_num = ap_book_num;
	}
	public String getAp_book_id() {
		return ap_book_id;
	}
	public void setAp_book_id(String ap_book_id) {
		this.ap_book_id = ap_book_id;
	}
	
	@Override
	public String toString() {
		return "ApBook [ap_book_num=" + ap_book_num + ", ap_book_id=" + ap_book_id + "]";
	}
	
	
}
