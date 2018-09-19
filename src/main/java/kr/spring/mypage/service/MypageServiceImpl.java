package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.mypage.dao.MypageMapper;
import kr.spring.recruit.domain.RecruitCommand;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {

	@Resource
	private MypageMapper mypageMapper;

	@Override
	public MemberCommand selectId(String id) {

		return mypageMapper.selectId(id);
	}

	@Override
	public List<RecruitCommand> selectList(String v_id) {

		return mypageMapper.selectList(v_id);
	}

	@Override
	public List<RecruitCommand> selectRecruitList(String r_id) {

		return mypageMapper.selectRecruitList(r_id);
	}

	@Override
	public List<OrderCommand> selectDanaList(String dona_id) {
		return mypageMapper.selectDanaList(dona_id);
	}

	@Override
	public int selectCountdonation(String dona_id) {
		return mypageMapper.selectCountdonation(dona_id);
	}

	@Override
	public List<ApCommand> selectApList() {
		// TODO Auto-generated method stub
		return null;
	}
	//------------------------------------------------------
	//�ӽú�ȣ�� ������ �θ��� //�Ϲ�ȸ�� //���� ��û ��
	@Override
	public List<ApCallCommand> selectCallList(String call_name) {	
		return mypageMapper.selectCallList(call_name);
	}
	//�ӽú�ȣ�� ������ �θ��� //�Ϲ�ȸ�� //���� �Ϸ�
	public List<ApCallCommand> selectCallListComplete(String call_name2){
		return mypageMapper.selectCallListComplete(call_name2);
	}

	//�ӽú�ȣ�� ������ �θ��� //�ӽú�ȣ��
	public List<ApCallCommand> selectCallbohoList(String bo_call_id){
		return mypageMapper.selectCallbohoList(bo_call_id);
	}

	//------------------------------------------------------------------

	//�ӽú�ȣ�� ���� �ñ�� //�Ϲ�ȸ��
	@Override
	public List<ApBoCommand> selectBoList(String bo_id) {
		return mypageMapper.selectBoList(bo_id);
	}
	//�ӽú�ȣ�� ���� �ñ�� //�ӽú�ȣ��
	@Override
	public List<ApBoCommand> selectBohoCallList(String id) {
		return mypageMapper.selectBohoCallList(id);
	}
	
	//------------------------------------------------------------------

	@Override
	public OrderCommand selectNowList(String dona_id) {
		// TODO Auto-generated method stub
		return mypageMapper.selectNowList(dona_id);
	}

	//��ȣ�� �������������� �Ŀ���������Ʈ ���
	@Override
	public List<OrderCommand> selectDonaSList(Map<String, Object> map) {
		return mypageMapper.selectDonaSList(map);
	}

	@Override
	public GoodsListCommand getMyGoods(Integer g_num) {		
		return mypageMapper.getMyGoods(g_num);
	}







}
