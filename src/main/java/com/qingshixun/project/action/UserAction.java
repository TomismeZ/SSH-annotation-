package com.qingshixun.project.action;

import java.util.List;
import java.util.Map;

import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.IUserService;

//使用拦截器注解时，必须引用package包name的值
@ParentPackage("web-default")
@Scope("prototype")
@Controller
public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserAction userAction;

	@Autowired
	private IUserService userService;
	private UserModel user;
	private int page;
	
	/**
	 * 跳转到登录界面
	 * @return
	 * @throws Exception
	 */
	@Action(value="loginUser",results={@Result(name=SUCCESS,location="/WEB-INF/views/user/login.jsp")})
	public String loginUser() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 登录成功后，就会重定向到finUser
	 * @return
	 * @throws Exception
	 */
	@Action(value="loginResultUser" ,results={@Result(name=SUCCESS,type="redirectAction", params={"actionName","findUser"}),
			@Result(name="login",location="/WEB-INF/views/user/login.jsp")})
	public String loginResultUser() throws Exception {
		UserModel u = userService.loginUser(user.getName(), user.getPassword());

		if (u != null) {
			// ActionContext.getContext().getSession().put("userInfo", u);
			ServletActionContext.getRequest().getSession().setAttribute("userInfo", u);
			return SUCCESS;
		} else {
			return "login";
		}
	}
	
	@Action(value="registerUser",results={@Result(name=SUCCESS,location="/WEB-INF/views/user/userForm.jsp")})
	public String registerUser() throws Exception {

		return SUCCESS;
	}

	@Action(value="addUser",results={@Result(name=SUCCESS,type="redirectAction", params={"actionName","findUser"})})
	public String addUser() throws Exception {

		if (userService.addUser(user)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	/**
	 * 分页界面
	 * @return
	 * @throws Exception
	 */
	@Action(value="queryByPage",results={@Result(name=SUCCESS,location="/WEB-INF/views/user/pageUser.jsp")})
	public String queryByPage() throws Exception {
		PageBean pageBean = userService.getPageBean(5, page);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 查询所有用户信息
	 * @return
	 * @throws Exception
	 */
	@Action(value="findUser",
//		interceptorRefs=@InterceptorRef("myInterceptorStack"),
			interceptorRefs={@InterceptorRef("myInterceptorStack")},
			results={@Result(name=SUCCESS,location="/WEB-INF/views/user/userList.jsp"),
					@Result(name="login",location="/WEB-INF/views/user/login.jsp")})
	public String findUser() throws Exception {

//		List<UserModel> userList = userService.findUser();
		List<UserModel> userList=userService.findAll();
		for (UserModel userModel : userList) {
			System.out.println(userModel.toString());
		}
		@SuppressWarnings("rawtypes")
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("userList", userList);
		return SUCCESS;
	}
	/**
	 * 刪除用戶
	 * @return
	 * @throws Exception
	 */
	@Action(value="deleteUser",
			interceptorRefs=@InterceptorRef("myInterceptorStack"),
			results={@Result(name=SUCCESS,type="redirectAction", params={"actionName","findUser"}),
					@Result(name=ERROR,location="/WEB-INF/views/user/userList.jsp"),
					@Result(name="login",location="/WEB-INF/views/user/login.jsp")})
	public String deleteUser() throws Exception {
//		System.out.println("----deleteUser------");
//		if (userService.deleteUser(user.getId())) {
//			return SUCCESS;
//		} else
//			return ERROR;
		userService.delete(user.getId());
		return SUCCESS;
	}
	/**
	 * 跳转到修改页面
	 * @return
	 * @throws Exception
	 */
	@Action(value="updateUser",
			interceptorRefs=@InterceptorRef("myInterceptorStack"),
			results={@Result(name=SUCCESS,location="/WEB-INF/views/user/userForm.jsp"),
					@Result(name=ERROR,location="/WEB-INF/views/user/userList.jsp"),
					@Result(name="login",location="/WEB-INF/views/user/login.jsp")})
	public String updateUser() throws Exception {
//		UserModel update_user = userService.findByIdUser(user.getId());
		UserModel update_user=userService.get(user.getId());
		if (update_user != null) {
			ServletActionContext.getRequest().setAttribute("update_user", update_user);
			return SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 修改成功后就跳转到首页
	 * @return
	 * @throws Exception
	 */
	@Action(value="updateResultUser",
			interceptorRefs=@InterceptorRef("myInterceptorStack"),
			results={@Result(name=SUCCESS,type="redirectAction", params={"actionName","findUser"}),
					@Result(name=ERROR,location="/WEB-INF/views/user/userList.jsp"),
					@Result(name="login",location="/WEB-INF/views/user/login.jsp")})
	public String updateResultUser() throws Exception {
//		if (userService.updateUser(user)) {
//			return SUCCESS;
//		} else
//			return ERROR;
		
//		userService.saveOrUpdate(user);
		
//		userService.persist(user);插入数据到数据库
//		userService.save(user);
		
		return SUCCESS;
	}
	/**
	 * 退出登录
	 * @return
	 */
	@Action(value="logoutUser",results={@Result(name=SUCCESS,location="/WEB-INF/views/user/login.jsp")})
	public String logoutUser() throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Object user=session.getAttribute("userInfo");
		if(user !=null){
			session.removeAttribute("userInfo");
			/*session.invalidate();*/		
		}
		return SUCCESS;
	}
	/*
	 * public String findByIdUser() throws Exception {
	 * System.out.println("----------findByIdUser------------"); UserModel
	 * update_user = service.findByIdUser(user.getId()); if (update_user !=
	 * null) { // Map request = (Map) ActionContext.getContext().get("request");
	 * // request.put("update_user", update_user);
	 * ServletActionContext.getRequest().setAttribute("update_user",
	 * update_user);
	 * 
	 * return "update"; } return ERROR; }
	 */

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public UserAction getUserAction() {
		return userAction;
	}

	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/*
	 * public IUserService getUserService() { return userService; }
	 * 
	 * public void setUserService(IUserService userService) { this.userService =
	 * userService; }
	 */

}
