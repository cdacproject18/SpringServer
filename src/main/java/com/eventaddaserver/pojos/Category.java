package com.eventaddaserver.pojos;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private String name;
	private String genre;

	public Category() {
		super();
	}

	public Category(String _id, String name, String genre) {
		super();
		this._id = _id;
		this.name = name;
		this.genre = genre;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
