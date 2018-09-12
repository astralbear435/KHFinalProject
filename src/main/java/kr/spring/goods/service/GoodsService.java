package kr.spring.goods.service;

import java.util.List;
import java.util.Map;

import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;


public interface GoodsService {
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
	public void addNewGoods(GoodsListCommand goods);
	//��ٱ��Ͽ��� �κ��ֹ� �� �� �� ��������
	public List<CartListCommand> getOrderPrice(Integer p_cartnum);
	//���� ���(�ֹ�)
	public void insertOrder(OrderCommand order);
	//���� ��� ������Ʈ(�����,���޳���)
	public void updateOrder(OrderCommand order);
	//������ �� �޾ƿ���
	public int selectDona_num();
}