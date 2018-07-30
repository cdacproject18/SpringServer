package com.eventaddaserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventaddaserver.dao.CustomerDao;
import com.eventaddaserver.pojos.Customer;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	// Displaying the initial users list.
	@GetMapping("/list")
	public ResponseEntity<?> getPersons() {
		try {
			return new ResponseEntity<List<Customer>>(customerDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Validate user
	@GetMapping("/{custId}/{custPass}")
	public ResponseEntity<?> getCustomer(@PathVariable String custId, @PathVariable String custPass) {
		try {
			return new ResponseEntity<Customer>(customerDao.getCustomer(custId, custPass), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Account not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Opening the add new user form page.
	@PostMapping("/")
	public ResponseEntity<String> addCustomer(@RequestBody Customer c) {
		System.out.println("in add customer");
		try {
			return new ResponseEntity<String>(customerDao.add(c), HttpStatus.OK);
		} catch (RuntimeException ex) {
			return new ResponseEntity<String>("Creating event failed" + ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Opening the edit user form page.
	@PutMapping(value = "/")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer c) {
		System.out.println("in update customer");
		try {
			return new ResponseEntity<String>(customerDao.edit(c), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Updating customer failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Deleting the specified user.
	@DeleteMapping("/{custId}")
	public ResponseEntity<String> delete(@PathVariable int custId) {
		System.out.println("in remove customer");
		try {
			return new ResponseEntity<String>(customerDao.delete(String.valueOf(custId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deleion failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
