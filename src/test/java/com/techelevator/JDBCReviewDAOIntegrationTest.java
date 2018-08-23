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

import com.techelevator.model.JDBCReviewDAO;
import com.techelevator.model.Review;

public class JDBCReviewDAOIntegrationTest {

	private static final String REVIEWER_NAME = "YoungSon";
	private static final String REVIEWEE_NAME = "lordgenius";

	private static SingleConnectionDataSource dataSource;
	
	@Autowired
	private JDBCReviewDAO reviewDAO;

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
		String sqlInsertMessage = "insert into reviews (reviewee, reviewer, panda_rating, review) "
				+ "values (?, ?, 3, 'lordgenius is both a lord and a genius, I slay zombies so hard now. I feel very confident in my survival skills and warmly welcome the coming apocalypse')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertMessage, REVIEWEE_NAME, REVIEWER_NAME);
		reviewDAO = new JDBCReviewDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException
	{
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void get_reviews_for_user() 
	{
		List<Review> results = reviewDAO.getReviewsForUser(REVIEWEE_NAME);
		
		assertNotNull(results);
		assertTrue(results.size()>=1);
	}
	
	@Test
	public void test_save_review()
	{
		Review reviews = getReview(REVIEWEE_NAME, REVIEWER_NAME, 5, "lordgenius is both a lord and a genius, I slay zombies so hard now. I feel very confident in my survival skills and warmly welcome the coming apocalypse");
		reviewDAO.saveReview(reviews);
		
		assertNotNull(reviews.getReviewId());
	}
	
	@Test
	public void test_panda_average() 
	{	
		Review review1 = getReview(REVIEWEE_NAME, REVIEWER_NAME, 5, "lordgenius is both a lord and a genius, I slay zombies so hard now. I feel very confident in my survival skills and warmly welcome the coming apocalypse");
		Review review2 = getReview(REVIEWEE_NAME, REVIEWER_NAME, 4, "lordgenius is both a lord and a genius, I slay zombies so hard now. I feel very confident in my survival skills and warmly welcome the coming apocalypse");
		Review review3 = getReview(REVIEWEE_NAME, REVIEWER_NAME, 3, "lordgenius is both a lord and a genius, I slay zombies so hard now. I feel very confident in my survival skills and warmly welcome the coming apocalypse");
		int averageRating = reviewDAO.averagePandaRating(REVIEWEE_NAME);
		
		assertTrue(averageRating == 4);	
	}
	
	@Test
	public void test_pada_average_equals_0()
	{
		Review reviews = getReview(REVIEWER_NAME, REVIEWEE_NAME, 0, "lordgenius is both a lord and a genius, I slay zombies so hard now. I feel very confident in my survival skills and warmly welcome the coming apocalypse");
		reviewDAO.saveReview(reviews);
		int averageRating = reviewDAO.averagePandaRating(REVIEWER_NAME);
		
		assertTrue(averageRating == 0);
	}
	
	private Review getReview(String reviewee, String reviewer, int pandaRating, String review) 
	{
		Review testReview = new Review();
		testReview.setReviewee(reviewee);
		testReview.setReviewer(reviewer);
		testReview.setPandaRating(pandaRating);
		testReview.setReview(review);
	
		return testReview;
	}
}
