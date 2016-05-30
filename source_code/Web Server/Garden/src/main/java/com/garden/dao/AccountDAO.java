package com.garden.dao;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import com.garden.utils.HibernateUtils;

public class AccountDAO implements Serializable {

	public boolean checkLogin(String username, String password) {
		Session session = HibernateUtils.getSessionFactory().openSession();

		try {
			session.getTransaction().begin();
			String sql = "FROM Account a WHERE a.username = :user AND a.password = :pass";
			Query query = session.createQuery(sql);
			query.setParameter("user", username);
			query.setParameter("pass", password);

			Object user = query.uniqueResult();
			if (user != null) {
				// close and commit transaction of current session
				session.getTransaction().commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			// rollback with wrong, use transaction
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
}
