package de.ar.rgk.hibernate;

import junit.framework.TestCase;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import de.ar.rgk.model.db.HibernateSessionFactory;

public class AbstractRgkTestCase extends TestCase {

	protected void createSchema() {
		try {
			Configuration configuration = HibernateSessionFactory.generateConfig();
			configuration.setProperty(Environment.HBM2DDL_AUTO, "create");
			HibernateSessionFactory.init(configuration);
		} catch (Exception e) {
			throw new RuntimeException("Hibernate Init failed: " + e);
		}
	}
}