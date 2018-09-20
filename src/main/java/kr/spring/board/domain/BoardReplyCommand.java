package kr.spring.board.domain;

import kr.spring.util.DurationFromNow;

public class BoardReplyCommand {
	private int re_num;
	private int pt_num;
	private int depth;
	private String re_content;
	private String re_date;
	private int num;
	private String id;
	
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public int getPt_num() {
		return pt_num;
	}
	public void setPt_num(int pt_num) {
		this.pt_num = pt_num;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = DurationFromNow.getTimeDiffLabel(re_date);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
