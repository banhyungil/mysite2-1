package kr.co.itcen.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.UserDao;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class UpdateFormAction implements Actionable{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근 제어(ACL) /////////////////////////
				HttpSession session = request.getSession();
				
				if( session == null ) {
					WebUtils.redirect(request, response, request.getContextPath());
					return;
				}
				UserVo authUser = (UserVo)session.getAttribute("authUser");
				if(authUser == null) {
					WebUtils.redirect(request, response, request.getContextPath());
					return;
				}
				/////////////////////////////////////////
				
				session.setAttribute("authUser", new UserDao().get(authUser.getNo()));
				WebUtils.forward(request, response, "/WEB-INF/views/user/updateform.jsp");
	}

}
