package com.azienda.progetto.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.progetto.model.Utente;


public class BusinessLogic {
	
	private UtenteDao utenteDao = null;
	private EntityManager em = null;
	
	public BusinessLogic (EntityManager em ,UtenteDao utenteDao) {
		setEm(em);
		setUtenteDao(utenteDao);
	}

	public UtenteDao getUtenteDao() {
		return utenteDao;
	}


	public void setUtenteDao(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}

	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void create(Utente utente) {
		em.getTransaction().begin();
		try {
			utenteDao.create(utente);
			em.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	public boolean checkUsernamePassword(String username, String password) {
		em.getTransaction().begin();
		boolean isCorrect = false;
		try {
			List<Utente> listaUtente = utenteDao.findByUsernameAndPassword(username, password);
			System.out.println("Ho fatto la query");
			
			if (listaUtente==null || listaUtente.isEmpty()) {
				isCorrect = false;
				System.out.println("isCorrect primo if ");
			}
			else if (listaUtente.size()>1) {
				isCorrect = false;
				System.out.println("isCorrect secondo if ");

				throw new Exception("Due o più persone con lo stesso username.");
				
			}
			else {
				Utente utente = listaUtente.get(0);
				String usernameDb = utente.getUsername();
				String passwordDb = utente.getPassword();
				boolean checkUserPass = usernameDb.equalsIgnoreCase(username) && passwordDb.equals(password);
				if (checkUserPass) {
					isCorrect = true;
					System.out.println("Sono nel true");
				} else {
					isCorrect = false;
					System.out.println("Sono nel false");

				}
			}	
			em.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return isCorrect;
	}

}
