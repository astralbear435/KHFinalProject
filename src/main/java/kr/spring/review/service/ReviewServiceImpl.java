package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	@Override
	public int updateRe_hit(int re_num) {
		// ��ȸ�� ����
		return reviewMapper.updateRe_hit(re_num);
	}

	@Override
	public void deleteReview(int re_num) {
		// �� ����
		reviewMapper.deleteReview(re_num);		
	}

	@Override
	public void insertReply(ReviewReplyCommand reply) {
		// ��� ���
		reviewMapper.insertReply(reply);		
	}

	@Override
	public int selectReplyCount(int reply_num) {
		//�ش� ���� ��� ����
		return reviewMapper.selectReplyCount(reply_num);
	}

	@Override
	public List<ReviewReplyCommand> selecReplyList(Map<String, Object> map) {
		// ��۸���Ʈ ��������
		return reviewMapper.selecReplyList(map);
	}

	@Override
	public void deleteReply(int reply_mynum) {
		//��� ����
		reviewMapper.deleteReply(reply_mynum);		
	}

	@Override
	public void updateReply(ReviewReplyCommand reviewReplyCommand) {
		//��� ����
		reviewMapper.updateReply(reviewReplyCommand);		
	}

	@Override
	public void deleteReplyByNum(Integer reply_num) {
		//�θ�� ������ ��� ����
		reviewMapper.deleteReplyByNum(reply_num);
	}

	@Override
	public ReviewCommand updateDetail(ReviewCommand review) {
		// TODO Auto-generated method stub
		return reviewMapper.updateDetail(review);
	}

}
