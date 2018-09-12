package kr.spring.admin.mainpage.service;

import java.util.List;



import kr.spring.admin.mainpage.domain.MainImageCommand;

public interface MainImageService {
	public void insertMainImage(MainImageCommand mainImage);
	public void deleteMainImage(String s_img);
	public MainImageCommand selectMainImage(String imagename);
	public void updateMainImageOrder(Integer num,Integer order);
	public Integer selectImageCount();
}
