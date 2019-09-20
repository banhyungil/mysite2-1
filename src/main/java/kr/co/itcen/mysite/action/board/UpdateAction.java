package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class UpdateAction implements Actionable {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Boolean isSuccess = new BoardDao().update(no,title,contents);
		if(!isSuccess) {
			System.out.println("fail");
		}
		
		BoardVo vo = new BoardDao().get(no);
		request.setAttribute("vo", vo);
		WebUtils.forward(request, response, "/WEB-INF/views/board/contentsform.jsp");	
	}

}
