/**
 * 
 */
package edu.fjnu.fundtradesys.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class AuthenticationFilter implements Filter {
	
	private String[] validateObjects=null;


	public void destroy() {
		// TODO Auto-generated method stub

	}


	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		String act=request.getParameter("act");
		
		String uri=req.getRequestURI();
		
		String resource=uri.substring(uri.lastIndexOf("/")+1); //获得目前拦截到的web资源的名字，以对其进行是否登陆验证的检查准备
		
		if(isValidateObject(resource) || (resource.equals("securityMgr") && act.equals("gotoMain"))){
			if(req.getSession().getAttribute("loginedOperator")==null) {//用户尚未登陆，终止用户访问，显示登陆页面，要求用户登陆。
				 res.sendRedirect("securityMgr?act=toLogin");
			     return;
			}
		}
		
		chain.doFilter(request, response);
		

	}


	public void init(FilterConfig config) throws ServletException {

		 validateObjects=config.getInitParameter("validate-objects").split("\\,");
		
	}
	
	/**
	 * 检测要访问的web资源是否是受控 对象
	 * @param resource  WEB资源
	 * @return
	 */
	private boolean isValidateObject(String resource){
		
		boolean result=false;
		
		for(int i=0;i<validateObjects.length;i++){
			if(validateObjects[i].equals(resource)) {
				result=true;
				break;
			}
		}
		
		return result;
	}

}
