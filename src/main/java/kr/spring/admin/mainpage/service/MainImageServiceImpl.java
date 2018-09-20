package kr.spring.admin.mainpage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.admin.mainpage.dao.MainImageMapper;
import kr.spring.admin.mainpage.domain.MainImageCommand;

@Service("mainImageService")
public class MainImageServiceImpl implements MainImageService {

	@Resource
	private MainImageMapper mainImageMapper;
	
	@Override
	public void insertMainImage(MainImageCommand mainImage) {
		mainImageMapper.insertMainImage(mainImage);
		
	}

	@Override
	public void deleteMainImage(String s_img) {
		mainImageMapper.deleteMainImage(s_img);
		
	}

	@Override
	public MainImageCommand selectMainImage(String imagename) {
		return mainImageMapper.selectMainImage(imagename);
	}

	@Override
	public void updateMainImageOrder(Integer num, Integer order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer selectImageCount() {
		// TODO Auto-generated method stub
		return mainImageMapper.selectImageCount();
	}

	@Override
	public List<MainImageCommand> selectImageList() {
		// TODO Auto-generated method stub
		return mainImageMapper.selectImageList();
	}

}
