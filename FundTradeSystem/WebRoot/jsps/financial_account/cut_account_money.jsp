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
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/main.css"/>">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/controls.css"/>">
	
		<script type="text/javascript">
      
       function validateFrm(){
          
          with(document.forms[0]){
          
            if(accno.value==''){
               alert("账号必须填写!");
               accno.focus();
               return false;
            }
            if(accpwd.value==''){
               alert("密码必须填写!");
               accpwd.focus();
               return false;
            }
            if(cutmoney.value==''){
               alert("取出金资的数目必须填写!");
               cutmoney.focus();
               return false;
            }
          }
          return true;
       }  
    </script>
	
	
</head>

<body>
	<div id="wrapper">
		<div id="f_title">取出账户资金</div>
		<form action="<c:url value="/financial_accountMgr?act=cut"/>" method="post"  onSubmit="return validateFrm();">
			<div class="f_row">
				<span>账号:</span> <input type="text" name="accno" size="20" />
			</div>

			<div class="f_row">
				<span>密码:</span> <input type="password" name="accpwd" size="50" />
			</div>

			<div class="f_row">
				<span>取出资金:</span> <input type="text" name="cutmoney" size="20" />
			</div>

			<div class="f_row">
				<input class="btn" type="submit" value="登记"
					onmouseout="this.style.backgroundPosition='left top'"
					onmouseover="this.style.backgroundPosition='left -30px'" /> <input
					class="btn" type="reset" value="重写"
					onmouseout="this.style.backgroundPosition='left top'"
					onmouseover="this.style.backgroundPosition='left -30px'" />
			</div>
		</form>
	</div>
	<c:if test="${not empty err}">
		<script type="text/javascript">
			alert('${err}');
		</script>
	</c:if>
</body>
</html>
