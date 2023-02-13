package com.example.web3;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }
    private static void createSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Point.class);
        //getting properties from hibernate.cfg.xml file
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    //throws HibernateException
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
                createSessionFactory();
        }
        return sessionFactory;
    }
}
