package pl.project.weekop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.project.weekop.model.Discovery;
import pl.project.weekop.model.User;
import pl.project.weekop.util.ConnectionProvider;

public class DiscoveryDAOImpl implements DiscoveryDAO {
	
	public static final String CREATE =
			"INSERT INTO discovery(name, description, url, user_id, date, up_vote, down_vote) "
			+ " VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";
	public static final String READ_ALL_DISCOVERIES = 
			"SELECT user.user_id, username, email, is_active, password, discovery_id, name, description, url, date, up_vote, down_vote "
			+ "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id;";
	public static final String READ =
			"SELECT user.user_id, username, email, is_active, password, discovery_id, name, description, url, date, up_vote, down_vote "
			+ "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id WHERE discovery_id=:discovery_id;";
	public static final String UPDATE =
			"UPDATE discovery SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote "
			+ "WHERE discovery_id=:discovery_id;";

	
	private NamedParameterJdbcTemplate template;
	
	public DiscoveryDAOImpl() {
		this.template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}

	@Override
	public Discovery create(Discovery object) {
		Discovery newDiscovery = new Discovery(object);
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", object.getName());
		paramMap.put("description", object.getDescription());
		paramMap.put("url", object.getUrl());
		paramMap.put("user_id", object.getUser().getId());
		paramMap.put("date", object.getTimestamp());
		paramMap.put("up_vote", object.getUpVote());
		paramMap.put("down_vote", object.getDownVote());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE, paramSource, holder);
		if(update>0) {
			newDiscovery.setId(holder.getKey().longValue());
		}
		return newDiscovery;
	}

	@Override
	public Discovery read(Long primaryKey) {
		SqlParameterSource paramSource = new MapSqlParameterSource("discovery_id", primaryKey);
		Discovery discovery = template.queryForObject(READ, paramSource, new DiscoveryRowMapper());
		return discovery;
	}

	@Override
	public boolean update(Discovery object) {
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", object.getName());
		paramMap.put("description", object.getDescription());
		paramMap.put("url", object.getUrl());
		paramMap.put("user_id", object.getUser().getId());
		paramMap.put("date", object.getTimestamp());
		paramMap.put("up_vote", object.getUpVote());
		paramMap.put("down_vote", object.getDownVote());
		paramMap.put("discovery_id", object.getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(UPDATE, paramSource);
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
	public List<Discovery> getAll() {
		List<Discovery> discoveryList = template.query(READ_ALL_DISCOVERIES, new DiscoveryRowMapper());
		return discoveryList;
	}
	
	private class DiscoveryRowMapper implements RowMapper<Discovery>{

		@Override
		public Discovery mapRow(ResultSet arg0, int arg1) throws SQLException {
			Discovery discovery = new Discovery();
			discovery.setDescription(arg0.getString("description"));
			discovery.setId(arg0.getLong("discovery_id"));
			discovery.setDownVote(arg0.getInt("down_vote"));
			discovery.setUpVote(arg0.getInt("up_vote"));
			discovery.setName(arg0.getString("name"));
			discovery.setTimestamp(arg0.getTimestamp("date"));
			discovery.setUrl(arg0.getString("url"));
			User user = new User();
			user.setId(arg0.getLong("user_id"));
			user.setUsername(arg0.getString("username"));
			user.setPassword(arg0.getString("password"));
			user.setEmail(arg0.getString("email"));
			user.setIs_active(arg0.getBoolean("is_active"));
			discovery.setUser(user);
			return discovery;
		}
		
		
		
	}

	

}
