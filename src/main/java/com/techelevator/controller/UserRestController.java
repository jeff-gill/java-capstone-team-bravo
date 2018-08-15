package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Subject;
import com.techelevator.model.SubjectDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@RestController
public class UserRestController 
{
	@Autowired 
	private UserDAO userDao;
	

	
	@RequestMapping(path="/users/sensei/{userName}", method=RequestMethod.POST)
	public String updateProfile(@PathVariable String userName,
								@RequestParam String firstName,
								@RequestParam String lastName,
								@RequestParam String bio,
								@RequestParam String email,
								@RequestParam String phone,
								@RequestParam String profileImage) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBio(bio);
		user.setEmail(email);
		user.setPhone(phone);
		user.setProfileImage(profileImage);
		
		userDao.updateProfile(user);
		
		return "redirect:/users/sensei/"+userName;
	}
}
