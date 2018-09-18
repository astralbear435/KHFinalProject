package kr.spring.visitor.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.visitor.domain.VisitorCommand;

public interface VisitorMapper {

	public void insertVisitor(VisitorCommand vo);
	@Select("SELECT * FROM TB_VISITOR WHERE VISIT_IP=#{visit_ip} AND VISIT_TIME= (SELECT MAX(VISIT_TIME) FROM TB_VISITOR)")
	public VisitorCommand selectVisittorId(VisitorCommand vo);
	@Update("UPDATE TB_VISITOR SET VISIT_LOGIN_ID=#{visit_login_id} WHERE VISIT_ID=#{visit_id}")
	public void updateVisitor(VisitorCommand vo);
	//오늘 순 방문자 수
	@Select("SELECT count(DISTINCT(VISIT_IP)) FROM TB_VISITOR WHERE TO_DATE(VISIT_TIME,'yyyy-MM-dd') = TO_DATE(sysdate,'yyyy-MM-dd')")
	public int selectCountToday();
	//전체 방문자 수
	@Select("SELECT count(*) FROM TB_VISITOR") 
	public int selectVisitorCount();
}
