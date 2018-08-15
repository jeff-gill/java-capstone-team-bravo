package com.techelevator.model;

import java.util.List;

public interface SubjectDAO {
	
	public void saveSubject(Subject subject);
	
	public List<Subject> searchSubject(String className);
	
	public void updateSubject(Subject subject, String userName);
	
	public void deleteSubject(String userName, int classId);
		
	

}