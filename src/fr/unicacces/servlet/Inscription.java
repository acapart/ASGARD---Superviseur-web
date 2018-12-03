package fr.unicacces.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.unicacces.bdd.TestJDBC;
import fr.unicacces.beans.Administrator;
import fr.unicacces.beans.User;
import fr.unicacces.dao.AdministratorDAO;
import fr.unicacces.dao.DAO;
import fr.unicacces.forms.ConnexionForm;
import fr.unicacces.forms.InscriptionForm;



//@WebServlet("/Accueil")
public class Inscription extends HttpServlet{

	public static final String ATT_ADMIN = "Administrator";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/Inscription.jsp";

	private AdministratorDAO adminDAO;



	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		/* Préparation de l'objet formulaire */

		InscriptionForm form = new InscriptionForm();

		/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */

		Administrator administrator = form.inscrireUtilisateur( request );

		/* Stockage du formulaire et du bean dans l'objet request */

		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_ADMIN, administrator );




		DAO dao = DAO.getInstance();
		this.adminDAO = dao.getAdministratorDAO();


		/* Recup paramètres renseigné par l'user */

		String paramEmail = administrator.getEmail();
		String paramMotDePasse = administrator.getPassword();
		//String paramNom = administrator.getNom();
		System.out.println(paramEmail);
		System.out.println(paramMotDePasse);


		if ( paramEmail != null && paramMotDePasse != null  ) {

			Administrator admin = new Administrator();
			
			admin.setEmail(paramEmail);
			admin.setPassword(paramMotDePasse);
			/* Exécution d'une requête d'écriture */
			adminDAO.add(admin);
		}




		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}


}


