<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>基金交易系统登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css"/>">
	
	<style type="text/css">
	.error{
        border:1px solid yellow;
        color:blue;
        width:180px;
        padding:8px;
        margin:5px;
      }  

       span{
		margin:10px;	
		font-size:20px; 
		color:#5a6f16;
		font-weight:bold;
 	 }

	.raised{background:transparent;width:600px;height:auto;opacity: 0.6;
	position:absolute;
	left:400px;
	top:250px;
	color:#333;
	filter:progid:DXImageTransform.Microsoft.Shadow(color=#5a6f16,direction=120,strength=4) alpha(opacity=50);/*ie*/
	-moz-box-shadow: 2px 2px 10px #5a6f16;/*firefox*/
	-webkit-box-shadow: 2px 2px 10px #5a6f16;/*safari或chrome*/
	box-shadow:2px 2px 10px #5a6f16;/*opera或ie9*/}

	.raised h1,.raised p{margin:0 10px;}

	.raised h1{padding:10px;font-size:2em;color:#5a6f16;font-style:italic;}
	
	.raised p{padding-bottom:0.5em;}

	.raised .b1,.raised .b2,.raised .b3,.raised .b4,.raised .b1b,.raised .b2b,.raised .b3b,.raised .b4b{
	   display:block;overflow:hidden;font-size:1px;}

	.raised .b1,.raised .b2,.raised .b3,.raised .b1b,.raised .b2b,.raised .b3b{height:1px;}

	.raised .b2{background:#cfee66;border-left:1px solid #fff;border-right:1px solid #eee;}

	.raised .b3{background:#cfee66;border-left:1px solid #fff;border-right:1px solid #ddd;}

	.raised .b4{background:#cfee66;border-left:1px solid #fff;border-right:1px solid #aaa;}
	
	.raised .b4b{background:#cfee66;border-left:1px solid #eee;border-right:1px solid #999;}

	.raised .b3b{background:#cfee66;border-left:1px solid #ddd;border-right:1px solid #999;}

	.raised .b2b{background:#cfee66;border-left:1px solid #aaa;border-right:1px solid #999;}

	.raised .b1{margin:0 5px;background:#fff;}

	.raised .b2, .raised .b2b{margin:0 3px;border-width:0 2px;}

	.raised .b3, .raised .b3b{margin:0 2px;}

	.raised .b4, .raised .b4b{height:2px; margin:0 1px;}

	.raised .b1b{margin:0 5px; background:#999;}

	.raised .boxcontent{display:block;background:#cfee66;border-left:1px solid #fff;border-right:1px solid #999;}

	.clear_l{
			height:20px;
			}
	.U_P{
		padding-left:100px;
		font-size:20px;
		}
	.login_b{
		position:relative;
		left:260px;
		top:10px;
		
		}
	body{
		background-image:url(/fundTradeSystem/res/z33.jpg);
		filter:alpha(opacity=100);}

	</style>
	
	<script type="text/javascript">   
       function validateFrm(){
          
          with(document.forms[0]){
          
            if(operCode.value==''){
               alert("用户编号必须填写!");
               operCode.focus();
               return false;
            }
            
            if(operPwd.value==''){
               alert("用户密码必须填写!");
               operPwd.focus();
               return false;
            }
          }
          return true;
       }  
    </script>

</head>



<body>



<div class="raised">

<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>

<div class="boxcontent">

<h1>建设银行基金交易系统</h1>
<div class="clear_l"></div>
  <form action="<c:url value="/securityMgr?act=login"/>" method="post" onSubmit="return validateFrm();">
<div class="U_P">
  <span>编号：</span>
  <input  type="text" name="operCode" style="width:200px;"><br/>
  <div class=clear_l></div>
  <span>密码：</span>
  <input type="password" name="operPwd" style="width:200px;" ><br/>
  <div class="clear_l"></div>
    <c:if test="${not empty err}">
		        <div class="error">
		            ${err}
		        </div>
	        </c:if>
  	<div class="login_b">
  		<input type="submit" value="登 录"  onmouseout="this.style.backgroundPosition='left top';this.style.color='#ffafaf';" 
  		onmouseover="this.style.backgroundPosition='left -80px';this.style.color='#bcf1a7';"
		 style="font-size:24px;font-weight:bold;width:140px;height:37px;padding:5px;color:#5a6f16;
		 background-image:url(/fundTradeSystem/res/bg10.jpg);position:left top;border:none;">
 	 </div>
  </form>
 	 <div class="clear_l"></div>
	</div>
	<div class="clear_l"></div>

	</div>

		<b class="b4b"></b><b class="b3b"></b><b class="b2b"></b><b class="b1b"></b>

	</div>
</body>