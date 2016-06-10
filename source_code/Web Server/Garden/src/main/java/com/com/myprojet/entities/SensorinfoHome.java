package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Sensorinfo.
 * @see com.myprojet.entities.Sensorinfo
 * @author Hibernate Tools
 */
@Stateless
public class SensorinfoHome {

	private static final Log log = LogFactory.getLog(SensorinfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Sensorinfo transientInstance) {
		log.debug("persisting Sensorinfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Sensorinfo persistentInstance) {
		log.debug("removing Sensorinfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Sensorinfo merge(Sensorinfo detachedInstance) {
		log.debug("merging Sensorinfo instance");
		try {
			Sensorinfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sensorinfo findById(Integer id) {
		log.debug("getting Sensorinfo instance with id: " + id);
		try {
			Sensorinfo instance = entityManager.find(Sensorinfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
