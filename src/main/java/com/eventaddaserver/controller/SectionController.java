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

import com.eventaddaserver.dao.SectionDao;
import com.eventaddaserver.pojos.Section;

@RestController
@CrossOrigin
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
	public ResponseEntity<?> getSection(@PathVariable String secId) {
		System.out.println("Inside get venue");
		try {
			return new ResponseEntity<Section>(sectionDao.findSectionById(secId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<String> addSection(@RequestBody Section v) {
		System.out.println("in create section");
		try {
			return new ResponseEntity<String>(sectionDao.add(v), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Creating section failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	public ResponseEntity<String> updateSection(@RequestBody Section s) {
		System.out.println("in update section");
		try {
			return new ResponseEntity<String>(sectionDao.edit(s), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Updating section info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{secId}")
	public ResponseEntity<String> deleteVenue(@PathVariable String secId) {
		System.out.println("in del " + secId);
		try {
			return new ResponseEntity<String>(sectionDao.delete(secId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Venue del failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
