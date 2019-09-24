package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class WriteFormAction implements Actionable {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		//로그인이 되지 않았을 경우	
		if(session == null || session.getAttribute("authUser") == null) { 	
			response.sendRedirect(request.getContextPath() + "/board");				
			return;
		}
		
		//로그인이된 경우
		String boardNoStr = request.getParameter("boardNo");

		if(boardNoStr != null) {
			Long boardNo = Long.parseLong(boardNoStr);
			request.setAttribute("boardNo", boardNo);
		}
			
		WebUtils.forward(request, response, "/WEB-INF/views/board/writeform.jsp");
	}

}
