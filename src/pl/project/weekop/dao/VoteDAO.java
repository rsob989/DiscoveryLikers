package pl.project.weekop.dao;

import pl.project.weekop.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {
	
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);
	
	

}
