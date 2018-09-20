package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.dao.ApMapper;
import kr.spring.ap.domain.ApCommand;

@Service("apService")
public class ApServiceImpl implements ApService{
	
	@Resource
	private ApMapper apMapper;

	@Override
	public List<ApCommand> selectApList(Map<String, Object> map) {
		return apMapper.selectApList(map);
	}

	@Override
	public int selectApRowCount(Map<String, Object> map) {
		return apMapper.selectApRowCount(map);
	}

	@Override
	public void insert(ApCommand apCommand) {
		apMapper.insert(apCommand);
	}

	@Override
	public ApCommand selectApBoard(Integer ap_num) {
		return apMapper.selectApBoard(ap_num);
	}

	@Override
	public void update(ApCommand apCommand) {
		apMapper.update(apCommand);
		
	}

	@Override
	public void apdelete(Integer ap_num) {
		apMapper.apdelete(ap_num);
	}

}
