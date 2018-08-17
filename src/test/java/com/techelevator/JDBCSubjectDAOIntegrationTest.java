package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JDBCSubjectDAO;
import com.techelevator.model.Subject;

public class JDBCSubjectDAOIntegrationTest 
{
	private static final String SUBJECT_NAME = "SWORD FIGHTING";
	private static final int TEST_ID = 2;

	private static SingleConnectionDataSource dataSource;
	
	@Autowired
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
		String sqlInsertSubject = "insert into subjects (subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) "
								 + "values (?, 'Nowhere special', '10/10/2018', '1:00', '2:00', 12.00, 4, 'All I want for Christmas is my two front teeth')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertSubject, SUBJECT_NAME);
		subjectDAO = new JDBCSubjectDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_save_subject() throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = formatter.parse("10-10-2018");
		Subject subjects = getSubject(SUBJECT_NAME, "Nowhere special", date, "1:00", "2:00", 12.00f, 4, "All I want for Christmas is my two front teeth");
		
		subjectDAO.saveSubject(subjects);
		assertNotNull(subjects);
	}

	@Test
	public void update_subject() throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = formatter.parse("10-10-2018");
		Subject subjects = getSubject(SUBJECT_NAME, "Nowhere special", date, "1:00", "2:00", 12.00f, 4, "All I want for Christmas is my two front teeth");
		subjectDAO.saveSubject(subjects);
		subjectDAO.updateSubject(subjects, TEST_ID);
		assertEquals(SUBJECT_NAME, subjects.getSubjectName());
	}
	
	@Test
	public void test_get_subject_by_id() throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = formatter.parse("10-10-2018");
		Subject subjects = getSubject(SUBJECT_NAME, "Nowhere special", date, "1:00", "2:00", 12.00f, 4, "All I want for Christmas is my two front teeth");
		subjectDAO.saveSubject(subjects);
		
		Subject sub = subjectDAO.getSubjectById(subjects.getClassId());
		
		assertNotNull(sub);
		assertSubjectsAreEqual(subjects, sub);
	}
	
	private void assertSubjectsAreEqual(Subject expected, Subject actual) 
	{
		assertEquals(expected.getSubjectName(), actual.getSubjectName());
		assertEquals(expected.getLocation(), actual.getLocation());
		assertEquals(expected.getDate(), actual.getDate());
		assertEquals(expected.getStartTime(), actual.getStartTime());
		assertEquals(expected.getEndTime(), actual.getEndTime());
		assertEquals(expected.getCost(), actual.getCost(), 0.01);
		assertEquals(expected.getDescription(), actual.getDescription());
	}
	
	private Subject getSubject(String subjectName, String location, Date date, String startTime, String endTime, float cost, int availableSlots, String description) 
	{
		Subject subjects = new Subject();
		subjects.setSubjectName(subjectName);
		subjects.setLocation(location);
		subjects.setDate(date);
		subjects.setStartTime(startTime);
		subjects.setEndTime(endTime);
		subjects.setCost(cost);
		subjects.setAvailableSlots(availableSlots);
		subjects.setDescription(description);
	
		return subjects;
	}
	
	private Subject getSubject(String className)
	{
		Subject subjects = new Subject();
		subjects.setSubjectName(className);
		
		return subjects;
	}
}
