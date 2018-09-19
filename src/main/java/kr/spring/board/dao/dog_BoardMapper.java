package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;

public interface dog_BoardMapper {
	//�θ��
	public List<BoardCommand> selectList(
			Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO dog_board (num,id,an_name,an_review,an_operate,an_color,an_gender,an_species,an_content,reg_date,uploadfile,filename) VALUES(board_seq.nextval,#{id},#{an_name},#{an_review},#{an_operate},#{an_color},#{an_gender},#{an_species},#{an_content},SYSDATE,#{uploadfile},#{filename})")
	public void insert(BoardCommand board);
	@Select("SELECT * FROM dog_board WHERE num=#{num}")
	public BoardCommand selectBoard(Integer num);
	@Update("UPDATE dog_board SET an_hit=an_hit+1 WHERE num=#{num}")
	public void updateHit(Integer num);
	public void update(BoardCommand board);
	@Delete("DELETE FROM dog_board WHERE num=#{num}")
	public void delete(Integer num);
	//��ȣ�� �̸�
	@Select("SELECT s_name FROM SHELTER_DETAIL where s_id=#{id}")
	public String selectName(String id);
	//��ȣ�� ��ȭ��ȣ
	@Select("SELECT s_phone FROM SHELTER_DETAIL where s_id=#{id}")
	public String selectNumber(String id);
	//��ȣ�� �̸� ������Ʈ
	@Update("UPDATE dog_board SET an_sheltername=#{sheltername} WHERE reg_date=(SELECT max(reg_date)from dog_board)")
	public void updateName(String shelterName);

	//���
	public List<BoardReplyCommand> selectListReply(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM dgboard_reply WHERE num=#{num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO dgboard_reply (re_num,re_content,pt_num,depth,re_date,num,id) VALUES (reply_seq.nextval,#{re_content},#{pt_num},#{depth},SYSDATE,#{num},#{id})")
	public void insertReply(BoardReplyCommand boardReply);
	@Update("UPDATE dgboard_reply SET re_content=#{re_content} WHERE re_num=#{re_num}")
	public void updateReply(BoardReplyCommand boardReply);
	@Delete("DELETE FROM dgboard_reply WHERE re_num=#{re_num}")
	public void deleteReply(Integer re_num);
	//�θ�� ������ ����� �����ϸ� �θ�� ������ ��� ����
	@Delete("DELETE FROM dgboard_reply WHERE num=#{num}")
	public void deleteReplyByNum(Integer num);
}








