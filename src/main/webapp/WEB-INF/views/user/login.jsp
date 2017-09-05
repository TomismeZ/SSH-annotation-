<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录页面</title>
<!-- 引入外部样式 -->
<link href="<%=path%>/css/login.css" type="text/css" rel="stylesheet" />
<!-- 引入外部脚本 -->
<script src="<%=path%>/js/common.js"></script>
</head>

<body>
	<div class="content">
		<div class="left">
			<img src="images/login.jpg" alt="图片信息出错">
		</div>
		<div class="right">
			<div class="headed">用户登陆</div>
			<br />
			<hr>
			<form method="post" action="loginResultUser" name="loginForm"
				onsubmit="return login()">
				<div class="input-group">
					<label class="input-label">用户名称：</label> <label
						class="input-content"> <input class="input" type="text"
						name="user.name" placeholder="请输入您的用户名！" minlength="2"
						maxlength="20" autofocus="true" required> <span></span>
					</label>
				</div>
				<div class="input-group">
					<label class="input-label">登陆密码：</label> <label
						class="input-content"> <input class="input"
						type="password" name="user.password" placeholder="请输入您的密码"
						minlength="6" maxlength="20" required> <span></span>
					</label>
				</div>
				<div class="input-group">
					<label> <input type="checkbox" id="remember"
						name="remember">
					</label> <label>记住密码</label>
				</div>


				<div class="button-group">
					<button>登录</button>
					<button type="reset">重置</button>
					<button type="button">返回</button>
				</div>

				<h4>${message}</h4>

			</form>
		</div>
	</div>
</body>

</html>