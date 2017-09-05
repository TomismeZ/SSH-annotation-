package com.qingshixun.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.project.dao.IUserDao;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserDao userDao;
	@Override
	public boolean addUser(UserModel user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public List<UserModel> findUser() {
		// TODO Auto-generated method stub
		return userDao.findUser();
	}

	@Override
	public UserModel loginUser(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.loginUser(username, password);
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	@Override
	public UserModel findByIdUser(int id) {
		// TODO Auto-generated method stub
		return userDao.findByIdUser(id);
	}

	@Override
	public boolean updateUser(UserModel user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	/**
	 * pageSize为每页显示的记录数
	 * page为当前显示的网页
	 */
	@Override
	public PageBean getPageBean(int pageSize, int page) {
		PageBean pageBean=new PageBean();
		String hql="from UserModel";
		int allRows=userDao.getAllRowCount(hql);
		int totalPage=pageBean.getTotalPages(pageSize, allRows);
		int currentPage=pageBean.getCurPage(page);
		int offset=pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<UserModel> list=userDao.queryByPage(hql, offset, pageSize);
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public UserModel load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过id来查找用户
	 */
	@Override
	public UserModel get(Integer id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}
	
	/**
	 * 查找所有的用户
	 */
	@Override
	public List<UserModel> findAll() {
		
		return userDao.findAll();
	}

	/**
	 * 插入数据到数据库
	 */
	@Override
	public void persist(UserModel entity) {
		userDao.persist(entity);
		
	}
	
	/**
	 * 保存数据到数据库
	 */
	@Override
	public Integer save(UserModel entity) {
		
		return userDao.save(entity);
	}
	
	/**
	 * 保存并更新数据到数据库
	 */
	@Override
	public void saveOrUpdate(UserModel entity) {
		
		userDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
		
	}

	@Override
	public void flush() {
		userDao.flush();
		
	}

/*	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}*/
	
}
