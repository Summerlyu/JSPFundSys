<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<title>My JSP 'salefund.jsp' starting page</title>

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
          
            if(acc_no.value==''){
               alert("账号必须填写!");
               acc_no.focus();
               return false;
            }
            if(pass.value==''){
               alert("密码不能为空!");
               pass.focus();
               return false;
            }
          }
          return true;
       }  
    </script>
</head>

<body>
	<div id="wrapper">
		<div id="f_title">基金赎回</div>
		<form action="<c:url value="/fund_tradeMgr?act=validateAccount"/>" method="post" onSubmit="return validateFrm();">
			<div class="f_row">
				<span>账号:</span> <input type="text" name="acc_no" size="50" />
			</div>
			<div class="f_row">
				<span>密码:</span> <input type="password" name="pass" size="20" />
			</div>
			<div class="f_row">
				<input class="btn" type="submit" value="点击查找"
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
