package fr.unicacces.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.unicacces.beans.User;

public class UserDAOImpl implements UserDAO{
	private DAO dao;
	
	public UserDAOImpl(DAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = dao.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO asgard_user(firstname,lastname) VALUES(?,?);");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();			
		}
	}

	public List<User> getAll(){
		List<User> result = new ArrayList<User>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = dao.getConnection();
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery( "SELECT * FROM asgard_user;" );
			
			while(resultat.next())
			{
				User user = new User();
				String firstName = resultat.getString("firstname");
				String lastname = resultat.getString("lastname");
				int idrecup = resultat.getInt("id");
				
				user.setFirstName(firstName);
				user.setLastName(lastname);
				user.setId(idrecup);
				
				result.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public User getOne(int id) {
		User result = new User();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM asgard_user WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat =  preparedStatement.executeQuery();
            
            if(resultat.next()) {
				String firstName = resultat.getString("firstname");
				String lastname = resultat.getString("lastname");
				int idrecup = resultat.getInt("id");

				result.setFirstName(firstName);
				result.setLastName(lastname);
				result.setId(idrecup);
    		}
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public void remove(User user) {
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM asgard_user WHERE id=?;");
            preparedStatement.setInt(1, user.getId());
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void modif(User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE asgard_user SET firstname = ?, lastname = ? WHERE id = ?;");
            
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setInt(3, user.getId());
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
