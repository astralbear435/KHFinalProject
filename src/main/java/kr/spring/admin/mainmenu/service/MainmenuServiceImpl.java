package kr.spring.admin.mainmenu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.admin.mainmenu.dao.MainmenuMapper;
import kr.spring.admin.mainmenu.domain.MainmenuCommend;

@Service("mainmenuService")
public class MainmenuServiceImpl implements MainmenuService{
	
	@Resource
	private MainmenuMapper mainmenuMapper;
	
	
	@Override
	public int selectmenuCount() {
		
		return mainmenuMapper.selectmenuCount();
	}

	@Override
	public int selectActiveMenuCount() {
		return mainmenuMapper.selectActiveMenuCount();
	}

	@Override
	public List<MainmenuCommend> selectActiveMenu() {
		return mainmenuMapper.selectActiveMenu();
	}

	@Override
	public List<MainmenuCommend> selectMenuList() {
		return mainmenuMapper.selectMenuList();
	}

	@Override
	public void insertMenu(MainmenuCommend mainmenuCommend) {
		mainmenuMapper.insertMenu(mainmenuCommend);
		
	}

	@Override
	public void updateMenuOrder(MainmenuCommend mainmenuCommend) {
		mainmenuMapper.updateMenuOrder(mainmenuCommend);
		
	}

	@Override
	public void updateMenuDd(MainmenuCommend mainmenuCommend) {
		mainmenuMapper.updateMenuDd(mainmenuCommend);
		
	}

	@Override
	public void deleteMenu(int menu_num) {
		MainmenuCommend menu = mainmenuMapper.selectMenu(menu_num);
		List<MainmenuCommend> list = mainmenuMapper.selectMenuList();
		for(MainmenuCommend m : list) {
			if(menu.getMenu_order()<m.getMenu_order()) {
				m.setMenu_order((m.getMenu_order()-1));
				System.out.println("--------->"+m);
				mainmenuMapper.updateMenuOrder(m);
			}
		}
		mainmenuMapper.deleteMenu(menu_num); 
		
	}

	@Override
	public List<MainmenuCommend> selectDdMenu(String dd) {
		
		return mainmenuMapper.selectDdMenu(dd);
	}

	@Override
	public MainmenuCommend selectMenu(int menu_num) {
		
		return mainmenuMapper.selectMenu(menu_num);
	}

	@Override
	public void updateMenu(MainmenuCommend mainmenuCommend) {
		List<MainmenuCommend> list = mainmenuMapper.selectMenuList();
		MainmenuCommend temp1= new MainmenuCommend();
		int order=0;
		for(MainmenuCommend m : list) {
			if(mainmenuCommend.getMenu_order()==m.getMenu_order()) {
				System.out.println(mainmenuCommend.getMenu_order()+"--------->"+m);
				temp1=m;
				System.out.println("--------->"+temp1);
			}
			if(mainmenuCommend.getMenu_num()==m.getMenu_num()) {
				order=m.getMenu_order();
			}
			
		}
		temp1.setMenu_order(order);
		System.out.println("temp1--------->"+temp1);
		mainmenuMapper.updateMenuOrder(temp1); 
		mainmenuMapper.updateMenu(mainmenuCommend);   
	}

}
