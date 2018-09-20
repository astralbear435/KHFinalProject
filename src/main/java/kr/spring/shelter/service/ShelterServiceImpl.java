package kr.spring.shelter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.util.CipherTemplate;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.email.MailHandler;
import kr.spring.shelter.dao.ShelterMapper;

import org.springframework.mail.javamail.JavaMailSender;

@Service("shelterService")
public class ShelterServiceImpl implements ShelterService{	
	@Resource
	private ShelterMapper shelterMapper;
	
	@Resource
	private CipherTemplate cipherAES;
	
	@Inject
	private JavaMailSender mailSender;
	
	@Override
	public List<ShelterCommand> selectList(Map<String, Object> map) {
		return shelterMapper.selectList(map);
	}


	@Override
	public int selectRowCount(Map<String, Object> map) {
		return shelterMapper.selectRowCount(map);
	}
	
	@Override
	public void insert(ShelterCommand shelter) {
		shelterMapper.insert(shelter);
		shelterMapper.insertShelterDetail(shelter);
	}

	
	@Override
	public ShelterCommand selectShelter(String id) {
		return shelterMapper.selectShelter(id);
	}

	@Override
	public void update(ShelterCommand shelter) {
		shelterMapper.update(shelter);
	}

	@Override
	public void delete(String id) {
		shelterMapper.delete(id);
		shelterMapper.deleteShelterDetail(id);
	}


	@Override
	public void updatePwShelter(String m_email) throws Exception {

			int index = 0;		
			char[] charset_num = new char[] {
					'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			};
			char[] charset_eng = new char[] {
					'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
					'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
					'U', 'V', 'W', 'X', 'Y', 'Z'
			};
			char[] charset_sc = new char[] {
					'`', '~', '!', '@','#', '$', '%', '^',
					'&' ,'*' ,'(', ')', '_', '-', '+', '='	
			};
			
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < 3; i++) {
				
				index = (int) (charset_num.length * Math.random());
				sb.append(charset_num[index]);
			}
			for(int i = 0; i < 4; i++) {

				index = (int) (charset_eng.length * Math.random());
				sb.append(charset_eng[index]);
			}
			for(int i = 0; i < 3; i++) {

				index = (int) (charset_sc.length * Math.random());
				sb.append(charset_sc[index]);
			}
			
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[��ȣ�� ���� �ӽú�й�ȣ �̸���]");
			sendMail.setText(new StringBuffer().append("<h1>�ӽú�й�ȣ</h1>").append("<hr>�ӽú�й�ȣ�� <span style='color:blue'><b>" + sb.toString() + "</b></span> �Դϴ�.<br>�α��� �� ��й�ȣ�� �������ּ���.").toString());
			sendMail.setFrom("choisw7491@gmail.com", "��ȣ��");
			sendMail.setTo(m_email);
			sendMail.send();
			
			shelterMapper.updatePwShelter(m_email,cipherAES.encrypt(sb.toString()));	
	}


	@Override
<<<<<<< HEAD
	public String selectAsName(String id) {
		//��ȣ�� �̸�
		return shelterMapper.selectAsName(id);
	}

=======
	public int selectShelterCount() {
		
		return shelterMapper.selectShelterCount();
	}


	@Override
	public int selectTodayShelterCount() {
		
		return shelterMapper.selectTodayShelterCount();
	}


	@Override
	public List<ShelterCommand> selectShelterList() {
		return shelterMapper.selectShelterList();
	}


	

>>>>>>> e1628094b0c6202790c9402b427d717daafd7612
}
