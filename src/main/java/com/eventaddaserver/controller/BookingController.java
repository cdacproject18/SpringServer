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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventaddaserver.dao.BookingDao;
import com.eventaddaserver.pojos.Booking;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
	@Resource(name = "bookingDao")
	private BookingDao bookingDao;

	@GetMapping("/customer/{custId:.+}")
	public ResponseEntity<List<Booking>> getBookingByCustomer(@PathVariable String custId) {
		System.out.println("Inside get booking by customer" + custId);
		try {
			return new ResponseEntity<List<Booking>>(bookingDao.getBookingByUser(custId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<List<Booking>>(new ArrayList<Booking>(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<?> getBooking(@PathVariable String bookId) {
		System.out.println("in fetch " + bookId);
		try {
			Booking b = bookingDao.findBookingById(bookId);
			return new ResponseEntity<Booking>(b, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Fetching a/c info failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<String> addBooking(@RequestBody Booking b) {
		System.out.println("in Booking ");
		try {
			return new ResponseEntity<String>(bookingDao.add(b), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Booking failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteCategory(@PathVariable String bookId) {
		System.out.println("in cancellation " + bookId);
		try {
			return new ResponseEntity<String>(bookingDao.delete(bookId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Cancellation Failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
