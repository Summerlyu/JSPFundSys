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
public class RightFilter implements Filter {
	
	private String[] validateOperation=null;


	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request; 
		HttpServletResponse res=(HttpServletResponse)response;
		
		//�û������������Դ����
		String resource=req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1);
		//System.out.println("RightFilter: resource->"+resource);
		
		String act=request.getParameter("act");
		if(checkResource(resource)||(resource.equals("securityMgr") && act.equals("gotoMain"))){
		
		   if(req.getSession().getAttribute("loginedOperator")==null){// û�еǽ�ϵͳ	
			 System.out.println("����"+resource+",Ȩ�޲��㣬���ʽ�ֹ!");
		     res.sendRedirect("securityMgr?act=toLogin");
		     return;
		   }
		}
		
		
		chain.doFilter(request,response);

	}


	public void init(FilterConfig config) throws ServletException {		
		validateOperation=config.getInitParameter("validateOperation").split(",");
				
	}
	
	
	private boolean checkResource(String resourceName){
		boolean result=false;
		
		for(int i=0;i<validateOperation.length;i++)
			if(validateOperation[i].equals(resourceName)){
				result=true;
				break;
			}
		
		return result;
	}

}
