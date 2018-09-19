package kr.spring.recruit.domain;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class RecruitCommand {

	private int r_num;
	private String r_id;
	private MultipartFile r_upload;
	//toString
	private byte[] r_image;
	private String r_filename;
	private String r_title;
	private String r_start_date;
	private String r_end_date;
	private int r_people;
	private int r_people_count;
	private String r_content;
	private int r_status;
	
	//봉사활동 신청자 변수
	private int v_num;
	private String v_id;
	private String v_date;
	private int v_ip;
	private int v_status;
	


	public int getR_status() {
		return r_status;
	}

	public void setR_status(int r_status) {
		this.r_status = r_status;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public MultipartFile getR_upload() {
		return r_upload;
	}

	public void setR_upload(MultipartFile r_upload) throws IOException {
		this.r_upload = r_upload;

		//byte[] 
		setR_image(r_upload.getBytes());
		//
		setR_filename(r_upload.getOriginalFilename());
	}

	public byte[] getR_image() {
		return r_image;
	}

	public void setR_image(byte[] r_image) {
		this.r_image = r_image;
	}

	public String getR_filename() {
		return r_filename;
	}

	public void setR_filename(String r_filename) {
		this.r_filename = r_filename;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_start_date() {
		return r_start_date;
	}

	public void setR_start_date(String r_start_date) {
		this.r_start_date = r_start_date;
	}

	public String getR_end_date() {
		return r_end_date;
	}

	public void setR_end_date(String r_end_date) {
		this.r_end_date = r_end_date;
	}

	public int getR_people() {
		return r_people;
	}

	public void setR_people(int r_people) {
		this.r_people = r_people;
	}

	public int getR_people_count() {
		return r_people_count;
	}

	public void setR_people_count(int r_people_count) {
		this.r_people_count = r_people_count;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	
	
	public int getV_num() {
		return v_num;
	}

	public void setV_num(int v_num) {
		this.v_num = v_num;
	}

	public String getV_id() {
		return v_id;
	}

	public void setV_id(String v_id) {
		this.v_id = v_id;
	}

	public String getV_date() {
		return v_date;
	}

	public void setV_date(String v_date) {
		this.v_date = v_date;
	}

	public int getV_ip() {
		return v_ip;
	}

	public void setV_ip(int v_ip) {
		this.v_ip = v_ip;
	}

	public int getV_status() {
		return v_status;
	}

	public void setV_status(int v_status) {
		this.v_status = v_status;
	}

	@Override
	public String toString() {
		return "RecruitCommand [r_num=" + r_num + ", r_id=" + r_id + ", r_upload=" + r_upload + ", r_filename="
				+ r_filename + ", r_title=" + r_title + ", r_start_date=" + r_start_date + ", r_end_date=" + r_end_date
				+ ", r_people=" + r_people + ", r_people_count=" + r_people_count + ", r_content=" + r_content
				+ ", r_status=" + r_status + ", v_num=" + v_num + ", v_id=" + v_id + ", v_date=" + v_date + ", v_ip="
				+ v_ip + ", v_status=" + v_status + "]";
	}



	
}


