package com.azienda.progetto.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.azienda.progetto.model.Utente;

public class UtenteDao implements DaoInterface<Utente>{
	
	private EntityManager em = null;
	
	public UtenteDao(){
		this(null);
	}
	
	public UtenteDao(EntityManager em){
		setManager(em);
	}
	
	public EntityManager getManager(){
		return em;
	}
	
	public void setManager(EntityManager em){
		 this.em=em;
	}
	
    public List<Utente> findByUsernameAndPassword(String username,String password){
		TypedQuery<Utente> query = em.createQuery("select x from Utente x where x.username = :username and x.password = :password",Utente.class);
	    query = query.setParameter("username",username);
	    query= query.setParameter("password",password);
	    List<Utente> utente = query.getResultList();
	    return utente;
    }

    @Override
	public void create(Utente utente){
		em.persist(utente);
		
	}

	@Override
	public List<Utente> retrieve(){
		List<Utente> persone = em.createQuery("from Utente)",Utente.class).getResultList();
		return persone;
	}

	@Override
	public void update(Utente utente) {
		em.persist(utente);
	}

	@Override
	public void delete(Utente utente) {
		em.remove(utente);
		
	}

	@Override
	public Utente findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utente> findByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}
}
