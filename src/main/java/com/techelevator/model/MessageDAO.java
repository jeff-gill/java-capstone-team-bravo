package com.techelevator.model;

import java.util.List;

public interface MessageDAO {
	
	public void saveMessage(Message message);
	
	public List<Message> getMessagesForUser(String userName);
	
	public String getMessage(String userName);
}
