package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminLoginCheckInterceptor extends
                         HandlerInterceptorAdapter{
	private Logger log = Logger.getLogger(
			                         this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			                 Object handler)
			                		 throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("====AdminLoginCheckInterceptor����====");
		}
		
		HttpSession session = request.getSession();

		//�α��� ���� �˻�
		if(session.getAttribute("user_id")==null) {
			//�α����� ���� ���� ����
			response.sendRedirect(
					request.getContextPath()+"/admin/notlogin.do");
			return false;
		}else {
			if((Integer)session.getAttribute("user_auth")!=-1) {
				//�����ڰ� �ƴϸ�
				response.sendRedirect(request.getContextPath()+"/admin/noadmin.do");
				session.invalidate();
				return false;
			}
		}
		
		return true;
	}
}





