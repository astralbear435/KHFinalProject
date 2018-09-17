package kr.spring.goods.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.goods.dao.GoodsMapper;
import kr.spring.goods.domain.AdminCheck;
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
		//보호소 리스트
		return goodsMapper.getASList(map);
	}


	@Override
	public int selectRowCount(Map<String, Object> map) {
		//페이지 갯수카운트로 페이지 수 나눔
		return goodsMapper.selectRowCount(map);
	}


	@Override
	public List<GoodsListCommand> goodsPhotoList(Map<String, Object> photo_map) {
		//물건 사진 뽑기
		return goodsMapper.goodsPhotoList(photo_map);
	}

	@Override
	public GoodsListCommand selectDetailGoods(Integer g_num) {
		// 물품 상세 정보
		return goodsMapper.selectDetailGoods(g_num);
	}

	@Override
	public GoodsCommand selectDetailAS(String as_name) {
		// 보호소 1개씩 상세 (dto에 담는)
		return goodsMapper.selectDetailAS(as_name);
	}

	//장바구니담기
	@Override
	public void insertCart(CartListCommand cart) {
		goodsMapper.insertCart(cart);
	}

	//장바구니 불러오기
	public List<CartListCommand> getCart(String p_id) {
		
		return 	goodsMapper.getCart(p_id);
	}

	@Override
	public int deleteCart(Integer p_cartnum) {
		//장바구니 선택삭제
		return goodsMapper.deleteCart(p_cartnum);
	}
//특수 물건 추가 등록
	@Override
	public void addNewGoods(AdminCheck check) {
		goodsMapper.addNewGoods(check);
	}

	@Override
	public List<CartListCommand> getOrderPrice(Integer p_cartnum) {
		// 선택한 값 가격 합계갖고오기
		return goodsMapper.getOrderPrice(p_cartnum);
	}

	@Override
	public void insertOrder(OrderCommand order) {
		//주문 등록하기
		goodsMapper.InsertOrder(order);		
	}

	@Override
	public void updateOrder(OrderCommand order) {
		//주문 업데이트(기부성명,메세지내용)
		goodsMapper.updateOrder(order);	
		
	}

	@Override
	public int selectDona_num() {
		// 시퀀스 받아오기
		return goodsMapper.selectDona_num();
	}

	@Override
	public int selectAuth(String id) {
		// member 데이터베이스의 auth값 받아오기
		return goodsMapper.selectAuth(id);
	}

	@Override
	public int selectDid(String id) {
		//보호소 회원의 did값 받아오기
		return goodsMapper.selectDid(id);
	}

	@Override
	public void updateAs(GoodsCommand goods) {
		//as_goods 갯수 업데이트
		goodsMapper.updateAs(goods);
	}

	@Override
	public void minusCount(Map<String, Object> map2) {
		// 결제시 as_goods에서 신청수량을 결제갯수만큼 뺀다
		goodsMapper.minusCount(map2);
		
	}

}
