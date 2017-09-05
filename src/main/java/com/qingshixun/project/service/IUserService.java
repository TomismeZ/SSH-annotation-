package com.qingshixun.project.service;

import java.util.List;

import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.UserModel;

public interface IUserService {
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public boolean addUser(UserModel user);
	/**
	 * 查找所有的用户信息
	 * @return
	 */
	public List<UserModel> findUser();
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public UserModel loginUser(String username,String password);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);
	/**
	 * 通过id来查找用户
	 * @param id
	 * @return
	 */
	public UserModel findByIdUser(int id);
	/**
	 * 通过id来查找用户
	 * @param id
	 * @return
	 */
	public boolean updateUser(UserModel user);
	
	/**
	 * 
	 * @param pageSize为每页显示的记录数
	 * @param page为当前显示的网页
	 * @return 返回一个PageBean对象
	 */
	public PageBean getPageBean(int pageSize, int page);
	
	UserModel load(Integer id);  
	UserModel get(Integer id);  
    List<UserModel> findAll();  
    void persist(UserModel entity);  
    Integer save(UserModel entity);  
    void saveOrUpdate(UserModel entity);  
    void delete(Integer id);  
    void flush();  
}
