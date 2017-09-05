<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<title>userInfo</title>

<link rel="stylesheet" href="<%=path%>/css/style.css">
<!--引入jQuery框架库文件-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
</head>
<body>
	<div id="container">
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
					<c:forEach items="${userList}" var="user">
						
						<tr>
						<td><input type="checkbox" name="checkbox"> 轻实训</td>
						<td>${user.name }</td>
						<td>${user.email }</td>
						<td>${user.gender }</td>
						<td>${user.creatTime }</td>
						<td>${user.updateTime }</td>
						<td>
							<a href="updateUser?user.id=${user.id }">编辑</a>
							<a href="deleteUser?user.id=${user.id }">删除</a>
						</td>
						</tr>
					</c:forEach>					
				</table>							
			</div>
		</div>
	</div>
</body>
</html>