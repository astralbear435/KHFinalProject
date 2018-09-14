package kr.spring.note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.note.domain.NoteCommand;

public interface NoteMapper {
	public List<NoteCommand> selectNoteList(Map<String,Object> map);
	public int selectNoteRowCount(Map<String,Object> map);
	
	// 안 읽은 쪽지 수 세기
	@Select("select count(*) from note where recipient=#{recipient} and read_status='open_not'")
	public int openNotCount(String recipient);
	
	@Insert("INSERT INTO note (note_num,sender,recipient,note_content) VALUES (note_seq.nextval,#{sender},#{recipient},#{note_content})")
	public void insert(NoteCommand note);
	
	@Select("SELECT * FROM note WHERE note_num=#{note_num}")
	public NoteCommand selectNote(int note_num);
	
	// 쪽지 읽음 상태 변경
	@Update("UPDATE note SET read_status='open' WHERE note_num=#{note_num}") // 받는 사람이 열어봤음
	public void updateNoteStatusToOpen(int note_num);
	
	// 쪽지 삭제 상태 변경
	@Update("UPDATE note SET delete_status='sender' WHERE note_num=#{note_num}") // 받는 사람만 쪽지 삭제(보낸 사람은 삭제 안함)
	public void deleteNoteStatusToSender(int note_num);
	@Update("UPDATE note SET delete_status='recipient' WHERE note_num=#{note_num}") // 보낸 사람만 쪽지 삭제(받는 사람은 삭제 안함)
	public void deleteNoteStatusToRecipient(int note_num);
	
	@Delete("DELETE FROM note WHERE note_num=#{note_num}") // sender / recipient 상태에서 나머지 사람도 쪽지를 삭제(DB에서 아예 삭제)
	public void delete(int note_num);
}
