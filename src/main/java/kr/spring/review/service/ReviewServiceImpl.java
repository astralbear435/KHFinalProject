package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.domain.ReviewCommand;
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

}
