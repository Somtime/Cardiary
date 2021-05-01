package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.FriendDAO;
import com.cardiary.controller.dto.MemberVO;

public class DeleteFriendAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "CardiaryServlet?command=user_info";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String friendid = request.getParameter("id");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			FriendDAO fDao = FriendDAO.getInstance();
			fDao.friendDelete(loginUser, friendid);
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
