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

import com.eventaddaserver.dao.VenueDao;
import com.eventaddaserver.pojos.Venue;

@RestController
@CrossOrigin
@RequestMapping("/venue")
public class VenueController {
	@Resource(name = "venueDao")
	private VenueDao venueDao;

	@GetMapping("/list")
	public ResponseEntity<?> getVenues() {
		System.out.println("Inside get venues");
		try {
			return new ResponseEntity<List<Venue>>(venueDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{venId}")
	public ResponseEntity<?> getVenue(@PathVariable String venId) {
		System.out.println("Inside get venue");
		try {
			return new ResponseEntity<Venue>(venueDao.findVenueById(venId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<String> addVenue(@RequestBody Venue v) {
		System.out.println("in create venue");
		try {
			return new ResponseEntity<String>(venueDao.add(v), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Creating venue failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	public ResponseEntity<String> updateCategory(@RequestBody Venue v) {
		System.out.println("in update venue ");
		try {
			return new ResponseEntity<String>(venueDao.edit(v), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Updating venue info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{venId}")
	public ResponseEntity<String> deleteVenue(@PathVariable String venId) {
		System.out.println("in del " + venId);
		try {
			return new ResponseEntity<String>(venueDao.delete(venId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Venue del failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
