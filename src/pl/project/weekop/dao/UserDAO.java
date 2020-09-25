package pl.project.weekop.dao;

import java.util.List;

import pl.project.weekop.model.User;


public interface UserDAO extends GenericDAO<User, Long> {

	
	List<User> getAll();
	User getUserByUsername(String username);	

}
