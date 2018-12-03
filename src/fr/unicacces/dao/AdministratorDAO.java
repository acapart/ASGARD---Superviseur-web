package fr.unicacces.dao;

import java.util.List;

import fr.unicacces.beans.Administrator;

public interface AdministratorDAO {
	void add(Administrator admin);
	
	void remove(Administrator user);
	
	void modif(Administrator user);
	
	List<Administrator> getAll();
	
	Administrator getOne(int id);

}
