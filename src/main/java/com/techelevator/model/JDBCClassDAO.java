package com.techelevator.model;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCClassDAO implements ClassDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCClassDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveClass(String className, String location, Date date, String startTime, String endTime, float cost,
			int availableSlots, String description) {
		
		String sqlSaveClass = "insert into class (subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlSaveSubject = "insert into subject (subject_name) values (?)";
		
		jdbcTemplate.update(sqlSaveSubject, className);
		jdbcTemplate.update(sqlSaveClass, className, location, date, startTime, endTime, cost, availableSlots, description);
		
	}

	@Override
	public List<Class> searchClass(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateClass(String className, String location, Date date, String startTime, String endTime, float cost,
			int availableSlots, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClass(String userName, int classId) {
		// TODO Auto-generated method stub
		
	}

}
