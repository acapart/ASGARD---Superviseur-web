package fr.unicacces.dao;

import java.util.List;

import fr.unicacces.beans.User;

public interface UserDAO {
	void add (User user);
	
	void remove(User user);
	
	void modif(User user);
	
	List<User> getAll();
	
	User getOne(int id);
	
	
}
