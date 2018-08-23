package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JDBCUserDAO;
import com.techelevator.model.Subject;
import com.techelevator.model.User;

public class JDBCUserDAOIntegrationTest 
{
	private String USER_NAME = "HumperDoo";
	private String PASSWORD = "1234";
	private static SingleConnectionDataSource dataSource;
	
	@Autowired
	private JDBCUserDAO userDAO;

	@BeforeClass
	public static void setupDataSource()
	{
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/capstone");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource()
	{
		dataSource.destroy();
	}

	@Before
	public void setup()
	{
		String sqlInsertUser = "insert into user_info (user_name, password, first_name, last_name, bio, email, is_sensei) "
							 + "values (?, ?, 'Humper', 'Doo', 'Hi, I am Humper Doo, how are you?', 'humperdoo@you.com', true)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertUser, USER_NAME, PASSWORD);
		userDAO = new JDBCUserDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_save_user()
	{
		User user = getUser("humperDont", PASSWORD, "Humper", "Doo", "Hi, I'm Humper Doo, how are you?", "humperdoo@you.com", true);
		userDAO.saveUser(user);
		
		assertNotNull(user.getUserName());
	}
	
	@Test
	public void search_for_user_name_and_password()
	{
		assertNotNull(userDAO.searchForUsernameAndPassword(USER_NAME, PASSWORD));
		assertTrue(userDAO.searchForUsernameAndPassword(USER_NAME, PASSWORD));
	}
	
	@Test
	public void get_user_by_user_name()
	{
		User user = getUser(USER_NAME, PASSWORD, "Humper", "Doo", "Hi, I am Humper Doo, how are you?", "humperdoo@you.com", true);
		User result = userDAO.getUserByUserName(user.getUserName());
		
		assertNotNull(result);
		assertUsersAreEqual(user, result);
	}
	
	@Test
	public void update_image_name()
	{
		String profileImage = "myPic";
		userDAO.updateImageName(USER_NAME, profileImage);
		User user = getUser(USER_NAME, PASSWORD, "Humper", "Doo", "Hi, I am Humper Doo, how are you?", "humperdoo@you.com", true);
		User result = userDAO.getUserByUserName(user.getUserName());
		
		assertNotNull(result.getProfileImage());
		assertTrue(result.getProfileImage().equals(profileImage));	
	}
	
	@Test
	public void test_get_senseis_by_subject()
	{
		List<User> results = userDAO.getSenseisBySubject("Sword Fighting");
		
		assertNotNull(results);
		assertTrue(results.size() >= 1);
	}
	
	@Test
	public void test_get_senseis()
	{
		List<User> results = userDAO.getSenseis();
		
		assertNotNull(results);
		assertTrue(results.size() >= 1);
	}
	
	@Test
	public void update_profile()
	{
		User user = getUser(USER_NAME, PASSWORD, "Humper", "Doo", "Hi, I am Humper Doo, how are you?", "humperdoo@you.com", true);
		user.setLastName("Doop");
		userDAO.updateProfile(user, USER_NAME);
		User result = userDAO.getUserByUserName(USER_NAME);
		
		assertUsersAreEqual(user, result);
	}
	
	private User getUser(String userName, String password, String firstName, String lastName, String bio, String email, boolean sensei) 
	{
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBio(bio);
		user.setEmail(email);
		user.setSensei(sensei);
	
		return user;
	}
	
	private void assertUsersAreEqual(User expected, User actual) 
	{
		assertEquals(expected.getUserName(), actual.getUserName());
		assertEquals(expected.getPassword(), actual.getPassword());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getBio(), actual.getBio());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.isSensei(), actual.isSensei());
	}
}
