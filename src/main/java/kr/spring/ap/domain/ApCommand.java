package kr.spring.ap.domain;

public class ApCommand {
	
	private int ap_num;
	private int ap_ok;
	private String id;
	private String ap_job;
	private String ap_cer;
	private int ap_act;
	private int ap_pet;
	private String ap_ser;
	private String ap_home;
	private int ap_sel;
	private String ap_nopet;
	private String ap_service;
	private int ap_mon;
	
	public int getAp_num() {
		return ap_num;
	}
	public void setAp_num(int ap_num) {
		this.ap_num = ap_num;
	}
	public int getAp_ok() {
		return ap_ok;
	}
	public void setAp_ok(int ap_ok) {
		this.ap_ok = ap_ok;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAp_job() {
		return ap_job;
	}
	public void setAp_job(String ap_job) {
		this.ap_job = ap_job;
	}
	public String getAp_cer() {
		return ap_cer;
	}
	public void setAp_cer(String ap_cer) {
		this.ap_cer = ap_cer;
	}
	public int getAp_act() {
		return ap_act;
	}
	public void setAp_act(int ap_act) {
		this.ap_act = ap_act;
	}
	public int getAp_pet() {
		return ap_pet;
	}
	public void setAp_pet(int ap_pet) {
		this.ap_pet = ap_pet;
	}
	public String getAp_ser() {
		return ap_ser;
	}
	public void setAp_ser(String ap_ser) {
		this.ap_ser = ap_ser;
	}
	public String getAp_home() {
		return ap_home;
	}
	public void setAp_home(String ap_home) {
		this.ap_home = ap_home;
	}
	public int getAp_sel() {
		return ap_sel;
	}
	public void setAp_sel(int ap_sel) {
		this.ap_sel = ap_sel;
	}
	public String getAp_nopet() {
		return ap_nopet;
	}
	public void setAp_nopet(String ap_nopet) {
		this.ap_nopet = ap_nopet;
	}
	public String getAp_service() {
		return ap_service;
	}
	public void setAp_service(String ap_service) {
		this.ap_service = ap_service;
	}
	public int getAp_mon() {
		return ap_mon;
	}
	public void setAp_mon(int ap_mon) {
		this.ap_mon = ap_mon;
	}
	
	@Override
	public String toString() {
		return "ApCommand [ap_num=" + ap_num + ", ap_ok=" + ap_ok + ", id=" + id + ", ap_job=" + ap_job + ", ap_cer="
				+ ap_cer + ", ap_act=" + ap_act + ", ap_pet=" + ap_pet + ", ap_ser=" + ap_ser + ", ap_home=" + ap_home
				+ ", ap_sel=" + ap_sel + ", ap_nopet=" + ap_nopet + ", ap_service=" + ap_service + ", ap_mon=" + ap_mon
				+ "]";
	}
	
}
