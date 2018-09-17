package kr.spring.shelter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.MemberCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ShelterMapper {
	// ����Ʈ
	public List<ShelterCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	// ȸ�� ����
	@Insert("INSERT INTO member VALUES (#{s_id},3,#{s_email})")
	public void insert(ShelterCommand shelter);
	@Insert("INSERT INTO shelter_detail (s_id,s_name,s_passwd,s_license_num,s_phone,s_email,s_zipcode,s_address1,s_address2,s_content,s_uploadfile,s_filename) VALUES(#{s_id},#{s_name},#{s_passwd},#{s_license_num},#{s_phone},#{s_email},#{s_zipcode},#{s_address1},#{s_address2},#{s_content},#{s_uploadfile},#{s_filename})")
	public void insertShelterDetail(ShelterCommand shelter);
	
	@Select("SELECT * FROM member m LEFT OUTER JOIN shelter_detail s ON m.m_id = s.s_id WHERE m.m_id=#{id}")
	public ShelterCommand selectShelter(String id);
	
	// ȸ�� ���� ����
	@Update("UPDATE shelter_detail SET s_name=#{s_name}, s_passwd=#{s_passwd}, s_phone=#{s_phone}, s_email=#{s_email}, s_zipcode=#{s_zipcode}, s_address1=#{s_address1}, s_address2=#{s_address2}, s_content=#{s_content}, s_uploadfile=#{s_uploadfile}, s_filename=#{s_filename} WHERE s_id=#{s_id}")
	public void update(ShelterCommand shelter);
	
	// ȸ�� Ż��
	@Update("UPDATE member SET auth=0,m_email='@.' WHERE m_id=#{s_id}")
	public void delete(String id);
	@Delete("DELETE FROM shelter_detail WHERE s_id=#{s_id}")
	public void deleteShelterDetail(String id);
	
	//��й�ȣ ã��
	@Update("UPDATE shelter_detail SET s_passwd=#{m_passwd} WHERE s_email=#{m_email}")
	public void updatePwShelter(@Param("m_email") String m_email, @Param("m_passwd") String m_passwd);
	
	
}
