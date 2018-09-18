package kr.spring.shelter.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.member.domain.MemberCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ShelterService {
	public List<ShelterCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insert(ShelterCommand shelter);
	public ShelterCommand selectShelter(String id);
	public void update(ShelterCommand shelter);
	public void delete(String id);
	
	//비밀번호 찾기(임시 비밀번호 전송)
	public void updatePwShelter(String m_email) throws Exception;
	
	//보호소 카운터
	public int selectShelterCount();
	//보호소 금일 카운터
	public int selectTodayShelterCount();
	
	//보호소 리스트
	public List<ShelterCommand> selectShelterList(); 
}
