package com.azienda.progetto.web;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.businessLogic.UtenteDao;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;
import com.azienda.progetto.utils.EntityManagerUtils;


@WebServlet(value="/iS", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
		EntityManager entityManager = EntityManagerUtils.apriConnessione();
		
		UtenteDao utenteDao = new UtenteDao(entityManager);
	    BusinessLogic businessLogic = new BusinessLogic(entityManager,utenteDao);
		getServletContext().setAttribute(Costanti.CHIAVE_SERVLET, entityManager);		
		getServletContext().setAttribute(Costanti.CHIAVE_UTENTE_DAO, utenteDao);
		getServletContext().setAttribute(Costanti.CHIAVE_BUSINESS_LOGIC, businessLogic);
		
	    Utente utente = new Utente("pippo", "Pluto");
	    businessLogic.create(utente);
	}
	
	
	public void destroy(){	
		EntityManager entityManager = (EntityManager) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET);	
		entityManager.close();
	}
	
 
}
