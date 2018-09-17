package kr.spring.mypage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApBoCommand;
import kr.spring.ap.domain.ApCallCommand;
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
	
	@Select("SELECT * FROM donation WHERE dona_id=#{dona_id}")
	public List<OrderCommand> selectDanaList(String dona_id);
	
	//가장 최근 나의 기부리스트 갖고오기
	@Select("SELECT * FROM DONATION WHERE dona_id=#{dona_id} AND DONA_DATE=(select max(DONA_DATE)from DONATION)")
	public OrderCommand selectNowList(String dona_id);
	
	@Select("SELECT COUNT(*) FROM donation WHERE dona_id=#{dona_id}")
	public int selectCountdonation(String dona_id);
	
	@Select("SELECT * FROM ap_call_home WHERE call_name=#{call_name}")
	public List<ApCallCommand> selectCallList(String call_name);
	
	@Select("SELECT * FROM ap_bo WHERE bo_id=#{bo_id}")
	public List<ApBoCommand>selectBoCallList(String bo_id);
	
	@Select("SELECT * FROM ap_bo WHERE id=#{boho_id}")
	public List<ApBoCommand>selectBohoCallList(String boho_id);
	
	
	
	

}
