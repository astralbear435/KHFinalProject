package kr.spring.admin.mainpage.domain;

public class MainImageCommand {
	Integer main_num; 
	String main_img_name;
	String s_main_img_name;
	Integer main_order;
	public Integer getMain_num() {
		return main_num;
	}
	public void setMain_num(Integer main_num) {
		this.main_num = main_num;
	}
	public String getMain_img_name() {
		return main_img_name;
	}
	public void setMain_img_name(String main_img_name) {
		this.main_img_name = main_img_name;
	}
	public String getS_main_img_name() {
		return s_main_img_name;
	}
	public void setS_main_img_name(String s_main_img_name) {
		this.s_main_img_name = s_main_img_name;
	}
	public Integer getMain_order() {
		return main_order;
	}
	public void setMain_order(Integer main_order) {
		this.main_order = main_order;
	}
	@Override
	public String toString() {
		return "MainImageCommand [main_num=" + main_num + ", main_img_name=" + main_img_name + ", s_main_img_name="
				+ s_main_img_name + ", main_order=" + main_order + "]";
	}
	
}
