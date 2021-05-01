package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardiary.controller.dao.MemberDAO;



public class IdCheckFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/idcheck.jsp";
		String userid = request.getParameter("id");
		String message = "";
		
		MemberDAO mDao = MemberDAO.getInstance();
		int idcheck = mDao.idCheck(userid);
		
		if (idcheck == 1) {
			message = "이미 존재하는 아이디입니다.";
		} else {
			message = "사용 가능한 아이디입니다.";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("userid", userid);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
