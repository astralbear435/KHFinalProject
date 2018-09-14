package kr.spring.ap.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApCallCommand;

public interface ApCallMapper {
	
	public List<ApCallCommand> selectCallList(Map<String,Object> map);
	public int selectCallRowCount(Map<String,Object> map);
	@Insert("INSERT INTO ap_call_home(call_num,call_name,call_phone,call_start,call_end,call_wei,call_re, call_re_date, call_re_hour, call_re_min, call_intro) VALUES (ap_call_seq.nextval,#{call_name}, #{call_phone}, #{call_start}, #{call_end}, #{call_wei}, #{call_re}, #{call_re_date}, #{call_re_hour}, #{call_re_min}, #{call_intro})")
	public void insert(ApCallCommand apcall);
	@Select("SELECT * FROM ap_call_home WHERE call_num=#{call_num}")
	public ApCallCommand selectCallBoard(Integer call_num);
	public void update(ApCallCommand apcall);
	@Delete("DELETE FROM ap_call_home WHERE call_num=#{call_num}")
	public void apCalldelete(Integer call_num);

}
