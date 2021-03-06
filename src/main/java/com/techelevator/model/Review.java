package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

public class Review {
	
	private int reviewId;
	private String reviewee;
	private String reviewer;
	@NotBlank(message="Panda Rating is required.")
	private int pandaRating;
	@NotBlank(message="Review field is required.")
	private String review;
	
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewee() {
		return reviewee;
	}
	public void setReviewee(String reviewee) {
		this.reviewee = reviewee;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public int getPandaRating() {
		return pandaRating;
	}
	public void setPandaRating(int pandaRating) {
		this.pandaRating = pandaRating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
	

}
