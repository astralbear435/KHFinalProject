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
	//(소은):가장 최근 기부리스트 필요해서 사용합니다.
	public OrderCommand selectNowList(String dona_id);

	
	//임시보호자 집에 맡기기 //일반회원 
	public List<ApBoCommand>selectBoList(String bo_id);
	//임시보호자 집에 맡기기 //임시보호자
	public List<ApBoCommand>selectBohoCallList(String id);
	
	//임시보호자 집으로 부르기 //일반회원 //예약 완료
	public List<ApCallCommand> selectCallListComplete(String call_name2);
	//임시보호자 집으로 부르기 //일반회원 //예약 신청 중
	public List<ApCallCommand> selectCallList(String call_name);
	//임시보호자 집으로 부르기 //임시보호자
	public List<ApCallCommand> selectCallbohoList(String bo_call_id);	
	
	public List<ApCommand> selectApList();
	//보호소 마이페이지에서 후원내역을 가져옴
	public List<OrderCommand> selectDonaSList(Map<String, Object> map);
	//마이페이지에서 가격, 상품명을 가져옴
	public GoodsListCommand getMyGoods(Integer g_num);
	

	  
	
	
}
