package com.cardiary.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.FriendDAO;
import com.cardiary.controller.dao.MemberDAO;
import com.cardiary.controller.dto.MemberVO;

public class SearchMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/searchmember.jsp";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			String searchid = request.getParameter("serach_id");			
			MemberDAO mDao = MemberDAO.getInstance();
			ArrayList<MemberVO> searchMemberList = mDao.searchMember(searchid);
			ArrayList<MemberVO> searchListFriend = new ArrayList<>();
			ArrayList<MemberVO> searchList = new ArrayList<>();
			
			// ���̵� �˻� => ���� ���̵� ���� ���� : search_id
			List<String> search_id = new ArrayList<>();
			for (MemberVO list : searchMemberList) {
				search_id.add(list.getId());
			}
			
			// �˻� ���̵� �� ģ���� ������ ���̵� List �����
			// ���� �߰��� ������ ������ ���� ����
			FriendDAO fDao = FriendDAO.getInstance();
			List<String> addUserList = fDao.friendAddMe(loginUser.getId());
			
			// ���� ģ�� �߰��� ������ ���̵� ����Ʈ
			List<String> addFriendList = fDao.friendAddByMe(loginUser.getId());
		
			// ģ�� ������ ���̵� List : addUserList
			addUserList.retainAll(addFriendList);
			
			// �˻� ���̵� �� ģ���� ������ ���̵� List
			search_id.retainAll(addUserList);
			
			// �˻� ���̵� �� ģ���� ������ ���̵� List�� ���̵� �־ �� ������ ������ ���
			// �Ͽ� SearchListFriend�� ����
			for (int i = 0; i < search_id.size(); i++) {
				MemberVO member = mDao.memberInfo(search_id.get(i));
				searchListFriend.add(member); // �˻� �� ���� �� ģ���� ���� list
			}
			
			request.setAttribute("searchListFriend", searchListFriend);
			
			// �˻� ���̵� �� ģ���� �ƴ� ������ ���̵� List �����
			// ���̵� �˻� => ���� ���̵� ���� ���� : search_id
			search_id = new ArrayList<>();
			for (MemberVO list : searchMemberList) {
				search_id.add(list.getId());
			}
			
			// ���� �߰��� �������� ID List
			addUserList = fDao.friendAddMe(loginUser.getId());
			
			// ���� �߰��� �������� ID List
			addFriendList = fDao.friendAddByMe(loginUser.getId());
			
			// ģ�� ������ ���̵� List : addUserList
			addUserList.retainAll(addFriendList);
			
			// �˻� ���̵� �� ģ���� �ƴ� ������ ���̵� List
			search_id.removeAll(addUserList);
			search_id.remove(loginUser.getId());
			
			// �˻� ���̵� �� ģ���� �ƴ� ������ ���̵� List�� ���̵� �־ �� ������ ������ ���
			// �Ͽ� SearchList�� ����
			for (int i = 0; i < search_id.size(); i++) {
				MemberVO member = mDao.memberInfo(search_id.get(i));
				searchList.add(member); // �˻��� ���� �� ģ���� �ƴ� ������ ���� list
			}
			
			request.setAttribute("searchList", searchList);
			
			request.setAttribute("searchid", searchid);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
