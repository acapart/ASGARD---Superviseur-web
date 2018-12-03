package fr.unicacces.forms;

import javax.servlet.http.HttpServletRequest;

import fr.unicacces.beans.User;
import fr.unicacces.dao.UserDAO;

public class ModifUserForm {

	public void modifUser(HttpServletRequest request, UserDAO userDAO) {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		int id = Integer.parseInt(request.getParameter("id"));

		User user = new User();
		user = userDAO.getOne(id);
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		userDAO.modif(user);
	}
}
