package com.octest.beans;

public class User {
	
	private String email;
    private String motDePasse;
    private String nom;

    public User(String email, String motDePasse, String nom) {
    	this.email = email;
    	this.motDePasse = motDePasse;
    	this.nom = nom;
    }
    
    public User() {
    	
    }
    
    public void setEmail(String email) {
	this.email = email;
    }
    public String getEmail() {
	return email;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
	return motDePasse;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }
    public String getNom() {
	return nom;
    }
}