package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:31 PM by Hibernate Tools 4.3.1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Libraryinfo.
 * @see com.myprojet.entities.Libraryinfo
 * @author Hibernate Tools
 */
@Stateless
public class LibraryinfoHome {

	private static final Log log = LogFactory.getLog(LibraryinfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Libraryinfo transientInstance) {
		log.debug("persisting Libraryinfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Libraryinfo persistentInstance) {
		log.debug("removing Libraryinfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Libraryinfo merge(Libraryinfo detachedInstance) {
		log.debug("merging Libraryinfo instance");
		try {
			Libraryinfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Libraryinfo findById(Integer id) {
		log.debug("getting Libraryinfo instance with id: " + id);
		try {
			Libraryinfo instance = entityManager.find(Libraryinfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
