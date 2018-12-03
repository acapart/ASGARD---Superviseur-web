package fr.unicacces.forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.unicacces.beans.Administrator;
import fr.unicacces.beans.User;

public class InscriptionForm extends Form {
	private static final String CHAMP_EMAIL  = "email";
	private static final String CHAMP_PASS   = "motdepasse";
	private static final String CHAMP_CONF   = "confirmation";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Administrator inscrireUtilisateur( HttpServletRequest request ) {
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF );
		

		Administrator admin = new Administrator();

		try {
			validationEmail( email );
		} catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}
		admin.setEmail( email );

		try {
			validationMotsDePasse( motDePasse, confirmation );
		} catch ( Exception e ) {
			setErreur( CHAMP_PASS, e.getMessage() );
			setErreur( CHAMP_CONF, null );
		}
		
		admin.setPassword( this.MD5(motDePasse) );
		
		

		if ( erreurs.isEmpty() ) {
			resultat = "Succès de l'inscription.";
		} else {
			resultat = "Échec de l'inscription.";
		}

		return admin;
	}
	
	private void validationEmail( String email ) throws Exception {
		if ( email != null ) {
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new Exception( "Merci de saisir une adresse mail valide." );
			}
		} else {
			throw new Exception( "Merci de saisir une adresse mail." );
		}
	}

	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
		if ( motDePasse != null && confirmation != null ) {
			if ( !motDePasse.equals( confirmation ) ) {
				throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
			} else if ( motDePasse.length() < 8 ) {
				throw new Exception( "Les mots de passe doivent contenir au moins 8 caractères." );
			}
		} else {
			throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
		}
		
	} 
	

	
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur.trim();
		}
	}
}