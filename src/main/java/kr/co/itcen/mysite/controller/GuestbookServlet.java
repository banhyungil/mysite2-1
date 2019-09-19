package kr.co.itcen.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.action.guestbook.GuestbookActionFactory;
import kr.co.itcen.web.mvc.ActionFactory;
import kr.co.itcen.web.mvc.Actionable;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = request.getParameter("a");

		ActionFactory actionFactory = new GuestbookActionFactory();
		Actionable action = actionFactory.getAction(actionName);
		action.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
