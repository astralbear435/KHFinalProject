package kr.spring.board.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class BoardCommand {
	private int num;//�۹�ȣ
	private String id;//id(��ȣ�� ��)
	@NotEmpty
	private String an_name;//������ �̸�
	private String an_review;//��������
	@NotEmpty
	private String an_content;//�۳���(����Ʈ�����ͷ� ��ü)
	private String an_operate;//�߼�ȭ ����
	@NotEmpty
	private String an_species;//ǰ��
	@NotEmpty
	private String an_color;//����
	private String an_gender;//����
	private Date reg_date;//��ϳ�¥
	private int an_hit;//��ȸ��
	private MultipartFile upload;//���ε� ����
	private byte[] uploadfile;//DB�� ����� ����
	private String filename;//���ϸ�
	private String an_sheltername;//��ȣ�� �̸�
	
	private int re_cnt; //��� ��

	//MultipartFile -> byte[] ��ȯ
	//���ϸ� ���ϱ�
	public void setUpload(MultipartFile upload) 
	                           throws IOException{
		this.upload = upload;
		             //byte[] ������ ����
		setUploadfile(upload.getBytes());
		             //���ϸ�
		setFilename(upload.getOriginalFilename());
	}
	


	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getAn_name() {
		return an_name;
	}



	public void setAn_name(String an_name) {
		this.an_name = an_name;
	}



	public String getAn_review() {
		return an_review;
	}



	public void setAn_review(String an_review) {
		this.an_review = an_review;
	}



	public String getAn_content() {
		return an_content;
	}



	public void setAn_content(String an_content) {
		this.an_content = an_content;
	}



	public String getAn_operate() {
		return an_operate;
	}



	public void setAn_operate(String an_operate) {
		this.an_operate = an_operate;
	}



	public String getAn_species() {
		return an_species;
	}



	public void setAn_species(String an_species) {
		this.an_species = an_species;
	}



	public String getAn_color() {
		return an_color;
	}



	public void setAn_color(String an_color) {
		this.an_color = an_color;
	}



	public String getAn_gender() {
		return an_gender;
	}



	public void setAn_gender(String an_gender) {
		this.an_gender = an_gender;
	}



	public Date getReg_date() {
		return reg_date;
	}



	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}



	public int getAn_hit() {
		return an_hit;
	}



	public void setAn_hit(int an_hit) {
		this.an_hit = an_hit;
	}



	public byte[] getUploadfile() {
		return uploadfile;
	}



	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getAn_sheltername() {
		return an_sheltername;
	}



	public void setAn_sheltername(String an_sheltername) {
		this.an_sheltername = an_sheltername;
	}

	public int getRe_cnt() {
		return re_cnt;
	}



	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}



	public MultipartFile getUpload() {
		return upload;
	}

	@Override
	public String toString() {
		return "BoardCommand [num=" + num + ", id=" + id + ", an_name=" + an_name + ", an_review=" + an_review
				+ ", an_content=" + an_content + ", an_operate=" + an_operate + ", an_species=" + an_species
				+ ", an_color=" + an_color + ", an_gender=" + an_gender + ", reg_date=" + reg_date + ", an_hit="
				+ an_hit + ", upload=" + upload + ", uploadfile=" + Arrays.toString(uploadfile) + ", filename="
				+ filename + ", an_sheltername=" + an_sheltername + ", re_cnt=" + re_cnt
				+ "]";
	}


}