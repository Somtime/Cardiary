package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.CardDAO;
import com.cardiary.controller.dto.CardviewVO;
import com.cardiary.controller.dto.MemberVO;

public class ChangeCardFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "card/cardchange.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			int cseq = Integer.parseInt(request.getParameter("cseq"));
			
			CardDAO cDao = CardDAO.getInstance();
			CardviewVO card = cDao.listCardByCseq(cseq, loginUser.getId());
			
			request.setAttribute("card", card);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
