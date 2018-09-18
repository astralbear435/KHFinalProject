package kr.spring.member.service;

import java.util.List;

import kr.spring.member.domain.MemberCommand;


public interface MemberService {
	// 회원 전체 리스트
	public List<MemberCommand> wholeList();
	public int wholeCount();
	
	// 권한값 구하기
	public int selectMemberAuth(String m_id);
	
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
	
	public int selectMemberCount();
	public int selectTodayMemberCount();
	
	//토탈맴버
	public List<MemberCommand> selectTotalMember();
	
	//어스변경
	public void updateAuth(int auth,String id);
	//구글 로그인
	//public MemberCommand googleLogin(MemberCommand member) throws Exception;
	
}