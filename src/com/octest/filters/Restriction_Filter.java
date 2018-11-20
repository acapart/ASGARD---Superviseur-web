package com.octest.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Restriction_Filter implements Filter {
	
		public static final String ACCES_PUBLIC     = "/Connexion";
	    public static final String ATT_SESSION_USER = "sessionUtilisateur";
	
public void init( FilterConfig config ) throws ServletException {
}

	public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException,
	ServletException {

		/* Cast des objets request et response */
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/**
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */
		if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
			/* Redirection vers la page publique */
			response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
		} else {
			/* Affichage de la page restreinte */
			chain.doFilter( request, response );
		}
	}

	public void destroy() {
	}
}