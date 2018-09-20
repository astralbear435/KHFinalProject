package kr.spring.admin.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.notice.domin.NoticeCommend;
 
public interface NoticeMapper {
	@Select("SELECT * FROM admin_notice order by n_idx desc")
	public List<NoticeCommend> selectNoticeList();
	@Select("SELECT * FROM admin_notice order by n_idx desc")
	public List<NoticeCommend> selectNoticeList2(Map<String,Object> map);
	@Select("SELECT * FROM admin_notice WHERE n_idx=#{n_idx}") 
	public NoticeCommend selectNotice(Integer n_idx);
	@Select("SELECT COUNT(*) FROM admin_notice")
	public Integer selectCountList();
	@Insert("INSERT INTO admin_notice (n_idx,n_subject,n_content,n_id,n_reg_date) VALUES(admin_notice_n_idx_SEQ.nextval,#{n_subject},#{n_content},#{n_id},SYSDATE)")
	public void insertNotice(NoticeCommend nc);
	@Update("UPDATE admin_notice SET n_subject=#{n_subject},n_content=#{n_content},n_last_modified=SYSDATE WHERE n_idx=#{n_idx}")
	public void updateNotice(NoticeCommend nc);
	@Update("UPDATE admin_notice SET n_hit= n_hit+1 WHERE n_idx=#{n_idx}") 
	public void updateHits(Integer n_idx);
	@Update("DELETE FROM admin_notice WHERE n_idx = #{n_idx}")
	public void deleteNotice(Integer n_idx);
	
}
