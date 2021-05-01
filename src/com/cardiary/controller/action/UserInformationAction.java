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
			// 클릭한 유저의 id를 담아와서 받음
			String userid = request.getParameter("id");
			
			if (loginUser.getId().equals(userid)) {
				url = "CardiaryServlet?command=mypage";
			} else {
				// 담아온 유저의 id로 유저 정보 조회
				MemberDAO mDao = MemberDAO.getInstance();
				MemberVO userInfo = mDao.memberInfo(userid);
				
				// 유저 정보 저장
				request.setAttribute("userInfo", userInfo);
				
				// 유저의 id로 해당 유저의 카드 조회
				CardDAO cDao = CardDAO.getInstance();
				ArrayList<CardviewVO> cardList = cDao.listCardByID(userid);
				
				// 카드 저장
				request.setAttribute("cardList", cardList);
				
				// 친구 정보 조회
				FriendDAO fDao = FriendDAO.getInstance();
				FriendVO friend = fDao.getFriend(loginUser.getId(), userid);
				
				// 친구 정보 저장
				request.setAttribute("friend", friend);
			}
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
