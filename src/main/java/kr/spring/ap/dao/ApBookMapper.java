package kr.spring.ap.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.ap.domain.ApBookCommand;

public interface ApBookMapper {
	
	public List<ApBookCommand> checkBook(Map<String, Object> map);
	
	//�ϸ�ũ�� �������� üũ
	@Select("SELECT count(*) FROM ap_book where ap_book_num=#{ap_book_num} AND ap_book_id=#{ap_book_id}")
	public int Bookchecked(ApBookCommand apbook);
	
	//�ϸ�ũ ���
	@Delete("DELETE FROM ap_book where ap_book_num=#{ap_book_num} AND ap_book_id=#{ap_book_id}")
	public void cancleBook(ApBookCommand apbook);
	
	//�ϸ�ũ ������
	@Insert("INSERT INTO ap_book(ap_book_num, ap_book_id) VALUES (#{ap_book_num}, #{ap_book_id})")
	public void insertBook(ApBookCommand apbook);
	
}
