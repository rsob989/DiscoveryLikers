package pl.project.weekop.service;


import java.sql.Timestamp;
import java.util.Date;

import pl.project.weekop.dao.DAOFactory;
import pl.project.weekop.dao.VoteDAO;
import pl.project.weekop.model.Vote;
import pl.project.weekop.model.VoteType;

public class VoteService {
	
	public Vote addVote(long discoveryId, long userId, VoteType voteType) {
		Vote vote = new Vote();
		vote.setDate(new Timestamp(new Date().getTime()));
		vote.setDiscoveryId(discoveryId);
		vote.setVoteType(voteType);
		vote.setUserId(userId);
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = factory.getVoteDao();
		vote = voteDao.create(vote);
		return vote;
	}
	
	public Vote updateVote(long discoveryId, long userId, VoteType voteType) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = factory.getVoteDao();
		Vote voteToUpdate = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
		if(voteToUpdate!=null) {
			voteToUpdate.setVoteType(voteType);
			voteDao.update(voteToUpdate);
		}
		return voteToUpdate;
	}
	
	public Vote addOrUpdateVote(long discoveryId, long userId, VoteType voteType) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = factory.getVoteDao();
		Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
		Vote resultVote = null;
		if(vote==null) {
			resultVote = addVote(discoveryId, userId, voteType);
		} else {
			resultVote = updateVote(discoveryId, userId, voteType);
		}
		return resultVote;
	}
	
	public Vote getVoteByDiscoverUserId(long discoveryId, long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDao = factory.getVoteDao();
		Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
		return vote;		
	}

}
