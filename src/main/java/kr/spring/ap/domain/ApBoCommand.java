package kr.spring.ap.domain;

public class ApBoCommand {
	private int bo_num;
	private String bo_id;
	private String id;
	private String bo_date_start;
	private String bo_start_hour;
	private String bo_start_min;
	private String bo_date_end;
	private String bo_end_hour;
	private String bo_end_min;
	private int ap_num;
	
	public int getAp_num() {
		return ap_num;
	}
	public void setAp_num(int ap_num) {
		this.ap_num = ap_num;
	}
	public int getBo_num() {
		return bo_num;
	}
	public void setBo_num(int bo_num) {
		this.bo_num = bo_num;
	}
	public String getBo_id() {
		return bo_id;
	}
	public void setBo_id(String bo_id) {
		this.bo_id = bo_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBo_date_start() {
		return bo_date_start;
	}
	public void setBo_date_start(String bo_date_start) {
		this.bo_date_start = bo_date_start;
	}
	public String getBo_start_hour() {
		return bo_start_hour;
	}
	public void setBo_start_hour(String bo_start_hour) {
		this.bo_start_hour = bo_start_hour;
	}
	public String getBo_start_min() {
		return bo_start_min;
	}
	public void setBo_start_min(String bo_start_min) {
		this.bo_start_min = bo_start_min;
	}
	public String getBo_date_end() {
		return bo_date_end;
	}
	public void setBo_date_end(String bo_date_end) {
		this.bo_date_end = bo_date_end;
	}
	public String getBo_end_hour() {
		return bo_end_hour;
	}
	public void setBo_end_hour(String bo_end_hour) {
		this.bo_end_hour = bo_end_hour;
	}
	public String getBo_end_min() {
		return bo_end_min;
	}
	public void setBo_end_min(String bo_end_min) {
		this.bo_end_min = bo_end_min;
	}
	
	@Override
	public String toString() {
		return "ApBoCommand [bo_num=" + bo_num + ", bo_id=" + bo_id + ", id=" + id + ", bo_date_start=" + bo_date_start
				+ ", bo_start_hour=" + bo_start_hour + ", bo_start_min=" + bo_start_min + ", bo_date_end=" + bo_date_end
				+ ", bo_end_hour=" + bo_end_hour + ", bo_end_min=" + bo_end_min + ", ap_num=" + ap_num + "]";
	}
	
	
}
