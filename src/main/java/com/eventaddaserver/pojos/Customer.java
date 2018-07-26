package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String _id;
	
	private String name ;
	private String number ;
	private Date dob ; 
	private String gender ;
	private Address address ; 
	private String email ;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String id, String name, String number, Date dob, String gender, Address address, String email) {
		super();
		this._id = id;
		this.name = name;
		this.number = number;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.email = email;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	
}
