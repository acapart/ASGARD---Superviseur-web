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
    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    
    	public List<String> executerTests( HttpServletRequest request ) {
    	    /* Chargement du driver JDBC pour MySQL */
    	    try {
    	        messages.add( "Chargement du driver..." );
    	        Class.forName( "com.mysql.jdbc.Driver" );
    	        messages.add( "Driver chargé !" );
    	    } catch ( ClassNotFoundException e ) {
    	        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
    	                + e.getMessage() );
    	    }

    	    /* Connexion à la base de données */
    	    String url = "jdbc:mysql://192.168.1.80:3306/bdd_asgard";
    	    String utilisateur = "root";
    	    String motDePasse = "root";
    	    Connection connexion = null;
    	    Statement statement = null;
    	    ResultSet resultat = null;
    	    try {
    	        messages.add( "Connexion à la base de données..." );
    	        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    	        messages.add( "Connexion réussie !" );

    	        /* Création de l'objet gérant les requêtes */
    	        statement = connexion.createStatement();
    	        messages.add( "Objet requête créé !" );

    	       
    	        
    	        /*Récupération des paramètres d'URL saisis par l'utilisateur */
    	        String paramEmail = request.getParameter( "email" );
    	        String paramMotDePasse = request.getParameter( "motDePasse" );
    	        String paramNom = request.getParameter( "nom" );

    	        if ( paramEmail != null && paramMotDePasse != null && paramNom != null ) {
    	            /* Exécution d'une requête d'écriture */
    	        /* Exécution d'une requête d'écriture */
    	        int statut = statement.executeUpdate( "INSERT INTO User (email, mot_de_passe, nom, date_inscription) VALUES ('alex@hotmail.fr', MD5('123'), 'alexis', NOW());" );

    	        /* Formatage pour affichage dans la JSP finale. */
    	        messages.add( "Résultat de la requête d'insertion : " + statut + "." );
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