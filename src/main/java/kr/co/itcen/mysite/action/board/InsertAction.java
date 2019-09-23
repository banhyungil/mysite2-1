package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class InsertAction implements Actionable{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		UserVo vo =  (UserVo)session.getAttribute("authUser");
		Long userNo = vo.getNo();
		
		String boardNoStr = request.getParameter("boardNo");
		
		if(boardNoStr == null || boardNoStr.isEmpty()) { 	//새글인 경우
			new BoardDao().insert(title,content, userNo);
		} else {				//답글인 경우
			Long boardNo = Long.parseLong(boardNoStr);
			new BoardDao().insert(title, content, userNo, boardNo);
		}
		
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board");
	}

}
