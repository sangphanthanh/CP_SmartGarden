package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Area.
 * @see com.myprojet.entities.Area
 * @author Hibernate Tools
 */
@Stateless
public class AreaHome {

	private static final Log log = LogFactory.getLog(AreaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Area transientInstance) {
		log.debug("persisting Area instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Area persistentInstance) {
		log.debug("removing Area instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Area merge(Area detachedInstance) {
		log.debug("merging Area instance");
		try {
			Area result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Area findById(Integer id) {
		log.debug("getting Area instance with id: " + id);
		try {
			Area instance = entityManager.find(Area.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
