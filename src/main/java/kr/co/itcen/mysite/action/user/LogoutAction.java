package kr.co.itcen.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class LogoutAction implements Actionable{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session != null && 
			session.getAttribute("authUser") != null) {
			// 로그아웃 처리
			session.removeAttribute("authUser");
			session.invalidate();
		}
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
