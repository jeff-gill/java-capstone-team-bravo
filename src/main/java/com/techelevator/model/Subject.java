package com.techelevator.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Subject {
	
	private int classId;
	@NotBlank(message="Subject Name field is required.")
	private String subjectName;
	@NotBlank(message="Location field is required.")
	private String location;
	@NotBlank(message="Date field is required.")
	@DateTimeFormat(pattern="MM-dd-yyyy")
	private Date date;
	@NotBlank(message="Start Time field is required.")
	private String startTime;
	@NotBlank(message="End Time field is required.")
	private String endTime;
	private float cost;
	@NotBlank(message="Available Slot field is required.")
	@Size(min=1, max=10, message="Must have between 1 and 10 available slots")
	private int availableSlots;
	@NotBlank(message="Description field is required.")
	private String description;
	
	public int getClassId() {
		return classId;
	}
	
	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getAvailableSlots() {
		return availableSlots;
	}
	public void setAvailableSlots(int availableSlots) {
		this.availableSlots = availableSlots;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
