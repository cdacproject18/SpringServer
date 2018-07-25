package com.eventaddaserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventaddaserver.dao.SectionDao;
import com.eventaddaserver.pojos.Section;
import com.eventaddaserver.pojos.Venue;

@RestController
@RequestMapping("/section")
public class SectionController {

	@Resource(name = "sectionDao")
	private SectionDao sectionDao;

	@GetMapping("/list")
	public ResponseEntity<?> getSection() {
		System.out.println("Inside get section");
		try {
			return new ResponseEntity<List<Section>>(sectionDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{secId}")
	public ResponseEntity<?> getSection(@PathVariable int secId) {
		System.out.println("Inside get venue");
		try {
			return new ResponseEntity<Section>(sectionDao.findSectionById(String.valueOf(secId)), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{secId}")
	public ResponseEntity<String> deleteVenue(@PathVariable int senId) {
		System.out.println("in del " + senId);
		try {
			return new ResponseEntity<String>(sectionDao.delete(String.valueOf(senId)), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Venue del failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
