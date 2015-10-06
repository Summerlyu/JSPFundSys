package edu.fjnu.fundtradesys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.fundtradesys.bo.FinancialAccountBO;
import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.service.ClientService;
import edu.fjnu.fundtradesys.service.ClientServiceImpl;
import edu.fjnu.fundtradesys.service.FinancialAccountService;
import edu.fjnu.fundtradesys.service.FinancialAccountServiceImpl;
import edu.fjnu.fundtradesys.utils.Page;

public class FinancialAccountMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FinancialAccountMgrServlet() {
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
			request.getRequestDispatcher(
					"jsps/financial_account/input_financial_account.jsp")
					.forward(request, response);
		} else if ("create".equals(act)) {
			FinancialAccount financialaccount = new FinancialAccount();
			ClientService clientService = new ClientServiceImpl();
			Client client = clientService.getClientByIdNo(request
					.getParameter("idcardno"));

			financialaccount.setAcc_pwd(request.getParameter("accpwd"));
			financialaccount.setAcc_amount(Float.parseFloat(request
					.getParameter("accamount")));
			financialaccount.setAcc_status(request.getParameter("fund_status"));
			financialaccount.setClient_no(client.getClientNo());
			financialaccount.setCreate_date(new Date(new java.util.Date()
					.getTime()));
			financialaccount.setOper_code(operator.getOperCode());

			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			financialaccountService.createFinancialAccount(financialaccount,
					operator);

			response.sendRedirect("financial_accountMgr?act=loadPagedFinancialAccounts");
		} else if ("loadPagedFinancialAccounts".equals(act)) {

			FinancialAccountBO financialaccountBO = new FinancialAccountBO();

			if (StringUtils.isNotEmpty(request.getParameter("qryaccno")))
				financialaccountBO.setAcc_no(Integer.parseInt((request
						.getParameter("qryaccno"))));

			if (StringUtils.isNotEmpty(request.getParameter("qryaccstatus")))
				financialaccountBO.setStatus((request
						.getParameter("qryaccstatus")));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			request.setAttribute("page", financialaccountService
					.loadPagedFinancialAccounts(page, financialaccountBO));

			request.getRequestDispatcher(
					"jsps/financial_account/list_financial_account.jsp")
					.forward(request, response);
		} else if ("forAdd".equals(act)) {
			request.getRequestDispatcher(
					"jsps/financial_account/add_account_money.jsp").forward(
					request, response);
		} else if ("add".equals(act)) {
			Integer acc_no = Integer.parseInt(request.getParameter("accno"));
			String acc_pwd = request.getParameter("accpwd");
			Float money = Float.parseFloat(request.getParameter("addmoney"));
			FinancialAccount f = new FinancialAccount();
			f.setAcc_no(acc_no);
			f.setAcc_pwd(acc_pwd);

			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			try {
				financialaccountService.checkAccountStatus(f);
				financialaccountService.addFund(f, money, operator);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());
				request.getRequestDispatcher(
						"jsps/financial_account/add_account_money.jsp")
						.forward(request, response);
				return;
			}

			response.sendRedirect("financial_accountMgr?act=loadPagedFinancialAccounts");
		} else if ("forCut".equals(act)) {
			request.getRequestDispatcher(
					"jsps/financial_account/cut_account_money.jsp").forward(
					request, response);
		} else if ("cut".equals(act)) {
			Integer acc_no = Integer.parseInt(request.getParameter("accno"));
			String acc_pwd = request.getParameter("accpwd");
			Float money = Float.parseFloat(request.getParameter("cutmoney"));
			FinancialAccount f = new FinancialAccount();
			f.setAcc_no(acc_no);
			f.setAcc_pwd(acc_pwd);

			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			try {
				financialaccountService.checkAccountStatus(f);
				financialaccountService.getFund(f, money, operator);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());
				request.getRequestDispatcher(
						"jsps/financial_account/cut_account_money.jsp")
						.forward(request, response);
				return;
			}

			response.sendRedirect("financial_accountMgr?act=loadPagedFinancialAccounts");
		} else if ("loadPagedFundAccountsStatus".equals(act)) {

			FinancialAccountBO financialaccountBO = new FinancialAccountBO();

			if (StringUtils.isNotEmpty(request.getParameter("qryaccno")))
				financialaccountBO.setAcc_no(Integer.parseInt((request
						.getParameter("qryaccno"))));

			if (StringUtils.isNotEmpty(request.getParameter("qryaccstatus")))
				financialaccountBO.setStatus((request
						.getParameter("qryaccstatus")));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			request.setAttribute("page", financialaccountService
					.loadPagedFinancialAccounts(page, financialaccountBO));

			request.getRequestDispatcher(
					"jsps/financial_account/list_financial_account_status.jsp")
					.forward(request, response);
		} else if ("forUpdate".equals(act)) {

			Integer acc_no = Integer.parseInt(request.getParameter("accno"));
			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			request.setAttribute("fundaccountBO",
					financialaccountService.getAccountByNo(acc_no));
			request.getRequestDispatcher(
					"jsps/financial_account/update_financial_account.jsp")
					.forward(request, response);

		} else if ("update".equals(act)) {

			int accountNo = Integer.parseInt(request.getParameter("accno"));

			FinancialAccountService financialaccountService = new FinancialAccountServiceImpl();
			FinancialAccountBO financialaccountBo = financialaccountService
					.getAccountByNo(accountNo);

			financialaccountBo.setStatus(request.getParameter("accstatus"));
			financialaccountBo.setOpercode(operator.getOperCode());

			financialaccountService
					.updateFinancialAccountBO(financialaccountBo);

			response.sendRedirect("financial_accountMgr?act=loadPagedFundAccountsStatus");

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
