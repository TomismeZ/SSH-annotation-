package com.qingshixun.project.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.project.dao.IUserDao;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.util.HibernateUtils;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	/**
	 * 当设置该属性时，就可以引用该对象，也不需要set注入了
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 添加用户
	 */
	@Override
	public boolean addUser(UserModel user) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// 获取session对象
			session = HibernateUtils.getSession();
			// 启动事务
			Transaction transaction = session.beginTransaction();
			// 创建User实例
			UserModel userModel = new UserModel();
			userModel.setName(user.getName());
			userModel.setPassword(user.getPassword());
			userModel.setGender(user.getGender());
			userModel.setEmail(user.getEmail());
			// userModel.setCreatTime(creatTime);
			// userModel.setUpdateTime(updateTime);
			session.save(userModel);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
			throw new HibernateException(e.getCause());
		} finally {
			// 7.关闭session
			if (session != null && session.isOpen())
				session.close();
		}

	}

	/**
	 * 查找所有的用戶
	 */
	@Override
	public List<UserModel> findUser() {
		// TODO Auto-generated method stub
		List<UserModel> list = new ArrayList<>();
		Session session = HibernateUtils.getSession();
		list = session.createQuery("from UserModel").list();
		session.close();
		return list;
	}

	/**
	 * 登录验证
	 * @param username 用户名
	 * @param password 登录密码
	 * @return
	 */
	@Override
	public UserModel loginUser(String username, String password) {
		// TODO Auto-generated method stub
		
		Session session = null;
		try {
			// 获取session对象
			session = HibernateUtils.getSession();
		
//			Query query = session.createSQLQuery(sql);
			String hql="FROM UserModel WHERE name=? and password=?";
			Query query=session.createQuery(hql);
			query.setString(0, username);
			query.setString(1, password);
			
//			query.setParameter(0, username);
			
			List<UserModel> list=query.list();
			Iterator<UserModel> iterator=list.iterator();
			while(iterator.hasNext()){
				UserModel userModel=iterator.next();
				return userModel;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return null;

	}

	/**
	 * 根据id来删除用户
	 */
	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// 获取session对象
			session = HibernateUtils.getSession();
			// 启动事务
			Transaction transaction = session.beginTransaction();
//			String sql = "DELETE FROM user WHERE id=?";
//			Query query = session.createSQLQuery(sql);
			String hql="Delete FROM UserModel Where id=?";
			Query query=session.createQuery(hql);
			query.setInteger(0, id);
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
			throw new HibernateException(e.getCause());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

	}

	/**
	 * 根据id来查找用户
	 */
	@Override
	public UserModel findByIdUser(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.getSession();
		UserModel userModel=session.get(UserModel.class, id);
		return userModel;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUser(UserModel user) {
		
		Session session = null;
		try {
			// 获取session对象
			session = HibernateUtils.getSession();
			// 启动事务
			Transaction transaction = session.beginTransaction();
//			String sql = "DELETE FROM user WHERE id=?";
//			Query query = session.createSQLQuery(sql);
			String hql="update UserModel set name=?,password=?,email=?,gender=? Where id=?";			
			Query query=session.createQuery(hql);
			query.setString(0, user.getName());
			query.setString(1, user.getPassword());
			query.setString(2, user.getEmail());
			query.setInteger(3, user.getGender().ordinal());
			query.setInteger(4, user.getId());
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
			throw new HibernateException(e.getCause());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}

	 /**
     * 使用hibernate提供的分页功能，得到分页显示的数据
     */
	@Override
	public List<UserModel> queryByPage(String hql, int offset, int pageSize) {
		// TODO Auto-generated method stub
		Session session=HibernateUtils.getSession();
		Transaction tx=null;
		List<UserModel> list=null;
		try {
			tx=session.beginTransaction();
			Query query=session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
			list=query.list();
			tx.commit();
		} catch (Exception e) {
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			if (session != null && session.isOpen())
				session.close();
		}
		return list;
	}
	/**
	 * 通过hql语句得到数据库中记录总数
	 */
	@Override
	public int getAllRowCount(String hql) {
		Session session=HibernateUtils.getSession();
		Transaction tx=null;
		int allRows=0;
		try {
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			allRows=query.list().size();
			tx.commit();
		} catch (Exception e) {
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			if (session != null && session.isOpen())
				session.close();
		}
		return allRows;
	}

	@Override
	public UserModel load(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().load(UserModel.class, id);
	}

	@Override
	public UserModel get(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().get(UserModel.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> findAll() {
		
		return getCurrentSession().createQuery("from UserModel").getResultList();
	}

	@Override
	public void persist(UserModel entity) {
		getCurrentSession().persist(entity);
		
	}

	@Override
	public Integer save(UserModel entity) {
		
		return (Integer) getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(UserModel entity) {
		getCurrentSession().saveOrUpdate(entity);
		
	}

	@Override
	public void delete(Integer id) {
		getCurrentSession().delete(id);
		
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
		
	}

}
