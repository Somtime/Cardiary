package com.cardiary.controller.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.FriendDAO;
import com.cardiary.controller.dao.MemberDAO;
import com.cardiary.controller.dto.MemberVO;

public class FriendListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/friendlist.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// 나를 추가한 유저의 정보를 담을 변수
		ArrayList<MemberVO> userList = new ArrayList<>(); 
		// 나와 친구인(상대도 나도 추가한) 유저의 정보를 담을 변수
		ArrayList<MemberVO> friendList = new ArrayList<>();
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			FriendDAO fDao = FriendDAO.getInstance();
			MemberVO user = new MemberVO();
			MemberVO friend = new MemberVO();
			MemberDAO mDao = MemberDAO.getInstance();
			
			// 나를 친구 추가한 유저의 아이디 리스트
			List<String> addUserList = fDao.friendAddMe(loginUser.getId());
			
			// 내가 친구 추가한 유저의 아이디 리스트
			List<String> addFriendList = fDao.friendAddByMe(loginUser.getId());
			
			// 요청한 유저 list - 내가 추가한 유저 list = 나에게 친구 요청한 유저 list (addUserList)
			addUserList.removeAll(addFriendList);
			
			for (String id : addUserList) {
				// 나에게 친구 요청한 유저ID list의 각 유저 정보를 추출해서 userList에 삽입
				user = mDao.memberInfo(id);
				
				userList.add(user); // 친구 요청 유저 ID list
			}
			
			// 나를 친구 추가한 유저의 아이디 리스트
			addUserList = fDao.friendAddMe(loginUser.getId());
			
			// 요청한 유저 list ∩ 내가 추가한 유저 list = 나와 서로 친구인 유저 list (addUserList)
			addUserList.retainAll(addFriendList);

			
			for (String id : addUserList) {
				// 나와 서로 친구인 유저ID list의 각 유저 정보를 추출하여 friendList에 삽입
				friend = mDao.memberInfo(id);
				
				friendList.add(friend); // 친구 유저 ID list
			}

			request.setAttribute("friendRequestList", userList);
			request.setAttribute("friendList", friendList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
