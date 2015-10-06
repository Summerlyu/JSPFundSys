<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css"/>">
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/controls.css"/>">
  </head>
  
  <body>
   <div id="wrapper">
	    <div id="f_title">修改账户状态</div>
	    <form action="<c:url value="/financial_accountMgr?act=update"/>" method="post">
	        <div class="f_row">
	          <span>账号:</span>
	          <input type="text" name="accno" size="20" value="${fundaccountBO.acc_no}" readonly/>        
	        </div>
	        <div class="f_row">
	          <span>状态：</span>
		        <select name="accstatus">
		           <option value="a" <c:if test="${fundaccountBO.status=='a'}">selected</c:if>> 可用</option>
		           <option value="b" <c:if test="${fundaccountBO.status=='b'}">selected</c:if>> 冻结</option>
		        </select>                 
	        </div>
	        <div class="f_row">
	          <span>身份证:</span>
	          <input type="text" name="idcardno" size="30" value="${fundaccountBO.idcard_no}" readonly/>          
	        </div>
	        <div class="f_row">
	          <input  class="btn" type="submit" value="修改" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />     
	        </div>                        
	    </form>
    </div>
  </body>
</html>
