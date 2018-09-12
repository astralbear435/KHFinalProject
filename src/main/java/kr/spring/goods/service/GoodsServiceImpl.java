package kr.spring.goods.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.goods.dao.GoodsMapper;
import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsMapper;
	
	@Override
	public void insert(GoodsCommand goods) {
		// TODO Auto-generated method stub
		goodsMapper.insertAS(goods);
	}

	@Override
	public List<GoodsCommand> getASList(Map<String, Object> map) {
		//��ȣ�� ����Ʈ
		return goodsMapper.getASList(map);
	}


	@Override
	public int selectRowCount(Map<String, Object> map) {
		//������ ����ī��Ʈ�� ������ �� ����
		return goodsMapper.selectRowCount(map);
	}


	@Override
	public List<GoodsListCommand> goodsPhotoList(Map<String, Object> photo_map) {
		//���� ���� �̱�
		return goodsMapper.goodsPhotoList(photo_map);
	}

	@Override
	public GoodsListCommand selectDetailGoods(Integer g_num) {
		// ��ǰ �� ����
		return goodsMapper.selectDetailGoods(g_num);
	}

	@Override
	public GoodsCommand selectDetailAS(String as_name) {
		// ��ȣ�� 1���� �� (dto�� ���)
		return goodsMapper.selectDetailAS(as_name);
	}

	//��ٱ��ϴ��
	@Override
	public void insertCart(CartListCommand cart) {
		goodsMapper.insertCart(cart);
	}

	//��ٱ��� �ҷ�����
	public List<CartListCommand> getCart(String p_id) {
		
		return 	goodsMapper.getCart(p_id);
	}

	@Override
	public int deleteCart(Integer p_cartnum) {
		//��ٱ��� ���û���
		return goodsMapper.deleteCart(p_cartnum);
	}
//Ư�� ���� �߰� ���
	@Override
	public void addNewGoods(GoodsListCommand goods) {
		goodsMapper.addNewGoods(goods);
	}

	@Override
	public List<CartListCommand> getOrderPrice(Integer p_cartnum) {
		// ������ �� ���� �հ谮�����
		return goodsMapper.getOrderPrice(p_cartnum);
	}

	@Override
	public void insertOrder(OrderCommand order) {
		//�ֹ� ����ϱ�
		goodsMapper.InsertOrder(order);		
	}

	@Override
	public void updateOrder(OrderCommand order) {
		//�ֹ� ������Ʈ(��μ���,�޼�������)
		goodsMapper.updateOrder(order);	
		
	}

	@Override
	public int selectDona_num() {
		// ������ �޾ƿ���
		return goodsMapper.selectDona_num();
	}

	@Override
	public int selectAuth(String id) {
		// TODO Auto-generated method stub
		return goodsMapper.selectAuth(id);
	}

}
