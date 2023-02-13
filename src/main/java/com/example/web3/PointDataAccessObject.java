package com.example.web3;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class PointDataAccessObject {

    public void save(Point point) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(point);
        transaction.commit();
        session.close();
    }

    public Point findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Point.class, id);
    }

    public void delete(Point point) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(point);
        tx1.commit();
        session.close();
    }

    public List<Point> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Point> criteriaQuery = criteriaBuilder.createQuery(Point.class);
        Root<Point> rootEntry = criteriaQuery.from(Point.class);
        CriteriaQuery<Point> all = criteriaQuery.select(rootEntry);
        TypedQuery<Point> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public void clearAll() {
        List<Point> current = getAll();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        for (Point point : current) {
            session.remove(point);
        }
        tx1.commit();
        session.close();
    }
}
