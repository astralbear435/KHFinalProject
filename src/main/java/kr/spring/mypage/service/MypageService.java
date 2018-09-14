package kr.spring.mypage.service;

import java.util.List;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
import kr.spring.ap.domain.ApCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.recruit.domain.RecruitCommand;


public interface MypageService {
	public MemberCommand selectId(String id);
	public List<RecruitCommand> selectList(String v_id);	
	public List<RecruitCommand> selectRecruitList(String r_id);	
	
	public List<OrderCommand> selectDanaList(String dona_id);
	public int selectCountdonation(String dona_id);
	
	public List<ApBoCommand>selectBoCallList(String bo_id);
	public List<ApBoCommand>selectBohoCallList(String boho_id);
	
	public List<ApCommand> selectApList();
	public List<ApCallCommand> selectCallList(String call_name);

	
	

	  
	
	
}
