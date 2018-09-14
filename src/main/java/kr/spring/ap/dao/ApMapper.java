package kr.spring.ap.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApCommand;

public interface ApMapper {
	
	public List<ApCommand> selectApList(Map<String,Object> map);
	public int selectApRowCount(Map<String,Object> map);
	@Insert("INSERT INTO ap_form(ap_num, id, ap_job, ap_cer, ap_act, ap_pet, ap_ser, ap_home, ap_sel, ap_nopet, ap_service, ap_mon) VALUES(ap_seq.nextval, #{id}, #{ap_job}, #{ap_cer}, #{ap_act}, #{ap_pet}, #{ap_ser}, #{ap_home}, #{ap_sel}, #{ap_nopet}, #{ap_service}, #{ap_mon})")
	public void insert(ApCommand apCommand);
	@Select("SELECT * FROM ap_form WHERE ap_num=#{ap_num}")
	public ApCommand selectApBoard(Integer ap_num);
	public void update(ApCommand apCommand);
	@Delete("DELETE FROM ap_form WHERE ap_num=#{ap_num}")
	public void apdelete(Integer ap_num);

}
