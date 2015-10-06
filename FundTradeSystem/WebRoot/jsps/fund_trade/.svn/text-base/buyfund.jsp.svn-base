<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		function find_price(){
			var temp=new Array();
			var temp2 = new Array();
			<c:forEach var="fund"  items="${fundList}" varStatus="idx">
				temp[${idx.index}]=${fund.fundPrice};
				temp2[${idx.index}]=${fund.fundNo};
			</c:forEach>
			var m=$('temp').options[$('temp').selectedIndex].value;
			$('price').innerText="当前价位："+temp[m];
			$('price').value = temp[m];
			$('fundno').value = temp2[m];
		}
		function validateFrm(){
          
          with(document.forms[0]){
          
            if(fund.value==''){
               alert("请选择您所要购买的基金!");
               fund.focus();
               return false;
            }
            if(quantity.value==''){
               alert("请填写您所要购买的份数!");
               quantity.focus();
               return false;
            }
            if(acc_no.value==''){
               alert("账号不能为空!");
               acc_no.focus();
               return false;
            }
            if(acc_pwd.value==''){
               alert("密码不能为空!");
               acc_pwd.focus();
               return false;
            }
          }
          return true;
       }  
	</script>

</head>

<body>
	<div id="wrapper">
		<div id="f_title">基金购买</div>
		<br />
		<form action="<c:url value="/fund_tradeMgr?act=buy"/>" method="post"  onSubmit="return validateFrm();">

			<div class="f_row">
				<span>上市基金：</span> <select id="temp" name="fund"
					onchange="find_price()">
					<option value="">===请选择===</option>
					<c:forEach var="fund" items="${fundList}" varStatus="idx">
						<option value="${idx.index}">${fund.fundName}</option>
					</c:forEach>
				</select> <input type="hidden" id="fundno" name="fundno" value="" readonly />
			</div>
			<div class="f_row">
				<span>当前价位：</span><input type="text" id="price" name="price"
					value="" readonly />
			</div>
			<div class="f_row">
				<span>购买份数:</span> <input type="text" name="quantity" size="20" />
			</div>
			<div class="f_row">
				<span>资金账户:</span> <input type="text" name="acc_no" size="30" />
			</div>
			<div class="f_row">
				<span>账户密码:</span> <input type="password" name="acc_pwd" size="20" />
			</div>
			<div class="f_row">
				<input class="btn" type="submit" value="点击购买"
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
