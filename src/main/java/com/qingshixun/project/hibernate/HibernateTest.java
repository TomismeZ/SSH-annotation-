package com.qingshixun.project.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.model.UserModel.Gender;

public class HibernateTest {
    @Test
    public void testHibernate(){
        Configuration cfg = null;
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try{
            cfg = new Configuration().configure();
            ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sf = cfg.buildSessionFactory(registry);
            session = sf.openSession();
            tx = session.beginTransaction();
            UserModel user = new UserModel();
            user.setName("咪咪");
            user.setPassword("2222");
            user.setGender(Gender.female);
            session.save(user); 
            //6.提交事务
            tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            tx.rollback();
            throw new HibernateException(e.getCause());

        }finally{
            //7.关闭session
            if(session!=null&&session.isOpen())
            session.close();
            sf.close();
        }
    }

}