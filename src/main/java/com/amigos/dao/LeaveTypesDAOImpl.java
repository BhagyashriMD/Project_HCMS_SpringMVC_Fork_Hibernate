package com.amigos.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amigos.dto.LeaveTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaveTypesDAOImpl implements LeaveTypesDAO {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<LeaveTypes> getAll() {
		System.out.println("------------   START --------------");
		Transaction tx = null;
		List<LeaveTypes> list = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("Session Created .......");
			tx = session.beginTransaction();
			System.out.println("Transaction Started .......");
			list = session.createQuery("FROM LeaveTypes", LeaveTypes.class).getResultList();
			System.out.println("Retrieve List of Objects : ");
			for (LeaveTypes obj : list) {
				System.out.println(obj);
			}
			tx.commit();
			System.out.println("Transaction committed .......");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
				System.out.println("Transaction rolledBack .......");
			}
			list = null;
		} finally {
			System.out.println("Closing Session .....");
			session.close();
		}
		System.out.println("------------   RETRUNING LIST --------------");
		System.out.println("------------   END --------------");
		return list;
	}

	@Override
	public LeaveTypes getLeaveTypesById(Long id) {
		System.out.println("------------   START --------------");
		Transaction tx = null;
		LeaveTypes obj = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("Session Created .......");
			tx = session.beginTransaction();
			System.out.println("Transaction Started .......");
			obj = session.get(LeaveTypes.class, Integer.valueOf(id.intValue()));
			System.out.println("Object retrieved with an ID " + id + "  obj " + obj);
			tx.commit();
			System.out.println("Transaction committed .......");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
				System.out.println("Transaction rolledBack .......");
			}
			obj = null;
		} finally {
			System.out.println("Closing Session .....");
			session.close();
		}
		System.out.println("------------   RETRUNING :: REQUESTED OBJECT --------------");
		System.out.println("------------   END --------------");
		return obj;
	}

	@Override
	public Long save(LeaveTypes obj) {

		System.out.println("------------   START --------------");
		Transaction tx = null;
		Serializable serializable = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("Session Created .......");
			tx = session.beginTransaction();
			System.out.println("Transaction Started .......");
			serializable = session.save(obj);
			System.out.println("Object persisted with ID : " + (Integer) serializable);
			tx.commit();
			System.out.println("Transaction committed .......");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
				System.out.println("Transaction rolledBack .......");
			}
			obj = null;
		} finally {
			System.out.println("Closing Session .....");
			session.close();
		}
		System.out.println("------------   RETRUNING VALUE :: ID --------------");
		System.out.println("------------   END --------------");
		return ((Integer) serializable).longValue();

	}

	@Override
	public LeaveTypes update(LeaveTypes obj) {
		System.out.println("------------   START --------------");
		Transaction tx = null;
		LeaveTypes updatedObj = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("Session Created .......");
			tx = session.beginTransaction();
			System.out.println("Transaction Started .......");
			updatedObj = (LeaveTypes) session.merge(obj);
			System.out.println("Object updated ::" + obj.toString());
			tx.commit();
			System.out.println("Transaction committed .......");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
				System.out.println("Transaction rolledBack .......");
			}
			updatedObj = null;
		} finally {
			System.out.println("Closing Session .....");
			session.close();
		}
		System.out.println("------------   RETRUNING UPDATED OBJECT --------------");
		System.out.println("------------   END --------------");
		return updatedObj;

	}

	@Override
	public void delete(Long id) {
		System.out.println("------------   START --------------");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			System.out.println("Session Created .......");
			tx = session.beginTransaction();
			System.out.println("Transaction Started .......");
			LeaveTypes obj = session.get(LeaveTypes.class, Integer.valueOf(id.intValue()));
			if (obj != null) {
				session.remove(obj);
				System.out.println("Object removed with Id ::" + id);
			}
			tx.commit();
			System.out.println("Transaction committed .......");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
				System.out.println("Transaction rolledBack .......");
			}
		} finally {
			System.out.println("Closing Session .....");
			session.close();
		}
		System.out.println("------------  OBJECT DELETED--------------");
		System.out.println("------------   END --------------");

	}

}
