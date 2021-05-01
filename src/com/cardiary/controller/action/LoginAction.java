package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.MemberDAO;
import com.cardiary.controller.dto.MemberVO;



public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "CardiaryServlet?command=index";
		String userid = request.getParameter("id");
		String password = request.getParameter("pwd");
		String message = null;
		
		// id�� pwd�� Ȯ���Ͽ� �� ����� logincheck�� ���� �α���:1/��й�ȣ����:0/���̵����:-1
		MemberDAO mDao = MemberDAO.getInstance();
		int logincheck = mDao.loginCheck(userid, password);
		
		// ���� ������ ���� MemberVO Ÿ���� ���� ����
		MemberVO member = new MemberVO();
		
		// logincheck ����� ���� �޼��� ��� �� ���� ���� ����
		if (logincheck == 1) {
			member = mDao.memberInfo(userid);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			url = "CardiaryServlet?command=card_list";
		} else if (logincheck == 0) {
			message = "��й�ȣ�� Ȯ�����ּ���.";
		} else {
			message = "�������� �ʴ� �����Դϴ�.";
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
