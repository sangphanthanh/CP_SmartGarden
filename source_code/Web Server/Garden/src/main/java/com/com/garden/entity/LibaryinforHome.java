package com.garden.entity;

// Generated Jun 10, 2016 12:51:32 AM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Libaryinfor.
 * @see com.garden.entity.Libaryinfor
 * @author Hibernate Tools
 */
@Stateless
public class LibaryinforHome {

	private static final Log log = LogFactory.getLog(LibaryinforHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Libaryinfor transientInstance) {
		log.debug("persisting Libaryinfor instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Libaryinfor persistentInstance) {
		log.debug("removing Libaryinfor instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Libaryinfor merge(Libaryinfor detachedInstance) {
		log.debug("merging Libaryinfor instance");
		try {
			Libaryinfor result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Libaryinfor findById(int id) {
		log.debug("getting Libaryinfor instance with id: " + id);
		try {
			Libaryinfor instance = entityManager.find(Libaryinfor.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
