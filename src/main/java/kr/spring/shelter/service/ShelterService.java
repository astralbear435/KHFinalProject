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
	
	//��й�ȣ ã��(�ӽ� ��й�ȣ ����)
	public void updatePwShelter(String m_email) throws Exception;
	
	//��ȣ�� ī����
	public int selectShelterCount();
	//��ȣ�� ���� ī����
	public int selectTodayShelterCount();
	
	//��ȣ�� ����Ʈ
	public List<ShelterCommand> selectShelterList(); 
}
