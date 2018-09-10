package kr.spring.shelter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.dao.ShelterMapper;

@Service("shelterService")
public class ShelterServiceImpl implements ShelterService{	
	@Resource
	private ShelterMapper shelterMapper;
	
	@Override
	public List<ShelterCommand> selectList(Map<String, Object> map) {
		return shelterMapper.selectList(map);
	}


	@Override
	public int selectRowCount(Map<String, Object> map) {
		return shelterMapper.selectRowCount(map);
	}
	
	@Override
	public void insert(ShelterCommand shelter) {
		shelterMapper.insert(shelter);
		shelterMapper.insertShelterDetail(shelter);
	}

	
	@Override
	public ShelterCommand selectShelter(String id) {
		return shelterMapper.selectShelter(id);
	}

	@Override
	public void update(ShelterCommand shelter) {
		shelterMapper.update(shelter);
	}

	@Override
	public void delete(String id) {
		shelterMapper.delete(id);
		shelterMapper.deleteShelterDetail(id);
	}


}
