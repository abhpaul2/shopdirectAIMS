package com.shopdirect.aims.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shopdirect.aims.model.User;
import com.shopdirect.aims.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String index (Model model) {
		return "index";
	}

	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET, params="userId")
	public ModelAndView search(@RequestParam("userId") String userId) {	
		ModelAndView model = null;
		try {
			User user = userService.getCloneById(userId);
			model = new ModelAndView("userDetails");
			model.addObject("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			model = new ModelAndView("revokeUser");
			model.addObject("errorMsg", "User does not exist.");			
		}
		return model;
	}	
	
	@RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("addUser", "user", new User());
	}	

	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("user") User user) {
		user.setStatus(true);
		boolean userExist = false;
		boolean cloneExist = false;
		ModelAndView model = null;
		try {
			User existingUser = userService.getUserById(user.getUserId());
			if(existingUser!=null) {
				userExist = true;
			}			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		if(!userExist) {
			try {
				User existingClone = userService.getCloneById(user.getCloneId());
				if(existingClone!=null) {
					cloneExist = true;
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(userExist) {
			model = new ModelAndView("addUser", "user", user);
			model.addObject("errorMsg", "User id is already exist !!!");
		} else if(!cloneExist) {
			model = new ModelAndView("addUser", "user", user);
			model.addObject("errorMsgClone", "Clone id doesn't exist !!!");
		} else {
			userService.insertUser(user);
			model = new ModelAndView("addUserSuccess");
			model.addObject("user", user);
			model.addObject("msg", "User has been added successfully.");		
			model.addObject("addFunction", true);
		}
		return model;
	}	
	
	@RequestMapping(value = "/alterUser", method = RequestMethod.GET)
	public ModelAndView alter() {	
		ModelAndView model = new ModelAndView("alterUser");
		return model;
	}
	@RequestMapping("/revoke")
	public ModelAndView revokePage() {
		return new ModelAndView("revokeUser");
	}
	
	@RequestMapping(value = "/revokeUser", method = RequestMethod.GET, params="userId")
	public ModelAndView revoke(@RequestParam("userId") String userId) {	
		User user = userService.getCloneById(userId);
		boolean result = userService.revokeUser(userId);		
		if(result) user.setStatus(false);		
		ModelAndView model = new ModelAndView("userDetails");
		model.addObject("msg", "Access has been revoked successfully.");
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET, params="userId")
	public ModelAndView update(@RequestParam("userId") String userId) {		
		User user = userService.getUserById(userId);	
		ModelAndView model = new ModelAndView("updateUser");
		model.addObject("user", user);
		return model;
	}	
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView processRequestUpdate(@ModelAttribute("user") User user) {
		user.setStatus(true);
		//userService.insertUser(user);
		//List<User> users = userService.getAllUsers();
		List<User> users = new ArrayList<User>();
		users.add(user);
		ModelAndView model = new ModelAndView("userDetails");
		model.addObject("msg", "CloneID has been updated successfully.");
		model.addObject("users", users);
		model.addObject("addFunction", true);
		return model;
	}	
	
	@RequestMapping("/getUsers")
	public ModelAndView getUsers() {
		//List<User> users = userService.getAllUsers();
		List<User> users = null;
		ModelAndView model = new ModelAndView("getUsers");
		model.addObject("users", users);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null) { 
			model.addAttribute("msg", "You have been logged out successfully.");
			return "index";
		}
		return "login";
	}

}
