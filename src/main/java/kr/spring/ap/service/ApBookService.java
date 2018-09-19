package kr.spring.ap.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import kr.spring.ap.domain.ApBookCommand;


public interface ApBookService {
	
	public List<ApBookCommand> checkBook(Map<String, Object> map);
	
	//�ϸ�ũ�� �������� üũ
	public int Bookchecked(ApBookCommand apbook);
	
	//�ϸ�ũ ���
	public void cancleBook(ApBookCommand apbook);
	
	//�ϸ�ũ ������
	public void insertBook(ApBookCommand apbook);
	
}
