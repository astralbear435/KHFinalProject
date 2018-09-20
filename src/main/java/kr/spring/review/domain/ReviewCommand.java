package kr.spring.review.domain;

import java.sql.Date;

public class ReviewCommand {
	private int re_num;//�� ��ȣ
	private String re_title;//������
	private String re_asname;//�ش� ��ȣ��
	private String re_id;//�ۼ��ھ��̵�
	private String re_content;//����
	private Date re_date;//�ۼ���
	private int re_hit;//��ȸ��
	private int re_auth;//��ȸ��
	
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public String getRe_title() {
		return re_title;
	}
	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}
	public String getRe_asname() {
		return re_asname;
	}
	public void setRe_asname(String re_asname) {
		this.re_asname = re_asname;
	}
	public String getRe_id() {
		return re_id;
	}
	public void setRe_id(String re_id) {
		this.re_id = re_id;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public Date getRe_date() {
		return re_date;
	}
	public void setRe_date(Date re_date) {
		this.re_date = re_date;
	}
	public int getRe_hit() {
		return re_hit;
	}
	public void setRe_hit(int re_hit) {
		this.re_hit = re_hit;
	}
	
	public int getRe_auth() {
		return re_auth;
	}
	public void setRe_auth(int re_auth) {
		this.re_auth = re_auth;
	}
	@Override
	public String toString() {
		return "ReviewCommand [re_num=" + re_num + ", re_title=" + re_title + ", re_asname=" + re_asname + ", re_id="
				+ re_id + ", re_content=" + re_content + ", re_date=" + re_date + ", re_hit=" + re_hit + ", re_auth="
				+ re_auth + "]";
	}
	
	
}
