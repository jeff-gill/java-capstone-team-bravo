package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Message;
import com.techelevator.model.MessageDAO;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewDAO;
import com.techelevator.model.Subject;
import com.techelevator.model.SubjectDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class UserController {

	@Autowired
	private SubjectDAO subjectDAO;
	private UserDAO userDAO;
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	ServletContext servletContext;

	@Autowired
	public UserController(UserDAO userDAO) 
	{
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) 
	{
		if( ! modelHolder.containsAttribute("currentUser")) 
		{
			modelHolder.addAttribute("currentUser", new User());
		}
		
		return "homePage";
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam MultipartFile file, ModelMap map, HttpSession session) 
	{	
		File imagePath = getImageFilePath();
		UUID guid = UUID.randomUUID();
		String imageName = imagePath + File.separator + guid.toString();
		User user = (User) session.getAttribute("currentUser");
		userDAO.updateImageName(user.getUserName(), guid.toString());
		
		if (file.isEmpty()) 
		{
			map.addAttribute("message", "File Object empty");
		} 
		else 
		{
			createImage(file, imageName);
		}
		
		return "redirect:/users/" + user.getUserName();
	}
	
	@RequestMapping(path="/image/{imageName}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value="imageName") String imageName) 
	{
		String imagePath = getServerContextPath() + File.separator + imageName;
		File image = new File(imagePath);
		try 
		{
			return Files.readAllBytes(image.toPath());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}	
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
		return servletContext.getRealPath("/") + "ProfileImages";
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
	
	@RequestMapping(path= "/", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash, HttpSession session) 
	{	
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
		
		return "redirect:/users/" + newU.getUserName();
	}
	
	@RequestMapping(path="/users/{userName}", method=RequestMethod.GET)
	public String profilePage(Map<String, User> model, @PathVariable String userName, HttpSession session, ModelMap map) 
	{
		model.put("profile", userDAO.getUserByUserName(userName));
		map.addAttribute("subject", subjectDAO.getAllSubjects(userName));
		int averagePandas = reviewDAO.averagePandaRating(userName);
		map.addAttribute("pandas", averagePandas);
		
		return "profilePage";
	}
	
	@RequestMapping(path="/users/{userName}/updateSubject", method=RequestMethod.GET)
	public String updateSubject(Map<String, User> model, @PathVariable String userName, HttpSession session, ModelMap map) 
	{
		model.put("profile", userDAO.getUserByUserName(userName));
		map.addAttribute("subject",subjectDAO.getAllSubjects(userName));
		return "profilePage";
	}
	
	@RequestMapping(path="/users/{userName}/createSubject", method=RequestMethod.GET)
	public String createSubject(Map<String, User> model, @PathVariable String userName, HttpSession session, ModelMap map) 
	{
		model.put("profile", userDAO.getUserByUserName(userName));
		map.addAttribute("subject",subjectDAO.getAllSubjects(userName));
		return "profilePage";
	}
	
	@RequestMapping(path="/users/{userName}", method=RequestMethod.POST)
	public String updateProfile(@PathVariable String userName, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String bio,
								@RequestParam String email, @RequestParam String phone, HttpSession session) 
	{
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBio(bio);
		user.setEmail(email);
		user.setPhone(phone);
		
		userDAO.updateProfile(user, userName);
		session.setAttribute("currentUser", user);
		
		return "redirect:/users/" + userName;
	}
	
	@RequestMapping(path="/users/{userName}/createSubject", method=RequestMethod.POST)
	public String createClass(@PathVariable String userName, @RequestParam String subjectName, @RequestParam String location, @RequestParam String date,
							  @RequestParam String startTime, @RequestParam String endTime, @RequestParam Float cost, @RequestParam int availableSlots,
							  @RequestParam String description, HttpSession session) throws ParseException 
	{
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		subject.setLocation(location);
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date newDate = formatter.parse(date);
		subject.setDate(newDate);
		subject.setStartTime(startTime);
		subject.setEndTime(endTime);
		subject.setCost(cost);
		subject.setAvailableSlots(availableSlots);
		subject.setDescription(description);
		
		subjectDAO.saveSubject(subject);
		subjectDAO.addUserToClass(subject.getClassId(), userName);
		session.setAttribute("currentUser", userName);
										
		return "redirect:/users/" + userName;
	}
	
	@RequestMapping(path="/users/{userName}/updateSubject", method=RequestMethod.POST)
	public String updateClass(@PathVariable String userName, @RequestParam int classId, @RequestParam String subjectName, @RequestParam String location,
							  @RequestParam String date, @RequestParam String startTime, @RequestParam String endTime, @RequestParam Float cost,
							  @RequestParam int availableSlots, @RequestParam String description, HttpSession session) throws ParseException 
	{	
		Subject subject = subjectDAO.getSubjectById(classId);
		subject.setSubjectName(subjectName);
		subject.setLocation(location);
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date newDate = formatter.parse(date);
		subject.setDate(newDate);
		subject.setStartTime(startTime);
		subject.setEndTime(endTime);
		subject.setCost(cost);
		subject.setAvailableSlots(availableSlots);
		subject.setDescription(description);
		
		subjectDAO.updateSubject(subject, subject.getClassId());
		session.setAttribute("currentUser", userName);
										
		return "redirect:/users/" + userName;
	}
	
	@RequestMapping(path="/messages/{userName}", method=RequestMethod.GET)
	public String displayAllMessages(@PathVariable String userName, ModelMap map, HttpSession session) 
	{
		map.addAttribute("message", messageDAO.getMessagesForUser(userName));
		session.setAttribute("currentUser", userName);
		
		return "messageList";
	}
	
	@RequestMapping(path="search/subjects/", method=RequestMethod.GET)
	public String displaySenseisForSubject(@PathVariable String subjectName, ModelMap map) {
		
		map.addAttribute("subject", subjectDAO.getAllSubjects(subjectName));
		return "subjectList";
	}
	
	@RequestMapping(path="/sendMessage/{userName}", method=RequestMethod.GET)
	public String displayMessagingForm(@PathVariable String userName, HttpSession session) 
	{	
		session.setAttribute("currentUser", userName);
		
		return "messaging";
	}
	
	@RequestMapping(path="/sendMessage/{userName}", method=RequestMethod.POST)
	public String displayMessagingForm(@PathVariable String userName, HttpSession session, @RequestParam String receiverName, @RequestParam String messageSubject,
										@RequestParam String messageBody) 
	{	
		Message message = new Message();
		message.setSenderName(userName);
		message.setReceiverName(receiverName);
		message.setMessageSubject(messageSubject);
		message.setMessageBody(messageBody);
		LocalDate date = LocalDate.now();
		message.setDate(date);

		messageDAO.saveMessage(message);
		session.setAttribute("currentUser", userName);
		
		return "redirect:/messages/" + userName;
	}
	
	@RequestMapping(path="/{userName}/review", method=RequestMethod.GET)
	public String displayReviewForm(@PathVariable String userName, HttpSession session, HttpServletRequest request, ModelMap map) 
	{	
		String selectedUser = request.getParameter("userName");
		User user = userDAO.getUserByUserName(selectedUser);
		map.addAttribute("userProfile", user);
		
		return "review";
	}
	
	@RequestMapping(path="/{userName}/review", method=RequestMethod.POST) 
	public String submitReview(@PathVariable String userName, HttpSession session, @RequestParam String reviewee, @RequestParam int pandaRating, @RequestParam String review) 
	{	
		session.setAttribute("currentUser", userName);

		Review r = new Review();
		r.setReviewee(reviewee);
		r.setReviewer(userName);
		r.setPandaRating(pandaRating);
		r.setReview(review);
		
		reviewDAO.saveReview(r);
		
		return "redirect:/" + userName + "/profile?userName=" + reviewee;
	}
	
	@RequestMapping(path="/{userName}/profile", method=RequestMethod.GET)
	public String displayProfile(@PathVariable String userName, ModelMap map, HttpServletRequest request) 
	{	
		String selectedUser = request.getParameter("userName");
		User user = userDAO.getUserByUserName(selectedUser);
		map.addAttribute("userProfile", user);
		List<Subject> subject = subjectDAO.getAllSubjects(userName);
		map.addAttribute("subject", subject);
		List<Review> review = reviewDAO.getReviewsForUser(selectedUser);
		map.addAttribute("review", review);
		int averagePandas = reviewDAO.averagePandaRating(selectedUser);
		map.addAttribute("pandas", averagePandas);
		
		return "viewProfile";
	}
	
	@RequestMapping(path="/{userName}/profile", method=RequestMethod.POST)
	public String postReview(@PathVariable String userName, HttpServletRequest request) 
	{	
		String profileName = request.getParameter("userName");
		User user = userDAO.getUserByUserName(profileName);
		request.setAttribute("userProfile", user);
		
		return "redirect:/{userName}/review?userName=" + profileName;
	}
	
	@RequestMapping(path="/users/search", method=RequestMethod.GET)
	public String displaySearch(ModelMap map, HttpServletRequest request) 
	{	
		String subjectName = request.getParameter("subjectName");
		List<User> senseisViaSubject = userDAO.getSenseisBySubject(subjectName);
		map.addAttribute("senseis", senseisViaSubject);

		return "searchPage";
	}
}	