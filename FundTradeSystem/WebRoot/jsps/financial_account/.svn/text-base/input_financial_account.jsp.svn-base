<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
 
 		<script type="text/javascript">
      
       function validateFrm(){
          
          with(document.forms[0]){
          
            if(accpwd.value==''){
               alert("密码必须填写!");
               accpwd.focus();
               return false;
            }
            if(accamount.value==''){
               alert("总资金数额必须填写!");
               accamount.focus();
               return false;
            }
            if(fund_status.value==''){
               alert("请选择账户的状态!");
               fund_status.focus();
               return false;
            }
            if(idcardno.value==''){
               alert("请填写身份证号!");
               idcardno.focus();
               return false;
            }
          }
          return true;
       }  
    </script>
 
 
  </head>
  
  <body> 
    <div id="wrapper">
	    <div id="f_title">资金账户开户</div>
	    <form action="<c:url value="/financial_accountMgr?act=create"/>" method="post" onSubmit="return validateFrm();">
	        <div class="f_row">
	          <span>密码:</span>
	          <input type="password" name="accpwd" size="20"/>
	        </div>
	        <div class="f_row">
	          <span>总资金:</span>
	          <input type="text" name="accamount" size="50"/>        
	        </div>
	         <div class="f_row">
	          <span>状态</span>
		        <select name="fund_status">
		           <option value="">== 请选择 == </option>
		           <option value="a" > 可用</option>
		           <option value="b" > 冻结</option>
		        </select>                 
	        </div> 
	           <div class="f_row">
	          <span>身份证:</span>
	          <input type="text" name="idcardno" size="20"/>          
	        </div> 
	        
	        <div class="f_row">
	          <input  class="btn" type="submit" value="登记" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />  
	          <input  class="btn" type="reset" value="重写" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />     
	        </div>                        
	    </form>
    </div>
  </body>
</html>
