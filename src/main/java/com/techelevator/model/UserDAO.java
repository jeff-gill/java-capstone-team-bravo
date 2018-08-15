package com.techelevator.model;

public interface UserDAO {

	public void saveUser(String userName, String password);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public Object getUserByUserName(String userName);
	
	public User getSenseiProfileByUserName(String userName);
	
	public User getGHProfileByUserName(String userName);
	
	public void updateImageName(String userName, String imageName);
	
	public void updateProfile(User user);
	

}
