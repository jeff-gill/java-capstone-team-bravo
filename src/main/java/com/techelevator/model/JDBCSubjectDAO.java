package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSubjectDAO implements SubjectDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSubjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//Changed some of this
	@Override
	public void saveClass(Subject subject) {
		
		String sqlSaveClass = "insert into class (class_id, subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sqlSaveClass, subject.getClassId(), subject.getClassName().toUpperCase(), subject.getLocation(), subject.getDate(), subject.getStartTime(), 
							subject.getEndTime(), subject.getCost(), subject.getAvailableSlots(), subject.getDescription());
	}
	
	//Added this
	@Override
	public void saveSubject(Subject subject) {
		Subject sub = getSubject(subject.getClassName());
		
		if (sub.getClassName().toUpperCase().equals(subject.getClassName().toUpperCase()))
		{
			throw new DuplicateKeyException("Duplicate subject name");
		}
		else
		{	
			String sqlSaveSubject = "insert into subjects (subject_name) values (?)";
			jdbcTemplate.update(sqlSaveSubject, subject.getClassName());
		}

	}

	@Override
	public List<Subject> searchSubject(String className) {

		return null;
	}

	//Added to this
	@Override
	public void updateSubject(Subject subject, int classId) {
		String updateSql = "update class set subject_name = ?, location = ? , event_date = ? , event_start_time = ?, "
						 + "event_end_time = ?, cost = ?, available_slots = ?, description = ? where class_id = ?";

		jdbcTemplate.update(updateSql, subject.getClassName(), subject.getLocation(), subject.getDate(), subject.getStartTime(), 
							subject.getEndTime(), subject.getCost(), subject.getAvailableSlots(), subject.getDescription(), classId);
		
	}

	@Override
	public void deleteSubject(String userName, int classId) {
		// TODO Auto-generated method stub
		
	}
	
	//Added this
	@Override
	public Subject getSubjectById(int id) 
	{
		Subject subjects = null;
		String sqlFindIdOfSubject = "select * from class where class_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindIdOfSubject, id);
		
		while (result.next())
		{
			subjects = mapRowToSubject(result);
		}
		
		return subjects;
	}
	
	private Subject mapRowToSubject(SqlRowSet results) 
	{
		Subject subject = new Subject();
		subject.setClassId(results.getInt("class_id"));
		subject.setClassName(results.getString("subject_name").toUpperCase());
		subject.setLocation(results.getString("location"));
		subject.setDate(results.getDate("event_date"));
		subject.setStartTime(results.getString("event_start_time"));
		subject.setEndTime(results.getString("event_end_time"));
		subject.setCost(results.getFloat("cost"));
		subject.setAvailableSlots(results.getInt("available_slots"));
		subject.setDescription(results.getString("description"));
		
		return subject;
	}
	
	private Subject getSubject(String subjectName)
	{
		Subject subjects = null;
		String sqlFindIdOfSubject = "select * from class where subject_name = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindIdOfSubject, subjectName);
		
		while (result.next())
		{
			subjects = mapRowToSubject(result);
		}
		
		return subjects;
	}
//	
//	//Added this
//	private int getNextSubjectId()
//	{
//		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("select nextval('seq_class_id')");
//		
//		if (nextIdResult.next())
//		{
//			return nextIdResult.getInt(1);
//		}
//		else
//		{
//			throw new RuntimeException("An error occured while getting the id for the class.");
//		}
//	}
//	

	@Override
	public void deleteClass(String userName, int classId) {
		// TODO Auto-generated method stub
		
	}
}
