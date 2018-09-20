package kr.spring.recriut.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.recruit.dao.BoardMapper;
import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;

@Service("recruitService")
public class RecruitServiceImpl implements RecruitService{

	@Resource
	private BoardMapper boardMapper;

	@Override
	public List<RecruitCommand> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public void insert(RecruitCommand recruit) {
		boardMapper.insert(recruit);
	}
	
	@Override
	public int selectRowCount(Map<String, Object> map) {		
		return boardMapper.selectRowCount(map);
	}

	@Override
	public RecruitCommand selectBoard(Integer r_num) {
		return boardMapper.selectBoard(r_num);
	}

	@Override
	public void updateHit(Integer r_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RecruitCommand recruit) {
		boardMapper.update(recruit);		
	}

	@Override
	public void delete(Integer r_num) {
		boardMapper.delete(r_num);
		
	}

	@Override
	public void deleteVolunteer(Integer r_num) {
		boardMapper.deleteVolunteer(r_num);
	}

	@Override
	public List<RecruitCommand> selectRecruitList(Map<String, Object> map) {
		
		return boardMapper.selectRecruitList(map);
	}
	public ShelterCommand selectBoName(Integer r_num) {
		
		return boardMapper.selectBoName(r_num);
	}

	@Override
	public List<ShelterCommand> selectBoNameList(Map<String, Object> map) {
		return boardMapper.selectBoNameList(map);
	}
	@Override
	public int recruitCount(String r_id) {
		// TODO Auto-generated method stub
		return boardMapper.recruitCount(r_id);
	}

}
