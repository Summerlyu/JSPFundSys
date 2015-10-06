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
		
		function removeClient(clientNo,clientName){
			if(confirm("确认要删除客户["+clientName+"]的信息吗?")){
				location.href='<c:url value="clientMgr?act=remove"/>&clientno='+clientNo;
			}
		}
		
		
		function updateClient(clientNo){
          location.href='<c:url value="clientMgr?act=forUpdate"/>&clientno='+clientNo;      
       }
       
       	function doQuery(pageno){
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
		     <form action="<c:url value="/clientMgr?act=loadPagedClientsDetail"/>" method="post">
		        <span>客户名称:</span>
		        <input type="text" name="qryclientname" size="20"/>
		        <span>身份证号:</span>
		        <input type="text" name="qryidcardno" size="20"/>
		        <input type="submit"  class="btn" value=" 查 询 " onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" />	
	         </form>          
	     </div>
	     <div>
	        <table id="listtable" cellpadding="0" cellspacing="0" align="left" border="1">
	        <caption>客户详细信息列表</caption>
	          <tr>
	            <th>姓名</th>
	            <th>性别</th>
	            <th>身份证</th>
	            <th>电话</th>
	            <th>email</th>     
	            <th>地址</th>
	            <th>爱好</th>
	            <th>注册时间</th>
	            <th>总资金</th> 
	            <th>总资产</th>
	            <th>操作</th>                                                   
	          </tr>
	          <c:forEach var="cusinfodetail" items="${page.pageContent}">
		          <tr>
		            <td>${cusinfodetail.clientName}</td>
		            <td>	           
	                  <c:choose>
	                     <c:when test="${cusinfodetail.clientSex=='a'}">男</c:when>
	                     <c:when test="${cusinfodetail.clientSex=='b'}">女</c:when>
	                  </c:choose>		            
		            </td>
		            <td>${cusinfodetail.idcardNo}</td>
		            <td>${cusinfodetail.clientPhone}</td>
		            <td>${cusinfodetail.clientEmail}</td> 
		            <td>${cusinfodetail.clientAddress}</td> 
		            <td>${cusinfodetail.clientHobby}</td> 
		            <td>${cusinfodetail.createDate}</td> 
		            <td>${cusinfodetail.accountAmount}</td> 
		            <td>${cusinfodetail.totalAmount}</td>
		            <td>
		            <button   title="点击修改客户[${cusinfodetail.clientName}]的信息" onclick="updateClient(${cusinfodetail.clientNo});">修改</button> 
		            <button  title="点击删除客户[${cusinfodetail.clientName}]的信息" onclick="removeClient(${cusinfodetail.clientNo},'${cusinfodetail.clientName}');">删除</button>
		            </td>                                          
		          </tr>          
	          </c:forEach>
	        </table>
	     </div>
	     <br>
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
	           <button  class="btn" onclick="doQuery($('pageNo').value);" onmouseout="this.style.backgroundPosition='left top'" 
	          onmouseover="this.style.backgroundPosition='left -30px'" > 跳 转 </button>		           		           	           	              
	        </div>
     </div>
     <c:if test="${not empty err}">
     <script type="text/javascript">
       alert('${err}');
     </script>
     </c:if>    
  </body>
</html>
