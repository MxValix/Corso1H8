package com.azienda.progetto.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.utils.Costanti;
import com.azienda.progetto.utils.HtmlPageUtils;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute(Costanti.CHIAVE_SESSIONE);
		String h3 = "<h3>Logout avvenuto con successo.</h3>";
		String btn1 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/\">Effettua di nuovo il login</a>";
		String btn2 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/areaPubblica/pubblica.html\">Visita l'area pubblica</a>";
		HashMap<String,String> tagMap = new HashMap<String,String>();
		tagMap.put("h3", h3);
		tagMap.put("btn1", btn1);
		tagMap.put("btn2", btn2);
		HtmlPageUtils.creaPagina(tagMap, response);
		
	}

}
