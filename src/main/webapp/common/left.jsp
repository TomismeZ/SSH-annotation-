<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="left">
	<div class="left_top">
		<div>
			<a href="findUser">用户查询</a>
		</div>
		<div>
			<a href="registerUser">用户新增</a>
		</div>
	</div>
	<div class="left_bottom">
		<img src="<%=path%>/images/login.png" alt="加载失败"> <span>当前登录用户</span>
	</div>
</div>
