package com.azienda.progetto.web;

import java.io.IOException;

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
import com.azienda.progetto.utils.FiltroUtils;

import javafx.util.Pair;

@WebFilter("/html/*")
public class FiltroAccessi implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = FiltroUtils.setHeaderResponse(response);
		UtenteDao utenteDao = (UtenteDao) httpRequest.getSession().getAttribute(Costanti.CHIAVE_SESSIONE);
		String pagePath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		Pair<Boolean,String> chainPair = FiltroUtils.uriDestinazioneFilter(pagePath, utenteDao);
		if (chainPair.getKey()) {
			chain.doFilter(httpRequest, httpResponse);
		}
		else {
			pagePath = chainPair.getValue();
			httpRequest.getRequestDispatcher(pagePath).forward(httpRequest, httpResponse);
		}
	}
}
