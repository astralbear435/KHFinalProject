package kr.spring.volunteer.service;

import java.util.List;
import java.util.Map;

import kr.spring.recruit.domain.RecruitCommand;


public interface VolunteerService {
	//遊됱궗�솢�룞 �떊泥� 湲� �옉�꽦
	public void insert(Map<String,Object> map);
	//遊됱궗�솢�룞 �떊泥� 湲� �솗�씤 
	//留덉씠�럹�씠吏� 罹섎┛�뜑�뿉�꽌 �긽�꽭 �솗�씤 
	//�븘�씠�뵒�� �씪移섑븯硫� �쟾遺� 蹂댁뿬二쇨린
	public List<RecruitCommand> selectList(Map<String,Object> map);	
	//�떊泥� 湲� 蹂닿린
	public RecruitCommand selectBoard(Integer v_num);
	//�떊泥� 湲� �닔�젙
	public void update(Map<String,Object> map);
	//�떊泥� 湲� �궘�젣
	public void delete(Integer v_num);
	
}
