package kr.spring.admin.mainmenu.domain;

public class MainmenuCommend {
	private int menu_num;
	private String menu_name;
	private String menu_url;
	private int menu_order;
	private int menu_depth;
	private int menu_parent_num;
	private String menu_dropdown;
	private String menu_use;
	public int getMenu_num() {
		return menu_num;
	}
	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public int getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}
	public int getMenu_depth() {
		return menu_depth;
	}
	public void setMenu_depth(int menu_depth) {
		this.menu_depth = menu_depth;
	}
	public int getMenu_parent_num() {
		return menu_parent_num;
	}
	public void setMenu_parent_num(int menu_parent_num) {
		this.menu_parent_num = menu_parent_num;
	}
	public String getMenu_dropdown() {
		return menu_dropdown;
	}
	public void setMenu_dropdown(String menu_dropdown) {
		this.menu_dropdown = menu_dropdown;
	}
	public String getMenu_use() {
		return menu_use;
	}
	public void setMenu_use(String menu_use) {
		this.menu_use = menu_use;
	}
	@Override
	public String toString() {
		return "MainmenuCommend [menu_num=" + menu_num + ", menu_name=" + menu_name + ", menu_url=" + menu_url
				+ ", menu_order=" + menu_order + ", menu_depth=" + menu_depth + ", menu_parent_num=" + menu_parent_num
				+ ", menu_dropdown=" + menu_dropdown + ", menu_use=" + menu_use + "]";
	}
	
	
}
