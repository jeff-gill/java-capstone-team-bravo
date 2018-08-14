package com.techelevator.model;

import java.util.Date;
import java.util.List;

public interface ClassDAO {
	
	public void saveClass(String className, String location, Date date, String startTime, String endTime, float cost, int availableSlots, String description);
	
	public List<Class> searchClass(String className);
	
	public void updateClass(String className, String location, Date date, String startTime, String endTime, float cost, int availableSlots, String description);
	
	public void deleteClass(String userName, int classId);
		
	

}
