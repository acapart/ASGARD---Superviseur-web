package com.octest.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.octest.dao.UtilisateurDaoImpl;

public class DAOFactory {
    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DAOFactory instance = new DAOFactory(
                "jdbc:mysql://192.168.1.80:3306/bdd_asgard", "root", "root");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao
    public UserDAO getUtilisateurDao() {
        return new UserDAO(this);
    }
}