package com.techelevator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JDBCMessageDAO;
import com.techelevator.model.JDBCSubjectDAO;
import com.techelevator.model.Message;

public class JDBCMessageDAOIntegrationTest {
	

		private static final String USER_NAME = "YoungSon";
		private static final String TEST_NAME = "lordgenius";

		private static SingleConnectionDataSource dataSource;
		
		@Autowired
		private JDBCMessageDAO messageDAO;

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
			String sqlInsertMessage = "insert into messaging (message_to, message_from, message_subject, message_body, message_date_sent) "
					+ "values (?, ?, 'Hi', 'I have always wanted to kill zombies, teach me please!!!', '08/19/2018')";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sqlInsertMessage, USER_NAME, TEST_NAME);
			messageDAO = new JDBCMessageDAO(dataSource);
		}

		@After
		public void rollback() throws SQLException
		{
			dataSource.getConnection().rollback();
		}
		
		@Test
		public void get_messages_for_user() {
			List<Message> results = messageDAO.getMessagesForUser(USER_NAME);
			
			assertNotNull(results);
			assertTrue(results.size()>=1);
		}

}
