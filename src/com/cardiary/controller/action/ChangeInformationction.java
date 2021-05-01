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
			// �̹��� ���ε� ����
			int sizeLimit = 5 * 1024 * 1024; // �̹��� ���� �ִ� �뷮 : 5MB
			String savePath = "images/info"; // �̹����� ������ ��ġ
			ServletContext context = session.getServletContext(); // ������Ʈ�� ���� ��ġ
			String uploadPath = context.getRealPath(savePath); // ������Ʈ�� ������ġ �ȿ� ������ ��ġ�� ����
			
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					sizeLimit,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			
			// DB ����
			String image = multi.getFilesystemName("image");
			String info = multi.getParameter("info");
			
			// ���� ���� �̹����� ���� �� ������ �̹����� preimage ���
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
