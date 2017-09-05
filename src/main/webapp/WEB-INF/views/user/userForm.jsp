<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

 <link rel="stylesheet" href="<%=path %>/css/style.css">
    <!--引入jQuery框架库文件-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js" type="text/javascript"></script>
     <script type="text/javascript" src="<%=path %>/js/common.js"></script>
      <script type="text/javascript">
        window.onload = function () {
            var formElements = document.getElementsByTagName("form")[0].elements;

            for (var i = 0; i < formElements.length; i++) {
                formElements[i].oninput = function (event) {
                    event.target.setCustomValidity("");
                }
            }
        };

        function checkInput(form) {
            var formElements = form.elements;
            var password = formElements.password;
            var confirmPassword = formElements.confirmPassword;
            
            if (password.value != confirmPassword.value) {
                confirmPassword.setCustomValidity("确认密码与登录密码必须相同");
                return false;
            }

            alert("登录成功");
            return true;
        }
    </script>
</head>
<body>
<div id="container">
        <header class="header">
            <div class="head_left">
                用户管理系统
            </div>
            <div class="head_right">
                <a href="#">帮助</a>
                <a href="#">退出</a>
            </div>
        </header>
        <div class="left">
            <div class="left_top">
                <div> <a href="findUser">用户查询</a></div>
                <div><a href="registerUser">用户新增</a></div>
            </div>
            <div class="left_bottom">
                <img src="images/login.png" alt="加载失败"> 
                <span>当前登录用户</span>
            </div>
        </div>
        <div class="right">
            <header class="right_head">
                <h5>新增&编辑用户信息</h5>
            </header>
            <div class="right_content">
            <c:if test="${update_user==null }">
             <form action="addUser" method="post" onsubmit="return checkInput(this)">

                    <div class="input-group">
                        <label class="input-label">用户名称：</label>
                        <label class="input-content">
                            <input class="input" type="text" name="user.name" placeholder="请输入您的用户名！"  minlength="2"  maxlength="20" autofocus="true" required>
                            <span></span>
                        </label>
                    </div>
                    <div class="input-group">
                        <label class="input-label">登陆密码：</label>
                        <label class="input-content">
                            <input class="input" type="password" name="user.password" placeholder="请输入您的密码"  minlength="6" maxlength="20" required>
                            <span></span>
                        </label>
                    </div>
                    <div class="input-group">
                        <label class="input-label">重复密码：</label>
                        <label class="input-content">
                           <input class="input" type="password" name="user.passwordAgain" placeholder="请输入您的确认密码"  minlength="6" maxlength="20" required>
                            <span></span>
                        </label>
                    </div>
                    <div class="input-group">
                        <label class="input-label">电子邮箱：</label>
                        <label class="input-content">
                           <input class="input" type="email" name="user.email" placeholder="请输入您的邮箱"  required>
                            <span></span>
                        </label>
                    </div>
                    <div class="input-group">
                        <label class="input-label">性 　 别：</label>
                        <label class="input-content">
                        <input type="radio" name="user.gender" value="male" checked>男
                        　　				<input type="radio" name="user.gender" value="female">女
                           
                        </label>
                    </div>
                    <div class="button-group">
                        <button>保存</button>
                        <button type="reset">重置</button>
                        <button type="button">返回</button>
                    </div>
                </form>
            </c:if>
            <c:if test="${update_user !=null }">
             <form action="updateResultUser" method="post" onsubmit="return checkInput(this)">

                    <div class="input-group">
                        <label class="input-label">用户名称：</label>
                        <label class="input-content">
                            <input class="input" type="text" name="user.name" value="${update_user.name }" placeholder="请输入您的用户名！"  minlength="2"  maxlength="20" autofocus="true" required>
                            <span></span>
                        </label>
                    </div>
                    <div class="input-group">
                        <label class="input-label">登陆密码：</label>
                        <label class="input-content">
                            <input class="input" type="password" name="user.password" value="update_user.password" placeholder="请输入您的密码"  minlength="6" maxlength="20" required>
                            <span></span>
                        </label>
                    </div>
                   
                    <div class="input-group">
                        <label class="input-label">电子邮箱：</label>
                        <label class="input-content">
                           <input class="input" type="email" name="user.email" value="${update_user.email }" placeholder="请输入您的邮箱"  required>
                            <span></span>
                        </label>
                    </div>
                    <div class="input-group">
                        <label class="input-label">性 　 别：</label>
                        <label class="input-content">
                        <input type="radio" name="user.gender" value="male" checked>男
                        　　				<input type="radio" name="user.gender" value="female">女
                           
                        </label>
                    </div>
                    <div class="button-group">
                        <button>保存</button>
                        <button type="reset">重置</button>
                        <button type="button">返回</button>
                    </div>
                </form>
            </c:if>
               
            </div>
        </div>
    </div>
</body>
</html>