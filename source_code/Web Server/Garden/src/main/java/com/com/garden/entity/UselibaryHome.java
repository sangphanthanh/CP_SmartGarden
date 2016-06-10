package com.garden.entity;

// Generated Jun 10, 2016 12:51:32 AM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Uselibary.
 * @see com.garden.entity.Uselibary
 * @author Hibernate Tools
 */
@Stateless
public class UselibaryHome {

	private static final Log log = LogFactory.getLog(UselibaryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Uselibary transientInstance) {
		log.debug("persisting Uselibary instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Uselibary persistentInstance) {
		log.debug("removing Uselibary instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Uselibary merge(Uselibary detachedInstance) {
		log.debug("merging Uselibary instance");
		try {
			Uselibary result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Uselibary findById(int id) {
		log.debug("getting Uselibary instance with id: " + id);
		try {
			Uselibary instance = entityManager.find(Uselibary.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
