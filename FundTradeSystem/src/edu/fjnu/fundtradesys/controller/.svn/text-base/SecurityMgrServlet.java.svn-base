package edu.fjnu.fundtradesys.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.service.OperatorService;
import edu.fjnu.fundtradesys.service.OperatorServiceImpl;

public class SecurityMgrServlet extends HttpServlet {

	public SecurityMgrServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String act = request.getParameter("act");

		if ("toLogin".equals(act)) {
			request.getRequestDispatcher("jsps/login.jsp").forward(request,response);
		} else if ("login".equals(act)) {

			Integer operCode = Integer.parseInt(request
					.getParameter("operCode"));
			String operPwd = request.getParameter("operPwd");

			OperatorService operatorService = new OperatorServiceImpl();
			try {
				Operator operator = operatorService.checkOperator(operCode,operPwd);
				request.getSession().setAttribute("loginedOperator", operator);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());
				request.getRequestDispatcher("jsps/login.jsp").forward(request,
						response);
				return;
			}

			response.sendRedirect("securityMgr?act=gotoMain");
		} else if ("gotoMain".equals(act)) {
			request.getRequestDispatcher("jsps/home.jsp").forward(request,
					response);
		} else if ("logout".equals(act)) {
			request.getSession().removeAttribute("loginedOperator");
			request.getSession().invalidate();
			response.sendRedirect("securityMgr?act=toLogin");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
