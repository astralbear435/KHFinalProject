package kr.spring.shelter.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.domain.MemberCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ShelterService {
	public List<ShelterCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insert(ShelterCommand shelter);
	public ShelterCommand selectShelter(String id);
	public void update(ShelterCommand shelter);
	public void delete(String id);
	//보호소 이름 불러오기
	public String selectAsName(String id);
	//비밀번호 찾기(임시 비밀번호 전송)
	public void updatePwShelter(String m_email) throws Exception;
}
