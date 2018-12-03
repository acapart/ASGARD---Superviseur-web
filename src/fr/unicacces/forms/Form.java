package fr.unicacces.forms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Form {

	protected String MD5(String password) {

		String passwordToHash = password;
		String generatedPassword = null;
		try {
			// Creation message digest MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			//Ajoute les octects du mdp dans message digest
			md.update(passwordToHash.getBytes());
			//Recois les octects du hash
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
}
