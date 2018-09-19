package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.dog_BoardMapper;
import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource
	private dog_BoardMapper dog_boardMapper;
	
	@Override
	public List<BoardCommand> selectList(Map<String, Object> map) {
		return dog_boardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return dog_boardMapper.selectRowCount(map);
	}

	@Override
	public void insert(BoardCommand board) {
		dog_boardMapper.insert(board);
	}

	@Override
	public BoardCommand selectBoard(Integer an_num) {
		return dog_boardMapper.selectBoard(an_num);
	}

	@Override
	public void updateHit(Integer an_num) {
		dog_boardMapper.updateHit(an_num);
	}

	@Override
	public void update(BoardCommand board) {
		dog_boardMapper.update(board);
	}

	@Override
	public void delete(Integer an_num) {
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		dog_boardMapper.deleteReplyByNum(an_num);
		//부모글 삭제
		dog_boardMapper.delete(an_num);
	}

	@Override
	public List<BoardReplyCommand> selectListReply(Map<String, Object> map) {
		return dog_boardMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return dog_boardMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(BoardReplyCommand boardReply) {
		dog_boardMapper.insertReply(boardReply);
	}

	@Override
	public void updateReply(BoardReplyCommand boardReply) {
		dog_boardMapper.updateReply(boardReply);
	}

	@Override
	public void deleteReply(Integer re_num) {
		dog_boardMapper.deleteReply(re_num);
	}

	@Override
	public String selectName(String id) {
		
		return dog_boardMapper.selectName(id);
	}

	@Override
	public void updateName(String shelterName) {
		dog_boardMapper.updateName(shelterName);
	}

	@Override
	public String selectNumber(String id) {
		return dog_boardMapper.selectNumber(id);
	}

}





