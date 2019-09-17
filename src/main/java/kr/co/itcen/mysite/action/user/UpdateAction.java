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

public class UpdateAction implements Actionable{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		UserVo vo = new UserVo();
		vo.setEmail(request.getParameter("email"));
		vo.setGender(request.getParameter("gender"));
		vo.setPassword(request.getParameter("password"));
		vo.setName(request.getParameter("name"));
		vo.setNo(authUser.getNo());
		
		if(new UserDao().update(vo)) {//update 성공시
			session.setAttribute("authUser", vo);
			System.out.println();
		}
			
		
		WebUtils.redirect(request, response, request.getContextPath());
	}

}
