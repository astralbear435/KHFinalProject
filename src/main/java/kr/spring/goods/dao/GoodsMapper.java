package kr.spring.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;

public interface GoodsMapper {
	public int selectRowCount(Map<String,Object> map);
	
	public List<GoodsCommand> getASList(Map<String,Object> map);
	@Insert("INSERT INTO AS_GOODS(as_name,as_id,as_location,pad,dogfood,catfood,shampoo,catsand,as_auth) VALUES(#{as_name},#{as_id},#{as_location},#{pad},#{dogfood},#{cattoy},#{shampoo},#{catsand},2)")
	public void insertAS(GoodsCommand goods);
	@Select("SELECT * FROM AS_GOODS WHERE as_name=#{as_name}")
	public GoodsCommand selectDetailAS(String as_name);
	@Select("SELECT * FROM GOODS_DB")
	public List<GoodsListCommand> goodsPhotoList(Map<String,Object> photo_map);
	@Select("SELECT * FROM GOODS_DB WHERE g_num=#{g_num}")
	public GoodsListCommand selectDetailGoods(Integer g_num);
	
	//장바구니 넣기
	@Insert("INSERT INTO persnal_cart(p_id,p_name,p_num,p_price,p_amount,p_goodsname,p_goodsphoto,p_cartnum) VALUES(#{p_id},#{p_name},#{p_num},#{p_price},#{p_amount},#{p_goodsname},#{p_goodsphoto},cart_seq.nextval)")
	public void insertCart(CartListCommand cart);
	//장바구니 불러오기
	@Select("SELECT * FROM persnal_cart WHERE p_id=#{p_id} ORDER BY p_cartnum DESC")
	public List<CartListCommand> getCart(String p_id);
	//장바구니 삭제
	@Delete("DELETE FROM persnal_cart WHERE p_cartnum=#{cart_num}")
	public int deleteCart(Integer p_cartnum);
	
	//새로운 물건 등록
	@Insert("INSERT INTO GOODS_DB(g_name,g_id,amount) VALUES(#{g_name},#{g_id},#{g_amount})")
	public void addNewGoods(GoodsListCommand goods);
	//선택한 정보 뽑아오기
	@Select("SELECT * FROM persnal_cart WHERE p_cartnum=#{cart_num}")
	public List<CartListCommand> getOrderPrice(Integer p_cartnum);
	
	
	//주문 등록
	@Insert("INSERT INTO DONATION(dona_num,dona_id,dona_asname,dona_date,dona_goodsnum,dona_goodsamount,dona_price,dona_username,dona_message) VALUES(order_seq.nextval,#{dona_id},#{dona_asname},SYSDATE,#{dona_goodsnum},#{dona_goodsamount},#{dona_price},#{dona_username,jdbcType=VARCHAR},#{dona_message,jdbcType=VARCHAR})")
	public void InsertOrder(OrderCommand order);
	//시퀀스 번호 받아오기
	@Select("select dona_num from DONATION WHERE DONA_DATE = (select max(DONA_DATE)from DONATION)")
	public int selectDona_num();
	//기부성명,메세지 업데이트
	@Update("UPDATE DONATION SET dona_username=#{dona_username},dona_message=#{dona_message} WHERE dona_num=#{dona_num}")
	public void updateOrder(OrderCommand order);
}
