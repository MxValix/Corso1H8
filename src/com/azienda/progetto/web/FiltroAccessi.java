package com.azienda.progetto.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.azienda.progetto.businessLogic.UtenteDao;
import com.azienda.progetto.utils.Costanti;


@WebFilter("/html/areaPrivata/*")
public class FiltroAccessi implements Filter {

  
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		UtenteDao utenteDao = (UtenteDao) httpRequest.getSession().getAttribute(Costanti.CHIAVE_SESSIONE);
		
		if (utenteDao == null) {
			httpRequest.getRequestDispatcher("/html/login.html").forward(httpRequest, response);
		} else {
			chain.doFilter(httpRequest, response);
		}
	}
	

}
