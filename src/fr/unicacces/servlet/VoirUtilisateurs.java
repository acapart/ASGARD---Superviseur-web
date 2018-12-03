package fr.unicacces.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.unicacces.dao.DAO;
import fr.unicacces.dao.UserDAO;
import fr.unicacces.forms.AddUserForm;

/**
 * Servlet implementation class VoirUtilisateurs
 */
@WebServlet("/VoirUtilisateurs")
public class VoirUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE              = "/Asgard_Superviseur_Web/Restreint/VoirUtilisateur.jsp";
	private UserDAO userDAO;
       
   
    public void init() throws ServletException{
    	DAO dao = DAO.getInstance();
    	this.userDAO = dao.getUserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", userDAO.getAll());
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddUserForm addUserForm = new AddUserForm();
		addUserForm.addUser(request, userDAO);
		doGet(request, response);
	}

}
