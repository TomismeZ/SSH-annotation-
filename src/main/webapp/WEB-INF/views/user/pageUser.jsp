<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户查询界面</title>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<!--引入jQuery框架库文件-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
	function validate() {
		var page = document.getElementsByName("page")[0].value;

		if (page > <s:property value="#request.pageBean.totalPage"/>) {
			alert("你输入的页数大于最大页数，页面将跳转到首页！");

			window.document.location.href = "userAction";

			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div id="container">
		<%-- <%@ include file="common/header.jsp" %> --%>
		<%-- <jsp:include page="<%=path%>/common/header.jsp"></jsp:include> --%>
		<c:import url="${path }/common/header.jsp"></c:import>
		<c:import url="${path}/common/left.jsp" />
		<div class="right">
			<div class="right_head">
				<form action="#" method="post" class="form">
					用户名称： <input type="text" name="userName">
					<button>查询</button>
				</form>
			</div>
			<div class="right_content">

				<table class="tab">
					<tr>
						<th><input type="checkbox" id="checkAll"> 编号</th>
						<th>用户名称</th>
						<th>邮箱</th>
						<th>性别</th>
						<th>创建时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageBean.list}" var="user">

						<tr>
							<td><input type="checkbox" name="checkbox"> 轻实训</td>
							<td>${user.name }</td>
							<td>${user.email }</td>
							<td>${user.gender }</td>
							<td>${user.creatTime }</td>
							<td>${user.updateTime }</td>
							<td><a href="updateUser?user.id=${user.id }">编辑</a> <a
								href="deleteUser?user.id=${user.id }">删除</a></td>
						</tr>
					</c:forEach>
				</table>

			</div>
			<div class="footer"
				style="position: absolute; right: 10px; top: 40%;">
				<font size="3">共<font color="red"><s:property
							value="#request.pageBean.totalPage" /></font>页
				</font>&nbsp;&nbsp; <font size="3">共<font color="red"><s:property
							value="#request.pageBean.allRows" /></font>条记录
				</font><br> <br>
				<s:if test="#request.pageBean.currentPage == 1">
            首页&nbsp;&nbsp;&nbsp;上一页
        	</s:if>
				<s:else>
					<a href="queryByPage">首页</a>
            &nbsp;&nbsp;&nbsp;
            <a href="queryByPage?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a>
				</s:else>
				<s:if
					test="#request.pageBean.currentPage != #request.pageBean.totalPage">
					<a
						href="queryByPage?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a>
            &nbsp;&nbsp;&nbsp;
            <a
						href="queryByPage?page=<s:property value="#request.pageBean.totalPage"/>">尾页</a>
				</s:if>
				<s:else>
            	下一页&nbsp;&nbsp;&nbsp;尾页
        		</s:else>
				<form action="queryByPage" onsubmit="return validate();">
					<font size="4">跳转至</font> <input type="text" size="2" name="page">页
					<input type="submit" value="跳转">
				</form>
			</div>
		</div>
	</div>

</body>
</html>