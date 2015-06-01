package de.ar.rgk.model.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.ar.rgk.model.abstracts.IHibernateDataObject;

public class HibernateDao {

	public static SessionFactory sessionFactory;

	public static void saveObject(IHibernateDataObject obj) {
		Session session = getSessionAndBeginTransaktion();
		session.saveOrUpdate(obj);
		commitOrRollbackOnError(session);
	}

	public static void saveObject(Session session, IHibernateDataObject obj) {
		session.saveOrUpdate(obj);
	}

	public static void deleteObject(IHibernateDataObject obj) {
		Session session = getSessionAndBeginTransaktion();
		session.delete(obj);
		commitOrRollbackOnError(session);
	}

	public static Session getSessionAndBeginTransaktion() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
			session.createSQLQuery("SELECT 1").list();
		} catch (Exception e) {
			HibernateSessionFactory.resetSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
		}
		return session;
	}

	public static void commitOrRollbackOnError(Session session) {
		RuntimeException commitException = null;
		try {
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			commitException = e;
			session.getTransaction().rollback();
		}
		if (commitException != null)
			throw commitException;
	}

	public static void rollback(Session session) {
		session.getTransaction().rollback();
	}
}