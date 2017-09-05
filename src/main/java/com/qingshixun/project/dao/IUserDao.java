package com.qingshixun.project.dao;

import java.util.List;

import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.UserModel;

public interface IUserDao extends BaseDao<UserModel, Integer>{
	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(UserModel user);

	/**
	 * 查找所有的用户信息
	 * 
	 * @return
	 */
	public List<UserModel> findUser();

	/**
	 * 用户登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public UserModel loginUser(String username, String password);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);

	/**
	 * 通过id来查找用户
	 * 
	 * @param id
	 * @return
	 */
	public UserModel findByIdUser(int id);

	/**
	 * 通过id来查找用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateUser(UserModel user);

	public List<UserModel> queryByPage(String hql, int offset, int pageSize);

	public int getAllRowCount(String hql);
}
