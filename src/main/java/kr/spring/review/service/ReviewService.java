package kr.spring.review.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import kr.spring.goods.domain.OrderCommand;
import kr.spring.review.domain.ReviewCommand;
import kr.spring.review.domain.ReviewReplyCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ReviewService {
	//���� ���
	public void insertReview(ReviewCommand review);
	//��ȣ�� �̸� ��������
	public List<ShelterCommand> selectAsname(Map<String,Object> map);
	public int selectCount(Map<String,Object> map);
	//���� �� �������
	public List<ReviewCommand> getReviewList(Map<String,Object> map);
	//����� �󼼺���
	public ReviewCommand selectDetail(int re_num);
	//��ȸ�� ����
	public int updateRe_hit(int re_num);
	//�� ����
	public void deleteReview(int re_num);
	//���� ����
	public ReviewCommand updateDetail(ReviewCommand review);
	//�ڱⰡ ���� ī��Ʈ �������
	public int selectCountId(String re_id);
	public List<ReviewCommand> shelterReviewList(String re_id);
	//���� �ֱ��� ����Ʈ �� �������
	public Date selectBeforeDate(String re_id);
	//���� ��ΰ� �������
	public List<OrderCommand> MyOrder(Map<String,Object> map);
	
	
	//��� ���
	public void insertReply(ReviewReplyCommand reply);
	//�� ����� ����
	public int selectReplyCount(int reply_num);
	//��� ���
	public List<ReviewReplyCommand> selecReplyList(Map<String,Object> map);
	//��� ����
	public void deleteReply(int reply_mynum);
	//��� ����
	public void updateReply(ReviewReplyCommand reviewReplyCommand);
	//�θ�� ������ ��� ����
	public void deleteReplyByNum(Integer reply_num);
}

