package fr.unicacces.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	private String url;
	private String username;
	private String password;
	
	private DAO(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	Connection connexion = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultat = null;
	
	public static DAO getInstance(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
		}
		
		DAO instance = new DAO("jdbc:mysql://localhost:3306/asgard", "root", "root");
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public UserDAO getUserDAO() {
		return new UserDAOImpl(this);
	}
	
	public AdministratorDAO getAdministratorDAO() {
		return new AdministratorDAOImpl(this);
	}
}
