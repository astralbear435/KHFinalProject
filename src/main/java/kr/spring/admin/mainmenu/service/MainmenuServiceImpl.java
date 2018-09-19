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
	public int selectActiveMenuCount(String use) {
		return mainmenuMapper.selectActiveMenuCount(use);
	}

	@Override
	public List<MainmenuCommend> selectActiveMenu(String use) {
		return mainmenuMapper.selectActiveMenu(use);
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
		for(MainmenuCommend m : list) {
			if(mainmenuCommend.getMenu_order()==m.getMenu_order()&&mainmenuCommend.getMenu_num()!=m.getMenu_num()) {
				System.out.println("--------->"+m);
				m.setMenu_order((mainmenuCommend.getMenu_order()));
				System.out.println("--------->"+m);
				mainmenuMapper.updateMenuOrder(m);
			}
			mainmenuMapper.updateMenu(mainmenuCommend);
		}
		
	}

}
