package kr.spring.member.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.email.MailHandler;
import kr.spring.member.email.TempKey;
import kr.spring.util.CipherTemplate;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Resource
	private MemberMapper memberMapper;

	@Resource
	private CipherTemplate cipherAES;
	
	@Inject
	private JavaMailSender mailSender;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//ȸ������ �� �������� ����
	@Override
	public void insert(MemberCommand member) throws Exception {
		
		memberMapper.insert(member);		
		memberMapper.insertDetail(member);
		
		String verify_key = new TempKey().getKey(50, false); // ����Ű ����
		
		memberMapper.insertEmail(member.getM_email(), verify_key, member.getM_id());
		
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[��ȣ�� ���� �̸��� ����]");
		sendMail.setText(new StringBuffer().append("<h1>��������</h1>").append("<a href='http://localhost:8080/ProjectCAN/member/emailConfirm.do?m_email=").append(member.getM_email()).append("&verify_key=").append(verify_key).append("'target='_blank'>�̸��� ���� Ȯ��</a>").toString());
		sendMail.setFrom("choisw7491@gmail.com", "��ȣ��");
		sendMail.setTo(member.getM_email());
		sendMail.send();
	}
	
	//�������� ������
	@Override
	public void resendEmail(MemberCommand member) throws Exception {
		
		String verify_key = memberMapper.findVerify_key(member.getM_email());
		
		if(verify_key != null) {
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[��ȣ�� ���� �̸��� ����]");
			sendMail.setText(new StringBuffer().append("<h1>��������</h1>").append("<a href='http://localhost:8080/ProjectCAN/member/emailConfirm.do?m_email=").append(member.getM_email()).append("&verify_key=").append(verify_key).append("'target='_blank'>�̸��� ���� Ȯ��</a>").toString());
			sendMail.setFrom("choisw7491@gmail.com", "��ȣ��");
			sendMail.setTo(member.getM_email());
			sendMail.send();
		}
	}
	
	//�ӽ� ��й�ȣ ���� ����
	@Override
	public void updatePw(String m_email) throws Exception {
		
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
		
		memberMapper.updatePw(m_email,cipherAES.encrypt(sb.toString()));
	}
	
	//ȸ����� ����
	@Override
	public void changeAuth(String m_email) throws Exception {
		
		memberMapper.changeAuth(m_email);
	}
	
	//����Ű ����
	@Override
	public void verify(String m_email) throws Exception {
		
		memberMapper.verify(m_email);
	}
	
	//ȸ������ �˻�
	@Override
	public MemberCommand selectMember(String m_id) {
		
		return memberMapper.selectMember(m_id);
	}
	
	//�г��� �˻�
	@Override
	public MemberCommand checkMember_n(String m_nickname) {
		
		return memberMapper.checkMember_n(m_nickname);
	}
	
	//�̸��� �˻�
	@Override
	public MemberCommand checkMember_e(String m_email) {
		
		return memberMapper.checkMember_e(m_email);
	}
	
	//ȸ������ ����
	@Override
	public void updateMember(MemberCommand member) {
		
		memberMapper.updateDetail(member);
	}
	
	//ȸ��Ż��
	@Override
	public void delete(String m_id) {
		
		memberMapper.delete(m_id);
		memberMapper.deleteDetail(m_id);
		memberMapper.deleteEmail(m_id);
	}
	
	//���̵� ã��
	@Override
	public MemberCommand findId(String m_email) {
		
		return memberMapper.findId(m_email);
	}
	
	//���� �α���
	/*@Override
	public MemberCommand googleLogin(MemberCommand member) throws Exception {
		
		memberMapper.googleLogin(member);
		
		return null;
	}*/
	

	// ȸ�� ��ü ����Ʈ
	@Override
	public List<MemberCommand> wholeList() {
		return memberMapper.wholeList();
	}


	@Override
	public int wholeCount() {
		return memberMapper.wholeCount();
	}

	@Override
	public int selectMemberAuth(String m_id) {
		return memberMapper.selectMemberAuth(m_id);
	}
}
