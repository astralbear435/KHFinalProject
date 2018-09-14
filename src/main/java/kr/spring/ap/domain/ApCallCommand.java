package kr.spring.ap.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class ApCallCommand {
	
	private int call_num;
	@NotEmpty
	private String call_name;
	@NotEmpty
	private String call_phone;
	@NotEmpty
	private String call_start;
	@NotEmpty
	private String call_end;
	private int call_wei;
	private int call_re;
	private String call_re_date;
	private String call_re_hour;
	private String call_re_min;
	@NotEmpty
	private String call_intro;
	
	public int getCall_num() {
		return call_num;
	}
	public void setCall_num(int call_num) {
		this.call_num = call_num;
	}
	public String getCall_name() {
		return call_name;
	}
	public void setCall_name(String call_name) {
		this.call_name = call_name;
	}
	public String getCall_phone() {
		return call_phone;
	}
	public void setCall_phone(String call_phone) {
		this.call_phone = call_phone;
	}
	public String getCall_start() {
		return call_start;
	}
	public void setCall_start(String call_start) {
		this.call_start = call_start;
	}
	public String getCall_end() {
		return call_end;
	}
	public void setCall_end(String call_end) {
		this.call_end = call_end;
	}
	public int getCall_wei() {
		return call_wei;
	}
	public void setCall_wei(int call_wei) {
		this.call_wei = call_wei;
	}
	public int getCall_re() {
		return call_re;
	}
	public void setCall_re(int call_re) {
		this.call_re = call_re;
	}
	public String getCall_re_date() {
		return call_re_date;
	}
	public void setCall_re_date(String call_re_date) {
		this.call_re_date = call_re_date;
	}
	public String getCall_re_hour() {
		return call_re_hour;
	}
	public void setCall_re_hour(String call_re_hour) {
		this.call_re_hour = call_re_hour;
	}
	public String getCall_re_min() {
		return call_re_min;
	}
	public void setCall_re_min(String call_re_min) {
		this.call_re_min = call_re_min;
	}
	public String getCall_intro() {
		return call_intro;
	}
	public void setCall_intro(String call_intro) {
		this.call_intro = call_intro;
	}
	
	@Override
	public String toString() {
		return "ApCallCommand [call_num=" + call_num + ", call_name=" + call_name + ", call_phone=" + call_phone
				+ ", call_start=" + call_start + ", call_end=" + call_end + ", call_wei=" + call_wei + ", call_re="
				+ call_re + ", call_re_date=" + call_re_date + ", call_re_hour=" + call_re_hour + ", call_re_min="
				+ call_re_min + ", call_intro=" + call_intro + "]";
	}
	
}
