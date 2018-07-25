package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.List;

public class Section implements Serializable {
	private static final long serialVersionUID = 1L;

	private String _id;
	private String name;
	private String noOfSeats;
	private Double price;

	private List<Seat> seats;

	public Section() {
		super();
	}

	public Section(String _id, String name, String noOfSeats, Double price, List<Seat> seats) {
		super();
		this._id = _id;
		this.name = name;
		this.noOfSeats = noOfSeats;
		this.price = price;
		this.seats = seats;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(String noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
