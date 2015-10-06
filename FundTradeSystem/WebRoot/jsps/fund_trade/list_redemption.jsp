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
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/main.css"/>">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/controls.css"/>">
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
		function redemption(hid,fund_name){
			if(confirm("确认赎回["+fund_name+"]吗?")){
				var amount = document.getElementById(hid).value;
				location.href='<c:url value="fund_tradeMgr?act=fundRedemption"/>&hid='+hid+'&amount='+amount;
			}
		}
		
		function doQuery(pageno)
         {
           if(pageno<1 || pageno>${page.totalPageNum})
           {
              alert("页号超出范围，有效范围：[1-${page.totalPageNum}]!");
              $('pageNo').select();
              return;
           }
            var f=document.forms[0];
            f.action=f.action+"&pageno="+pageno;
            f.submit();
        }
	</script>
</head>

<body>
	<div id="wrapper">
		<div>
			<table id="listtable" cellpadding="0" cellspacing="0" align="left"
				border="1">
				<caption>基金赎回</caption>
				<tr>
					<th>基金编号</th>
					<th>基金名称</th>
					<th>基金单价</th>
					<th>描述说明</th>
					<th>是否开放</th>
					<th>持有数量</th>
					<th>操作</th>
				</tr>
				<c:forEach var="redemptionInfo" items="${page.pageContent}">
					<tr>
						<td>${redemptionInfo.fund_no}</td>
						<td>${redemptionInfo.fund_name}</td>
						<td>${redemptionInfo.fund_price}</td>
						<td>${redemptionInfo.fund_desc}</td>
						<td><c:choose>
								<c:when test="${redemptionInfo.fund_status=='o'}">开放</c:when>
								<c:when test="${redemptionInfo.fund_status=='c'}">封闭</c:when>
							</c:choose>
						<td>${redemptionInfo.amount}</td>
						<td>赎回数量<input type="text" id="${redemptionInfo.hid}">
							<button title="赎回[${redemptionInfo.fund_name}]的信息"
								onclick="redemption(${redemptionInfo.hid},'${redemptionInfo.fund_name}');">赎回</button>

						</td>
					</tr>
				</c:forEach>
			</table>
			<div id="pageinfo">
				共${page.totalRecNum}条, 当前显示${page.startIndex}-${page.endIndex}条,
				第${page.pageNo}/${page.totalPageNum}页 |
				<c:if test="${page.pageNo>1}">
					<span class="linkspan" onclick="doQuery(1)">首页</span>&nbsp;
	           </c:if>
				<c:if test="${page.prePage}">
					<span class="linkspan" onclick="doQuery(${page.pageNo-1})">上一页</span>&nbsp;
	           </c:if>
				<c:if test="${page.nextPage}">
					<span class="linkspan" onclick="doQuery(${page.pageNo+1})">下一页</span>&nbsp;
	           </c:if>
				<c:if test="${page.pageNo!=page.totalPageNum}">
					<span class="linkspan" onclick="doQuery(${page.totalPageNum})">末页</span>&nbsp;
	           </c:if>
				| 到<input type="text" id="pageNo" size=4 style="text-align:right;"
					onkeypress="onlynumber();" /> 页
				<button class="btn" onclick="doQuery($('pageNo').value);"
					onmouseout="this.style.backgroundPosition='left top'"
					onmouseover="this.style.backgroundPosition='left -30px'">
					跳 转</button>
			</div>
		</div>
	</div>
	<c:if test="${not empty err}">
		<script type="text/javascript">
       alert('${err}');
     </script>
	</c:if>
</body>
</html>