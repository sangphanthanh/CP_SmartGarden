package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Userlibrary.
 * @see com.myprojet.entities.Userlibrary
 * @author Hibernate Tools
 */
@Stateless
public class UserlibraryHome {

	private static final Log log = LogFactory.getLog(UserlibraryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Userlibrary transientInstance) {
		log.debug("persisting Userlibrary instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Userlibrary persistentInstance) {
		log.debug("removing Userlibrary instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Userlibrary merge(Userlibrary detachedInstance) {
		log.debug("merging Userlibrary instance");
		try {
			Userlibrary result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Userlibrary findById(Integer id) {
		log.debug("getting Userlibrary instance with id: " + id);
		try {
			Userlibrary instance = entityManager.find(Userlibrary.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
