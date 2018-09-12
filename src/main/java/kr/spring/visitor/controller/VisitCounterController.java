package kr.spring.visitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.visitor.domain.VisitorCommand;
import kr.spring.visitor.service.VisitorService;

@Controller
public class VisitCounterController implements HttpSessionListener {

	private VisitorService visitor;
	VisitorCommand vo = new VisitorCommand();
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		//��ϵǾ��ִ� ���� ����Ҽ� �ֵ��� �������ش�
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		//request�� �Ķ���Ϳ� ���� �ʰ��� ����Ҽ� �ֵ��� ����
		visitor = (VisitorService)wac.getBean("visitorService");

		vo.setVisit_ip(req.getRemoteAddr());
		vo.setVisit_agent(req.getHeader("User-Agent"));//������ ����
		vo.setVisit_refer(req.getHeader("referer"));//���� �� ����Ʈ ����

		System.out.println(vo);
		visitor.insertVisitor(vo);

	}



	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id")!=null) {
			vo.setVisit_login_id((String)session.getAttribute("user_id"));
		}
	}
}