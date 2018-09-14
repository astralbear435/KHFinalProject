package kr.spring.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.MemberCommand;


public interface MemberMapper {
	// ȸ�� ��ü ����Ʈ(����)
	@Select("SELECT m_id FROM member")
	public List<MemberCommand> wholeList();
	@Select("SELECT count(*) FROM member")
	public int wholeCount();
	
	//���
	@Insert("INSERT INTO member (m_id,m_email) VALUES (#{m_id},#{m_email})")
	public void insert(MemberCommand member);
	@Insert("INSERT INTO member_detail (m_num,m_id,m_name,m_nickname,m_passwd,m_phone,m_zipcode,m_address,m_address_detail,m_email,uploadProfile,profileFilename,m_birth,m_reg_date) VALUES (mem_seq.nextval,#{m_id},#{m_name},#{m_nickname},#{m_passwd},#{m_phone},#{m_zipcode},#{m_address},#{m_address_detail},#{m_email},#{uploadProfile},#{profileFilename},#{m_birth},SYSDATE)")
	public void insertDetail(MemberCommand member);
	@Insert("INSERT INTO member_email (m_email,verify_key,m_id) VALUES (#{m_email},#{verify_key},#{m_id})")
	public void insertEmail(@Param("m_email") String m_email, @Param("verify_key") String verify_key, @Param("m_id") String m_id);
	
	//�������� ������ ���� ����Ű �˻�
	@Select("SELECT verify_key FROM member_email WHERE m_email=#{m_email}")
	public String findVerify_key(String m_email);
	
	//�������� ����
	@Update("UPDATE member_email SET verified='Y' WHERE m_email=#{m_email}")
	public void verify(String m_email);
	@Update("UPDATE member SET auth=2 WHERE m_email=#{m_email}")
	public void changeAuth(String m_email);
	
	//����Ű ���� 
	@Update("UPDATE member_email SET verify_key=#{verify_key} WHERE m_email=#{m_email}")
	public void updateAuthKey(@Param("m_email") String m_email, @Param("verify_key") String verify_key);
	
	//������
	@Select("SELECT * FROM member m LEFT OUTER JOIN member_detail d ON m.m_id=d.m_id WHERE m.m_id=#{m_id}")
	public MemberCommand selectMember(String m_id);
	
	//�г��� ���� üũ
	@Select("SELECT m_nickname FROM member_detail WHERE m_nickname=#{m_nickname}")
	public MemberCommand checkMember_n(String m_nickname);
	
	// �̸��� ���� üũ(���� ����)
	@Select("SELECT * FROM member WHERE m_email=#{m_email}")
	public MemberCommand checkMember_e(String m_email);
	
	//����
	@Update("UPDATE member_detail SET m_name=#{m_name},m_nickname=#{m_nickname},m_passwd=#{m_passwd},m_phone=#{m_phone},m_zipcode=#{m_zipcode},m_address=#{m_address},m_address_detail=#{m_address_detail},uploadProfile=#{uploadProfile},profileFilename=#{profileFilename},m_birth=#{m_birth} WHERE m_id=#{m_id}")
	public void updateDetail(MemberCommand member);
	
	//Ż��
	@Update("UPDATE member SET auth=0,m_email='claer' WHERE m_id=#{m_id}")
	public void delete(String m_id);
	@Delete("DELETE FROM member_detail WHERE m_id=#{m_id}")
	public void deleteDetail(String m_id);
	@Delete("DELETE FROM member_email WHERE m_id=#{m_id}")
	public void deleteEmail(String m_id);
	
	// ���̵� ã��
	@Select("SELECT m_id FROM member WHERE m_email=#{m_email}")
	public MemberCommand findId(String m_email);
	
	//��й�ȣ ã��
	@Update("UPDATE member_detail SET m_passwd=#{m_passwd} WHERE m_email=#{m_email}")
	public void updatePw(@Param("m_email") String m_email, @Param("m_passwd") String m_passwd);
	
	/*//���� �α���
	@Update("UPDATE google_login SET g_name=#{g_name},g_id=#{g_id} WHERE m_email=#{m_email}")
	public void googleLogin(MemberCommand member);*/
	
}
