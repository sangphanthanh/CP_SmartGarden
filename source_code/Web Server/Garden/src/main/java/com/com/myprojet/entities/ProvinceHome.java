package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Province.
 * @see com.myprojet.entities.Province
 * @author Hibernate Tools
 */
@Stateless
public class ProvinceHome {

	private static final Log log = LogFactory.getLog(ProvinceHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Province transientInstance) {
		log.debug("persisting Province instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Province persistentInstance) {
		log.debug("removing Province instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Province merge(Province detachedInstance) {
		log.debug("merging Province instance");
		try {
			Province result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Province findById(Integer id) {
		log.debug("getting Province instance with id: " + id);
		try {
			Province instance = entityManager.find(Province.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
