package kr.spring.ap.domain;

public class ApBoCallCommand {
	private int bo_call_num;
	private String bo_call_id;
	private String bo_call_date_start;
	private String bo_call_date_end;
	private String bo_call_re;
	private String bo_call_re_date;
	private String bo_call_re_hour;
	private String bo_call_re_min;
	private String call_name;
	private int call_num;
	
	public int getBo_call_num() {
		return bo_call_num;
	}
	public void setBo_call_num(int bo_call_num) {
		this.bo_call_num = bo_call_num;
	}
	public String getBo_call_id() {
		return bo_call_id;
	}
	public void setBo_call_id(String bo_call_id) {
		this.bo_call_id = bo_call_id;
	}
	public String getBo_call_date_start() {
		return bo_call_date_start;
	}
	public void setBo_call_date_start(String bo_call_date_start) {
		this.bo_call_date_start = bo_call_date_start;
	}
	public String getBo_call_date_end() {
		return bo_call_date_end;
	}
	public void setBo_call_date_end(String bo_call_date_end) {
		this.bo_call_date_end = bo_call_date_end;
	}
	public String getBo_call_re() {
		return bo_call_re;
	}
	public void setBo_call_re(String bo_call_re) {
		this.bo_call_re = bo_call_re;
	}
	public String getBo_call_re_date() {
		return bo_call_re_date;
	}
	public void setBo_call_re_date(String bo_call_re_date) {
		this.bo_call_re_date = bo_call_re_date;
	}
	public String getBo_call_re_hour() {
		return bo_call_re_hour;
	}
	public void setBo_call_re_hour(String bo_call_re_hour) {
		this.bo_call_re_hour = bo_call_re_hour;
	}
	public String getBo_call_re_min() {
		return bo_call_re_min;
	}
	public void setBo_call_re_min(String bo_call_re_min) {
		this.bo_call_re_min = bo_call_re_min;
	}
	public String getCall_name() {
		return call_name;
	}
	public void setCall_name(String call_name) {
		this.call_name = call_name;
	}
	public int getCall_num() {
		return call_num;
	}
	public void setCall_num(int call_num) {
		this.call_num = call_num;
	}
	
	@Override
	public String toString() {
		return "ApBoCallCommand [bo_call_num=" + bo_call_num + ", bo_call_id=" + bo_call_id + ", bo_call_date_start="
				+ bo_call_date_start + ", bo_call_date_end=" + bo_call_date_end + ", bo_call_re=" + bo_call_re
				+ ", bo_call_re_date=" + bo_call_re_date + ", bo_call_re_hour=" + bo_call_re_hour + ", bo_call_re_min="
				+ bo_call_re_min + ", call_name=" + call_name + ", call_num=" + call_num + "]";
	}
	
}
