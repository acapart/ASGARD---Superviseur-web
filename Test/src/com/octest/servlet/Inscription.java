package com.octest.servlet;

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

import com.octest.beans.User;
import com.octest.forms.ConnexionForm;
import com.octest.forms.InscriptionForm;

import com.octest.bdd.TestJDBC;



//@WebServlet("/Accueil")
public class Inscription extends HttpServlet {

	public static final String ATT_USER = "User";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/Inscription.jsp";



	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{


		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		/* Préparation de l'objet formulaire */

		InscriptionForm form = new InscriptionForm();

		/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */

		User User = form.inscrireUtilisateur( request );

		/* Stockage du formulaire et du bean dans l'objet request */

		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_USER, User );



		try {

			System.out.println( "Chargement du driver..." );
			Class.forName( "com.mysql.jdbc.Driver" );
			System.out.println( "Driver chargé !" );
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
					+ e.getMessage() );
		}

		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/bdd_asgard";
		String utilisateur = "root";
		String motDePasse = "root";
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;


		try {  

			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

			/* Création de l'objet gérant les requêtes */
			statement = connexion.createStatement();

			/* Recup paramètres renseigné par l'user */

			String paramEmail = User.getEmail();
			String paramMotDePasse = User.getMotDePasse();
			String paramNom = User.getNom();
			System.out.println(paramEmail);
			System.out.println(paramMotDePasse);
			System.out.println(paramNom);

			if ( paramEmail != null && paramMotDePasse != null && paramNom != null ) {


				/* Exécution d'une requête d'écriture */
				int statut = statement.executeUpdate( "INSERT INTO User (email, mot_de_passe, nom, date_inscription) "
						+ "VALUES ('" + paramEmail + "', MD5('" + paramMotDePasse + "'), '" + paramNom + "', NOW());" );
			}

		
		} catch ( SQLException e ) {
			System.out.println( "Erreur lors de la connexion : <br/>"
	                + e.getMessage() );
	    } finally {
	    	System.out.println( "Fermeture de l'objet ResultSet." );
	        if ( resultat != null ) {
	            try {
	                resultat.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        System.out.println( "Fermeture de l'objet Statement." );
	        if ( statement != null ) {
	            try {
	                statement.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        System.out.println( "Fermeture de l'objet Connection." );
	        if ( connexion != null ) {
	            try {
	                connexion.close();
	            } catch ( SQLException ignore ) {
	            }

		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	}
}
	}

