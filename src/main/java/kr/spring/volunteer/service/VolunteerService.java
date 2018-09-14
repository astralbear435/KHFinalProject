package kr.spring.volunteer.service;

import java.util.List;
import java.util.Map;

import kr.spring.recruit.domain.RecruitCommand;


public interface VolunteerService {
	//봉사활동 신청 글 작성
	public void insert(RecruitCommand volunteer);
	//봉사활동 신청 글 확인 
	//마이페이지 캘린더에서 상세 확인 
	//아이디와 일치하면 전부 보여주기
	public List<RecruitCommand> selectList(Map<String,Object> map);	
	//신청 글 보기
	public RecruitCommand selectBoard(Integer v_num);
	//신청 글 수정
	public void update(RecruitCommand volunteer);
	//신청 글 삭제
	public void delete(Integer v_num);
	
}
