package com.qingshixun.project.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterception implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 从session中获取user对象
		Object user = ServletActionContext.getRequest().getSession().getAttribute("userInfo");
		System.out.println("Object :"+user);
		// 判断有无user对象
		if (user != null) {
			// 如果有，表示已经登录，可以访问
			return invocation.invoke();
		} else {
			// 没有user对象，表示没有登录，不能继续访问，跳转到登录界面
			return "login";
		}
	}

}
