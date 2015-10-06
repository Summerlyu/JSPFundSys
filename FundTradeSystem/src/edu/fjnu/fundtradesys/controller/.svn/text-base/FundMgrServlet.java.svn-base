package edu.fjnu.fundtradesys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.fundtradesys.domain.Fund;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.service.FundService;
import edu.fjnu.fundtradesys.service.FundServiceImpl;
import edu.fjnu.fundtradesys.utils.Page;

public class FundMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FundMgrServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");

		Operator operator = (Operator) request.getSession().getAttribute(
				"loginedOperator");
		if ("input".equals(act)) {
			request.getRequestDispatcher("jsps/fund/input_fund.jsp").forward(
					request, response);
		} else if ("create".equals(act)) {
			System.out.println(request.getParameter("fundname"));
			Fund f = new Fund();
			f.setFundName(request.getParameter("fundname"));
			f.setFundPrice(Float.parseFloat((request.getParameter("fundprice"))));
			f.setFundDesc(request.getParameter("funddesc"));
			f.setFundStatus(request.getParameter("fund_status"));
			f.setCreateDate(new Date(new java.util.Date().getTime()));
			f.setOperCode(operator.getOperCode());
			FundService fundService = new FundServiceImpl();
			fundService.createFund(f);

			response.sendRedirect("fundMgr?act=loadPagedFunds");
		} else if ("loadPagedFunds".equals(act)) {

			Fund fundHelper = new Fund();

			if (StringUtils.isNotEmpty(request.getParameter("qryfundname")))
				fundHelper.setFundName(request.getParameter("qryfundname"));

			if (StringUtils.isNotEmpty(request.getParameter("qryfundstatus")))
				fundHelper.setFundStatus(request.getParameter("qryfundstatus"));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			FundService fundService = new FundServiceImpl();
			request.setAttribute("page",
					fundService.loadPagedFunds(page, fundHelper));

			request.getRequestDispatcher("jsps/fund/list_fund.jsp").forward(
					request, response);
		} else if ("remove".equals(act)) {
			int fundNo = Integer.parseInt(request.getParameter("fundno"));

			FundService fundService = new FundServiceImpl();
			try {
				fundService.removeFund(fundNo);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());
				
				Fund fundHelper = new Fund();
				Page page = new Page();
				if (StringUtils.isNotEmpty(request.getParameter("pageno")))
					page.setPageNo(Integer.parseInt(request
							.getParameter("pageno")));
				request.setAttribute("page",
						fundService.loadPagedFunds(page, fundHelper));

				request.getRequestDispatcher("jsps/fund/list_fund.jsp")
						.forward(request, response);
				return;
			}
			response.sendRedirect("fundMgr?act=loadPagedFunds");
		} else if ("forUpdate".equals(act)) {
			int fundNo = Integer.parseInt(request.getParameter("fundno"));
			FundService fundService = new FundServiceImpl();
			request.setAttribute("fund", fundService.getFundByNo(fundNo));
			request.getRequestDispatcher("jsps/fund/update_fund.jsp").forward(
					request, response);
		} else if ("update".equals(act)) {
			int fundNo = Integer.parseInt(request.getParameter("fundno"));

			FundService fundService = new FundServiceImpl();
			Fund f = fundService.getFundByNo(fundNo);

			f.setFundName(request.getParameter("fundname"));
			f.setFundPrice(Float.parseFloat((request.getParameter("fundprice"))));
			f.setFundDesc(request.getParameter("funddesc"));
			f.setFundStatus(request.getParameter("fund_status"));
			f.setOperCode(operator.getOperCode());

			fundService.update(f);

			response.sendRedirect("fundMgr?act=loadPagedFunds");
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
