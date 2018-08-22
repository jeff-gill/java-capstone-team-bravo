package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}

	@Override
	public void saveUser(User user) {
		String sqlSaveUser = "insert into user_info(user_name, password, first_name, last_name, bio, email, is_sensei) values (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveUser, user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getBio(), user.getEmail(), user.isSensei());
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "select * from user_info where (user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName);
		if(user.next()) {
			String dbHashedPassword = user.getString("password");
			return dbHashedPassword.equals(password);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("update user_info set password = ? where user_name = ?", password, userName);
	}

	@Override
	public User getUserByUserName(String userName) {
		String sqlSearchForUsername ="select * from user_info where user_name = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName); 
		User thisUser = new User();
		if(user.next()) {
			thisUser = mapRowToUser(user);
		}

		return thisUser;
	}

	@Override
	public User getProfileByUserName(String userName) {
		String sqlProfileByUserName = "select * from user_info where user_name = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlProfileByUserName, userName);

		User user = new User();

		while(result.next()) 
		{
			user = mapRowToUser(result);
		}

		return user;
	}

	@Override
	public void updateImageName(String userName, String imageName) {
		jdbcTemplate.update("update user_info set profile_image = ? where user_name = ?", imageName, userName);
	}	

	@Override
	public void updateProfile(User user, String userName) {
		String updateProfile = "update user_info set first_name = ?, last_name = ?, bio = ?, email = ?, phone = ?, interests = ? " + 
							   "where user_name = ?";
		jdbcTemplate.update(updateProfile, user.getFirstName(), user.getLastName(), user.getBio(), user.getEmail(), user.getPhone(), user.getInterests(), userName);	
	}

	@Override
	public List<User> getSenseisBySubject(String className) {
		String sqlProfileBySubject = "select subject_name, user_info.user_name from user_info " + 
									 "join user_subjects on user_info.user_name = user_subjects.user_name " + 
									 "join subjects on user_subjects.subject_id = subjects.subject_id " + 
									 "where is_sensei = true and subject_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlProfileBySubject, className);

		return mapRowSetToUser(results);
	}
	
	@Override
	public List<User> getSenseis() {
		String sqlGetSenseis = "select * from user_info " + 
				"where is_sensei = ?";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sqlGetSenseis, true);
		
		return mapRowSetToUser(results);
	}

	private User mapRowToUser(SqlRowSet results) {
		User user = new User();
		user.setUserName(results.getString("user_name"));
		user.setPassword(results.getString("password"));
		user.setFirstName(results.getString("first_name"));
		user.setLastName(results.getString("last_name"));
		user.setBio(results.getString("bio"));
		user.setSensei(results.getBoolean("is_sensei"));
		user.setEmail(results.getString("email"));
		user.setPhone(results.getString("phone"));
		user.setProfileImage(results.getString("profile_image"));
		user.setInterests(results.getString("interests"));

		return user;
	}

	private List<User> mapRowSetToUser(SqlRowSet results){
		List<User> userList = new ArrayList<User>();
		while(results.next()) {
			userList.add(mapRowToUser(results));
		}
		return userList;
	}


}
