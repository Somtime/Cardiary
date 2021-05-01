package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dto.MemberVO;

public class NewCardFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "card/newcardform.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			request.setAttribute("loginUser", loginUser);
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
