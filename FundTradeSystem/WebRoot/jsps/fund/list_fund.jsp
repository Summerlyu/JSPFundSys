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
    <script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
    
    <script type="text/javascript">
    
       function removeFund(fundNo,fundName){
          if(confirm("确认要删除基金["+fundName+"]的信息吗?")){
            location.href='<c:url value="fundMgr?act=remove"/>&fundno='+fundNo;
          }
       }
       
       function updateFund(fundNo){
          location.href='<c:url value="fundMgr?act=forUpdate"/>&fundno='+fundNo;      
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
	    
	      <div id="qryarea">
		     <form action="<c:url value="/fundMgr?act=loadPagedFunds"/>" method="post">
		        <span>基金名称：</span>
		        <input type="text" name="qryfundname" size="20"/>
		        <span>基金状态：</span>
		         <select name="qryfundstatus">
		           <option value="">== 请选择 == </option>
		           <option  value="o" <c:if test="${param.qryfundstatus=='o'}">selected</c:if>> 开放</option>
		           <option  value="c" <c:if test="${param.qryfundstatus=='c'}">selected</c:if>> 封闭</option>
		         </select>
		         <input class="btn" type="submit" value=" 查 询 " onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />	
	         </form>          
	     </div>
	     <div>
	        <table id="listtable" cellpadding="0" cellspacing="0">
	        <caption>基金信息列表</caption>
	          <tr>
	            <th>编号</th>
	            <th>基金名称</th>
	            <th>基金单价</th>
	            <th>描述说明</th>
	            <th>是否开放</th>
	            <th>操作</th>                                                            
	          </tr>
	          <c:forEach var="fund" items="${page.pageContent}">
		          <tr>
		            <td>${fund.fundNo}</td>
		            <td>${fund.fundName}</td>
		            <td>${fund.fundPrice}</td>
		            <td>${fund.fundDesc}</td>
		            <td>	           
	                  <c:choose>
	                     <c:when test="${fund.fundStatus=='o'}">开放</c:when>
	                     <c:when test="${fund.fundStatus=='c'}">封闭</c:when>
	                  </c:choose>		            
		            </td>
		            <td>
		               <button  title="点击修改基金[${fund.fundName}]" onclick="updateFund(${fund.fundNo});"> 修 改 </button>
		               <button title="点击删除基金[${fund.fundName}]" onclick="removeFund(${fund.fundNo},'${fund.fundName}');"> 删 除 </button>
		            </td>                                                            
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
	           <button class="btn" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'"  onclick="doQuery($('pageNo').value);"> 跳 转 </button>		           		           	           	              
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
