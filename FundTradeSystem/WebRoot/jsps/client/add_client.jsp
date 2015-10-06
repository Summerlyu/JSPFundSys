<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>添加新客户</title>
    
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
          
            if(cusid.value==''){
               alert("身份证号必须填写!");
               cusid.focus();
               return false;
            }
            if(cusname.value==''){
               alert("姓名必须填写!");
               cusname.focus();
               return false;
            }
            if(cusgender.value==''){
               alert("请选择性别!");
               cusgender.focus();
               return false;
            }
          }
          return true;
       }  
    </script>


  </head>
  
  <body>
    <div id="wrapper">
	    <div id="f_title">添加新客户</div>
	    <form action="<c:url value="/clientMgr?act=create"/>" method="post" onSubmit="return validateFrm();">
	        <div class="f_row">
	          <span>身份证号:</span>
	          <input type="text" name="cusid" size="50"/>
	        </div>
	        <div class="f_row">
	          <span>姓名:</span>
	          <input type="text" name="cusname" size="20"/>        
	        </div>
	        <div class="f_row">
	          <span>性别：</span>
		        <select name="cusgender">
		           <option value="">== 请选择 == </option>
		           <option value="a" > 男 </option>
		           <option value="b" > 女  </option>
		        </select>                 
	        </div> 
	        <div class="f_row">
	          <span>电话号码:</span>
	          <input type="text" name="cusphone" size="30"/>          
	        </div> 
	         <div class="f_row">
	          <span>email地址:</span>
	          <input type="text" name="cusemail" size="30"/>          
	        </div> 
	        <div class="f_row">
	          <span>家庭地址:</span>
	          <input type="text" name="cusaddr" size="30"/>          
	        </div> 
	        <div class="f_row">
	          <span>爱好:</span>
	          <textarea rows="3" cols="10" name="hobby"></textarea>         
	        </div> 
	        <div class="f_row">
	          <input type="submit" class="btn" value="登记" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />  
	          <input type="reset"  class="btn" value="重写" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />     
	        </div>                        
	    </form>
    </div>
  </body>
</html>
