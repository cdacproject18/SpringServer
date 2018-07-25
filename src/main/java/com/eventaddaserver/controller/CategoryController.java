package com.eventaddaserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventaddaserver.dao.CategoryDao;
import com.eventaddaserver.pojos.Category;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Resource(name = "categoryDao")
	private CategoryDao categoryDao;

	@GetMapping("/list")
	public ResponseEntity<?> getCategories() {
		System.out.println("Inside get categories");
		try {
			return new ResponseEntity<List<Category>>(categoryDao.getAll(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{catId}")
	public ResponseEntity<?> getCategory(@PathVariable int catId) {
		System.out.println("in fetch " + catId);
		try {
			Category c = categoryDao.findCategoryById(String.valueOf(catId));
			return new ResponseEntity<Category>(c, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<String> addCategory(@RequestBody Category c) {
		System.out.println("in create category");
		try {
			return new ResponseEntity<String>(categoryDao.add(c), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Creating category failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	public ResponseEntity<String> updateCategory(@RequestBody Category c) {
		System.out.println("in update a/c " + c);
		try {
			return new ResponseEntity<String>(categoryDao.edit(c), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Updating a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{catId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int catId) {
		System.out.println("in del " + catId);
		try {
			return new ResponseEntity<String>(categoryDao.delete(String.valueOf(catId)), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Closing  a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
