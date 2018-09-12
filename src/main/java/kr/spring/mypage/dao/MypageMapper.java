package kr.spring.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

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
}
