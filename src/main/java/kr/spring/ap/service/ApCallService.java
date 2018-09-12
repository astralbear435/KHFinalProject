package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import kr.spring.ap.domain.ApCallCommand;

public interface ApCallService {
	
	public List<ApCallCommand> selectCallList(Map<String,Object> map);
	public int selectCallRowCount(Map<String,Object> map);
	public void insert(ApCallCommand apcall);
	public ApCallCommand selectCallBoard(Integer call_num);
	public void update(ApCallCommand apcall);
	public void apCalldelete(Integer call_num);

}
