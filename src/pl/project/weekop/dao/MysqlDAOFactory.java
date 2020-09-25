package pl.project.weekop.dao;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDao() {
		return new UserDAOImpl();
	}

	@Override
	public DiscoveryDAO getDiscoveryDao() {
		return new DiscoveryDAOImpl();
	}

	@Override
	public VoteDAO getVoteDao() {
		return new VoteDAOImpl();
	}

	
	
	

	
	
	

}
