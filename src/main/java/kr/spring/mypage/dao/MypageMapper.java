package kr.spring.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.recruit.domain.RecruitCommand;


public interface MypageMapper {
	
	@Select("SELECT * FROM member m LEFT OUTER JOIN member_detail d ON m.m_id=d.m_id WHERE m.m_id=#{m_id}")
	public MemberCommand selectId(String id);
	
	@Select("SELECT * FROM volunteer WHERE v_id=#{v_id}")
	public List<RecruitCommand> selectList(String v_id);
	
	@Select("SELECT * FROM recruit WHERE r_id=#{r_id}")
	public List<RecruitCommand> selectRecruitList(String r_id);
	
	
	
	//���� �ֱ� ���� ��θ���Ʈ �������
	@Select("SELECT * FROM DONATION WHERE dona_id=#{dona_id} AND DONA_DATE=(select max(DONA_DATE)from DONATION)")
	public OrderCommand selectNowList(String dona_id);
	
	@Select("SELECT COUNT(*) FROM donation WHERE dona_id=#{dona_id}")
	public int selectCountdonation(String dona_id);
	
	//�ӽú�ȣ�� ������ �θ��� //�Ϲ�ȸ�� //���� �Ϸ�
	@Select("SELECT * FROM AP_BO_CALL WHERE call_name=#{call_name}")
	public List<ApCallCommand> selectCallListComplete(String call_name2);
	//�ӽú�ȣ�� ������ �θ��� //�Ϲ�ȸ�� //���� ��û ��
	@Select("SELECT * FROM ap_call_home WHERE call_name=#{call_name}")
	public List<ApCallCommand> selectCallList(String call_name);
	//�ӽú�ȣ�� ������ �θ��� //�ӽú�ȣ��
	@Select("SELECT *FROM ap_bo_call WHERE bo_call_id=#{bo_call_id}")
	public List<ApCallCommand> selectCallbohoList(String bo_call_id);
	
	//�ӽú�ȣ�� ���� �ñ�� //�Ϲ�ȸ�� ����������
	@Select("SELECT * FROM ap_bo WHERE bo_id=#{bo_id}")
	public List<ApBoCommand>selectBoList(String bo_id);	
	//�ӽú�ȣ�� ���� �ñ�� //�ӽú�ȣ�� 
	@Select("SELECT * FROM ap_bo WHERE id=#{id}")
	public List<ApBoCommand>selectBohoCallList(String id);
	
	//donation���̺��� �Ŀ��� ��� ������������ ���
		@Select("SELECT * FROM donation WHERE dona_id=#{dona_id}")
		public List<OrderCommand> selectDanaList(String dona_id);
	
	//��ȣ�ҿ��� �Ŀ���������Ʈ �̱�
	public List<OrderCommand>selectDonaSList(Map<String, Object> map);
	
	@Select("SELECT g_name, g_price FROM goods_db WHERE g_num =#{g_num}")
	public GoodsListCommand getMyGoods(Integer g_num);
	

	
	

}
