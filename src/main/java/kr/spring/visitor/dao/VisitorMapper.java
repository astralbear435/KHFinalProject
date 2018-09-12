package kr.spring.visitor.dao;

import kr.spring.visitor.domain.VisitorCommand;

public interface VisitorMapper {
	public void insertVisitor(VisitorCommand vo);
}
