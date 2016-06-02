package com.garden.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		
		//Create configuration
		Configuration configuration = new Configuration();
		
		//By default, hibernate will read hibernate.cfg.xml configuration file
		//You can specify the file if you want 
		//configuration.configure("myhibernate.cfg.xml");	
		configuration.configure();
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		
		//create sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		getSessionFactory().close();
	}

}
