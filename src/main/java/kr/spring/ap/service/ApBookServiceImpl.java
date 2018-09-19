package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.ap.dao.ApBookMapper;
import kr.spring.ap.domain.ApBookCommand;

@Service("apBookService")
public class ApBookServiceImpl implements ApBookService {
	
	@Resource
	private ApBookMapper apBookMapper;

	@Override
	public List<ApBookCommand> checkBook(Map<String, Object> map) {
		return apBookMapper.checkBook(map);
	}

	@Override
	public int Bookchecked(ApBookCommand apbook) {
		return apBookMapper.Bookchecked(apbook);
	}

	@Override
	public void cancleBook(ApBookCommand apbook) {
		apBookMapper.cancleBook(apbook);
	}

	@Override
	public void insertBook(ApBookCommand apbook) {
		apBookMapper.insertBook(apbook);
	}
	
	
}
