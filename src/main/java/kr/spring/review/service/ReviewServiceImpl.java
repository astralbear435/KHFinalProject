package kr.spring.review.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.goods.domain.OrderCommand;
import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.domain.ReviewCommand;
import kr.spring.review.domain.ReviewReplyCommand;
import kr.spring.shelter.domain.ShelterCommand;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	@Resource
	private ReviewMapper reviewMapper;
	
	@Override
	public void insertReview(ReviewCommand review) {
		// 리뷰등록
		reviewMapper.insertReview(review);
	}

	@Override
	public List<ShelterCommand> selectAsname(Map<String, Object> map) {
		//보호소 이름 가져오기
		return reviewMapper.selectAsname(map);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		// 목록
		return reviewMapper.selectCount(map);
	}

	@Override
	public List<ReviewCommand> getReviewList(Map<String, Object> map) {
		// 검색 목록 및 글 내용갖고오기
		return reviewMapper.getReviewList(map);
	}

	@Override
	public ReviewCommand selectDetail(int re_num) {
		// TODO Auto-generated method stub
		return reviewMapper.selectDetail(re_num);
	}

	@Override
	public int updateRe_hit(int re_num) {
		// 조회수 증가
		return reviewMapper.updateRe_hit(re_num);
	}

	@Override
	public void deleteReview(int re_num) {
		// 글 삭제
		reviewMapper.deleteReview(re_num);		
	}

	@Override
	public void insertReply(ReviewReplyCommand reply) {
		// 댓글 등록
		reviewMapper.insertReply(reply);		
	}

	@Override
	public int selectReplyCount(int reply_num) {
		//해당 글의 댓글 갯수
		return reviewMapper.selectReplyCount(reply_num);
	}

	@Override
	public List<ReviewReplyCommand> selecReplyList(Map<String, Object> map) {
		// 댓글리스트 가져오기
		return reviewMapper.selecReplyList(map);
	}

	@Override
	public void deleteReply(int reply_mynum) {
		//댓글 삭제
		reviewMapper.deleteReply(reply_mynum);		
	}

	@Override
	public void updateReply(ReviewReplyCommand reviewReplyCommand) {
		//댓글 수정
		reviewMapper.updateReply(reviewReplyCommand);		
	}

	@Override
	public void deleteReplyByNum(Integer reply_num) {
		//부모글 삭제시 댓글 삭제
		reviewMapper.deleteReplyByNum(reply_num);
	}

	@Override
	public ReviewCommand updateDetail(ReviewCommand review) {
		// TODO Auto-generated method stub
		return reviewMapper.updateDetail(review);
	}

	@Override
	public int selectCountId(String re_id) {
		// TODO Auto-generated method stub
		return reviewMapper.selectCountId(re_id);
	}

	@Override
	public List<ReviewCommand> shelterReviewList(String re_id) {
		// 자기가 쓴글 전부 갖고오세염
		return reviewMapper.shelterReviewList(re_id);
	}

	@Override
	public Date selectBeforeDate(String re_id) {
		//가장 최근의 데이트 값 갖고오셈
		return reviewMapper.selectBeforeDate(re_id);
	}

	@Override
	public List<OrderCommand> MyOrder(Map<String, Object> map) {
		// 기부받은 목록 가져오기
		return reviewMapper.MyOrder(map);
	}

}
