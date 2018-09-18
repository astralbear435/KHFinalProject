package kr.spring.ap.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApBoCallCommand;
import kr.spring.ap.domain.ApBookCommand;


public interface ApBoCallMapper {
	
	@Select("SELECT * FROM ap_bo_call")
	public List<ApBoCallCommand> boCallList(Map<String,Object> map);
	@Select("SELECT count(*) FROM ap_bo_call where call_num=#{call_num}")
	public int boCallCount(int call_num);

	@Insert("INSERT INTO ap_bo_call(bo_call_num, bo_call_id, bo_call_date_start, bo_call_date_end, bo_call_re, bo_call_re_date, bo_call_re_hour, bo_call_re_min, call_name, call_num) VALUES (ap_bo_call_seq.nextval, #{bo_call_id}, #{bo_call_date_start}, #{bo_call_date_end}, #{bo_call_re}, #{bo_call_re_date}, #{bo_call_re_hour}, #{bo_call_re_min}, #{call_name}, #{call_num})")
	public void insert(ApBoCallCommand apBoCallCommand);

}
