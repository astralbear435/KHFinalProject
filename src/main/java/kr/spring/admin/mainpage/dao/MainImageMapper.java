package kr.spring.admin.mainpage.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.mainpage.domain.MainImageCommand;

public interface MainImageMapper {
	@Insert("INSERT INTO main_image(main_num,main_img_name,main_s_img_name,main_order) VALUES(MAIN_IMAGE_MAIN_NUM_SEQ.nextval,#{main_img_name},#{s_main_img_name},#{main_order})")
	public void insertMainImage(MainImageCommand mainImage);
	@Delete("DELETE FROM main_image WHERE main_num")
	public void deleteMainImage(Integer num);
	@Select("SELECT * FROM main_image WHERE main_img_name=#{imagename}")
	public MainImageCommand selectMainImage(String imagename);
	@Update("UPDATE main_image SET main_order =#{order} WHERE main_num=#{num}")
	public void updateMainImageOrder(Integer num,Integer order);
	@Select("SELECT COUNT(*) FROM main_image")
	public Integer selectImageCount();
}
