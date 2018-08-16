package com.techelevator.model;

import java.util.List;

public interface UserDAO {

	public void saveUser(User user);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public User getUserByUserName(String userName);
	
	public User getSenseiProfileByUserName(String userName);
	
	public User getGHProfileByUserName(String userName);
	
	public void updateImageName(String userName, String imageName);
	
	public void updateProfile(User user, String userName);
	
	public List<User> getSenseisBySubject(String className);
	
	

}
