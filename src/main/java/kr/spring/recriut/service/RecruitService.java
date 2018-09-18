package kr.spring.recriut.service;

import java.util.List;
import java.util.Map;

import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface RecruitService {
<<<<<<< HEAD

	public List<RecruitCommand> selectList(Map<String,Object> map);

	public List<ShelterCommand> selectBoNameList(Map<String,Object> map);

	public void insert(RecruitCommand recruit);
	//
	public int selectRowCount(Map<String,Object> map);

	public RecruitCommand selectBoard(Integer r_num);
=======
	public List<RecruitCommand> selectList(Map<String,Object> map);
	public List<ShelterCommand> selectBoNameList(Map<String,Object> map);
	
	public void insert(RecruitCommand recruit);
	//
	public int selectRowCount(Map<String,Object> map);
	public RecruitCommand selectBoard(Integer r_num);

>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
	
	public ShelterCommand selectBoName(Integer r_num);
	
	public void updateHit(Integer r_num);
<<<<<<< HEAD
	
	public void update(RecruitCommand recruit);
	
=======
	public void update(RecruitCommand recruit);
>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
	public void delete(Integer r_num);
	
	public void deleteVolunteer(Integer r_num);
	
	public List<RecruitCommand> selectRecruitList(Map<String,Object> map);
	
<<<<<<< HEAD
	
=======
>>>>>>> d3f122582d0e687d12cb3d34dbd4a14f2954e329
	public int recruitCount(String r_id);
}
