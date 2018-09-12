package kr.spring.note.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import kr.spring.note.domain.NoteCommand;

public interface NoteService {
	public List<NoteCommand> selectNoteList(Map<String,Object> map);
	public int selectNoteRowCount(Map<String,Object> map);
	
	public void insert(NoteCommand note);
	
	public NoteCommand selectNote(int note_num);
	
	public void updateNoteStatusToOpen(int note_num);
	public void deleteNoteStatusToSender(int note_num);
	public void deleteNoteStatusToRecipient(int note_num);
	
	public void delete(int note_num);
}
