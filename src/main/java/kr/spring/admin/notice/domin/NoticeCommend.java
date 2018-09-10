package kr.spring.admin.notice.domin;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
public class NoticeCommend {
	private Integer n_idx;
	@Size(max = 100)
	@NotEmpty
	private String n_subject;
	@NotEmpty
	private String n_content;
	private String n_id;
	private Integer n_hit;
	private Date n_reg_date;
	private Date n_last_modified;
	public Integer getN_idx() {
		return n_idx;
	}
	public void setN_idx(Integer n_idx) {
		this.n_idx = n_idx;
	}
	public String getN_subject() {
		return n_subject;
	}
	public void setN_subject(String n_subject) {
		this.n_subject = n_subject;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public String getN_id() {
		return n_id;
	}
	public void setN_id(String n_id) {
		this.n_id = n_id;
	}
	public Integer getN_hit() {
		return n_hit;
	}
	public void setN_hit(Integer n_hit) {
		this.n_hit = n_hit;
	}
	public Date getN_reg_date() {
		return n_reg_date;
	}
	public void setN_reg_date(Date n_reg_date) {
		this.n_reg_date = n_reg_date;
	}
	public Date getN_last_modified() {
		return n_last_modified;
	}
	public void setN_last_modified(Date n_last_modified) {
		this.n_last_modified = n_last_modified;
	}
	@Override
	public String toString() {
		return "NoticeCommend [n_idx=" + n_idx + ", n_subject=" + n_subject + ", n_content=" + n_content + ", n_id="
				+ n_id + ", n_hit=" + n_hit + ", n_reg_date=" + n_reg_date + ", n_last_modified=" + n_last_modified
				+ "]";
	}

	
}
