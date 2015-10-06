<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html  xmlns="http://www.w3.org/1999/xhtml">
  <head>
 
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css"/>">
    <script type="text/javascript" language="JavaScript1.2" src="/fundTradeSystem/js/menu.js"></script>
	
	 <script type="text/javascript">
        
        function logout(){
          if(confirm("尊敬的${loginedOperator.operName},您真的要离开系统吗？")){
             location.href='<c:url value="/securityMgr"/>?act=logout'; 
          }
        }
    
    </script>

    <style>
  .body {padding:0px;margin:0px;font-size:12px;color:#000;border:0px;font-family:Tahoma,Arial,sans-serif ,"宋体";}
	


  body { margin:0 auto; 
      font-family:"宋体"; 
      background-image:url(/fundTradeSystem/res/sa2.jpg);
      background-repeat: no-repeat; 
      background-position: center top; 
      }

</style>

  </head>
  
  <body class="body">
  	
		<div class="nav_i" style="display:none;">0</div>
		<div class="main">
			
			<div class="head_new">
				<div id="logo" class="head_logo">
					<img width="276" height="50" src="<c:url value="/res/logo_1.png"/>">
				</div>
				<div class="head_right">
					 <div style="float:right; margin:10px; font-size:14px;font-weight:bold;">
                           	      操作员:${loginedOperator.operName}&nbsp;
            		 <span class="linkspan"  style="cursor:pointer;color:#47d52a;" onclick="logout();" title="点击离开系统">离开系统</span>
      				 </div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>	
			<div style="background-color:#FFFFFF;margin-left:5;margin-top:5;">
				<script type="text/javascript" language="JavaScript1.2" src="/fundTradeSystem/js/buildlayer.js"></script>
			</div>
			<div class="clear"></div>
			<div class="container">
				<iframe id="contentFrame" width="100%" scrolling="no" height="550px" frameborder="0" 
					src="<c:url value="/welcome.jsp"/>" allowtransparency="true" name="contentFrame">
				</iframe>
			
			</div>	
			<jsp:include page="/jsps/common/footer.jsp"/>
		</div>
		
  	
  </body>
</html>
