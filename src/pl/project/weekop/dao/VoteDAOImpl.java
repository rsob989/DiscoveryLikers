package pl.project.weekop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.project.weekop.model.Vote;
import pl.project.weekop.model.VoteType;
import pl.project.weekop.util.ConnectionProvider;

public class VoteDAOImpl implements VoteDAO {

	private static final String CREATE = 
			"INSERT INTO vote(discovery_id, user_id, date, type) VALUES(:discovery_id, :user_id, :date, :type);";
	private static final String READ =
			"SELECT vote_id, discovery_id, user_id, date, type FROM vote WHERE vote_id=:vote_id;";
	private static final String READ_VOTE_BY_DISCOVERY_USE_IDS =
			"SELECT vote_id, discovery_id, user_id, date, type FROM vote WHERE user_id=:user_id AND discovery_id=:discovery_id;";
	private static final String UPDATE_VOTE =
			"UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id";

	NamedParameterJdbcTemplate template;
	
	public VoteDAOImpl() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}

	@Override
	public Vote create(Vote object) {
		Vote vote = new Vote(object);
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("discovery_id", vote.getDiscoveryId());
		paramMap.put("user_id", vote.getUserId());
		paramMap.put("date", vote.getDate());
		paramMap.put("type", vote.getVoteType().toString());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE, paramSource, holder);
		if(update>0) {
			vote.setId(holder.getKey().longValue());
		}
		return vote;
	}

	@Override
	public Vote read(Long primaryKey) {
		Vote vote = null;
		SqlParameterSource paramSource = new MapSqlParameterSource("vote_id", primaryKey);
		vote = template.queryForObject(READ, paramSource, new VoteRowMapper());
		return vote;
	}

	@Override
	public boolean update(Vote object) {
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("date", object.getDate());
		paramMap.put("type", object.getVoteType().toString());
		paramMap.put("vote_id", object.getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(UPDATE_VOTE, paramSource);
		if(update>0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Vote> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
		Vote vote = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", userId);
		paramMap.put("discovery_id", discoveryId);
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		try {
		vote = template.queryForObject(READ_VOTE_BY_DISCOVERY_USE_IDS, paramSource, new VoteRowMapper());
		} catch(EmptyResultDataAccessException e) {
			
		}
		return vote;
	}
	
	private class VoteRowMapper implements RowMapper<Vote>{

		@Override
		public Vote mapRow(ResultSet arg0, int arg1) throws SQLException {
			Vote vote = new Vote();
			vote.setDate(arg0.getTimestamp("date"));
			vote.setDiscoveryId(arg0.getLong("discovery_id"));
			vote.setId(arg0.getLong("vote_id"));
			vote.setUserId(arg0.getLong("user_id"));
			String type = (arg0.getString("type"));
			vote.setVoteType(VoteType.valueOf(type));
			return vote;
		}
		
	}
	
	

}
