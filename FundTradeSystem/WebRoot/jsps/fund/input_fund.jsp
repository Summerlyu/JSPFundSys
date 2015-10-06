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
          
            if(fundname.value==''){
               alert("基金名称必须填写!");
               fundname.focus();
               return false;
            }
            if(fundprice.value==''){
               alert("基金单价必须填写!");
               fundprice.focus();
               return false;
            }
            if(funddesc.value==''){
               alert("描述说明必须填写!");
               funddesc.focus();
               return false;
            }
            if(fund_status.value==''){
               alert("请选择状态");
               fund_status.focus();
               return false;
            }
          }
          return true;
       }  
    </script>
	
  </head>
  
  <body> 
    <div id="wrapper">
	    <div id="f_title">添加基金产品</div>
	    <form action="<c:url value="/fundMgr?act=create"/>" method="post"  onSubmit="return validateFrm();">
	        <div class="f_row">
	          <span>基金名称:</span>
	          <input type="text" name="fundname" size="20"/>
	        </div>
	        <div class="f_row">
	          <span>基金单价:</span>
	          <input type="text" name="fundprice" size="50"/>        
	        </div>
	        <div class="f_row">
	          <span>描述说明:</span>
	          <input type="text" name="funddesc" size="20"/>          
	        </div> 
	         <div class="f_row">
	          <span>是否开放</span>
		        <select name="fund_status">
		           <option value="">== 请选择 == </option>
		           <option value="o" <c:if test="${fund.fundStatus=='o'}">selected</c:if>> 开放</option>
		           <option value="c" <c:if test="${fund.fundStatus=='c'}">selected</c:if>> 封闭 </option>
		        </select>                 
	        </div> 
	        <div class="f_row">
	          <input type="submit" value="登记"  class="btn"
	          onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" 
	         />  
	          <input type="reset" value="重写" class="btn"
	          onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" 
	          />     
	        </div>                        
	    </form>
    </div>
  </body>
</html>
