package kr.spring.goods.service;

import java.util.List;
import java.util.Map;

import kr.spring.goods.domain.AdminCheck;
import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;


public interface GoodsService {
	//as_goods��� ��ȣ�� ���� ����ϱ�
	public void insert(GoodsCommand goods);
	public List<GoodsCommand> getASList(Map<String,Object> map);
	public GoodsCommand selectDetailAS(String as_name);
	public int selectRowCount(Map<String,Object> map);
	List<GoodsListCommand> goodsPhotoList(Map<String, Object> photo_map);
	public GoodsListCommand selectDetailGoods(Integer g_num);
	
	//��ٱ��� �ֱ�
	public void insertCart(CartListCommand cart);
	//��ٱ��� �ҷ�����
	public List<CartListCommand> getCart(String p_id);
	//��ٱ��� ����
	public int deleteCart(Integer p_cartnum);
	
	//���� �߰� ���
	public void addNewGoods(AdminCheck check);
	//��ٱ��Ͽ��� �κ��ֹ� �� �� �� �������
	public List<CartListCommand> getOrderPrice(Integer p_cartnum);
	//���� ���(�ֹ�)
	public void insertOrder(OrderCommand order);
	//���� ��� ������Ʈ(�����,���޳���)
	public void updateOrder(OrderCommand order);
	//������ �� �޾ƿ���
	public int selectDona_num();
	//ȸ���� auth �� �޾ƿ���
	public int selectAuth(String id);
	//��ȣ���� did�� �޾ƿ���
	public int selectDid(String id);
	
	//������Ʈ
	public void updateAs(GoodsCommand goods);
	//������ ī��Ʈ ����
	public void minusCount(Map<String,Object> map2);
	
}
