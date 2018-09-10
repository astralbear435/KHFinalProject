package kr.spring.visitor.domain;

import java.sql.Date;

public class VisitorCommand {
	private int visit_id;		//시퀀스 넘버
    private String visit_ip;	//접속아이피
    private Date visit_time;	//접속시간
    private String visit_refer;	//접속자 유입 경로
    private String visit_agent;	//접속자 브라우저 정보
    private String visit_login_id;	//로그인 아이디 없으면 null;
	public int getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}
	public String getVisit_ip() {
		return visit_ip;
	}
	public void setVisit_ip(String visit_ip) {
		this.visit_ip = visit_ip;
	}
	public Date getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}
	public String getVisit_refer() {
		return visit_refer;
	}
	public void setVisit_refer(String visit_refer) {
		this.visit_refer = visit_refer;
	}
	public String getVisit_agent() {
		return visit_agent;
	}
	public void setVisit_agent(String visit_agent) {
		this.visit_agent = visit_agent;
	}
	public String getVisit_login_id() {
		return visit_login_id;
	}
	public void setVisit_login_id(String visit_login_id) {
		this.visit_login_id = visit_login_id;
	}
	@Override
	public String toString() {
		return "VisitorCommand [visit_id=" + visit_id + ", visit_ip=" + visit_ip + ", visit_time=" + visit_time
				+ ", visit_refer=" + visit_refer + ", visit_agent=" + visit_agent + ", visit_login_id=" + visit_login_id
				+ "]";
	}
	
    
}
