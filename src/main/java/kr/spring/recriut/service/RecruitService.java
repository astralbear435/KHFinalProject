package kr.spring.recriut.service;

import java.util.List;
import java.util.Map;

import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface RecruitService {
	//����Ȱ�� ���� �Խ��� ��Ϻ���
	public List<RecruitCommand> selectList(Map<String,Object> map);
	//���� �� �ۼ�
	public List<ShelterCommand> selectBoNameList(Map<String,Object> map);
	
	public void insert(RecruitCommand recruit);
	//
	public int selectRowCount(Map<String,Object> map);
	//���� �� �� ����
	public RecruitCommand selectBoard(Integer r_num);
	
	public ShelterCommand selectBoName(Integer r_num);
	//���� �� ��ȸ�� ����
	public void updateHit(Integer r_num);
	//���� �� ����
	public void update(RecruitCommand recruit);
	//���� �� ����
	public void delete(Integer r_num);
	
	public void deleteVolunteer(Integer r_num);
	
	public List<RecruitCommand> selectRecruitList(Map<String,Object> map);	
	
}
