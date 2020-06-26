package com.azienda.progetto.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

	public static EntityManager apriConnessione() {
		String nomeLogico= "Corso1H8";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(nomeLogico);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	
	public static void chiudiConnessione(EntityManager entityManager) {
		entityManager.close();
	}
}
