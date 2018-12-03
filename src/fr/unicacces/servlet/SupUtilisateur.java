package fr.unicacces.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.unicacces.beans.User;
import fr.unicacces.dao.DAO;
import fr.unicacces.dao.UserDAO;

/**
 * Servlet implementation class SupUtilisateur
 */
@WebServlet("/SupUtilisateur")
public class SupUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_INITIAL     = "/Asgard_Superviseur_Web/Restreint/SupUtilisateur.jsp";
	public static final String VUE     = "Asgard_Superviseur_Web/Restreint/VoirUtilisateur.jsp";
   
	private UserDAO userDAO;
	
	public void init() {
		DAO dao = DAO.getInstance();
		this.userDAO = dao.getUserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean sup = Boolean.parseBoolean(request.getParameter("sup"));
		User user = new User();
		user = userDAO.getOne(id);
		if(sup) {
			userDAO.remove(user);
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
		else {
			request.setAttribute("id", id);
			request.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher( VUE_INITIAL ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
