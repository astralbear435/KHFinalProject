package kr.spring.shelter.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ShelterCommand {
	@NotEmpty
	private String s_id;
	private int auth;
	@NotEmpty
	private String s_passwd;
	@NotEmpty
	private String s_name;
	@NotEmpty
	private String s_license_num;
	@NotEmpty
	private String s_phone;
	@NotEmpty
	private String s_email;
	@NotEmpty
	private String s_zipcode;
	@NotEmpty
	private String s_address1;
	private String s_address2;
	private Date s_reg_date;
	private String s_content;
	private MultipartFile s_upload; // 업로드 파일(저장될때는 얘가 동작해서 저장 > db에는 없음)
	private byte[] s_uploadfile; // DB에 저장되는 파일
	private String s_filename; // 파일명

	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd) {
		if(auth>0 && s_passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//MultipartFile -> byte[] 변환, 정보얻음
	//파일명 구하기
	public void setS_upload(MultipartFile upload) throws IOException{
		this.s_upload = upload;

		//byte[] 데이터 저장
		setS_uploadfile(upload.getBytes());
		//파일명
		setS_filename(upload.getOriginalFilename());
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getS_passwd() {
		return s_passwd;
	}

	public void setS_passwd(String s_passwd) {
		this.s_passwd = s_passwd;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_license_num() {
		return s_license_num;
	}

	public void setS_license_num(String s_license_num) {
		this.s_license_num = s_license_num;
	}

	public String getS_zipcode() {
		return s_zipcode;
	}

	public void setS_zipcode(String s_zipcode) {
		this.s_zipcode = s_zipcode;
	}

	public String getS_address1() {
		return s_address1;
	}

	public void setS_address1(String s_address1) {
		this.s_address1 = s_address1;
	}

	public String getS_address2() {
		return s_address2;
	}

	public void setS_address2(String s_address2) {
		this.s_address2 = s_address2;
	}

	public String getS_phone() {
		return s_phone;
	}

	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}

	public String getS_email() {
		return s_email;
	}

	public void setS_email(String s_email) {
		this.s_email = s_email;
	}

	public Date getS_reg_date() {
		return s_reg_date;
	}

	public void setS_reg_date(Date s_reg_date) {
		this.s_reg_date = s_reg_date;
	}

	public byte[] getS_uploadfile() {
		return s_uploadfile;
	}

	public void setS_uploadfile(byte[] s_uploadfile) {
		this.s_uploadfile = s_uploadfile;
	}

	public String getS_filename() {
		return s_filename;
	}

	public void setS_filename(String s_filename) {
		this.s_filename = s_filename;
	}

	public MultipartFile getS_upload() {
		return s_upload;
	}
	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	@Override
	public String toString() {
		return "ShelterCommand [s_id=" + s_id + ", auth=" + auth + ", s_passwd=" + s_passwd + ", s_name=" + s_name
				+ ", s_license_num=" + s_license_num + ", s_zipcode=" + s_zipcode + ", s_address1=" + s_address1
				+ ", s_address2=" + s_address2 + ", s_phone=" + s_phone + ", s_email=" + s_email + ", s_reg_date="
				+ s_reg_date + ", s_upload=" + s_upload + ", s_filename=" + s_filename + ", s_content=" + s_content + "]";
	}
}
