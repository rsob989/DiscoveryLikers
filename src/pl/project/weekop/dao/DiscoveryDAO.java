package pl.project.weekop.dao;

import java.util.List;

import pl.project.weekop.model.Discovery;

public interface DiscoveryDAO extends GenericDAO<Discovery, Long> {

	List<Discovery> getAll();	

}
