package com.cardiary.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardiary.controller.dao.MemberDAO;
import com.cardiary.controller.dto.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ChangeInformationction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "CardiaryServlet?command=mypage";
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		MemberDAO mDao = MemberDAO.getInstance();
		
		if (loginUser == null) {
			url = "CardiaryServlet?command=login";
		} else {
			// 이미지 업로드 설정
			int sizeLimit = 5 * 1024 * 1024; // 이미지 저장 최대 용량 : 5MB
			String savePath = "images/info"; // 이미지를 저장할 위치
			ServletContext context = session.getServletContext(); // 프로젝트의 저장 위치
			String uploadPath = context.getRealPath(savePath); // 프로젝트의 저장위치 안에 저장할 위치를 설정
			
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					sizeLimit,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			
			// DB 저장
			String image = multi.getFilesystemName("image");
			String info = multi.getParameter("info");
			
			// 새로 넣은 이미지가 없을 때 기존의 이미지인 preimage 사용
			if (image == null) {
				image = multi.getParameter("preimage");
			} else {
				image = multi.getFilesystemName("image");
			}
			
			mDao.updateMember(image, info, loginUser.getId());
		}
		
		loginUser = mDao.memberInfo(loginUser.getId());
		
		session.setAttribute("loginUser", loginUser);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
