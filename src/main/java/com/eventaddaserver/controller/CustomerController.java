package com.eventaddaserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventaddaserver.dao.*;
import com.eventaddaserver.pojos.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static Logger log = Logger.getLogger(CustomerController.class);

	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	// Displaying the initial users list.
	@GetMapping("/list")
	public ResponseEntity<?> getPersons() {
		log.debug("Request to fetch all users from the mongo database");
		try {
			return new ResponseEntity<List<Customer>>(customerDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	// Opening the add new user form page.
		@GetMapping("/add")
		public String addCustomer(Model model) {
			log.debug("Request to open the new cutomer form page");
			model.addAttribute("custAttr", new Customer());
			return "form";
		}
		
		
		// Opening the edit user form page.
		@GetMapping(value = "/edit")
		public String editCustomer(@RequestParam(value = "id", required = true) String id, Model model) {
			log.debug("Request to open the edit Customer form page");
			model.addAttribute("custAttr", customerDao.findUserById(id));
			return "form";
		}

		// Deleting the specified user.
		@GetMapping("/delete")
		public String delete(@RequestParam(value = "id", required = true) String id, Model model) {
			customerDao.delete(id);
			return "redirect:list";
		}

		// Adding a new user or updating an existing user.
		@PostMapping("/save")
		public String save(@ModelAttribute("custAttr") Customer cu) {
			if (cu.getId() != null && !cu.getId().trim().equals("")) {
				customerDao.edit(cu);
			} else {
				customerDao.add(cu);
			}
			return "redirect:list";
		}
}
