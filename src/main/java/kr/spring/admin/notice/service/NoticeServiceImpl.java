package kr.spring.admin.notice.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.admin.notice.dao.NoticeMapper;
import kr.spring.admin.notice.domin.NoticeCommend;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Resource
	private NoticeMapper notice;
	
	@Override
	public List<NoticeCommend> selectNoticeList() {
		
		return notice.selectNoticeList();
	}

	@Override
	public NoticeCommend selectNotice(Integer n_inx) {
		return notice.selectNotice(n_inx);
	}

	@Override
	public void insertNotice(NoticeCommend nc) {
		notice.insertNotice(nc);
	}

	@Override
	public void updateNotice(NoticeCommend nc) {
		notice.updateNotice(nc);
	}

	@Override
	public void updateHits(Integer n_idx) {
		notice.updateHits(n_idx);
	}

	@Override
	public void deleteNotice(Integer n_idx) {
		notice.deleteNotice(n_idx);
	}

}
