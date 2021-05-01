package com.cardiary.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.CardDAO;
import com.cardiary.controller.dto.CardviewVO;
import com.cardiary.controller.dto.MemberVO;

public class MypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/mypage.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			CardDAO cDao = CardDAO.getInstance();
			ArrayList<CardviewVO> cardList = cDao.listCardByID(loginUser.getId());
			request.setAttribute("cardList", cardList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
