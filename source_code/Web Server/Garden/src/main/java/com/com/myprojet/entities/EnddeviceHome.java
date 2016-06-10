package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Enddevice.
 * @see com.myprojet.entities.Enddevice
 * @author Hibernate Tools
 */
@Stateless
public class EnddeviceHome {

	private static final Log log = LogFactory.getLog(EnddeviceHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Enddevice transientInstance) {
		log.debug("persisting Enddevice instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Enddevice persistentInstance) {
		log.debug("removing Enddevice instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Enddevice merge(Enddevice detachedInstance) {
		log.debug("merging Enddevice instance");
		try {
			Enddevice result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Enddevice findById(Integer id) {
		log.debug("getting Enddevice instance with id: " + id);
		try {
			Enddevice instance = entityManager.find(Enddevice.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
