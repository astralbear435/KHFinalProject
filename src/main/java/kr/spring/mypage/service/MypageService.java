package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.recruit.domain.RecruitCommand;


public interface MypageService {
	public MemberCommand selectId(String id);
	public List<RecruitCommand> selectList(String v_id);	
	public List<RecruitCommand> selectRecruitList(String r_id);	
	
	public List<OrderCommand> selectDanaList(String dona_id);
	
	public int selectCountdonation(String dona_id);
	//(����):���� �ֱ� ��θ���Ʈ �ʿ��ؼ� ����մϴ�.
	public OrderCommand selectNowList(String dona_id);

	
	//�ӽú�ȣ�� ���� �ñ�� //�Ϲ�ȸ�� 
	public List<ApBoCommand>selectBoList(String bo_id);
	//�ӽú�ȣ�� ���� �ñ�� //�ӽú�ȣ��
	public List<ApBoCommand>selectBohoCallList(String id);
	
	//�ӽú�ȣ�� ������ �θ��� //�Ϲ�ȸ�� //���� �Ϸ�
	public List<ApCallCommand> selectCallListComplete(String call_name2);
	//�ӽú�ȣ�� ������ �θ��� //�Ϲ�ȸ�� //���� ��û ��
	public List<ApCallCommand> selectCallList(String call_name);
	//�ӽú�ȣ�� ������ �θ��� //�ӽú�ȣ��
	public List<ApCallCommand> selectCallbohoList(String bo_call_id);	
	
	public List<ApCommand> selectApList();
	//��ȣ�� �������������� �Ŀ������� ������
	public List<OrderCommand> selectDonaSList(Map<String, Object> map);
	//�������������� ����, ��ǰ���� ������
	public GoodsListCommand getMyGoods(Integer g_num);
	

	  
	
	
}
