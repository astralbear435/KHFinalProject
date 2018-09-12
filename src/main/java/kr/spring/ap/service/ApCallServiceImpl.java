package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.dao.ApCallMapper;
import kr.spring.ap.domain.ApCallCommand;

@Service("apCallService")
public class ApCallServiceImpl implements ApCallService{
	
	@Resource
	private ApCallMapper apCallMapper;

	@Override
	public void insert(ApCallCommand apcall) {
		apCallMapper.insert(apcall);
	}

	@Override
	public List<ApCallCommand> selectCallList(Map<String, Object> map) {
		return apCallMapper.selectCallList(map);
	}

	@Override
	public int selectCallRowCount(Map<String, Object> map) {
		return apCallMapper.selectCallRowCount(map);
	}

	@Override
	public ApCallCommand selectCallBoard(Integer call_num) {
		return apCallMapper.selectCallBoard(call_num);
	}

	@Override
	public void update(ApCallCommand apcall) {
		apCallMapper.update(apcall);
	}

	@Override
	public void apCalldelete(Integer call_num) {
		apCallMapper.apCalldelete(call_num);
	}

}
