package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import kr.spring.ap.domain.ApBookCommand;


public interface ApBookService {
	
	public List<ApBookCommand> checkBook(Map<String, Object> map);
	
	//ºÏ¸¶Å©¸¦ ´­·¶´ÂÁö Ã¼Å©
	public int Bookchecked(ApBookCommand apbook);
	
	//ºÏ¸¶Å© Ãë¼Ò
	public void cancleBook(ApBookCommand apbook);
	
	//ºÏ¸¶Å© ´©¸£±â
	public void insertBook(ApBookCommand apbook);
	
}
