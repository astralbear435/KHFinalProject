package kr.spring.member.domain;

import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class MemberCommand {
	
	@NotEmpty
	private String user_id;
	@NotEmpty
	private int auth;
	@NotEmpty
	private int user_num;
	@NotEmpty
	private String user_name;
	@Size(min=10,max=40)
	@NotEmpty
	private String user_passwd;
	@NotEmpty
	private String user_phone;
	@NotEmpty
	private String user_birth;
	@Email
	@NotEmpty
	private String user_email;
	@NotEmpty
	private String address1;
	@NotEmpty
	private String address2;
	@NotEmpty
	private Date reg_date;
	private MultipartFile upload;	//업로드 파일
	private byte[] uploadProfile;	//DB에 저장된 파일
	private String profileFilename;	//파일명
	
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd) {
		
		if(auth > 0 && user_passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public int getAuth() {
		return auth;
	}


	public void setAuth(int auth) {
		this.auth = auth;
	}


	public int getUser_num() {
		return user_num;
	}


	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_passwd() {
		return user_passwd;
	}


	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}


	public String getUser_phone() {
		return user_phone;
	}


	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public String getUser_birth() {
		return user_birth;
	}


	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public Date getReg_date() {
		return reg_date;
	}


	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}


	public MultipartFile getUpload() {
		return upload;
	}


	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}


	public byte[] getUploadProfile() {
		return uploadProfile;
	}


	public void setUploadProfile(byte[] uploadProfile) {
		this.uploadProfile = uploadProfile;
	}


	public String getProfileFilename() {
		return profileFilename;
	}


	public void setProfileFilename(String profileFilename) {
		this.profileFilename = profileFilename;
	}


	@Override
	public String toString() {
		return "MemberCommand [user_id=" + user_id + ", auth=" + auth + ", user_num=" + user_num + ", user_name="
				+ user_name + ", user_passwd=" + user_passwd + ", user_phone=" + user_phone + ", user_birth="
				+ user_birth + ", user_email=" + user_email + ", address1=" + address1 + ", address2=" + address2
				+ ", reg_date=" + reg_date + ", upload=" + upload + ", uploadProfile=" + Arrays.toString(uploadProfile)
				+ ", profileFilename=" + profileFilename + "]";
	}	
	
}
