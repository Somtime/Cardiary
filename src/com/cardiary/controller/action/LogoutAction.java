package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "CardiaryServlet?command=login";
		HttpSession session = request.getSession();
		
		if (session != null) {
			session.invalidate();
		}
		
		request.getRequestDispatcher(sql).forward(request, response);
	}

}
