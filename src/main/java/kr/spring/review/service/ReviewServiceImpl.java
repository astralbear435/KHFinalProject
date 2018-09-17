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
		// ������
		reviewMapper.insertReview(review);
	}

	@Override
	public List<ShelterCommand> selectAsname(Map<String, Object> map) {
		//��ȣ�� �̸� ��������
		return reviewMapper.selectAsname(map);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		// ���
		return reviewMapper.selectCount(map);
	}

	@Override
	public List<ReviewCommand> getReviewList(Map<String, Object> map) {
		// �˻� ��� �� �� ���밮�����
		return reviewMapper.getReviewList(map);
	}

	@Override
	public ReviewCommand selectDetail(int re_num) {
		// TODO Auto-generated method stub
		return reviewMapper.selectDetail(re_num);
	}

}
