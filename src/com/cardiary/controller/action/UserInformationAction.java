package com.cardiary.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.CardDAO;
import com.cardiary.controller.dao.FriendDAO;
import com.cardiary.controller.dao.MemberDAO;
import com.cardiary.controller.dto.CardviewVO;
import com.cardiary.controller.dto.FriendVO;
import com.cardiary.controller.dto.MemberVO;

public class UserInformationAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/userpage.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			// Ŭ���� ������ id�� ��ƿͼ� ����
			String userid = request.getParameter("id");
			
			if (loginUser.getId().equals(userid)) {
				url = "CardiaryServlet?command=mypage";
			} else {
				// ��ƿ� ������ id�� ���� ���� ��ȸ
				MemberDAO mDao = MemberDAO.getInstance();
				MemberVO userInfo = mDao.memberInfo(userid);
				
				// ���� ���� ����
				request.setAttribute("userInfo", userInfo);
				
				// ������ id�� �ش� ������ ī�� ��ȸ
				CardDAO cDao = CardDAO.getInstance();
				ArrayList<CardviewVO> cardList = cDao.listCardByID(userid);
				
				// ī�� ����
				request.setAttribute("cardList", cardList);
				
				// ģ�� ���� ��ȸ
				FriendDAO fDao = FriendDAO.getInstance();
				FriendVO friend = fDao.getFriend(loginUser.getId(), userid);
				
				// ģ�� ���� ����
				request.setAttribute("friend", friend);
			}
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
