package com.octest.forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import java.security.*;

import javax.servlet.http.HttpServletRequest;

import com.mysql.cj.Query;
import com.mysql.cj.QueryResult;
import com.octest.beans.User;
import com.sun.org.apache.bcel.internal.generic.Select;

public final class ConnexionForm {
	private static final String CHAMP_EMAIL  = "email";
	private static final String CHAMP_PASS   = "motdepasse";

	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User connecterUtilisateur( HttpServletRequest request ) {
		/* R�cup�ration des champs du formulaire */
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );

		User utilisateur = new User();
		
		
		
	    
		/* Validation du champ email et mot de passe. */
		try {
			validationEMAIL_MP(request);
			//System.out.println( "verification" );
		} catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		utilisateur.setEmail( email );
		utilisateur.setMotDePasse(motDePasse);
		

		/* Initialisation du résultat global de la validation. */
	    if ( erreurs.isEmpty()) {
	        resultat = "Succès de la connexion.";
	    } else {
	        resultat = "Échec de la connexion.";
	    }
	    return utilisateur;
	}
	
	


	private String MD5(String password) {

		String passwordToHash = password;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			//Add password bytes to digest
			md.update(passwordToHash.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
		return generatedPassword;
	}
	
	
	/**
	 * Valide l'adresse email saisie.
	 */

	private void validationEMAIL_MP( HttpServletRequest request ) throws Exception {
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String password = this.MD5(getValeurChamp( request, CHAMP_PASS ));
		try {

			System.out.println( "Chargement du driver..." );
			Class.forName( "com.mysql.jdbc.Driver" );
			System.out.println( "Driver charg� !" );
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur lors du chargement : le driver n'a pas �t� trouv� dans le classpath ! <br/>"
					+ e.getMessage() );
		}

		System.out.println( "Au dessus le la co bdd" );

		/* Connexion � la base de donn�es */
		String url = "jdbc:mysql://localhost:3306/bdd_asgard";
		String utilisateur = "root";
		String motDePasse = "root";
		Connection connexion = null;

		System.out.println( "On est connecte" );
		Statement statement = null;


		try {

			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

			/* Création de l'objet gérant les requêtes */
			statement = connexion.createStatement();

			ResultSet resultat = statement.executeQuery( "SELECT email, mot_de_passe FROM User;" );

			System.out.println( "On a cherché" );



			while(resultat.next())
			{
				String emailrecup = resultat.getString("email"); 
				String passrecup = resultat.getString("mot_de_passe"); 
				//System.out.println( "recuperation email et mot de passe" );
				System.out.println(emailrecup);
				System.out.println(passrecup);


				if (emailrecup.equals(email) & passrecup.equals(password) ) { 
					System.out.println( "email et mot de passe valides" );
					erreurs.isEmpty();
					System.out.println(emailrecup);
					System.out.println(passrecup);

				}else {
					throw new Exception("");
					
				}

			}
		}catch ( Exception e ) {
			System.out.println( "ERREUR RECHERCHE DANS USER"
					+ e.getMessage() );
		}

	}


	/*
	 * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}

	/*
	 * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}
}