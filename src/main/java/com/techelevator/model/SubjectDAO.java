package com.techelevator.model;

import java.util.List;

public interface SubjectDAO {

	public void saveSubject(Subject subject);
	
	public List<Subject> searchSubject(String subjectName);
	
	public void updateSubject(Subject subject, int classId);
	
	public void deleteSubject(int classId);
		
	public Subject getSubjectById(int id) ;

}
