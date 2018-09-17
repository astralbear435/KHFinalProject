package kr.spring.goods.service;

import java.util.List;
import java.util.Map;

import kr.spring.goods.domain.AdminCheck;
import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;


public interface GoodsService {
	//as_goods디비에 보호소 정보 등록하기
	public void insert(GoodsCommand goods);
	public List<GoodsCommand> getASList(Map<String,Object> map);
	public GoodsCommand selectDetailAS(String as_name);
	public int selectRowCount(Map<String,Object> map);
	List<GoodsListCommand> goodsPhotoList(Map<String, Object> photo_map);
	public GoodsListCommand selectDetailGoods(Integer g_num);
	
	//장바구니 넣기
	public void insertCart(CartListCommand cart);
	//장바구니 불러오기
	public List<CartListCommand> getCart(String p_id);
	//장바구니 삭제
	public int deleteCart(Integer p_cartnum);
	
	//물건 추가 등록
	public void addNewGoods(AdminCheck check);
	//장바구니에서 부분주문 할 때 합 갖고오기
	public List<CartListCommand> getOrderPrice(Integer p_cartnum);
	//결제 등록(주문)
	public void insertOrder(OrderCommand order);
	//결제 등록 업데이트(기부자,전달내용)
	public void updateOrder(OrderCommand order);
	//시퀀스 값 받아오기
	public int selectDona_num();
	//회원의 auth 값 받아오기
	public int selectAuth(String id);
	//보호소의 did값 받아오기
	public int selectDid(String id);
	
	//업데이트
	public void updateAs(GoodsCommand goods);
	//결제시 카운트 빼기
	public void minusCount(Map<String,Object> map2);
	
}
