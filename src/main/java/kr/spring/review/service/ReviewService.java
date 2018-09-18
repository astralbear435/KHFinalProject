package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import kr.spring.review.domain.ReviewCommand;
import kr.spring.review.domain.ReviewReplyCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ReviewService {
	//리뷰 등록
	public void insertReview(ReviewCommand review);
	//보호소 이름 가져오기
	public List<ShelterCommand> selectAsname(Map<String,Object> map);
	public int selectCount(Map<String,Object> map);
	//리뷰 글 갖고오기
	public List<ReviewCommand> getReviewList(Map<String,Object> map);
	//리뷰글 상세보기
	public ReviewCommand selectDetail(int re_num);
	//조회수 증가
	public int updateRe_hit(int re_num);
	//글 삭제
	public void deleteReview(int re_num);
	//리뷰 수정
	public ReviewCommand updateDetail(ReviewCommand review);
	
	
	
	//댓글 등록
	public void insertReply(ReviewReplyCommand reply);
	//총 댓글의 갯수
	public int selectReplyCount(int reply_num);
	//댓글 목록
	public List<ReviewReplyCommand> selecReplyList(Map<String,Object> map);
	//댓글 삭제
	public void deleteReply(int reply_mynum);
	//댓글 수정
	public void updateReply(ReviewReplyCommand reviewReplyCommand);
	//부모글 삭제시 댓글 삭제
	public void deleteReplyByNum(Integer reply_num);
}

