package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.ReplyDAO;
import com.cardiary.controller.dto.MemberVO;
import com.cardiary.controller.dto.ReplyVO;

public class InsertReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "CardiaryServlet?command=card_list";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			ReplyVO reply = new ReplyVO();
			ReplyDAO rDao = ReplyDAO.getInstance();
			
			String cseq = request.getParameter("cseq");
			String content = request.getParameter("content");
			String user = loginUser.getId();
			
			reply.setCseq(Integer.parseInt(cseq));
			reply.setContent(content);
			reply.setId(user);
			
			rDao.insertReply(reply);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
