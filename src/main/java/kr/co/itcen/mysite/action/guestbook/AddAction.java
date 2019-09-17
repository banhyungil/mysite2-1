package kr.co.itcen.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.web.mvc.Actionable;

public class AddAction implements Actionable {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String contents = request.getParameter("contents");
		
		System.out.println(name);
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(contents);
		
		new GuestbookDao().insert(vo);
		response.sendRedirect(request.getContextPath() + "/gb");
	}

}
