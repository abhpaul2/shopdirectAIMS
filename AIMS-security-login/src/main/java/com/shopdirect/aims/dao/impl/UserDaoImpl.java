package com.shopdirect.aims.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.shopdirect.aims.dao.UserDao;
import com.shopdirect.aims.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("jdbcTemplateOne")
	private JdbcTemplate template1;

	@Autowired
	@Qualifier("jdbcTemplateTwo")
	private JdbcTemplate template2;



	@Override
	public void insertUser(User usr) {
		// Fetch data from GENERIC_TABLE table
		String sql = "SELECT * FROM GENERIC_TABLE WHERE TBL_NAME = 'GB0002' and TBL_KEY_1 LIKE ?";
		String cloneID = usr.getCloneId()+"%";
		Map<String, Object> updateValues = (Map<String, Object>)template2.queryForObject(sql, new Object[]{cloneID}, new RowMapper<Map<String, Object>>(){
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rwNumber) throws SQLException {				
				Map<String, Object> colData = new HashMap<String, Object>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				for (int i = 1; i <= columnsNumber; i++) {
					colData.put(rsmd.getColumnName(i), rs.getObject(i));			           
				}
				return colData;
			}
		});

		//update new user values		
		boolean first = true;
		StringBuffer colNames = new StringBuffer();
		StringBuffer colValues = new StringBuffer();
		//updateValues.forEach((k,v)->{
		for(Map.Entry<String, Object> pair: updateValues.entrySet()) {
			System.out.println("key : " + pair.getKey() + " value : " + pair.getValue());

			if(first) {				
				first = false;
			} else {
				colNames.append(", ");
				colValues.append(", ");
			}
			colNames.append(pair.getKey());
			if("TBL_KEY_1".equals(pair.getKey())){
				colValues.append("'");
				colValues.append(usr.getUserId());
				colValues.append("'");
			} else if("JD_DATA".equals(pair.getKey())) {
				colValues.append("'");
				colValues.append(usr.getUserName());
				colValues.append("'");
			} else {
				colValues.append("'");
				colValues.append(pair.getValue());
				colValues.append("'");
			}
		}
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("INSERT INTO GENERIC_TABLE (");
		insertSql.append(colNames);
		insertSql.append(") VALUES (");
		insertSql.append(colValues);
		insertSql.append(")");		
		System.out.println(insertSql.toString());
		
		//Step1: INSERT INTO GENERIC_TABLE table
		int result1 = template2.update(insertSql.toString(), new Object[]{});
		System.out.println("Insert in GENERIC_TABLE table result:" + result1);

		//Step2: INSERT INTO NM_NS_USERS table
		String sql1 = "INSERT INTO NM_NS_USERS (USER_TYPE, USER_ID, USER_NAME, USER_PASSWORD, USER_ETID, USER_BATCH_ID, USER_LANGUAGE, USER_DEFAULTLIB, USER_PRIVATELIB)" + 
				" VALUES ('MEMBER', ?, ?,NULL, ?,NULL,0,NULL,'N')" ;		

		int result = template1.update(sql1, new Object[]{usr.getUserId(), usr.getUserName(), usr.getUserId()});
		System.out.println("Insert in NM_NS_USERS table result:" + result);

		//Step3: INSERT INTO NM_NS_GROUP_MEMBERS table
		String sql2 = "INSERT INTO NM_NS_GROUP_MEMBERS (GROUP_ID, USER_ID) VALUES ('GDEVAIMS', ?)" ;
		int result2 = template1.update(sql2, new Object[]{usr.getUserId()});
		System.out.println("Insert in NM_NS_GROUP_MEMBERS table result:" + result2);

	}

	@Override
	public User getUserById(String userId) {
		System.out.println("getUserById called");
		System.out.println(template1);
		System.out.println(template1.getDataSource());
		String sql = "SELECT * FROM NM_NS_USERS WHERE USER_ID = ?";
		return (User)template1.queryForObject(sql, new Object[]{userId}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User usr = new User();
				usr.setUserId(rs.getString("USER_ID"));
				usr.setUserName(rs.getString("USER_NAME"));
				usr.setCloneId(rs.getString("USER_ETID"));
				usr.setStatus(true);
				System.out.println(usr);
				return usr;
			}
		});
	}

	@Override
	public User getCloneById(String cloneId) {
		System.out.println("getCloneById called");
		String sql = "SELECT * FROM GENERIC_TABLE WHERE TBL_NAME = 'GB0002' and TBL_KEY_1 LIKE ?";
		System.out.println(template2);
		System.out.println(template2.getDataSource());
		return (User)template2.queryForObject(sql, new Object[]{cloneId+"%"}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
				User usr = new User();
				usr.setUserId(rs.getString("TBL_KEY_1"));
				usr.setUserName(rs.getString("JD_DATA"));
				//usr.setCloneId(rs.getString(""));
				usr.setStatus(true);
				System.out.println("Clone ID exist.");
				return usr;
			}
		});
	}

	@Override
	public boolean revokeUser(String userId) {
		String sql = "DELETE FROM GENERIC_TABLE WHERE TBL_NAME = 'GB0002' and TBL_KEY_1 LIKE ?";
		int row = template2.update(sql, new Object[]{userId+"%"});		
		System.out.println("Revoke result:" + row);
		return row==1 ? true:false;
	}

	/*
	 * @Override public void insertUsers(List<User> users) { String sql =
	 * "INSERT INTO user " +
	 * "(user_id, user_name,clone_id,access_status) VALUES (?, ?, ?, ?)";
	 * getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
	 * public void setValues(PreparedStatement ps, int i) throws SQLException { User
	 * usr = users.get(i); ps.setString(1, usr.getUserId()); ps.setString(2,
	 * usr.getUserName()); ps.setString(3, usr.getCloneId()); ps.setBoolean(4,
	 * usr.isStatus()); } public int getBatchSize() { return users.size(); } });
	 * 
	 * }
	 */

	/*
	 * @Override public List<User> getAllUsers() { String sql =
	 * "SELECT * FROM User"; List<Map<String, Object>> rows =
	 * getJdbcTemplate().queryForList(sql);
	 * 
	 * List<User> result = new ArrayList<User>(); for(Map<String, Object> row:rows){
	 * User usr = new User(); usr.setUserId((String)row.get("userId"));
	 * usr.setUserName((String)row.get("userName"));
	 * usr.setCloneId((String)row.get("cloneId"));
	 * usr.setStatus((Boolean)row.get("accStatus")); result.add(usr); } return
	 * result; }
	 */
}
