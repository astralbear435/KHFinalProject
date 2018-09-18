package kr.spring.ap.service;

import java.util.List;
import java.util.Map;
import kr.spring.ap.domain.ApBoCallCommand;
import kr.spring.ap.domain.ApBookCommand;

public interface ApBoCallService {
	
	public List<ApBoCallCommand> boCallList(Map<String,Object> map);
	public int boCallCount(int call_num);

	public void insert(ApBoCallCommand apBoCallCommand);


}