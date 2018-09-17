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
	VisitorCommand visitorId = new VisitorCommand();
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		//등록되어있는 빈을 사용할수 있도록 설정해준다
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		//request를 파라미터에 넣지 않고도 사용할수 있도록 설정
		visitor = (VisitorService)wac.getBean("visitorService");
		VisitorCommand vo = new VisitorCommand();
		vo.setVisit_ip(req.getRemoteAddr());
		vo.setVisit_agent(req.getHeader("User-Agent"));//브라우저 정보
		vo.setVisit_refer(req.getHeader("referer"));//접속 전 사이트 정보
		if(session.getAttribute("user_id")!=null) {
			vo.setVisit_login_id((String)session.getAttribute("user_id"));
		}
		System.out.println(vo);
		visitor.insertVisitor(vo);
		visitorId= visitor.selectVisittorId(vo);

	}



	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		if(session.getAttribute("user_id")!=null) {
			visitorId.setVisit_login_id((String)session.getAttribute("user_id"));
		}
		visitor.updateVisitor(visitorId);
	}
}
