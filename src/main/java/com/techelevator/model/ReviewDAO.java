package com.techelevator.model;

import java.util.List;

public interface ReviewDAO {
	
	public void saveReview(Review review);
	
	public List<Review> getReviewsForUser(String userName);

}
