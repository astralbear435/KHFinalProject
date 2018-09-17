package kr.spring.recruit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.recruit.domain.RecruitCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface BoardMapper {
	//占쏙옙占쏙옙활占쏙옙 占쏙옙占쏙옙 占쌉쏙옙占쏙옙 占쏙옙瞿占쏙옙占�
	
	public List<RecruitCommand> selectList(Map<String,Object> map);
	
	
	public int selectRowCount(Map<String,Object> map);
	
<<<<<<< HEAD
	//占쏙옙占쏙옙 占쏙옙 占쌜쇽옙
=======

	public List<ShelterCommand> selectBoNameList(Map<String, Object> map);
	
	//���� �� �ۼ�
>>>>>>> ab8c4c0f96ccef362ae667ea47f15553b7b31774
	@Insert("INSERT INTO recruit (r_num,r_id,r_image,r_title,r_start_date,r_end_date,r_people,r_people_count,r_filename,r_content,r_status) "
	+ "VALUES (recruit_seq.nextval,#{r_id},#{r_image},#{r_title},#{r_start_date},#{r_end_date},#{r_people},#{r_people_count},#{r_filename},#{r_content},#{r_status})")
	public void insert(RecruitCommand recruit);
	
<<<<<<< HEAD
	//占쏙옙占쏙옙 占쏙옙 占쏙옙 占쏙옙占쏙옙
	@Select("SELECT * FROM (SELECT * FROM recruit, shelter_detail WHERE r_id = s_id) WHERE r_num=#{r_num}")
=======
	//���� �� �� ����
	@Select("SELECT * FROM recruit WHERE r_num=#{r_num}")
>>>>>>> ab8c4c0f96ccef362ae667ea47f15553b7b31774
	public RecruitCommand selectBoard(Integer r_num);
	
	@Select("SELECT s_name FROM recruit, shelter_detail WHERE r_id=s_id AND r_num=#{r_num}")
	public ShelterCommand selectBoName(Integer r_num);

	@Select("SELECT v_id, v_date, v_status, r_content,r_start_date,r_end_date FROM (SELECT * FROM volunteer v, recruit r WHERE v.r_num=r.r_num) vo WHERE r_id= #{r_id}")
	public List<RecruitCommand> selectRecruitList(Map<String,Object> map);	
	
	public void updateHit(Integer r_num);
	
	//占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
	@Update("UPDATE recruit SET r_image=#{r_image},r_title=#{r_title},r_start_date=#{r_start_date},r_end_date=#{r_end_date},r_people=#{r_people},r_filename=#{r_filename},r_content=#{r_content},r_status=#{r_status} WHERE r_num = #{r_num}")
	public void update(RecruitCommand recruit);
	
	//占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙	
	@Delete("DELETE recruit WHERE r_num=#{r_num}")
	public void delete(Integer r_num);
	
	@Delete("DELETE volunteer WHERE r_num=#{r_num}")
	public void deleteVolunteer(Integer r_num);
	

	// 봉사활동 글 수 세기(세영 추가)
	@Select("SELECT count(*) FROM recruit WHERE r_id=#{r_id}")
	public int recruitCount(String r_id);
}

