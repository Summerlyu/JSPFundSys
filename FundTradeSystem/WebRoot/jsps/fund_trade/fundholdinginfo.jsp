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
    <script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
	
	<script type="text/javascript">
	
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
	     <div id="qryarea">
		     <form action="<c:url value="/fundholdinginfoMgr?act=loadPagedFundHolding"/>" method="post">
		        <span>账号:</span>
		        <input type="text" name="qryaccno" size="20"/>
		        <span>基金编号:</span>
		        <input type="text" name="qryfundno" size="20"/>
		         <input class="btn" type="submit" value=" 查 询 " onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />	
	         </form>          
	     </div>
	     <div>
	        <table id="listtable" cellpadding="0" cellspacing="0" >
	        <caption>持仓情况</caption>
	          <tr>
	            <th>持仓流水号</th>
	            <th>交易账户</th>
	            <th>交易基金的编号</th>
	            <th>交易基金的份数</th>                                                       
	          </tr>
	          <c:forEach var="fundHolding" items="${page.pageContent}">
		          <tr>
		            <td>${fundHolding.hid }</td>
		            <td>${fundHolding.accno }</td>
		            <td>${fundHolding.fundno }</td>
		            <td>${fundHolding.amount }</td>                                         
		          </tr>          
	          </c:forEach>
	        </table>
	        <div id="pageinfo">
	                            共${page.totalRecNum}条, 当前显示${page.startIndex}-${page.endIndex}条, 第${page.pageNo}/${page.totalPageNum}页
	           |
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
	           |
	                             到<input type="text" id="pageNo" size=4 style="text-align:right;" onkeypress="onlynumber();"/> 页
	           <button class="btn" onclick="doQuery($('pageNo').value);" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" > 跳 转 </button>		           		           	           	              
	        </div>
	     </div>
     </div>
  </body>
</html>
