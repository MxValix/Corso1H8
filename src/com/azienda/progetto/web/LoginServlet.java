package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

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
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isUsernameValid = username!=null && !username.equals("");
		boolean isPasswordValid = password!=null && !password.equals("");
		HashMap<String, String> tagMap = new HashMap<String, String>();
		
		if (!(isUsernameValid && isPasswordValid)) { 
			 String h3 = "<h3>Username e/o password mancante.</h3>";
			 String btn1 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/login.html\" >Effettua di nuovo il login</a>";
			 String btn2 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/areaPubblica/pubblica.html\">Visita l'area pubblica</a>";
			 tagMap.put("h3", h3);
			 tagMap.put("btn1", btn1);
			 tagMap.put("btn2", btn2);	
		}
		else {
			tagMap = esitoLogin(username,password,request);
		}
		
		HtmlPageUtils.creaPagina(tagMap,response);

	}
		
	private HashMap<String,String> esitoLogin(String username,String password, HttpServletRequest request) {
		UtenteDao utenteDao = (UtenteDao)getServletContext().getAttribute(Costanti.CHIAVE_UTENTE_DAO);
		BusinessLogic businessLogic = (BusinessLogic)getServletContext().getAttribute(Costanti.CHIAVE_BUSINESS_LOGIC);
		boolean checkUserPassword = businessLogic.checkUsernamePassword(username, password);
		HashMap<String, String> tagMap = new HashMap<String, String>();
		String h3 = "";
		String btn1 = "";
		String btn2 = "";
		if (checkUserPassword) {
			request.getSession().setAttribute(Costanti.CHIAVE_SESSIONE, utenteDao);
			h3 = "<h3>Login effettuato.</h3>";
			btn1 =  "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/areaPrivata/privata.html\">Entra nell'area riservata</a>";
			
		}
		else {
			h3 = "<h3>Username e/o password errata.</h3>";
			btn1 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/login.html\">Effettua di nuovo il login</a>";
			btn2 = "<a style=\"text-decoration:none;color:black;\" href=\"/Corso1H8/html/areaPubblica/pubblica.html\">Visita l'area pubblica.</a>";

		}	
		
		tagMap.put("h3", h3);
		tagMap.put("btn1", btn1);
		tagMap.put("btn2", btn2);

		return tagMap;
	}

}

