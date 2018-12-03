package fr.unicacces.forms;

import javax.servlet.http.HttpServletRequest;

import fr.unicacces.beans.User;
import fr.unicacces.dao.UserDAO;

public class AddUserForm {
	public void addUser(HttpServletRequest request, UserDAO userDAO) {
		User user = new User();
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		
		userDAO.add(user);
	}

}
