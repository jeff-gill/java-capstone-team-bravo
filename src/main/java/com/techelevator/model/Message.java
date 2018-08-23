package com.techelevator.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotBlank;

public class Message {
	
	private int messageId;
	@NotBlank(message="To field is required.")
	private String senderName;
	private String receiverName;
	@NotBlank(message="Subject field is required.")
	private String messageSubject;
	@NotBlank(message="Message body is required.")
	private String messageBody;
	private LocalDate date;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}	
}
