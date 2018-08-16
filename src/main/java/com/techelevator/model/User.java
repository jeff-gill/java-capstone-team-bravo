package com.techelevator.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	private String userName;
	
	@Size(min=10, message="Password too short, must be at least 10")
	@Pattern.List({
		@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
		@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	private String password;
	private String role;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String bio;
	private int rating;
	private boolean isSensei;
	private String profileImage;
	private String salt;
	private String email;
	private String phone;
	private String interests;
	
	//Added isSensei and salt getters and setters
	
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public boolean isSensei() {
		return isSensei;
	}
	public void setSensei(boolean isSensei) {
		this.isSensei = isSensei;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
