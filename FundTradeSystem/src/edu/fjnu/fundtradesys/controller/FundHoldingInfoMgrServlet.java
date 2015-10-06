package edu.fjnu.fundtradesys.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.fundtradesys.domain.FundHolding;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.service.FundHoldingService;
import edu.fjnu.fundtradesys.service.FundHoldingServiceImpl;
import edu.fjnu.fundtradesys.utils.Page;

public class FundHoldingInfoMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FundHoldingInfoMgrServlet() {
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

		if ("loadPagedFundHolding".equals(act)) {

			FundHolding fundholding = new FundHolding();

			if (StringUtils.isNotEmpty(request.getParameter("qryaccno")))
				fundholding.setAccno(Integer.parseInt((request
						.getParameter("qryaccno"))));

			if (StringUtils.isNotEmpty(request.getParameter("qryfundno")))
				fundholding.setFundno(Integer.parseInt((request
						.getParameter("qryfundno"))));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			FundHoldingService fundholdingService = new FundHoldingServiceImpl();
			request.setAttribute("page",
					fundholdingService.loadPagedFundHolding(page, fundholding));

			request.getRequestDispatcher("jsps/fund_trade/fundholdinginfo.jsp")
					.forward(request, response);
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
