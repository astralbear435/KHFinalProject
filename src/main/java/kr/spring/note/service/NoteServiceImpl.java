package kr.spring.note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.note.dao.NoteMapper;
import kr.spring.note.domain.NoteCommand;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

	@Override
	public List<NoteCommand> selectNoteList(Map<String, Object> map) {
		return noteMapper.selectNoteList(map);
	}

	@Override
	public int selectNoteRowCount(Map<String, Object> map) {
		return noteMapper.selectNoteRowCount(map);
	}

	@Override
	public int openNotCount(String recipient) {
		return noteMapper.openNotCount(recipient);
	}

	@Resource
	private NoteMapper noteMapper;
	
	@Override
	public void insert(NoteCommand note) {
		noteMapper.insert(note);
	}

	@Override
	public NoteCommand selectNote(int note_num) {
		return noteMapper.selectNote(note_num);
	}

	@Override
	public void updateNoteStatusToOpen(int note_num) {
		noteMapper.updateNoteStatusToOpen(note_num);
	}

	@Override
	public void deleteNoteStatusToSender(int note_num) {
		noteMapper.deleteNoteStatusToSender(note_num);
	}

	@Override
	public void deleteNoteStatusToRecipient(int note_num) {
		noteMapper.deleteNoteStatusToRecipient(note_num);
	}

	@Override
	public void delete(int note_num) {
		noteMapper.delete(note_num);
	}
}
