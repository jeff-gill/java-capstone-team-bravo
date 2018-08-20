package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCMessageDAO implements MessageDAO
{	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCMessageDAO(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveMessage(Message message) 
	{
		String sqlSaveMessage = "INSERT INTO messaging (message_to, message_from, message_subject, message_body, message_date_sent) VALUES (?,?,?,?,?) returning message_id";
		int messageId = jdbcTemplate.queryForObject(sqlSaveMessage, Integer.class, message.getSenderName(), message.getReceiverName(), message.getMessageSubject(), message.getMessageBody(), 
													message.getDate());
		message.setMessageId(messageId);
	}

	@Override
	public List<Message> getMessagesForUser(String userName) 
	{
		return null;
	}

	@Override
	public String getMessage(String userName) 
	{
		return null;
	}
}
