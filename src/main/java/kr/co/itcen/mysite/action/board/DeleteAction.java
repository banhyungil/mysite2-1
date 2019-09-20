package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class DeleteAction implements Actionable {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		
		Boolean isSuccess = new BoardDao().delete(no);
		if(!isSuccess) {
			System.out.println("Delete 실패");
			return;
		}
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}

}
