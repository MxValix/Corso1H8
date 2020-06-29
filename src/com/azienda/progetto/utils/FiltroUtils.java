package com.azienda.progetto.utils;

import java.time.LocalDateTime;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.UtenteDao;

import javafx.util.Pair;

public class FiltroUtils {

	public static HttpServletResponse setHeaderResponse(ServletResponse response) {
		String justNow = LocalDateTime.now().toString();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Last-modified", justNow) ;
		httpResponse.setHeader("Cache-control", "no-store");
		return httpResponse;
	}

	public static Pair<Boolean,String> uriDestinazioneFilter(String pagePath, UtenteDao utenteDao) {
		String path = "/";
		String login = "/html/login.html";
		String negato = "/html/accesso-negato.html";
		String privata = "/html/areaPrivata/privata.html";
		String pubblica = "/html/areaPubblica/pubblica.html";
		String pageNotFound = "/html/404.html";
		String areaPrivata = privata.substring(0, privata.lastIndexOf("/") + 1);
		boolean isChain = false;
		if (utenteDao!=null) {
			boolean isUtente = pagePath.equals(login) || pagePath.equals(path) || pagePath.equals(negato);
			if (isUtente) {
				pagePath = privata;
			} else if (pagePath.equals(pubblica) || pagePath.equals(privata)) {
				isChain = true;
			}
			else {
				pagePath = pageNotFound;
			}
		} 
		else {
			if (pagePath.startsWith(areaPrivata)) {
				pagePath = negato;
			}
			else if (pagePath.equals(path)) {
				pagePath = login;
			}
			else if (pagePath.equals(login) || pagePath.equals(pubblica)) {
				isChain = true;

			}
			else {
				pagePath = pageNotFound;
			}
		}
		Pair<Boolean,String> chainPair = new Pair<Boolean,String>(isChain,pagePath);
		return chainPair;

	}

}
