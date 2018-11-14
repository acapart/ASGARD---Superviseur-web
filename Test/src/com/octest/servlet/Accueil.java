package com.octest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.User;
import com.octest.forms.ConnexionForm;


@WebServlet("/Test")
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
        public static final String ATT_USER         = "utilisateur";
        
        public static final String VUE              = "/WEB-INF/Accueil.jsp";

        public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
            /* Affichage de la page de connexion */
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }

        public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
          

          

            /* Récupération de la session depuis la requête */
            HttpSession session = request.getSession();

            /**
             * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
             * Utilisateur à la session, sinon suppression du bean de la session.
             */
       
            

            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
    }
