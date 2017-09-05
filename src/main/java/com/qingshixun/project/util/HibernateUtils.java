package com.qingshixun.project.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {  
/* 
  *读取Hibernate.cfg.xml文件 
*/  
    private static SessionFactory factory;  
      
    static {  
        try {  
            //读取hibernate.cfg.xml文件  
            Configuration cfg = new Configuration().configure();  
            ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            //建立SessionFactory  
            factory = cfg.buildSessionFactory(registry);  
        }catch(Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /* 
      *打开Session 
    */  
      
    public static Session getSession() {  
        return factory.openSession();  
    }   
    /* 
      *关闭Session 
    */  
      
    public static void closeSession(Session session) {  
        if (session != null) {  
            if (session.isOpen()) {  
                session.close();  
            }  
        }  
    }  
      
    public static SessionFactory getSessionFactory() {  
        return factory;  
    }  
}  
