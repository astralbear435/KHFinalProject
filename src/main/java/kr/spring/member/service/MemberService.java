package kr.spring.member.service;

import kr.spring.member.domain.MemberCommand;


public interface MemberService {
	
	//회원관리
	public void insert(MemberCommand member) throws Exception;
	
	public MemberCommand selectMember(String m_id);
	
	public MemberCommand checkMember_n(String m_nickname);
	public MemberCommand checkMember_e(String m_email);
	
	public void updateMember(MemberCommand member);
	
	public void delete(String m_id);
	
	//이메일 인증
	public void changeAuth(String m_email) throws Exception;
	public void verify(String m_email) throws Exception;
	
	//인증메일 재전송
	public void resendEmail(MemberCommand member) throws Exception;
	
	//아이디찾기
	public MemberCommand findId(String m_email);
	//비밀번호 찾기(임시 비밀번호 전송)
	public void updatePw(String m_email) throws Exception;
	
	
}