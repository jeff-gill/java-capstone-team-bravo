package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Subject;
import com.techelevator.model.SubjectDAO;

@RestController
public class SubjectRestController 
{
	@Autowired 
	private SubjectDAO subjectDao;
	
	@RequestMapping(path="/users/sensei/{userName}/updateSubject", method=RequestMethod.POST)
	public String updateSenseiSubject(@ModelAttribute Subject subject, @RequestParam int classId)
	{
		subjectDao.updateSubject(subject, classId);
		return "redirect:/users/sensei/{userName}";
	}
	
	@RequestMapping(path="/users/gh/{userName}/updateSubject", method=RequestMethod.POST)
	public String updateGHSubject(@ModelAttribute Subject subject, @RequestParam int classId)
	{
		subjectDao.updateSubject(subject, classId);
		return "redirect:/users/gh/{userName}";
	}
}
