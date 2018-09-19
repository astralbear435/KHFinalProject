package kr.spring.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.goods.domain.AdminCheck;
import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;

public interface GoodsMapper {
	public int selectRowCount(Map<String,Object> map);
	
	public List<GoodsCommand> getASList(Map<String,Object> map);
	@Insert("INSERT INTO AS_GOODS(as_name,as_id,as_location,pad,dogfood,catfood,shampoo,catsand,as_auth,as_did) VALUES(#{as_name},#{as_id},#{as_location},#{pad},#{dogfood},#{catfood},#{shampoo},#{catsand},4,#{as_did})")
	public void insertAS(GoodsCommand goods);
	@Select("SELECT * FROM AS_GOODS WHERE as_name=#{as_name}")
	public GoodsCommand selectDetailAS(String as_name);
	@Select("SELECT * FROM GOODS_DB")
	public List<GoodsListCommand> goodsPhotoList(Map<String,Object> photo_map);
	@Select("SELECT * FROM GOODS_DB WHERE g_num=#{g_num}")
	public GoodsListCommand selectDetailGoods(Integer g_num);
	
	//��ٱ��� �ֱ�
	@Insert("INSERT INTO persnal_cart(p_id,p_name,p_num,p_price,p_amount,p_goodsname,p_goodsphoto,p_cartnum) VALUES(#{p_id},#{p_name},#{p_num},#{p_price},#{p_amount},#{p_goodsname},#{p_goodsphoto},cart_seq.nextval)")
	public void insertCart(CartListCommand cart);
	//��ٱ��� �ҷ�����
	@Select("SELECT * FROM persnal_cart WHERE p_id=#{p_id} ORDER BY p_cartnum DESC")
	public List<CartListCommand> getCart(String p_id);
	//��ٱ��� ����
	@Delete("DELETE FROM persnal_cart WHERE p_cartnum=#{cart_num}")
	public int deleteCart(Integer p_cartnum);
	
	//���ο� ���� ���
	@Insert("INSERT INTO admin_check(ch_id,ch_name,ch_amount,ch_message,ch_url) VALUES(#{ch_id},#{ch_name},#{ch_amount},#{ch_message,jdbcType=VARCHAR},#{ch_url,jdbcType=VARCHAR})")
	public void addNewGoods(AdminCheck check);
	//������ ���� �̾ƿ���
	@Select("SELECT * FROM persnal_cart WHERE p_cartnum=#{cart_num}")
	public List<CartListCommand> getOrderPrice(Integer p_cartnum);
	
	
	//�ֹ� ���
	@Insert("INSERT INTO DONATION(dona_num,dona_id,dona_asname,dona_date,dona_goodsnum,dona_goodsamount,dona_price,dona_username,dona_message) VALUES(order_seq.nextval,#{dona_id},#{dona_asname},SYSDATE,#{dona_goodsnum},#{dona_goodsamount},#{dona_price},#{dona_username,jdbcType=VARCHAR},#{dona_message,jdbcType=VARCHAR})")
	public void InsertOrder(OrderCommand order);
	//������ ��ȣ �޾ƿ���
	@Select("select dona_num from DONATION WHERE DONA_DATE = (select max(DONA_DATE)from DONATION)")
	public int selectDona_num();
	//��μ���,�޼��� ������Ʈ
	@Update("UPDATE DONATION SET dona_username=#{dona_username},dona_message=#{dona_message} WHERE dona_num=#{dona_num}")
	public void updateOrder(OrderCommand order);
	//auth�� �޾ƿ���
	@Select("SELECT auth FROM MEMBER WHERE m_id=#{id}")
	public int selectAuth(String id);
	//��ȣ���� did�� �޾ƿ��� => did���� ��ǰ����ߴ������ߴ��� �������ִ� ���̴�.
	@Select("SELECT as_did FROM as_goods WHERE as_id=#{id}")
	public int selectDid(String id);
	//as_goods ��ȣ�� ���� ������Ʈ
	@Update("UPDATE as_goods SET pad=#{pad},dogfood=#{dogfood},catfood=#{catfood},shampoo=#{shampoo},catsand=#{catsand},as_did=#{as_did} WHERE as_id=#{as_id}")
	public void updateAs(GoodsCommand goods);
	//������ �ش� �÷� ���� ����
	public void minusCount(Map<String,Object> map2);
	
	//���� �� ���� �ݾ�
	@Select("SELECT NVL(sum(DONA_PRICE),0) FROM DONATION") 
	public int selectTotalPayment();
	//���� �� �����ݾ�
	@Select("SELECT NVL(sum(DONA_PRICE),0) FROM DONATION WHERE TO_DATE(DONA_DATE,'yyyy-MM-dd') = TO_DATE(sysdate,'yyyy-MM-dd')")
	public int selectTodayPayment();

}

