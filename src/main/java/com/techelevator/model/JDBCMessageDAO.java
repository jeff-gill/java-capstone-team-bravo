package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
		List<Message> messages = new ArrayList<Message>();
		String sqlGetAllUsersMessages = "select * from messaging " + 
				"where message_to = ? or message_from = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllUsersMessages, userName, userName);
		
		while (result.next())
		{
			messages.add(mapRowToMessage(result));
		}
		return messages;
	}

	private Message mapRowToMessage(SqlRowSet results) 
	{
		Message message = new Message();
		message.setMessageId(results.getInt("message_id"));
		message.setReceiverName(results.getString("message_to"));
		message.setSenderName(results.getString("message_from"));
		message.setMessageSubject(results.getString("message_subject"));
		message.setMessageBody(results.getString("message_body"));
		message.setDate(results.getDate("message_date_sent").toLocalDate());
		

		return message;
	}

	@Override
	public Message getMessageById(int messageId) 
	{	
		Message message = new Message();
		String sqlGetMessageById = "select * from messaging " + 
				"where message_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetMessageById, messageId);
		
		while(result.next()) {
			message = mapRowToMessage(result);
		}
		return message;
	}
}
