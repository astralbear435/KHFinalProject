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
	
	//회원가입 및 인증메일 전송
	@Override
	public void insert(MemberCommand member) throws Exception {
		
		memberMapper.insert(member);		
		memberMapper.insertDetail(member);
		
		String verify_key = new TempKey().getKey(50, false); // 인증키 생성
		
		memberMapper.insertEmail(member.getM_email(), verify_key, member.getM_id());
		
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[보호소 서비스 이메일 인증]");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>").append("<a href='http://localhost:8080/ProjectCAN/member/emailConfirm.do?m_email=").append(member.getM_email()).append("&verify_key=").append(verify_key).append("'target='_blank'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("choisw7491@gmail.com", "보호소");
		sendMail.setTo(member.getM_email());
		sendMail.send();
	}
	
	//인증메일 재전송
	@Override
	public void resendEmail(MemberCommand member) throws Exception {
		
		String verify_key = memberMapper.findVerify_key(member.getM_email());
		
		if(verify_key != null) {
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[보호소 서비스 이메일 인증]");
			sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>").append("<a href='http://localhost:8080/ProjectCAN/member/emailConfirm.do?m_email=").append(member.getM_email()).append("&verify_key=").append(verify_key).append("'target='_blank'>이메일 인증 확인</a>").toString());
			sendMail.setFrom("choisw7491@gmail.com", "보호소");
			sendMail.setTo(member.getM_email());
			sendMail.send();
		}
	}
	
	//임시 비밀번호 메일 전송
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
		sendMail.setSubject("[보호소 서비스 임시비밀번호 이메일]");
		sendMail.setText(new StringBuffer().append("<h1>임시비밀번호</h1>").append("<hr>임시비밀번호는 <span style='color:blue'><b>" + sb.toString() + "</b></span> 입니다.<br>로그인 후 비밀번호를 변경해주세요.").toString());
		sendMail.setFrom("choisw7491@gmail.com", "보호소");
		sendMail.setTo(m_email);
		sendMail.send();
		
		memberMapper.updatePw(m_email,cipherAES.encrypt(sb.toString()));
	}
	
	//회원등급 변경
	@Override
	public void changeAuth(String m_email) throws Exception {
		
		memberMapper.changeAuth(m_email);
	}
	
	//인증키 삽입
	@Override
	public void verify(String m_email) throws Exception {
		
		memberMapper.verify(m_email);
	}
	
	//회원정보 검색
	@Override
	public MemberCommand selectMember(String m_id) {
		
		return memberMapper.selectMember(m_id);
	}
	
	//닉네임 검색
	@Override
	public MemberCommand checkMember_n(String m_nickname) {
		
		return memberMapper.checkMember_n(m_nickname);
	}
	
	//이메일 검색
	@Override
	public MemberCommand checkMember_e(String m_email) {
		
		return memberMapper.checkMember_e(m_email);
	}
	
	//회원정보 수정
	@Override
	public void updateMember(MemberCommand member) {
		
		memberMapper.updateDetail(member);
	}
	
	//회원탈퇴
	@Override
	public void delete(String m_id) {
		
		memberMapper.delete(m_id);
		memberMapper.deleteDetail(m_id);
		memberMapper.deleteEmail(m_id);
	}
	
	//아이디 찾기
	@Override
	public MemberCommand findId(String m_email) {
		
		return memberMapper.findId(m_email);
	}
	
	//구글 로그인
	/*@Override
	public MemberCommand googleLogin(MemberCommand member) throws Exception {
		
		memberMapper.googleLogin(member);
		
		return null;
	}*/
	

	// 회원 전체 리스트
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
