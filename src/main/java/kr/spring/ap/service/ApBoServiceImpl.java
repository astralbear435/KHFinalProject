package kr.spring.ap.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.dao.ApBoMapper;
import kr.spring.ap.domain.ApBoCommand;


@Service("apBoService")
public class ApBoServiceImpl implements ApBoService{
	
	@Resource
	private ApBoMapper apBoMapper;

	@Override
	public void insert(ApBoCommand apBoCommand) {
		apBoMapper.insert(apBoCommand);
	}

}
