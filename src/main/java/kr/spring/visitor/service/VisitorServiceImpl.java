package kr.spring.visitor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.visitor.dao.VisitorMapper;
import kr.spring.visitor.domain.VisitorCommand;

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService{
	
	@Resource
	private VisitorMapper visitor;
	@Override
	public void insertVisitor(VisitorCommand vo) {
		visitor.insertVisitor(vo);
		
	}

}
