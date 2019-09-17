package kr.co.itcen.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

/*
 * 회원가입 화면
 */
public class JoinFormAction implements Actionable{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtils.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
	}

}
