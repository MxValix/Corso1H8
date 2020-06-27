package com.azienda.progetto.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

public class HtmlPageUtils {
	
	public static void creaPagina(final HashMap<String,String> tagMap, final HttpServletResponse response) throws IOException {
		
		boolean checkBtn2 = tagMap.get("btn2")!=null && !tagMap.get("btn2").isEmpty();
		String type = "text/html";
		String html = "<div style=\"text-align:center\">";
		html += "<h3 style=\"text-align:center\">" + tagMap.get("h3") + "</h3>";
		html += "<span style=\"display:inline\">";
		html += "<button style=\"margin:5px\">" + tagMap.get("btn1") + "</button>";
		if (checkBtn2) {
			html += "<button style=\"margin:5px\">" + tagMap.get("btn2") + "</button>";
		}
		html += "</span></div>";
		response.setHeader("Cache-control", "no-store");
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		Date date = new Date();

		response.setContentType(type);
		PrintWriter printWriter = response.getWriter();
		printWriter.println(html);
		printWriter.flush();
		printWriter.close();
		
	}
}
