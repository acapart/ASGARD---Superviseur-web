package fr.unicacces.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.unicacces.beans.Administrator;

public class AdministratorDAOImpl implements AdministratorDAO{
	private DAO dao;

	public AdministratorDAOImpl(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void add(Administrator admin) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		/*
		try {
			connexion = dao.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO User(firstname,lastname) VALUES(?,?);");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		UserDAO userDao = dao.getUserDAO();*/
		
		try {
			connexion = dao.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO asgard_administrator(email,password) VALUES(?,?);");
			preparedStatement.setString(1, admin.getEmail());
			preparedStatement.setString(2, admin.getPassword());
			
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();			
		}	
	}

	@Override
	public void remove(Administrator admin) {
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM asgard_administrator WHERE id=?;");
            preparedStatement.setInt(1, admin.getId());
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void modif(Administrator admin) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE asgard_administrator SET email = ?, password = ? WHERE id = ?;");
            
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setInt(3, admin.getId());
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Administrator> getAll() {
		List<Administrator> result = new ArrayList<Administrator>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = dao.getConnection();
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery( "SELECT * FROM asgard_administrator;" );
			
			while(resultat.next())
			{
				Administrator admin = new Administrator();
				String email = resultat.getString("email");
				String password = resultat.getString("password");
				int idrecup = resultat.getInt("id");
				
				admin.setEmail(email);;
				admin.setPassword(password);;
				admin.setId(idrecup);
				
				result.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Administrator getOne(int id) {
		Administrator result = new Administrator();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM asgard_administrator WHERE id=?;");
            preparedStatement.setInt(1, id);
            
            resultat =  preparedStatement.executeQuery();
            
            if(resultat.next()) {

				String email = resultat.getString("email");
				String password = resultat.getString("password");
				int idRecup = resultat.getInt("id");

				result.setEmail(email);
				result.setPassword(password);;
				result.setId(idRecup);
    		}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
		return result;
	}
	
}
