package kr.spring.member.service;

import kr.spring.member.domain.MemberCommand;

public interface MemberService {
	public void insert(MemberCommand member);
	public MemberCommand selectMember(String id);
	public void update(MemberCommand member);
	public void delete(String id);
}
