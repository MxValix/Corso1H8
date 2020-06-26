package com.azienda.progetto.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HtmlPageUtils {
	
	public static void creaPagina(final String divElements, final HttpServletResponse response) throws IOException {
		String div = "<div style=\"text-align:center\">" + divElements + "</div>";
		PrintWriter printWriter = response.getWriter();
		printWriter.println(div);
		printWriter.flush();
		printWriter.close();
	}
}
