package com.octest.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.octest.bdd.DAOFactory;
import com.octest.beans.User;

public class UserDAO {
    private DAOFactory daoFactory;

    UserDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

   
    public void add(User user) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom) VALUES(?);");
            preparedStatement.setString(1, user.getNom());
            //preparedStatement.setString(2, user.getPrenom());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
    public List<User> lister() {
        List<User> utilisateurs = new ArrayList<User>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM noms;");

            while (resultat.next()) {
                String nom = resultat.getString("nom");
                //String prenom = resultat.getString("prenom");

                User user = new User();
                user.setNom(nom);
                //user.setPrenom(prenom);

                utilisateurs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

}