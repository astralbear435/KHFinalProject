package kr.spring.note.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.note.domain.BlockCommand;
import kr.spring.note.domain.NoteCommand;

public interface NoteService {
	public List<NoteCommand> selectNoteList(Map<String,Object> map);
	public int selectNoteRowCount(Map<String,Object> map);
	
	// 안 읽은 쪽지 수 세기
	public int openNotCount(String recipient);
	
	public void insert(NoteCommand note);
	
	public NoteCommand selectNote(int note_num);
	
	// 쪽지 읽음 상태 변경
	public void updateNoteStatusToOpen(int note_num);
	
	// 쪽지 삭제 상태 변경
	public void deleteNoteStatusToSender(int note_num);
	public void deleteNoteStatusToRecipient(int note_num);
	
	// 쪽지 삭제
	public void delete(int note_num);
	
	// 차단하기
	public void block(String block, String be_blocked);
	public void unblock(String block, String be_blocked);
	
	// 차단리스트
	public List<BlockCommand> blockList(String id);
}
