package fr.unicacces.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.unicacces.dao.DAO;
import fr.unicacces.dao.UserDAO;
import fr.unicacces.forms.ModifUserForm;

/**
 * Servlet implementation class ModifUtilisateur
 */
@WebServlet("/ModifUtilisateur")
public class ModifUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE              = "/Asgard_Superviseur_Web/Restreint/ModifUtilisateur.jsp";
	private UserDAO userDAO;
	
	public void init() throws ServletException{
		DAO dao = DAO.getInstance();
		this.userDAO = dao.getUserDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("id", id);
		request.setAttribute("user", userDAO.getOne(id));
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModifUserForm modifUser = new ModifUserForm();
		
		modifUser.modifUser(request, userDAO);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
