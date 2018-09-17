package kr.spring.recriut.service;

import java.util.List;
import java.util.Map;

import kr.spring.recruit.domain.RecruitCommand;

public interface RecruitService {
	//占쏙옙占쏙옙활占쏙옙 占쏙옙占쏙옙 占쌉쏙옙占쏙옙 占쏙옙瞿占쏙옙占�
	public List<RecruitCommand> selectList(
			Map<String,Object> map);
	//占쏙옙占쏙옙 占쏙옙 占쌜쇽옙
	public void insert(RecruitCommand recruit);
	//
	public int selectRowCount(Map<String,Object> map);
	//占쏙옙占쏙옙 占쏙옙 占쏙옙 占쏙옙占쏙옙
	public RecruitCommand selectBoard(Integer r_num);
	//占쏙옙占쏙옙 占쏙옙 占쏙옙회占쏙옙 占쏙옙占쏙옙
	public void updateHit(Integer r_num);
	//占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
	public void update(RecruitCommand recruit);
	//占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
	public void delete(Integer r_num);
	
	public void deleteVolunteer(Integer r_num);
	
	public List<RecruitCommand> selectRecruitList(Map<String,Object> map);
	
	// 봉사활동 글 수 세기(세영 추가)
	public int recruitCount(String r_id);
}
