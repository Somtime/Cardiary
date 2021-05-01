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
		
		// Member의 정보를 입력 받을 변수 생성
		MemberVO member = new MemberVO();
		
		// Member의 정보를 입력 파라메타를 통해 입력
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		
		// 위에서 입력한 Member의 정보를 바탕으로 회원가입( insertMember() )
		MemberDAO mDao = MemberDAO.getInstance();
		mDao.insertMember(member);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
