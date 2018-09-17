package kr.spring.ap.dao;

import org.apache.ibatis.annotations.Insert;

import kr.spring.ap.domain.ApBoCallCommand;


public interface ApBoCallMapper {

	@Insert("INSERT INTO ap_bo_call(bo_call_num, bo_call_id, bo_call_date_start, bo_call_date_end, bo_call_re, bo_call_re_date, bo_call_re_hour, bo_call_re_min, call_name, call_num) VALUES (ap_bo_call_seq.nextval, #{bo_call_id}, #{bo_call_date_start}, #{bo_call_date_end}, #{bo_call_re}, #{bo_call_re_date}, #{bo_call_re_hour}, #{bo_call_re_min}, #{call_name}, #{call_num})")
	public void insert(ApBoCallCommand apBoCallCommand);

}
