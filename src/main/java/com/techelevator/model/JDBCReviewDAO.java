package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCReviewDAO implements ReviewDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCReviewDAO(DataSource dataSource) 
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveReview(Review review) {
		String sqlSaveReview ="insert into reviews (reviewee, reviewer, panda_rating, review) "
				+ " values (?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveReview, review.getReviewee(), review.getReviewer(), review.getPandaRating(), review.getReview());
	}

	@Override
	public List<Review> getReviewsForUser(String userName) {
		List<Review> reviews = new ArrayList<Review>();
		String getAllReviews ="select * from reviews where reviewee = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllReviews, userName);
		
		while (result.next())
		{
			reviews.add(mapRowToReviews(result));
		}

		
		return reviews;
	}

	private Review mapRowToReviews(SqlRowSet result) {
		Review review = new Review();
		review.setReviewId(result.getInt("review_id"));
		review.setReviewee(result.getString("reviewee"));
		review.setReviewer(result.getString("reviewer"));
		review.setPandaRating(result.getInt("panda_rating"));
		review.setReview(result.getString("review"));
		
		return review;
	}

}
