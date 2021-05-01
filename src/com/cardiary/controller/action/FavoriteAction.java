package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.FavoriteDAO;
import com.cardiary.controller.dto.MemberVO;

public class FavoriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		int cseq = Integer.parseInt(request.getParameter("cseq"));
		String userid = loginUser.getId();
		String url = "CardiaryServlet?command=card_list";
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			FavoriteDAO fDao = FavoriteDAO.getInstance();
			// 1이면 y/ 0이면 데이터없음/ -1이면 n
			int favor = fDao.checkFavorite(cseq, userid);
			
			if (favor == 0) {
				fDao.insertFavorite(cseq, userid);
			} else if (favor == 1) {
				fDao.updateFavorite(cseq, userid, "n");
			} else {
				fDao.updateFavorite(cseq, userid, "y");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
