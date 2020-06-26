package com.azienda.progetto.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.businessLogic.UtenteDao;
import com.azienda.progetto.utils.Costanti;
import com.azienda.progetto.utils.HtmlPageUtils;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isUsernameValid = username!=null && !username.equals("");
		boolean isPasswordValid = password!=null && !password.equals("");
		String divEsito = "";
		
		if (!(isUsernameValid && isPasswordValid)) { 
			 divEsito = "<h3>Username e/o password mancante.</h3>";
			 divEsito += "<a href=\"/Corso1H8/html/login.html\">Effettua di nuovo il login</a>";
			 divEsito += "<a href=\"/Corso1H8/html/areaPubblica/pubblica.html\">Visita l'area pubblica.</a>";
		}
		else {
			divEsito = esitoLogin(username,password,request);
		}
		
		HtmlPageUtils.creaPagina(divEsito,response);

	}
		
	private String esitoLogin(String username,String password, HttpServletRequest request) {
		UtenteDao utenteDao = (UtenteDao)getServletContext().getAttribute(Costanti.CHIAVE_UTENTE_DAO);
		BusinessLogic businessLogic = (BusinessLogic)getServletContext().getAttribute(Costanti.CHIAVE_BUSINESS_LOGIC);
		boolean checkUserPassword = businessLogic.checkUsernamePassword(username, password);
		String divEsito = "";
		if (checkUserPassword) {
			request.getSession().setAttribute(Costanti.CHIAVE_SESSIONE, utenteDao);
			divEsito = "<h3>Login effettuato.</h3>";
			divEsito += "<a href=\"/Corso1H8/html/areaPrivata/privata.html\">Entra nell'area riservata</a>";
		}
		else {
			divEsito = "<h3>Username e/o password errata.</h3>";
			divEsito += "<a href=\"/Corso1H8/html/login.html\">Effettua di nuovo il login</a>";
			divEsito += "<a href=\"/Corso1H8/html/areaPubblica/pubblica.html\">Visita l'area pubblica.</a>";
		}	
		return divEsito;
	}

}

