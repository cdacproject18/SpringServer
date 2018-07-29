package com.eventaddaserver.controller;

import java.util.ArrayList;
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

import com.eventaddaserver.dao.EventDao;
import com.eventaddaserver.pojos.Event;

@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {
	@Resource(name = "eventDao")
	private EventDao eventDao;

	@GetMapping("/list")
	public ResponseEntity<?> getEvents() {
		System.out.println("Inside get event");
		try {
			return new ResponseEntity<List<Event>>(eventDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{eveId}")
	public ResponseEntity<?> getEvent(@PathVariable int eveId) {
		System.out.println("Inside get event");
		try {
			return new ResponseEntity<Event>(eventDao.findEventById(String.valueOf(eveId)), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("list/sports")
	public ResponseEntity<List<Event>> getSportEvent() {
		System.out.println("Inside sport event");
		try {
			return new ResponseEntity<List<Event>>(eventDao.getSportEvent(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<List<Event>>(new ArrayList<Event>(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<String> addEvent(@RequestBody Event e) {
		System.out.println("in create event");
		try {
			return new ResponseEntity<String>(eventDao.add(e), HttpStatus.OK);
		} catch (RuntimeException ex) {
			return new ResponseEntity<String>("Creating event failed" + ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	public ResponseEntity<String> updateEvent(@RequestBody Event e) {
		System.out.println("in update event");
		try {
			return new ResponseEntity<String>(eventDao.edit(e), HttpStatus.OK);
		} catch (RuntimeException ex) {
			return new ResponseEntity<String>("Updating venue info failed " + ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{eveId}")
	public ResponseEntity<String> deleteEvent(@PathVariable int eveId) {
		System.out.println("in del " + eveId);
		try {
			return new ResponseEntity<String>(eventDao.delete(String.valueOf(eveId)), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Event del failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
