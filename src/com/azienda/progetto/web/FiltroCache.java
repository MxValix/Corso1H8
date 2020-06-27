package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.UtenteDao;
import com.azienda.progetto.utils.Costanti;


@WebFilter("/html/login.html")
public class FiltroCache implements Filter {

  
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Last-modified", LocalDateTime.now().toString());
		httpResponse.setHeader("Cache-control", "no-store");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		UtenteDao utenteDao = (UtenteDao) httpRequest.getSession().getAttribute(Costanti.CHIAVE_SESSIONE);
		
		if (utenteDao != null) {
			httpRequest.getRequestDispatcher("/html/areaPrivata/privata.html").forward(httpRequest, response);
		} else {
			chain.doFilter(httpRequest, response);
		}
	}
	

}
