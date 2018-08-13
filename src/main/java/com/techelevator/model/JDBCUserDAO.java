package com.techelevator.model;

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
	public void saveUser(String userName, String password) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		jdbcTemplate.update("INSERT INTO user_info(user_name, password, salt) VALUES (?, ?, ?)",
				userName, hashedPassword, saltString);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM user_info "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("UPDATE user_info SET password = ? WHERE user_name = ?", password, userName);
	}

	@Override
	public Object getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM user_info "+
		"WHERE user_name = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
		}

		return thisUser;
	}

	@Override
	public User getUserProfileByUserName(String userName) {
		String sqlProfileByUserName = "select * from user_info " + 
									  "where user_name = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlProfileByUserName, userName);
		
		User user = new User();
		
		while(result.next()) 
		{
			user = mapRowToUser(result);
		}
		
		return user;
	}
	
	private User mapRowToUser(SqlRowSet results) {
		User user = new User();
		user.setUserName(results.getString("user_name"));
		user.setFirstName(results.getString("first_name"));
		user.setLastName(results.getString("last_name"));
		user.setBio(results.getString("bio"));
		
		return user;
	}	
}
