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
	    <div id="f_title">修改客户信息</div>
	    <form action="<c:url value="/clientMgr?act=update"/>" method="post">
	        <input type="hidden" name="cusno" value="${cusinfo.clientNo}"/>
	        <div class="f_row">
	          <span>姓名:</span>
	          <input type="text" name="cusname" size="20" value="${cusinfo.clientName}"/>        
	        </div>
	        <div class="f_row">
	          <span>性别：</span>
		        <select name="cusgender">
		           <option value="a" <c:if test="${cusinfo.clientSex=='a'}">selected</c:if>> 男</option>
		           <option value="b" <c:if test="${cusinfo.clientSex=='b'}">selected</c:if>> 女 </option>
		        </select>                 
	        </div>
	        <div class="f_row">
	          <span>身份证号:</span>
	          <input type="text" name="cusid" size="50" value="${cusinfo.idcardNo}" disabled/>
	        </div> 
	        <div class="f_row">
	          <span>电话号码:</span>
	          <input type="text" name="cusphone" size="30" value="${cusinfo.clientPhone}"/>          
	        </div> 
	         <div class="f_row">
	          <span>email地址:</span>
	          <input type="text" name="cusemail" size="30" value="${cusinfo.clientEmail}"/>          
	        </div> 
	        <div class="f_row">
	          <span>家庭地址:</span>
	          <input type="text" name="cusaddr" size="30" value="${cusinfo.clientAddress}"/>          
	        </div> 
	        <div class="f_row">
	          <span>爱好:</span>
	          <textarea rows="3" cols="10" name="hobby">${cusinfo.clientHobby}</textarea>         
	        </div> 
	         <div class="f_row">
	          <span>总资金:</span>
	          <input type="text" name="cusaccount" size="20" value="${cusinfo.accountAmount}" disabled/>          
	        </div>
	         <div class="f_row">
	          <span>总资产:</span>
	          <input type="text" name="cusproperty" size="20" value="${cusinfo.totalAmount}" disabled/>          
	        </div>
	        <div class="f_row">
	          <input type="submit"  class="btn" value="登记" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />  
	          <input type="reset"  class="btn" value="重写" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />     
	        </div>                        
	    </form>
    </div>
  </body>
</html>
