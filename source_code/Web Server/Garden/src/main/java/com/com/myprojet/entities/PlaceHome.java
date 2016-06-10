package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Place.
 * @see com.myprojet.entities.Place
 * @author Hibernate Tools
 */
@Stateless
public class PlaceHome {

	private static final Log log = LogFactory.getLog(PlaceHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Place transientInstance) {
		log.debug("persisting Place instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Place persistentInstance) {
		log.debug("removing Place instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Place merge(Place detachedInstance) {
		log.debug("merging Place instance");
		try {
			Place result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Place findById(Integer id) {
		log.debug("getting Place instance with id: " + id);
		try {
			Place instance = entityManager.find(Place.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
