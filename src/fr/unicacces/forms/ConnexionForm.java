package fr.unicacces.forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.security.*;

import javax.servlet.http.HttpServletRequest;

import com.mysql.cj.Query;
import com.mysql.cj.QueryResult;
import com.sun.org.apache.bcel.internal.generic.Select;

import fr.unicacces.beans.Administrator;
import fr.unicacces.beans.User;
import fr.unicacces.dao.*;
import fr.unicacces.filters.Restriction_Filter;

public final class ConnexionForm extends Form{
	private static final String CHAMP_EMAIL  = "email";
	private static final String CHAMP_PASS   = "motdepasse";
	private int status; 
	private AdministratorDAO adminDAO;


	private String resultat;
	private Map<String, String> erreurs      = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Administrator connecterUtilisateur( HttpServletRequest request ) {
		/* R�cup�ration des champs du formulaire */
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );

		Administrator administrator = new Administrator();

		/* Validation du champ email et mot de passe. */
		try {
			validationEMAIL_MP(request);
			
		} catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		administrator.setEmail( email );
		administrator.setPassword(motDePasse);


		/* Initialisation du résultat global de la validation. */
		if ( status == 1) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
		}
		return administrator;
		
	}


	/**
	 * Valide l'adresse email saisie.
	 */

	private void validationEMAIL_MP( HttpServletRequest request )  {
		String email = getValeurChamp( request, CHAMP_EMAIL );

		String password = this.MD5(getValeurChamp( request, CHAMP_PASS ));

		DAO dao = DAO.getInstance();
		this.adminDAO = dao.getAdministratorDAO();

		List<Administrator> allAdmin = adminDAO.getAll();

		for(int i = 0; i< allAdmin.size();i++)
		{
			Administrator administrator = allAdmin.get(i);
			String emailrecup = administrator.getEmail();
			String passrecup = administrator.getPassword(); 
	

			if (emailrecup.equals(email) & passrecup.equals(password) ) { 
				status =1;
				
			}else {
				status = 0;
			}
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