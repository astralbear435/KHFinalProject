package kr.spring.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.review.domain.ReviewCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ReviewMapper{
	@Insert("INSERT INTO REVIEW(re_num,re_title,re_id,re_asname,re_content,re_date,re_hit) VALUES(review_seq.nextval,#{re_title},#{re_id},#{re_asname},#{re_content},SYSDATE,0)")
	public void insertReview(ReviewCommand review);
	//보호소 이름 가져오기
	@Select("SELECT * FROM shelter_detail")
	public List<ShelterCommand> selectAsname(Map<String,Object> map);
	//최소 카운트 갖고오기 글목록
	public int selectCount(Map<String,Object> map);
	//글 검색 내용 들고오기
	public List<ReviewCommand> getReviewList(Map<String,Object> map);
}
