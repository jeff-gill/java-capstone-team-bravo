package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.model.Subject;
import com.techelevator.model.SubjectDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@RestController
public class UserRestController 
{
	@Autowired 
	private UserDAO userDAO;
	
	@Autowired
	ServletContext servletContext;
	


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



