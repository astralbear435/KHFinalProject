package kr.spring.recriut.service;

import java.util.List;
import java.util.Map;

import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface RecruitService {
<<<<<<< HEAD
	//占쏙옙占쏙옙활占쏙옙 占쏙옙占쏙옙 占쌉쏙옙占쏙옙 占쏙옙瞿占쏙옙占�
	public List<RecruitCommand> selectList(
			Map<String,Object> map);
	//占쏙옙占쏙옙 占쏙옙 占쌜쇽옙
=======
	//����Ȱ�� ���� �Խ��� ��Ϻ���
	public List<RecruitCommand> selectList(Map<String,Object> map);
	//���� �� �ۼ�
	public List<ShelterCommand> selectBoNameList(Map<String,Object> map);
	
>>>>>>> ab8c4c0f96ccef362ae667ea47f15553b7b31774
	public void insert(RecruitCommand recruit);
	//
	public int selectRowCount(Map<String,Object> map);
	//占쏙옙占쏙옙 占쏙옙 占쏙옙 占쏙옙占쏙옙
	public RecruitCommand selectBoard(Integer r_num);
<<<<<<< HEAD
	//占쏙옙占쏙옙 占쏙옙 占쏙옙회占쏙옙 占쏙옙占쏙옙
=======
	
	public ShelterCommand selectBoName(Integer r_num);
	//���� �� ��ȸ�� ����
>>>>>>> ab8c4c0f96ccef362ae667ea47f15553b7b31774
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
