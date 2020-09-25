package pl.project.weekop.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import pl.project.weekop.dao.DAOFactory;
import pl.project.weekop.dao.DiscoveryDAO;
import pl.project.weekop.model.Discovery;
import pl.project.weekop.model.User;

public class DiscoveryService {
	
	public void addDiscovery(String name, String desc, String url, User user) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = factory.getDiscoveryDao();
		Discovery discovery = createDiscovery(name, desc, url, user);
		discoveryDao.create(discovery);
	}
	
	public Discovery getDiscoveryById(long discoveryId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = factory.getDiscoveryDao();
		Discovery discovery = discoveryDao.read(discoveryId);
		return discovery;
	}
	
	public boolean updateDiscovery(Discovery discovery) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = factory.getDiscoveryDao();
		boolean result = discoveryDao.update(discovery);
		return result;
	}
	
	private Discovery createDiscovery(String name, String desc, String url, User user) {
		Discovery discovery = new Discovery();
		discovery.setName(name);
		discovery.setDescription(desc);
		discovery.setUrl(url);
		discovery.setTimestamp(new Timestamp(new Date().getTime()));
		User userCopy = new User(user);
		discovery.setUser(userCopy);
		return discovery;
	}
	
	public List<Discovery> getAllDiscoveries(){
		return getAllDiscoveries(null);
	}
	
	public List<Discovery> getAllDiscoveries(Comparator<Discovery> comparator){
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDao = daoFactory.getDiscoveryDao();
		List<Discovery> discoveries = discoveryDao.getAll();
		if(comparator!=null && discoveries!=null) {
			discoveries.sort(comparator);
		}
		return discoveries;
	}

}
