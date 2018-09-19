package kr.spring.review.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.goods.domain.OrderCommand;
import kr.spring.review.domain.ReviewCommand;
import kr.spring.review.domain.ReviewReplyCommand;
import kr.spring.shelter.domain.ShelterCommand;

public interface ReviewMapper{
	@Insert("INSERT INTO REVIEW(re_num,re_title,re_id,re_asname,re_content,re_date,re_hit,re_auth) VALUES(review_seq.nextval,#{re_title},#{re_id},#{re_asname},#{re_content},SYSDATE,0,#{re_auth})")
	public void insertReview(ReviewCommand review);
	//보호소 이름 가져오기
	@Select("SELECT * FROM shelter_detail")
	public List<ShelterCommand> selectAsname(Map<String,Object> map);
	//최소 카운트 갖고오기 글목록
	public int selectCount(Map<String,Object> map);
	//자기가 쓴글의 카운트 값 갖고오기
	@Select("SELECT count(*) FROM review WHERE re_id=#{re_id}")
	public int selectCountId(String re_id);
	//자기가 쓴 글 갖고오기(보호소)
	@Select("SELECT * FROM review WHERE re_id=#{re_id} ORDER BY re_num DESC")
	public List<ReviewCommand> shelterReviewList(String re_id);
	//글 검색 내용 들고오기
	public List<ReviewCommand> getReviewList(Map<String,Object> map);
	//상세페이지 상세글
	@Select("SELECT * FROM REVIEW where re_num=#{re_num}")
	public ReviewCommand selectDetail(int re_num);
	//조회수 증가
	@Update("UPDATE REVIEW SET re_hit=re_hit+1 WHERE re_num=#{re_num}")
	public int updateRe_hit(int re_num);
	//글 삭제 
	@Delete("DELETE FROM REVIEW WHERE re_num=#{re_num}")
	public void deleteReview(int re_num);
	//글 수정
	@Update("UPDATE review SET re_title=#{re_title},re_content=#{re_content},as_date=sysdate WHERE re_num=#{re_num}")
	public ReviewCommand updateDetail(ReviewCommand review);
	//가장 최근 글쓴날 갖고오기
	@Select("SELECT RE_DATE FROM review WHERE re_date= (select max(re_date)from review WHERE re_id=#{re_id})")
	public Date selectBeforeDate(String re_id);
	//기부 받은 목록 가져오기
	@Select("SELECT * FROM donation WHERE dona_date<=#{beforeDate} AND dona_asname LIKE '%' || #{re_asname} || '%'")
	public List<OrderCommand> MyOrder(Map<String,Object> map);
	
	
	//댓글 등록
	@Insert("INSERT INTO REVIEW_REPLY(reply_mynum,reply_id,reply_content,reply_date,reply_num) VALUES(review_reply_seq.nextval,#{reply_id},#{reply_content},SYSDATE,#{reply_num})")
	public void insertReply(ReviewReplyCommand reply);
	//해당 글의 댓글갯수
	@Select("SELECT count(*) FROM review_reply WHERE reply_num=#{reply_num}")
	public int selectReplyCount(int reply_num);
	@Select("SELECT * FROM review_reply WHERE reply_num=#{reply_num}")
	public List<ReviewReplyCommand> selecReplyList(Map<String,Object> map);
	//댓글삭제
	@Delete("DELETE FROM review_reply WHERE reply_mynum=#{reply_mynum}")
	public void deleteReply(int reply_mynum);
	//댓글 수정
	@Update("UPDATE review_reply SET reply_content=#{reply_content} WHERE reply_mynum=#{reply_mynum}")
	public void updateReply(ReviewReplyCommand reviewReplyCommand);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM review_reply WHERE reply_num=#{reply_num}")
	public void deleteReplyByNum(Integer reply_num);
}
