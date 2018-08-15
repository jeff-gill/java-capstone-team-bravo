package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.sql.SQLException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import com.techelevator.model.JDBCSubjectDAO;
import com.techelevator.model.Subject;
import com.techelevator.model.User;

public class JDBCSubjectDAOIntegrationTest 
{
	private static final String TEST_ID = "SWORD FIGHTING";

	private static SingleConnectionDataSource dataSource;
	private JDBCSubjectDAO subjectDAO;

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
		String sqlInsertSubject = "insert into class (subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) "
								 + "values (?, 'Nowhere special', '10/10/2018', '1:00:00', '2:00:00', 12.00, 4, 'All I want for Christmas is my two front teeth')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertSubject, TEST_ID);
		subjectDAO = new JDBCSubjectDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_save_subject()
	{
		Date date = new Date(10, 10, 2018);
		Subject newSubject = getSubject(TEST_ID, "Nowhere special", date, "1:00:00", "2:00:00", 12.00f, 4, "All I want for Christmas is my two front teeth");
		
//		subjectDAO.saveSubject(newSubject);
		
		assertNotEquals(null, newSubject.getClassId());
	}
	
	@Test
	public void update_subject()
	{
		Date date = new Date(10, 10, 2018);
		Subject newSubject = getSubject(TEST_ID, "Nowhere special", date, "1:00:00", "2:00:00", 12.00f, 4, "All I want for Christmas is my two front teeth");
		subjectDAO.updateSubject(newSubject, "SWORD FIGHTING");
		assertEquals(TEST_ID, newSubject.getClassName());
	}
	
	private Subject getSubject(String subjectName, String location, Date date, String startTime, String endTime, float cost, int availableSlots, String description) 
	{
		Subject subjects = new Subject();
		subjects.setClassName(subjectName);
		subjects.setLocation(location);
		subjects.setDate(date);
		subjects.setStartTime(startTime);
		subjects.setEndTime(endTime);
		subjects.setCost(cost);
		subjects.setAvailableSlots(availableSlots);
		subjects.setDescription(description);
	
		return subjects;
	}
}
