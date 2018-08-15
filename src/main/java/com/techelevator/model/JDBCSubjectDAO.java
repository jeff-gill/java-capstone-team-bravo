package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void saveSubject(Subject subject) {
		
		String sqlSaveClass = "insert into class (subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
//		String sqlSaveSubject = "insert into subject (subject_name) values (?)";
//		
//		jdbcTemplate.update(sqlSaveSubject, subject.getClassName());
		jdbcTemplate.update(sqlSaveClass, subject.getClassName(), subject.getLocation(), subject.getDate(), subject.getStartTime(), 
							subject.getEndTime(), subject.getCost(), subject.getAvailableSlots(), subject.getDescription());
		
	}

	@Override
	public List<Subject> searchSubject(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	//Added to this
	@Override
	public void updateSubject(Subject subject, String userName) {
		String updateSql = "update class set subject_name = ?, location = ? , event_date = ? , event_start_time = ?, "
						 + "event_end_time = ?, cost = ?, available_slots = ?, description = ? WHERE user_name = ?";
		jdbcTemplate.update(updateSql, subject.getClassName(), subject.getLocation(), subject.getDate(), subject.getStartTime(), 
							subject.getEndTime(), subject.getCost(), subject.getAvailableSlots(), subject.getDescription(), userName);
		
	}

	@Override
	public void deleteSubject(String userName, int classId) {
		// TODO Auto-generated method stub
		
	}
	
	//Added this
	private Subject mapRowToSubject(SqlRowSet results) 
	{
		Subject subject = new Subject();
		
		subject.setClassName(results.getString("subject_name").toUpperCase());
		subject.setLocation(results.getString("location"));
		subject.setDate(results.getDate("event_date"));
		subject.setStartTime(results.getString("event_start_time"));
		subject.setEndTime(results.getString("event_end_time"));
		subject.setCost(results.getFloat("cost"));
		subject.setAvailableSlots(results.getInt("available_slots"));
		subject.setDescription(results.getString("desription"));
		
		return subject;
	}
}
