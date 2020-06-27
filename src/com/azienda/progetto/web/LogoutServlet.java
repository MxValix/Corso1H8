package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.setHeader("Cache-control", "no-store");
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		String h3 = "<h3>Logout avvenuto con successo.</h3>";
		String btn1 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/\">Effettua di nuovo il login</a>";
		String btn2 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/areaPubblica/pubblica.html\">Visita l'area pubblica</a>";
		HashMap<String,String> tagMap = new HashMap<String,String>();
		tagMap.put("h3", h3);
		tagMap.put("btn1", btn1);
		tagMap.put("btn2", btn2);
        HttpSession session=request.getSession();  
        session.invalidate();  
		HtmlPageUtils.creaPagina(tagMap, response);
		
	}

}
