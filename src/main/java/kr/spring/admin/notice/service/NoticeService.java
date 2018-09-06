package kr.spring.admin.notice.service;

import java.util.List;

import kr.spring.admin.notice.domin.NoticeCommend;

public interface NoticeService {
	public List<NoticeCommend> selectNoticeList();
	public NoticeCommend selectNotice(Integer n_inx);
	public void insertNotice(NoticeCommend nc);
	public void updateNotice(NoticeCommend nc);
	public void updateHits(Integer n_idx);
	public void deleteNotice(Integer n_idx);
}
