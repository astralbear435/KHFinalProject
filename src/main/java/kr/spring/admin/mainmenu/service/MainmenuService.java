package kr.spring.admin.mainmenu.service;

import java.util.List;


import kr.spring.admin.mainmenu.domain.MainmenuCommend;

public interface MainmenuService {
	public int selectmenuCount();
	public int selectActiveMenuCount();
	public List<MainmenuCommend> selectActiveMenu();
	public List<MainmenuCommend> selectMenuList();
	public List<MainmenuCommend> selectDdMenu(String dd);
	public MainmenuCommend selectMenu(int menu_num);
	public void insertMenu(MainmenuCommend mainmenuCommend);
	public void updateMenuOrder(MainmenuCommend mainmenuCommend);
	public void updateMenuDd(MainmenuCommend mainmenuCommend);
	public void updateMenu(MainmenuCommend mainmenuCommend);
	public void deleteMenu(int menu_num);
}
