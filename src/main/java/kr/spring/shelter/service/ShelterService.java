package kr.spring.shelter.service;

import java.util.List;
import java.util.Map;

import kr.spring.shelter.domain.ShelterCommand;

public interface ShelterService {
	public List<ShelterCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insert(ShelterCommand shelter);
	public ShelterCommand selectShelter(String id);
	public void update(ShelterCommand shelter);
	public void delete(String id);
}
