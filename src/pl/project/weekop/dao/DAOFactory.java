package pl.project.weekop.dao;

import pl.project.weekop.exception.NoSuchDbTypeException;

public abstract class DAOFactory {
	
	public static final  int MYSQL_DAO=1;
	
	private static DAOFactory daoFactory;
	
	public abstract UserDAO getUserDao();
	
	public abstract DiscoveryDAO getDiscoveryDao();
	
	public abstract VoteDAO getVoteDao();
	
	public static DAOFactory getDAOFactory(){
		if(daoFactory==null) {
			try {
				daoFactory = getDAOFactory(MYSQL_DAO);
			} catch(NoSuchDbTypeException e) {
				e.printStackTrace();
			}
		}
		return daoFactory;
	}
	
	private static DAOFactory getDAOFactory(int type) throws NoSuchDbTypeException {
		switch(type) {
		case MYSQL_DAO:
			return new MysqlDAOFactory();
		default:
			throw new NoSuchDbTypeException();
		}
	}
	
}
