package kr.spring.member.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class MemberCommand {
	
	private int auth;
	
	private int m_num;
	@NotEmpty
	private String m_id;
	private String m_name;
	private String m_nickname;
	@NotEmpty
	private String m_passwd;
	private String m_phone;
	private String m_zipcode;
	private String m_address;
	private String m_address_detail;
	@NotEmpty
	private String m_email;
	private MultipartFile upload;	//업로드 파일
	private byte[] uploadProfile;	//DB에 저장된 파일
	private String profileFilename;	//파일명
	private String m_birth;
	private Date m_reg_date;
	private String m_checked_email;
	
	private String verify_key;
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd) {

		if(auth > 0 && m_passwd.equals(userPasswd)) {
			
			return true;
		}
		
		return false;
	}
	
	public String getVerify_key() {
		return verify_key;
	}
	public void setVerify_key(String verify_key) {
		this.verify_key = verify_key;
	}
	public String getM_checked_email() {
		return m_checked_email;
	}
	public void setM_checked_email(String m_checked_email) {
		this.m_checked_email = m_checked_email;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_nickname() {
		return m_nickname;
	}
	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}
	public String getM_passwd() {
		return m_passwd;
	}
	public void setM_passwd(String m_passwd) {
		this.m_passwd = m_passwd;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_address() {
		return m_address;
	}
	public void setM_address(String m_address) {
		this.m_address = m_address;
	}
	public String getM_address_detail() {
		return m_address_detail;
	}
	public void setM_address_detail(String m_address_detail) {
		this.m_address_detail = m_address_detail;
	}
	public String getM_email() {
		return m_email;
	}
	
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public MultipartFile getUpload() {
		return upload;
	}
	//MultipartFile -> byte[] 로 변환
	//파일명 구하기
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
						// byte[] 데이터 저장
		setUploadProfile(upload.getBytes());
						// 파일명
		setProfileFilename(upload.getOriginalFilename());
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
	public String getM_birth() {
		return m_birth;
	}
	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}
	public Date getM_reg_date() {
		return m_reg_date;
	}
	public void setM_reg_date(Date m_reg_date) {
		this.m_reg_date = m_reg_date;
	}

	@Override
	public String toString() {
		return "MemberCommand [auth=" + auth + ", m_num=" + m_num + ", m_id=" + m_id + ", m_name=" + m_name
				+ ", m_nickname=" + m_nickname + ", m_passwd=" + m_passwd + ", m_phone=" + m_phone + ", m_zipcode="
				+ m_zipcode + ", m_address=" + m_address + ", m_address_detail=" + m_address_detail + ", m_email="
				+ m_email + ", upload=" + upload + ", profileFilename=" + profileFilename + ", m_birth=" + m_birth
				+ ", m_reg_date=" + m_reg_date + ", m_checked_email=" + m_checked_email + ", verify_key=" + verify_key + "]";
	}
}












