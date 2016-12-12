package br.edu.ifsc.carrinhojsfjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carrinhojsfjpa");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
