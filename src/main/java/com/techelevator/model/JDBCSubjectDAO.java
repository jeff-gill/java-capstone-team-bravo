package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSubjectDAO implements SubjectDAO 
{	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSubjectDAO(DataSource dataSource) 
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveSubject(Subject subject) 
	{
		String sqlSaveSubject = "insert into subjects (subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?) returning class_id";

		int classId = jdbcTemplate.queryForObject(sqlSaveSubject, Integer.class, subject.getSubjectName(), subject.getLocation(), subject.getDate(),
				subject.getStartTime(), subject.getEndTime(), subject.getCost(), subject.getAvailableSlots(), subject.getDescription());
		subject.setClassId(classId);
	}


	@Override
	public List<Subject> searchSubject(String subjectName) 
	{
		List<Subject> subjects = new ArrayList<Subject>();
		String sqlFindIdOfSubject = "select * from subjects where subject_name = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindIdOfSubject, subjectName);

		while (result.next())
		{
			subjects.add(mapRowToSubject(result));
		}

		return subjects;
	}

	@Override
	public void updateSubject(Subject subject, int classId) 
	{
		String sqlUpdateSubject = "update subjects set subject_name = ?, location = ? , event_date = ? , event_start_time = ?, "
				+ "event_end_time = ?, cost = ?, available_slots = ?, description = ? where class_id = ?";

		jdbcTemplate.update(sqlUpdateSubject, subject.getSubjectName(), subject.getLocation(), subject.getDate(), subject.getStartTime(), 
				subject.getEndTime(), subject.getCost(), subject.getAvailableSlots(), subject.getDescription(), classId);	
	}

	@Override
	public void deleteSubject(int classId) 
	{
		String sqlDeleteSubject = "delete from subjects where class_id = ?";
		jdbcTemplate.update(sqlDeleteSubject, classId);	
	}

	@Override
	public Subject getSubjectById(int id) 
	{
		Subject subjects = null;
		String sqlFindIdOfSubject = "select * from subjects where class_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindIdOfSubject, id);

		while (result.next())
		{
			subjects = mapRowToSubject(result);
		}

		return subjects;
	}

	@Override
	public List<Subject> getAllSubjects(String userName) 
	{
		List<Subject> subjects = new ArrayList<Subject>();
		String sqlGetAllSubjects = "select * from subjects join user_subjects as US on subjects.class_id = US.class_id join user_info as UI on US.user_name = UI.user_name where UI.user_name = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllSubjects, userName);

		while (result.next())
		{
			subjects.add(mapRowToSubject(result));
		}

		return subjects;
	}

	@Override
	public void addUserToClass(int classId, String userName) 
	{
		String sqlSaveUserSubject = "insert into user_subjects (user_name, class_id) values (?, ?)";

		jdbcTemplate.update(sqlSaveUserSubject, userName, classId);

	}
	
	private Subject mapRowToSubject(SqlRowSet results) 
	{
		Subject subject = new Subject();
		subject.setSubjectName(results.getString("subject_name"));
		subject.setLocation(results.getString("location"));
		subject.setDate(results.getDate("event_date"));
		subject.setStartTime(results.getString("event_start_time"));
		subject.setEndTime(results.getString("event_end_time"));
		subject.setCost(results.getFloat("cost"));
		subject.setAvailableSlots(results.getInt("available_slots"));
		subject.setDescription(results.getString("description"));

		return subject;
	}
	//	
	//	private Subject getSubject(String subjectName)
	//	{
	//		Subject subjects = null;
	//		String sqlFindIdOfSubject = "select * from subjects where subject_name = ?";
	//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindIdOfSubject, subjectName);
	//		
	//		while (result.next())
	//		{
	//			subjects = mapRowToSubject(result);
	//		}
	//		
	//		return subjects;
	//	}

	private List<Subject> mapRowSetToSubject(SqlRowSet results)
	{
		List<Subject> subjectList = new ArrayList<Subject>();

		while(results.next()) 
		{
			subjectList.add(mapRowToSubject(results));
		}

		return subjectList;
	}



}
