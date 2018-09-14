package kr.spring.ap.service;

import java.util.List;
import java.util.Map;
import kr.spring.ap.domain.ApCommand;

public interface ApService {
	
	public List<ApCommand> selectApList(Map<String,Object> map);
	public int selectApRowCount(Map<String,Object> map);
	public void insert(ApCommand apCommand);
	public ApCommand selectApBoard(Integer ap_num);
	public void update(ApCommand apCommand);
	public void apApdelete(Integer ap_num);

}
