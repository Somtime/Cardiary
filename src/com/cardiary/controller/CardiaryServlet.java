package com.cardiary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardiary.controller.action.Action;

/**
 * Servlet implementation class CardiaryServlet
 */
@WebServlet("/CardiaryServlet")
public class CardiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardiaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// command�� �Է¹��� ����
		String command = request.getParameter("command");
		
		// consoleâ�� Ŀ�ǵ� ǥ��
		System.out.println("SnsServlet : " + command);
		
		// ActionFactory�� command ���� �Է�
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);
		
		// Action ����
		if (action != null) {
			action.execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}