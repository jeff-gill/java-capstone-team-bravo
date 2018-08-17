package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Subject;
import com.techelevator.model.SubjectDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class UserController {

	@Autowired
	private SubjectDAO subjectDAO;
	private UserDAO userDAO;
	


	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@RequestMapping(value= {"/", "/users/homePage"}, method=RequestMethod.GET)
	public String displayHomePage() {
		return "homePage";
	}
	

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}
	
	@RequestMapping(path= {"/", "/users/homePage"}, method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash, HttpSession session) {
//		if(result.hasErrors()) {
//			flash.addFlashAttribute("user", user);
//			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
//			return "redirect:/users/new";
//		}
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setBio(user.getBio());
		newUser.setEmail(user.getEmail());
		newUser.setUserName(user.getUserName());
		newUser.setPassword(user.getPassword());
		newUser.setSensei(user.isSensei());
		
		if (user.isSensei())
		{
			newUser.isSensei();
		}
		
		userDAO.saveUser(newUser);
		
		User newU = userDAO.getUserByUserName(user.getUserName());
		session.setAttribute("currentUser", newU);

		if (newU.isSensei())
		{
			return "redirect:/users/sensei/"+newU.getUserName();
		}
		
			return "redirect:/users/gh/"+newU.getUserName();
	
	}
	
	@RequestMapping(path="/users/sensei/{userName}", method=RequestMethod.GET)
	public String senseiProfile(Map<String, User> model, @PathVariable String userName) {
		model.put("profile", userDAO.getSenseiProfileByUserName(userName));
		
		return "senseiProfilePage";
	}
	
	@RequestMapping(path="/users/sensei/{userName}", method=RequestMethod.POST)
	public String updateSenseiProfile(@PathVariable String userName,
								@RequestParam String firstName,
								@RequestParam String lastName,
								@RequestParam String bio,
								@RequestParam String email,
//								@RequestParam String profileImage,
								@RequestParam String phone,
								HttpSession session
								) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBio(bio);
		user.setEmail(email);
		user.setPhone(phone);
//		user.setProfileImage(profileImage);
		
		userDAO.updateProfile(user, userName);
		
		session.setAttribute("currentUser", user);
		
		return "redirect:/users/sensei/"+userName;
	}
	
	@RequestMapping(path="/users/gh/{userName}", method=RequestMethod.POST)
	public String updateGhProfile(@PathVariable String userName,
								@RequestParam String firstName,
								@RequestParam String lastName,
								@RequestParam String bio,
								@RequestParam String email,
	//							@RequestParam String profileImage,
								@RequestParam String phone,
								@RequestParam String interests,
								HttpSession session
								) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBio(bio);
		user.setEmail(email);
		user.setPhone(phone);
//		user.setProfileImage(profileImage);
		user.setInterests(interests);
		
		userDAO.updateProfile(user, userName);
		
		session.setAttribute("currentUser", user);
		
		return "redirect:/users/gh/"+userName;
	}
	
	@RequestMapping(path="/users/sensei/{userName}", method=RequestMethod.POST)
	public String createClass(@PathVariable String userName,
								@RequestParam String subjectName,
								@RequestParam String location,
								@RequestParam Date date,
								@RequestParam String startTime,
								@RequestParam String endTime,
								@RequestParam Float cost,
								@RequestParam int availableSlots,
								@RequestParam String description,
								HttpSession session) {
		
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		subject.setLocation(location);
		subject.setDate(date);
		subject.setStartTime(startTime);
		subject.setEndTime(endTime);
		subject.setCost(cost);
		subject.setAvailableSlots(availableSlots);
		subject.setDescription(description);
		
		subjectDAO.saveSubject(subject);
		
		session.setAttribute("currentUser", userName);
		
									
		return "redirect:/users/sensei/"+userName;
	}
	
	@RequestMapping(path="users/sensei/{userName}/updateSubject", method=RequestMethod.POST)
	public String updateSenseiSubject(@ModelAttribute Subject subject, @RequestParam int classId)
	{
		subjectDAO.updateSubject(subject, classId);
		return "redirect:/users/sensei/{userName}";
	}
	
	@RequestMapping(path="/gh/updateSubject", method=RequestMethod.POST)
	public String updateGHSubject(@ModelAttribute Subject subject, @RequestParam int classId)
	{
		subjectDAO.updateSubject(subject, classId);
		return "redirect:/users/gh/{userName}";
	}
	
	@RequestMapping(path="/users/gh/{userName}", method=RequestMethod.GET)
	public String ghProfile(Map<String, User> model, @PathVariable String userName) {
		model.put("profile", userDAO.getGHProfileByUserName(userName));
		
		return "ghProfilePage";
	}
}	