package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import kr.spring.review.domain.ReviewCommand;
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

}

