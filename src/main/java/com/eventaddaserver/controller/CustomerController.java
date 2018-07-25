package com.eventaddaserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
