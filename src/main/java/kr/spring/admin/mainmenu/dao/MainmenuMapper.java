package kr.spring.admin.mainmenu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.mainmenu.domain.MainmenuCommend;

public interface MainmenuMapper {
	
	@Select("SELECT count(*) FROM MAIN_MENU")
	public int selectmenuCount();
	@Select("SELECT count(*) FROM MAIN_MENU WHERE menu_use=#{use}")
	public int selectActiveMenuCount(String use);
	@Select("SELECT * FROM MAIN_MENU WHERE menu_use=#{use}")
	public List<MainmenuCommend> selectActiveMenu(String use);
	@Select("SELECT * FROM MAIN_MENU WHERE menu_dropdown=#{dd} ")
	public List<MainmenuCommend> selectDdMenu(String dd);
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (select * FROM (SELECT * FROM MAIN_MENU START WITH menu_depth=1 CONNECT BY PRIOR menu_num = menu_parent_num ORDER SIBLINGS BY menu_num asc)b)a)")
	public List<MainmenuCommend> selectMenuList();
	@Select("SELECT * FROM MAIN_MENU WHERE menu_num=#{menu_num} ")
	public MainmenuCommend selectMenu(int menu_num);
	@Insert("INSERT INTO MAIN_MENU(menu_num,menu_name,menu_url,menu_order,menu_depth,menu_parent_num,menu_dropdown,menu_use) VALUES(main_menu_menu_num_SEQ.nextval,#{menu_name},#{menu_url},#{menu_order},#{menu_depth},#{menu_parent_num},#{menu_dropdown},#{menu_use})")
	public void insertMenu(MainmenuCommend mainmenuCommend);
	@Update("UPDATE MAIN_MENU SET menu_order=#{menu_order} WHERE menu_num=#{menu_num}")
	public void updateMenuOrder(MainmenuCommend mainmenuCommend);
	@Update("UPDATE MAIN_MENU SET menu_dropdown=#{menu_dropdown} WHERE menu_num=#{menu_num}")
	public void updateMenuDd(MainmenuCommend mainmenuCommend);
	@Delete("DELETE FROM MAIN_MENU WEHRE menu_num=#{menu_num}")
	public void deleteMenu(int menu_num);

}