package kr.spring.note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.note.domain.BlockCommand;
import kr.spring.note.domain.NoteCommand;

public interface NoteMapper {
	public List<NoteCommand> selectNoteList(Map<String,Object> map);
	public int selectNoteRowCount(Map<String,Object> map);
	
	// �� ���� ���� �� ����
	@Select("select count(*) from note where recipient=#{recipient} and read_status='open_not'")
	public int openNotCount(String recipient);
	
	@Insert("INSERT INTO note (note_num,sender,recipient,note_content) VALUES (note_seq.nextval,#{sender},#{recipient},#{note_content})")
	public void insert(NoteCommand note);
	
	@Select("SELECT * FROM note WHERE note_num=#{note_num}")
	public NoteCommand selectNote(int note_num);
	
	// ���� ���� ���� ����
	@Update("UPDATE note SET read_status='open' WHERE note_num=#{note_num}") // �޴� ����� �������
	public void updateNoteStatusToOpen(int note_num);
	
	// ���� ���� ���� ����
	@Update("UPDATE note SET delete_status='sender' WHERE note_num=#{note_num}") // �޴� ����� ���� ����(���� ����� ���� ����)
	public void deleteNoteStatusToSender(int note_num);
	@Update("UPDATE note SET delete_status='recipient' WHERE note_num=#{note_num}") // ���� ����� ���� ����(�޴� ����� ���� ����)
	public void deleteNoteStatusToRecipient(int note_num);
	
	@Delete("DELETE FROM note WHERE note_num=#{note_num}") // sender / recipient ���¿��� ������ ����� ������ ����(DB���� �ƿ� ����)
	public void delete(int note_num);
	
	// �����ϱ�
	@Insert("INSERT INTO block VALUES (#{block},#{be_blocked})")
	public void block(String block, String be_blocked);
	@Delete("DELETE FROM block b WHERE b.block=#{block} AND b.be_blocked=#{be_blocked}")
	public void unblock(String block, String be_blocked);
	
	// ���ܸ���Ʈ
	@Select("SELECT be_blocked FROM block b WHERE b.block=#{id}")
	public List<BlockCommand> blockList(String id);
}
