package kr.spring.ap.dao;

import org.apache.ibatis.annotations.Insert;

import kr.spring.ap.domain.ApBoCommand;

public interface ApBoMapper {

	@Insert("INSERT INTO ap_bo_call(bo_num, bo_id, id, bo_date_start, bo_start_hour, bo_start_min, bo_date_end, bo_end_hour, bo_end_min, ap_num) VALUES (ap_bo_seq.nextval, #{bo_id}, #{id}, #{bo_date_start}, #{bo_start_hour}, #{bo_start_min}, #{bo_date_end}, #{bo_end_hour}, #{bo_end_min}, #{ap_num})")
	public void insert(ApBoCommand apBoCommand);

}
