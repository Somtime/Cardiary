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
		
		// id와 pwd를 확인하여 그 결과를 logincheck에 담음 로그인:1/비밀번호오류:0/아이디없음:-1
		MemberDAO mDao = MemberDAO.getInstance();
		int logincheck = mDao.loginCheck(userid, password);
		
		// 계정 정보를 담을 MemberVO 타입의 변수 생성
		MemberVO member = new MemberVO();
		
		// logincheck 결과에 따라서 메세지 출력 및 계정 정보 저장
		if (logincheck == 1) {
			member = mDao.memberInfo(userid);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			url = "CardiaryServlet?command=card_list";
		} else if (logincheck == 0) {
			message = "비밀번호를 확인해주세요.";
		} else {
			message = "존재하지 않는 계정입니다.";
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
