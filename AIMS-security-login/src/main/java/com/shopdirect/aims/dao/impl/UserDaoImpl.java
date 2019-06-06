package com.shopdirect.aims.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.shopdirect.aims.dao.UserDao;
import com.shopdirect.aims.model.User;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired 
	DataSource dataSource;

	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	@Override
	public void insertUser(User usr) {
		String sql = "INSERT INTO user " +
				"(user_id, user_name,clone_id,access_status) VALUES (?, ?, ?, ?)" ;
		int result = getJdbcTemplate().update(sql, new Object[]{
				usr.getUserId(), usr.getUserName(), usr.getCloneId(), usr.isStatus()
		});
		System.out.println("Insert result:" + result);
	}

	@Override
	public User getUserById(String userId) {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		return (User)getJdbcTemplate().queryForObject(sql, new Object[]{userId}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User usr = new User();
				usr.setUserId(rs.getString("user_id"));
				usr.setUserName(rs.getString("user_name"));
				usr.setCloneId(rs.getString("clone_id"));
				usr.setStatus(rs.getBoolean("access_status"));
				System.out.println(usr);
				return usr;
			}
		});
	}

	@Override
	public void revokeUser(String userId) {
		String sql = "UPDATE user SET access_status = false WHERE user_id = ?";
		int row = getJdbcTemplate().update(sql, new Object[]{userId});
		System.out.println("Revoke result:" + row);
	}

	@Override
	public void insertUsers(List<User> users) {
		String sql = "INSERT INTO user " + "(user_id, user_name,clone_id,access_status) VALUES (?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				User usr = users.get(i);
				ps.setString(1, usr.getUserId());
				ps.setString(2, usr.getUserName());
				ps.setString(3, usr.getCloneId());
				ps.setBoolean(4, usr.isStatus());
			}			
			public int getBatchSize() {
				return users.size();
			}
		});

	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM User";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<User> result = new ArrayList<User>();
		for(Map<String, Object> row:rows){			
			User usr = new User();
			usr.setUserId((String)row.get("userId"));
			usr.setUserName((String)row.get("userName"));
			usr.setCloneId((String)row.get("cloneId"));
			usr.setStatus((Boolean)row.get("accStatus"));
			result.add(usr);
		}		
		return result;
	}
}
