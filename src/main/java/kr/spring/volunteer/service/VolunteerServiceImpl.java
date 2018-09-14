package kr.spring.volunteer.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.volunteer.dao.VolunteerMapper;

@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService{
	
	@Resource
	private VolunteerMapper volunteerMapper;

	@Override
	public void insert(RecruitCommand volunteer) {
		volunteerMapper.insert(volunteer);
	}
	
	@Override
	public List<RecruitCommand> selectList(Map<String,Object> map){
		return volunteerMapper.selectList(map);		
	}
	
	@Override
	public RecruitCommand selectBoard(Integer v_num) {
		return volunteerMapper.selectBoard(v_num);
	}

	@Override
	public void update(RecruitCommand volunteer) {
		volunteerMapper.update(volunteer);
	}

	@Override
	public void delete(Integer v_num) {
		volunteerMapper.delete(v_num);
	}



}
