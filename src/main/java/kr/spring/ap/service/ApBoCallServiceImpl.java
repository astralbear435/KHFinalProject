package kr.spring.ap.service;

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

}
