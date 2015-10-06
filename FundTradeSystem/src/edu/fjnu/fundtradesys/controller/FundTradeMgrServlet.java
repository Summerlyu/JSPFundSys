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
import edu.fjnu.fundtradesys.bo.FundAccountInfoBO;
import edu.fjnu.fundtradesys.bo.FundRedemptionInfoBO;
import edu.fjnu.fundtradesys.domain.FinancialAccount;
import edu.fjnu.fundtradesys.domain.Fund;
import edu.fjnu.fundtradesys.domain.FundHolding;
import edu.fjnu.fundtradesys.domain.FundTransInfo;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.service.FinancialAccountService;
import edu.fjnu.fundtradesys.service.FinancialAccountServiceImpl;
import edu.fjnu.fundtradesys.service.FundAccountService;
import edu.fjnu.fundtradesys.service.FundAccountServiceImpl;
import edu.fjnu.fundtradesys.service.FundHoldingService;
import edu.fjnu.fundtradesys.service.FundHoldingServiceImpl;
import edu.fjnu.fundtradesys.service.FundService;
import edu.fjnu.fundtradesys.service.FundServiceImpl;
import edu.fjnu.fundtradesys.service.FundTransService;
import edu.fjnu.fundtradesys.service.FundTransServiceImpl;
import edu.fjnu.fundtradesys.utils.Page;

public class FundTradeMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FundTradeMgrServlet() {
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
		if ("buyFund".equals(act)) {
			FundService fundService = new FundServiceImpl();

			request.setAttribute("fundList", fundService.loadall());
			request.getRequestDispatcher("jsps/fund_trade/buyfund.jsp")
					.forward(request, response);
		} else if ("buy".equals(act)) {
			FinancialAccount financialaccount = new FinancialAccount();
			financialaccount.setAcc_no(Integer.parseInt(request
					.getParameter("acc_no")));
			financialaccount.setAcc_pwd(request.getParameter("acc_pwd"));

			try {
				FinancialAccountService finanaccService = new FinancialAccountServiceImpl();
				/**
				 * 判断账号密码是否正确
				 */
				finanaccService.checkAccount(financialaccount);
				/**
				 * 判断资金账户状态是否正常
				 */
				FinancialAccount f = finanaccService
						.getFinancialAccountByNo(Integer.parseInt(request
								.getParameter("acc_no")));
				finanaccService.checkAccountStatus(f);
				/**
				 * 对资金账户和资金账户交易记录做修改
				 */
				float quantity = Float.parseFloat(request
						.getParameter("quantity"));
				float price = Float.parseFloat(request.getParameter("price"));
				float money = quantity * price;

				FundAccountService fundaccountService = new FundAccountServiceImpl();
				fundaccountService.buyFund(financialaccount, money, operator);
				/**
				 * 对持仓信息表做修改
				 */
				FundHolding fundholding = new FundHolding();
				fundholding.setAccno(Integer.parseInt(request
						.getParameter("acc_no")));
				fundholding.setAmount(quantity);
				fundholding.setFundno(Integer.parseInt(request
						.getParameter("fundno")));
				FundHoldingService fundholdingService = new FundHoldingServiceImpl();
				fundholdingService.add(fundholding);
				/**
				 * 对基金交易记录表做修改
				 */
				FundTransInfo fundtransinfo = new FundTransInfo();
				fundtransinfo.setTrans_type("b");
				fundtransinfo.setAcc_no(Integer.parseInt(request
						.getParameter("acc_no")));
				fundtransinfo.setFund_no(Integer.parseInt(request
						.getParameter("fundno")));
				fundtransinfo.setAmount(quantity);
				fundtransinfo.setPrice(price);
				fundtransinfo.setTrans_date(new Date(new java.util.Date()
						.getTime()));
				FundTransService fundtransinfoService = new FundTransServiceImpl();
				fundtransinfoService.add(fundtransinfo);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());

				FundService fundService = new FundServiceImpl();
				request.setAttribute("fundList", fundService.loadall());
				request.getRequestDispatcher("jsps/fund_trade/buyfund.jsp")
						.forward(request, response);
				return;
			}
			response.sendRedirect("acctransinfoMgr?act=loadPagedAccountTransInfo");
		} else if ("loadPagedTradeRecord".equals(act)) {

			FundTransInfo fundtransinfo = new FundTransInfo();

			if (StringUtils.isNotEmpty(request.getParameter("qryaccno")))
				fundtransinfo.setAcc_no(Integer.parseInt((request
						.getParameter("qryaccno"))));
			if (StringUtils.isNotEmpty(request.getParameter("qryfundno")))
				fundtransinfo.setFund_no(Integer.parseInt((request
						.getParameter("qryfundno"))));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			FundTransService fundtransService = new FundTransServiceImpl();
			request.setAttribute("page",
					fundtransService.loadPagedTradeRecord(page, fundtransinfo));
			;

			request.getRequestDispatcher("jsps/fund_trade/list_traderecord.jsp")
					.forward(request, response);

		} else if ("loadPagedFundAccountInfo".equals(act)) {

			FundAccountInfoBO fundaccountinfoBO = new FundAccountInfoBO();

			if (StringUtils.isNotEmpty(request.getParameter("qryaccno")))
				fundaccountinfoBO.setAcc_no(Integer.parseInt((request
						.getParameter("qryaccno"))));
			if (StringUtils.isNotEmpty(request.getParameter("qryclientname")))
				fundaccountinfoBO.setClient_name((request
						.getParameter("qryclientname")));
			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			FundAccountService fundaccountService = new FundAccountServiceImpl();
			request.setAttribute("page", fundaccountService
					.loadPagedFundAccountInfo(page, fundaccountinfoBO));

			request.getRequestDispatcher("jsps/fund_trade/list_fundacc.jsp")
					.forward(request, response);
		} else if ("saleFund".equals(act)) {
			request.getRequestDispatcher("jsps/fund_trade/salefund.jsp")
					.forward(request, response);
		} else if ("validateAccount".equals(act)) {

			String pass = request.getParameter("pass");
			int acc_no = Integer.parseInt(request.getParameter("acc_no"));
			FinancialAccount f = new FinancialAccount();
			f.setAcc_no(acc_no);
			f.setAcc_pwd(pass);
			FinancialAccountService financialAccountService = new FinancialAccountServiceImpl();
			try {
				/**
				 * 判断账号密码是否正确
				 */
				financialAccountService.checkAccount(f);
				/**
				 * 判断账号状态是否正确
				 */
				financialAccountService
						.checkAccountStatus(financialAccountService
								.getFinancialAccountByNo(acc_no));

				FundRedemptionInfoBO fundredemptionBO = new FundRedemptionInfoBO();
				fundredemptionBO.setAcc_no(acc_no);
				Page page = new Page();
				if (StringUtils.isNotEmpty(request.getParameter("pageno")))
					page.setPageNo(Integer.parseInt(request
							.getParameter("pageno")));
				FundAccountService fundaccountService = new FundAccountServiceImpl();
				request.setAttribute("page", fundaccountService
						.loadPagedfundRedemptionInfoBO(page, fundredemptionBO));
				request.setAttribute("financialaccount",
						financialAccountService.getAccountByNo(acc_no));
				request.getRequestDispatcher(
						"jsps/fund_trade/list_redemption.jsp").forward(request,
						response);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());

				request.getRequestDispatcher("jsps/fund_trade/salefund.jsp")
						.forward(request, response);
				return;
			}
		} else if ("fundRedemption".equals(act)) {

			float amount = Float.parseFloat(request.getParameter("amount"));
			int hid = Integer.parseInt(request.getParameter("hid"));

			// 得到持仓记录
			FundHoldingService fundHoldingService = new FundHoldingServiceImpl();
			FundHolding fundHolding = fundHoldingService
					.getFundHoldingByNo(hid);
			try {
				/**
				 * 判断基金是否是封闭式基金
				 */
				FundService fundService = new FundServiceImpl();
				fundService.checkFundStatus(fundHolding.getFundno());

				FinancialAccountService financialAccountService = new FinancialAccountServiceImpl();
				/**
				 * 对资金账户和资金账户交易记录做修改
				 */
				FinancialAccount financialaccount = new FinancialAccount();
				financialaccount = financialAccountService
						.getFinancialAccountByNo(fundHolding.getAccno());
				FundAccountService fundaccountService = new FundAccountServiceImpl();
				// 得到基金产品信息

				Fund fund = fundService.getFundByNo(fundHolding.getFundno());

				float money = amount * (fund.getFundPrice()) * 0.99f;
				fundaccountService.saleFund(financialaccount, money, operator);
				/**
				 * 对持仓信息表做修改
				 */
				FundHolding fundHoldingPO = new FundHolding();
				fundHoldingPO.setHid(hid);
				fundHoldingPO.setAccno(fundHolding.getAccno());
				fundHoldingPO.setAmount(fundHolding.getAmount() - amount);
				fundHoldingPO.setFundno(fundHolding.getFundno());
				fundHoldingService.updateFundHolding(fundHoldingPO);
				/**
				 * 对基金交易记录表做修改
				 */
				FundTransInfo fundtransinfo = new FundTransInfo();
				fundtransinfo.setTrans_type("r");
				fundtransinfo.setAcc_no(fundHolding.getAccno());
				fundtransinfo.setFund_no(fundHolding.getFundno());
				fundtransinfo.setAmount(amount);
				fundtransinfo.setPrice(fund.getFundPrice());
				fundtransinfo.setTrans_date(new Date(new java.util.Date()
						.getTime()));
				FundTransService fundtransinfoService = new FundTransServiceImpl();
				fundtransinfoService.add(fundtransinfo);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());

				FinancialAccountService financialAccountService = new FinancialAccountServiceImpl();
				FundRedemptionInfoBO fundredemptionBO = new FundRedemptionInfoBO();
				fundredemptionBO.setAcc_no(fundHolding.getAccno());
				Page page = new Page();
				if (StringUtils.isNotEmpty(request.getParameter("pageno")))
					page.setPageNo(Integer.parseInt(request
							.getParameter("pageno")));
				FundAccountService fundaccountService = new FundAccountServiceImpl();
				request.setAttribute("page", fundaccountService
						.loadPagedfundRedemptionInfoBO(page, fundredemptionBO));
				request.setAttribute("financialaccount",
						financialAccountService.getAccountByNo(fundHolding.getAccno()));

				request.getRequestDispatcher(
						"jsps/fund_trade/list_redemption.jsp").forward(request,
						response);
				return;
			}
			response.sendRedirect("acctransinfoMgr?act=loadPagedAccountTransInfo");
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
