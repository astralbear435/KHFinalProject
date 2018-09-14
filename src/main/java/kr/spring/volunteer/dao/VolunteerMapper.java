package kr.spring.volunteer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.recruit.domain.RecruitCommand;



public interface VolunteerMapper {
	//봉사활동 신청 글 작성
	@Insert("INSERT INTO volunteer (v_num,v_id,v_date,r_num,v_ip,v_status) VALUES (volunteer_seq.nextval,#{v_id},#{v_date},#{r_num},null,#{v_status})")
	public void insert(RecruitCommand volunteer);

	@Select("SELECT * FROM volunteer WHERE v_id=#{v_id}")
	public List<RecruitCommand> selectList(Map<String,Object> map);

	@Select("SELECT v_num, v_id, v_date, v_ip, v_status, r_id, r_title, r_content, r_status, r_start_date, r_end_date FROM (SELECT * FROM volunteer v, recruit r WHERE v.r_num=r.r_num) vo WHERE vo.v_num = #{v_num}")
	public RecruitCommand selectBoard(Integer v_num);

	@Update("UPDATE volunteer SET v_date=#{v_date}, v_status=#{v_status} WHERE v_num=#{v_num}")
	public void update(RecruitCommand volunteer);

	@Delete("DELETE FROM volunteer WHERE v_num=#{v_num}")
	public void delete(Integer v_num);







}
