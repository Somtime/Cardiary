package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.CardDAO;
import com.cardiary.controller.dto.CardVO;
import com.cardiary.controller.dto.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ChangeCardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "CardiaryServlet?command=mypage";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			// 이미지 업로드
			int sizeLimit = 5 * 1024 * 1024;
			String savePath = "images/card";
			ServletContext context = session.getServletContext();
			String uploadPath = context.getRealPath(savePath);
			
			// 이미지 등을 담을수 있는 객체 생성
			MultipartRequest multi = new MultipartRequest (
					request,
					uploadPath,
					sizeLimit,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			
			// updateCard 메소드에 넣은 매개변수 생성
			CardDAO cDao = CardDAO.getInstance();
			CardVO card = new CardVO();
			
			card.setCseq(Integer.parseInt(multi.getParameter("cseq")));
			card.setId(loginUser.getId());
			if (multi.getFilesystemName("image") == null) {
				// 수정하는 이미지가 없을 때, 기존의 이미지 사용
				card.setImage(multi.getParameter("preimage"));
			} else {
				card.setImage(multi.getFilesystemName("image"));
			}
			card.setContent(multi.getParameter("content"));
			
			cDao.updateCard(card);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
