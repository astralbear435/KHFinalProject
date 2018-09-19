package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.dao.ApBoCallMapper;
import kr.spring.ap.domain.ApBoCallCommand;

@Service("apBoCallService")
public class ApBoCallServiceImpl implements ApBoCallService{
	
	@Resource
	private ApBoCallMapper apBoCallMapper;

	@Override
	public void insert(ApBoCallCommand apBoCallCommand) {
		apBoCallMapper.insert(apBoCallCommand);
	}

	@Override
	public List<ApBoCallCommand> boCallList(Map<String, Object> map) {
		return apBoCallMapper.boCallList(map);
	}

	@Override
	public int boCallCount(int call_num) {
		return apBoCallMapper.boCallCount(call_num);
	}
}
