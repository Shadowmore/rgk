package de.ar.rgk.model.db;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	static {
		initSessionFactory();
	}

	private static void initSessionFactory() {
		try {
			Configuration configuration = generateConfig();

			init(configuration);

		} catch (Exception e) {
			throw new RuntimeException("Hibernate Init failed: " + e);
		}
	}

	public static Configuration generateConfig() {
		String cfgPath = "E:/checkout/rgk/src/de/ar/rgk/model/hibernate.cfg.xml";

		File cfgFile = new File(cfgPath);

		Configuration configuration = new Configuration();
		configuration.configure(cfgFile);

		return configuration;
	}

	public static void init(Configuration configuration) throws Exception {
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		HibernateDao.sessionFactory = sessionFactory;
	}

	public static void resetSessionFactory() {
		initSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}