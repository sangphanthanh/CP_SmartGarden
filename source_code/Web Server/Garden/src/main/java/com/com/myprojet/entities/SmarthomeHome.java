package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Smarthome.
 * @see com.myprojet.entities.Smarthome
 * @author Hibernate Tools
 */
@Stateless
public class SmarthomeHome {

	private static final Log log = LogFactory.getLog(SmarthomeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Smarthome transientInstance) {
		log.debug("persisting Smarthome instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Smarthome persistentInstance) {
		log.debug("removing Smarthome instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Smarthome merge(Smarthome detachedInstance) {
		log.debug("merging Smarthome instance");
		try {
			Smarthome result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Smarthome findById(Integer id) {
		log.debug("getting Smarthome instance with id: " + id);
		try {
			Smarthome instance = entityManager.find(Smarthome.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
