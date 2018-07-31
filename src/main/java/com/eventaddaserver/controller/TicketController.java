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

import com.eventaddaserver.dao.TicketDao;
import com.eventaddaserver.pojos.Ticket;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {

	@Resource(name = "ticketDao")
	private TicketDao ticketDao;

	@GetMapping("/{bookId}")
	public ResponseEntity<List<Ticket>> getTicket(@PathVariable String bookId) {
		System.out.println("in fetch " + bookId);
		try {
			return new ResponseEntity<List<Ticket>>(ticketDao.findBookingById(bookId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<List<Ticket>>(new ArrayList<Ticket>(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<String> addBooking(@RequestBody Ticket t) {
		System.out.println("in Booking");
		try {
			return new ResponseEntity<String>(ticketDao.add(t), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Ticket Creating failed" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBooking(@PathVariable String bookId) {
		System.out.println("in del " + bookId);
		try {
			return new ResponseEntity<String>(ticketDao.delete(bookId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Ticket Cancellation failed " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
