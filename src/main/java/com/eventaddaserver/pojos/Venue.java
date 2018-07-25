package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.List;

public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;

	private String _id;
	private String name;
	private String location;
	private String contact;

	private List<String> section;

	public Venue() {
		super();
	}

	public Venue(String _id, String name, String location, String contact, List<String> section) {
		super();
		this._id = _id;
		this.name = name;
		this.location = location;
		this.contact = contact;
		this.section = section;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<String> getSection() {
		return section;
	}

	public void setSection(List<String> section) {
		this.section = section;
	}

}
