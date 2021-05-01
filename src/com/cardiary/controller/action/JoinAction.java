package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardiary.controller.dao.MemberDAO;
import com.cardiary.controller.dto.MemberVO;



public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		
		// Member�� ������ �Է� ���� ���� ����
		MemberVO member = new MemberVO();
		
		// Member�� ������ �Է� �Ķ��Ÿ�� ���� �Է�
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		
		// ������ �Է��� Member�� ������ �������� ȸ������( insertMember() )
		MemberDAO mDao = MemberDAO.getInstance();
		mDao.insertMember(member);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
