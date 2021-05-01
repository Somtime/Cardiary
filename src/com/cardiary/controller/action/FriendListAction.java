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
		
		// ���� �߰��� ������ ������ ���� ����
		ArrayList<MemberVO> userList = new ArrayList<>(); 
		// ���� ģ����(��뵵 ���� �߰���) ������ ������ ���� ����
		ArrayList<MemberVO> friendList = new ArrayList<>();
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			FriendDAO fDao = FriendDAO.getInstance();
			MemberVO user = new MemberVO();
			MemberVO friend = new MemberVO();
			MemberDAO mDao = MemberDAO.getInstance();
			
			// ���� ģ�� �߰��� ������ ���̵� ����Ʈ
			List<String> addUserList = fDao.friendAddMe(loginUser.getId());
			
			// ���� ģ�� �߰��� ������ ���̵� ����Ʈ
			List<String> addFriendList = fDao.friendAddByMe(loginUser.getId());
			
			// ��û�� ���� list - ���� �߰��� ���� list = ������ ģ�� ��û�� ���� list (addUserList)
			addUserList.removeAll(addFriendList);
			
			for (String id : addUserList) {
				// ������ ģ�� ��û�� ����ID list�� �� ���� ������ �����ؼ� userList�� ����
				user = mDao.memberInfo(id);
				
				userList.add(user); // ģ�� ��û ���� ID list
			}
			
			// ���� ģ�� �߰��� ������ ���̵� ����Ʈ
			addUserList = fDao.friendAddMe(loginUser.getId());
			
			// ��û�� ���� list �� ���� �߰��� ���� list = ���� ���� ģ���� ���� list (addUserList)
			addUserList.retainAll(addFriendList);

			
			for (String id : addUserList) {
				// ���� ���� ģ���� ����ID list�� �� ���� ������ �����Ͽ� friendList�� ����
				friend = mDao.memberInfo(id);
				
				friendList.add(friend); // ģ�� ���� ID list
			}

			request.setAttribute("friendRequestList", userList);
			request.setAttribute("friendList", friendList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
