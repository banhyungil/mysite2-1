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
		
		List<BoardVo> list = null;
		int totalRows;
		final int pageRows = 5;
		int currentPage = 1;	
		String keyword = request.getParameter("keyword");
	
		String currentPageStr = request.getParameter("currentPage");	
		if(currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		if(keyword != null) {				//찾기 기능
			BoardDao bdao = new BoardDao();
			totalRows = new BoardDao().getListCount(keyword);
			list = new BoardDao().getList(keyword, pageRows, currentPage);
		} else {
			totalRows = new BoardDao().getListCount();
			list = new BoardDao().getList(pageRows, currentPage);
			keyword="";
		}
		
		request.setAttribute("list", list);		
		processPagingk(list, request, pageRows, currentPage, totalRows);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp?keyword=" + keyword);
	}
	
	public void processPagingk(List<BoardVo> list, HttpServletRequest request, int pageRows, int currentPage, int totalRows) {
		String currentPageBlockStr = request.getParameter("currentPageBlock");
		
		final int pageBlockSize = 5;
		int totalPage = (totalRows / pageRows) + 1;
		int currentPageBlock = 1;
		int firstPage;
		int lastPage;
		
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
