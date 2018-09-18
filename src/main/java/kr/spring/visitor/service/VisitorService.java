package kr.spring.visitor.service;

import kr.spring.visitor.domain.VisitorCommand;

public interface VisitorService {
	public void insertVisitor(VisitorCommand vo);
	public VisitorCommand selectVisittorId(VisitorCommand vo);
	public void updateVisitor(VisitorCommand vo);
	public int selectCountToday();
	public int selectVisitorCount();
}
