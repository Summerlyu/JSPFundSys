package edu.fjnu.fundtradesys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import edu.fjnu.fundtradesys.bo.CusInfo;
import edu.fjnu.fundtradesys.domain.Client;
import edu.fjnu.fundtradesys.domain.Operator;
import edu.fjnu.fundtradesys.exception.ApplicationException;
import edu.fjnu.fundtradesys.service.ClientService;
import edu.fjnu.fundtradesys.service.ClientServiceImpl;
import edu.fjnu.fundtradesys.utils.Page;

public class ClientMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ClientMgrServlet() {
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
			request.getRequestDispatcher("jsps/client/add_client.jsp").forward(
					request, response);
		} else if ("create".equals(act)) {
			Client client = new Client();
			client.setIdcardNo(request.getParameter("cusid"));
			client.setClientName(request.getParameter("cusname"));
			client.setClientSex(request.getParameter("cusgender"));
			client.setClientPhone(request.getParameter("cusphone"));
			client.setClientEmail(request.getParameter("cusemail"));
			client.setClientAddress(request.getParameter("cusaddr"));
			client.setClientHobby(request.getParameter("hobby"));
			client.setCreateDate(new Date(new java.util.Date().getTime()));
			client.setOperCode(operator.getOperCode());

			ClientService clientService = new ClientServiceImpl();
			clientService.createClient(client);

			response.sendRedirect("clientMgr?act=loadPagedClients");
		} else if ("loadPagedClients".equals(act)) {

			CusInfo cusInfo = new CusInfo();

			if (StringUtils.isNotEmpty(request.getParameter("qryclientname")))
				cusInfo.setClientName(request.getParameter("qryclientname"));

			if (StringUtils.isNotEmpty(request.getParameter("qryidcardno")))
				cusInfo.setIdcardNo(request.getParameter("qryidcardno"));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			ClientService clientService = new ClientServiceImpl();
			request.setAttribute("page",
					clientService.loadPagedClients(page, cusInfo));

			request.getRequestDispatcher("jsps/client/list_client.jsp")
					.forward(request, response);
		} else if ("loadPagedClientsDetail".equals(act)) {

			CusInfo cusInfo = new CusInfo();

			if (StringUtils.isNotEmpty(request.getParameter("qryclientname")))
				cusInfo.setClientName(request.getParameter("qryclientname"));

			if (StringUtils.isNotEmpty(request.getParameter("qryidcardno")))
				cusInfo.setIdcardNo(request.getParameter("qryidcardno"));

			Page page = new Page();
			if (StringUtils.isNotEmpty(request.getParameter("pageno")))
				page.setPageNo(Integer.parseInt(request.getParameter("pageno")));

			ClientService clientService = new ClientServiceImpl();
			request.setAttribute("page",
					clientService.loadPagedClientsDetail(page, cusInfo));

			request.getRequestDispatcher("jsps/client/list_detailclient.jsp")
					.forward(request, response);
		} else if ("remove".equals(act)) {
			int clientNo = Integer.parseInt(request.getParameter("clientno"));

			ClientService clientService = new ClientServiceImpl();

			try {
				clientService.removeClient(clientNo);
			} catch (ApplicationException e) {
				request.setAttribute("err", e.getMessage());

				CusInfo cusInfo = new CusInfo();
				Page page = new Page();
				if (StringUtils.isNotEmpty(request.getParameter("pageno")))
					page.setPageNo(Integer.parseInt(request
							.getParameter("pageno")));
				request.setAttribute("page",
						clientService.loadPagedClientsDetail(page, cusInfo));
				request.getRequestDispatcher(
						"jsps/client/list_detailclient.jsp").forward(request,
						response);
				return;
			}
			response.sendRedirect("clientMgr?act=loadPagedClientsDetail");
		} else if ("forUpdate".equals(act)) {

			int clientNo = Integer.parseInt(request.getParameter("clientno"));
			ClientService clientService = new ClientServiceImpl();
			request.setAttribute("cusinfo",
					clientService.getCusInfoByNo(clientNo));
			request.getRequestDispatcher("jsps/client/update_client.jsp")
					.forward(request, response);
		} else if ("update".equals(act)) {

			int clientNo = Integer.parseInt(request.getParameter("cusno"));

			ClientService clientService = new ClientServiceImpl();
			Client client = clientService.getClientByNo(clientNo);

			client.setClientName(request.getParameter("cusname"));
			client.setClientSex(request.getParameter("cusgender"));
			client.setIdcardNo(client.getIdcardNo());
			client.setClientPhone(request.getParameter("cusphone"));
			client.setClientEmail(request.getParameter("cusemail"));
			client.setClientAddress(request.getParameter("cusaddr"));
			client.setClientHobby(request.getParameter("hobby"));
			client.setOperCode(operator.getOperCode());

			clientService.update(client);

			response.sendRedirect("clientMgr?act=loadPagedClientsDetail");
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
