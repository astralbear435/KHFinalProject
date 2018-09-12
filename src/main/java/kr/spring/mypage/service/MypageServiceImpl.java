package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	
}
