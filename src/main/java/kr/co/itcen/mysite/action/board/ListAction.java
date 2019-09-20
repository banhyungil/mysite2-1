package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Actionable;

public class ListAction implements Actionable {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		final int pageRows = 5;
		
		List<BoardVo> list = null;
		if(keyword != null) {				//찾기 기능
			list = new BoardDao().getList(keyword, pageRows);
		} else {
			list = new BoardDao().getList(pageRows);	
		}
		
		request.setAttribute("list", list);
		
		processPagingk(list, request, pageRows);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}
	
	public void processPagingk(List<BoardVo> list, HttpServletRequest request, int pageRows) {
		String currentPageStr = request.getParameter("currentPage");
		String currentPageBlockStr = request.getParameter("currentPageBlock");
		
		final int pageBlockSize = 5;
		int totalRows = list.size();	
		int totalPage = (totalRows / pageRows) + 1;
		int currentPage = 1;
		int currentPageBlock = 1;
		int firstPage;
		int lastPage;
		if(currentPageStr != null)
			currentPage = Integer.parseInt(currentPageStr);
		
		if(currentPageBlockStr != null)
			currentPageBlock = Integer.parseInt(currentPageBlockStr);
		
		currentPageBlock = (currentPage / pageBlockSize);
		
		/* 현재 페이지  Block의 first page, last page 계산 */
		firstPage = currentPageBlock * pageBlockSize + 1;
		lastPage = currentPageBlock * pageBlockSize + pageBlockSize;
		if(lastPage > totalPage)
			lastPage = totalPage;
		
		request.setAttribute("currentPageBlock", currentPageBlock);
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pageRows", pageRows);
		
		
	}
}
