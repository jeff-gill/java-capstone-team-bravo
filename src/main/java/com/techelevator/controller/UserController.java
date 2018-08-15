package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
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

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class UserController {

	private UserDAO userDAO;
	
	@Autowired
	ServletContext servletContext;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/new";
		}
		
		userDAO.saveUser(user.getUserName(), user.getPassword());
		return "redirect:/login";
	}
	
	@RequestMapping(path="/users/sensei/{userName}", method=RequestMethod.GET)
	public String senseiProfile(Map<String, User> model, @PathVariable String userName) {
		model.put("profile", userDAO.getSenseiProfileByUserName(userName));
		
		return "senseiProfilePage";
	}
	
	@RequestMapping(path="/users/gh/{userName}", method=RequestMethod.GET)
	public String ghProfile(Map<String, User> model, @PathVariable String userName) {
		model.put("profile", userDAO.getGHProfileByUserName(userName));
		
		return "ghProfilePage";
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam MultipartFile file, ModelMap map, @RequestParam String userName) 
	{	
		File imagePath = getImageFilePath();
		UUID guid = UUID.randomUUID();
		String imageName = imagePath + File.separator + guid.toString();
		userDAO.updateImageName(userName, imageName);
		
		if (file.isEmpty()) 
		{
			map.addAttribute("message", "File Object empty");
		} 
		else 
		{
			createImage(file, imageName);
		}
		
		map.addAttribute("message", "uploaded to: " + imageName);
		
		return "showFile";
	}
	
	private File getImageFilePath() 
	{
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		
		if (!filePath.exists()) 
		{
			filePath.mkdirs();
		}
		
		return filePath;
	}
	
	private String getServerContextPath() 
	{
		return servletContext.getRealPath("/capstone/src/main/ProfileImages/");
	}
	
	private void createImage(MultipartFile file, String name) 
	{
		File image = new File(name);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) 
		{
			stream.write(file.getBytes());
		
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
