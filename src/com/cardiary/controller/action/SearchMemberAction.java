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
			
			// 아이디 검색 => 유사 아이디 전부 저장 : search_id
			List<String> search_id = new ArrayList<>();
			for (MemberVO list : searchMemberList) {
				search_id.add(list.getId());
			}
			
			// 검색 아이디 중 친구인 유저의 아이디 List 만들기
			// 나를 추가한 유저의 정보를 담을 변수
			FriendDAO fDao = FriendDAO.getInstance();
			List<String> addUserList = fDao.friendAddMe(loginUser.getId());
			
			// 내가 친구 추가한 유저의 아이디 리스트
			List<String> addFriendList = fDao.friendAddByMe(loginUser.getId());
		
			// 친구 유저의 아이디 List : addUserList
			addUserList.retainAll(addFriendList);
			
			// 검색 아이디 중 친구인 유저의 아이디 List
			search_id.retainAll(addUserList);
			
			// 검색 아이디 중 친구인 유저의 아이디 List의 아이디를 넣어서 각 유저의 정보를 출력
			// 하여 SearchListFriend에 넣음
			for (int i = 0; i < search_id.size(); i++) {
				MemberVO member = mDao.memberInfo(search_id.get(i));
				searchListFriend.add(member); // 검색 한 유저 중 친구의 정보 list
			}
			
			request.setAttribute("searchListFriend", searchListFriend);
			
			// 검색 아이디 중 친구가 아닌 유저의 아이디 List 만들기
			// 아이디 검색 => 유사 아이디 전부 저장 : search_id
			search_id = new ArrayList<>();
			for (MemberVO list : searchMemberList) {
				search_id.add(list.getId());
			}
			
			// 나를 추가한 유저들의 ID List
			addUserList = fDao.friendAddMe(loginUser.getId());
			
			// 내가 추가한 유저들의 ID List
			addFriendList = fDao.friendAddByMe(loginUser.getId());
			
			// 친구 유저의 아이디 List : addUserList
			addUserList.retainAll(addFriendList);
			
			// 검색 아이디 중 친구가 아닌 유저의 아이디 List
			search_id.removeAll(addUserList);
			search_id.remove(loginUser.getId());
			
			// 검색 아이디 중 친구가 아닌 유저의 아이디 List의 아이디를 넣어서 각 유저의 정보를 출력
			// 하여 SearchList에 넣음
			for (int i = 0; i < search_id.size(); i++) {
				MemberVO member = mDao.memberInfo(search_id.get(i));
				searchList.add(member); // 검색한 유저 중 친구가 아닌 유저의 정보 list
			}
			
			request.setAttribute("searchList", searchList);
			
			request.setAttribute("searchid", searchid);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
