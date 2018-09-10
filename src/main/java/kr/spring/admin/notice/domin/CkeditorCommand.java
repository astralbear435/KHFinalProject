package kr.spring.admin.notice.domin;

import org.springframework.web.multipart.MultipartFile;

public class CkeditorCommand {
	private String n_attach_path;
	private MultipartFile n_upload;
	private String n_filename;
	private String CKEditorFuncNum;
	public String getN_attach_path() {
		return n_attach_path;
	}
	public void setN_attach_path(String n_attach_path) {
		this.n_attach_path = n_attach_path;
	}
	public MultipartFile getN_upload() {
		return n_upload;
	}
	public void setN_upload(MultipartFile n_upload) {
		this.n_upload = n_upload;
	}
	public String getN_filename() {
		return n_filename;
	}
	public void setN_filename(String n_filename) {
		this.n_filename = n_filename;
	}
	/**
	 * 
	 * @return the cKEditorFuncNum'
	 * @since 1.0
	 * 
	 */
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}
	/**
	 * 
	 * @param cKEditorFuncNum the cKEditorFuncNum to set
	 * @since 1.0
	 */
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
	@Override
	public String toString() {
		return "CkeditorCommand [n_attach_path=" + n_attach_path + ", n_filename="
				+ n_filename + ", CKEditorFuncNum=" + CKEditorFuncNum + "]";
	}
	
}
