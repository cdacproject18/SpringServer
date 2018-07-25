package com.eventaddaserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventaddaserver.dao.UserDao;
import com.eventaddaserver.pojos.User;

@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Resource(name = "userDao")
	private UserDao userDao;

	// Displaying the initial users list.
	// @RequestMapping(value = "/list", method = RequestMethod.GET)
	@GetMapping("/list")
	public ResponseEntity<?> getPersons() {
		log.debug("Request to fetch all users from the mongo database");
		try {
			return new ResponseEntity<List<User>>(userDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

/*	// Opening the add new user form page.
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		log.debug("Request to open the new user form page");
		model.addAttribute("userAttr", new User());
		return "form";
	}

	// Opening the edit user form page.
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value = "id", required = true) String id, Model model) {
		log.debug("Request to open the edit user form page");
		model.addAttribute("userAttr", userDao.findUserId(id));
		return "form";
	}

	// Deleting the specified user.
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id", required = true) String id, Model model) {
		userDao.delete(id);
		return "redirect:list";
	}

	// Adding a new user or updating an existing user.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("userAttr") User user) {
		if (user.getId() != null && !user.getId().trim().equals("")) {
			userDao.edit(user);
		} else {
			userDao.add(user);
		}
		return "redirect:list";
	}*/
}
