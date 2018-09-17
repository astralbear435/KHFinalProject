package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.mypage.dao.MypageMapper;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {

	@Resource
	private MypageMapper mypageMapper;
	
	@Override
	public MemberCommand selectId(String id) {
		
		return mypageMapper.selectId(id);
	}

	/*@Override
	public List<RecruitCommand> selectList(String v_id) {
		
		return mypageMapper.selectList(v_id);
	}

	@Override
	public List<RecruitCommand> selectRecruitList(String r_id) {
		
		return mypageMapper.selectRecruitList(r_id);
	}*/

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

	@Override
	public List<ApCallCommand> selectCallList(String call_name) {
	
		return mypageMapper.selectCallList(call_name);
	}

	@Override
	public List<ApBoCommand> selectBoCallList(String bo_id) {
		return mypageMapper.selectBoCallList(bo_id);
	}

	@Override
	public List<ApBoCommand> selectBohoCallList(String boho_id) {
		return mypageMapper.selectBohoCallList(boho_id);
	}

	@Override
	public OrderCommand selectNowList(String dona_id) {
		// TODO Auto-generated method stub
		return mypageMapper.selectNowList(dona_id);
	}



	

	
}
