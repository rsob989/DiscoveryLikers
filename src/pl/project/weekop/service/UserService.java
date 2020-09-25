package pl.project.weekop.service;

import pl.project.weekop.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pl.project.weekop.dao.DAOFactory;
import pl.project.weekop.dao.UserDAO;

public class UserService {
	
	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		String md5Pass = encryptPassword(password);
		user.setPassword(md5Pass);
		user.setEmail(email);
		user.setIs_active(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDao();
		userDao.create(user);
	}
	
	private String encryptPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(password.getBytes());
		String md5Password = new BigInteger(1, digest.digest()).toString(16);
		return md5Password;
	}
	
	public User getUserById(long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDao();
		User user = userDAO.read(userId);
		return user;
	}
	
	public User getUserByUsername(String username) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDao();
		User user = userDAO.getUserByUsername(username);
		return user;
	}

}
