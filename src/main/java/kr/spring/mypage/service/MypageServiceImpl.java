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
	//임시보호자 집으로 부르기 //일반회원 //예약 신청 중
	@Override
	public List<ApCallCommand> selectCallList(String call_name) {	
		return mypageMapper.selectCallList(call_name);
	}
	//임시보호자 집으로 부르기 //일반회원 //예약 완료
	public List<ApCallCommand> selectCallListComplete(String call_name2){
		return mypageMapper.selectCallListComplete(call_name2);
	}

	//임시보호자 집으로 부르기 //임시보호자
	public List<ApCallCommand> selectCallbohoList(String bo_call_id){
		return mypageMapper.selectCallbohoList(bo_call_id);
	}

	//------------------------------------------------------------------

	//임시보호자 집에 맡기기 //일반회원
	@Override
	public List<ApBoCommand> selectBoList(String bo_id) {
		return mypageMapper.selectBoList(bo_id);
	}
	//임시보호자 집에 맡기기 //임시보호자
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

	//보호소 마이페이지에서 후원내역리스트 출력
	@Override
	public List<OrderCommand> selectDonaSList(Map<String, Object> map) {
		return mypageMapper.selectDonaSList(map);
	}

	@Override
	public GoodsListCommand getMyGoods(Integer g_num) {		
		return mypageMapper.getMyGoods(g_num);
	}







}
