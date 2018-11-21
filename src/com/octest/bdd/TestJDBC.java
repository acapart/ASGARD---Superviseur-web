package com.octest.bdd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.octest.beans.User;

public class TestJDBC {
    /* La liste qui contiendra tous les r�sultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    
    	public List<String> executerTests( HttpServletRequest request ) {
    	    /* Chargement du driver JDBC pour MySQL */
    	    try {
    	        messages.add( "Chargement du driver..." );
    	        Class.forName( "com.mysql.jdbc.Driver" );
    	        messages.add( "Driver charg� !" );
    	    } catch ( ClassNotFoundException e ) {
    	        messages.add( "Erreur lors du chargement : le driver n'a pas �t� trouv� dans le classpath ! <br/>"
    	                + e.getMessage() );
    	    }

    	    /* Connexion � la base de donn�es */
    	    String url = "jdbc:mysql://localhost:3306/bdd_asgard";
    	    String utilisateur = "root";
    	    String motDePasse = "root";
    	    Connection connexion = null;
    	    Statement statement = null;
    	    ResultSet resultat = null;
    	    try {
    	        messages.add( "Connexion � la base de donn�es..." );
    	        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    	        messages.add( "Connexion r�ussie !" );

    	        /* Cr�ation de l'objet g�rant les requ�tes */
    	        statement = connexion.createStatement();
    	        messages.add( "Objet requ�te cr�� !" );

    	       
    	        
    	        /*R�cup�ration des param�tres d'URL saisis par l'utilisateur */
    	        String paramEmail = request.getParameter( "email" );
    	        String paramMotDePasse = request.getParameter( "motDePasse" );
    	        String paramNom = request.getParameter( "nom" );

    	        if ( paramEmail != null && paramMotDePasse != null && paramNom != null ) {
    	            /* Ex�cution d'une requ�te d'�criture */
    	        /* Ex�cution d'une requ�te d'�criture */
    	        int statut = statement.executeUpdate( "INSERT INTO User (email, mot_de_passe, nom, date_inscription) VALUES ('alex@hotmail.fr', MD5('123'), 'alexis', NOW());" );

    	        /* Formatage pour affichage dans la JSP finale. */
    	        messages.add( "R�sultat de la requ�te d'insertion : " + statut + "." );
    	       }
    	    } catch ( SQLException e ) {
    	        messages.add( "Erreur lors de la connexion : <br/>"
    	                + e.getMessage() );
    	    } finally {
    	        messages.add( "Fermeture de l'objet ResultSet." );
    	        if ( resultat != null ) {
    	            try {
    	                resultat.close();
    	            } catch ( SQLException ignore ) {
    	            }
    	        }
    	        messages.add( "Fermeture de l'objet Statement." );
    	        if ( statement != null ) {
    	            try {
    	                statement.close();
    	            } catch ( SQLException ignore ) {
    	            }
    	        }
    	        messages.add( "Fermeture de l'objet Connection." );
    	        if ( connexion != null ) {
    	            try {
    	                connexion.close();
    	            } catch ( SQLException ignore ) {
    	            }
    	        }
    	    }

    	    return messages;
    	
        
    }
}