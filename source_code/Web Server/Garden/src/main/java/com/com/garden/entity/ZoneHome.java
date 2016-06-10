package com.garden.entity;

// Generated Jun 10, 2016 12:51:32 AM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Zone.
 * @see com.garden.entity.Zone
 * @author Hibernate Tools
 */
@Stateless
public class ZoneHome {

	private static final Log log = LogFactory.getLog(ZoneHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Zone transientInstance) {
		log.debug("persisting Zone instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Zone persistentInstance) {
		log.debug("removing Zone instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Zone merge(Zone detachedInstance) {
		log.debug("merging Zone instance");
		try {
			Zone result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zone findById(int id) {
		log.debug("getting Zone instance with id: " + id);
		try {
			Zone instance = entityManager.find(Zone.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
