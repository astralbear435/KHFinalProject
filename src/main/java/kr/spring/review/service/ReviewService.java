package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import kr.spring.review.domain.ReviewCommand;
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

}

