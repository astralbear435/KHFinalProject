package kr.spring.member.service;

import kr.spring.member.domain.MemberCommand;


public interface MemberService {
	
	//ȸ������
	public void insert(MemberCommand member) throws Exception;
	
	public MemberCommand selectMember(String m_id);
	
	public MemberCommand checkMember_n(String m_nickname);
	public MemberCommand checkMember_e(String m_email);
	
	public void updateMember(MemberCommand member);
	
	public void delete(String m_id);
	
	//�̸��� ����
	public void changeAuth(String m_email) throws Exception;
	public void verify(String m_email) throws Exception;
	
	//�������� ������
	public void resendEmail(MemberCommand member) throws Exception;
	
	//���̵�ã��
	public MemberCommand findId(String m_email);
	//��й�ȣ ã��(�ӽ� ��й�ȣ ����)
	public void updatePw(String m_email) throws Exception;
	
	
}