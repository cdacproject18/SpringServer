package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	private String _id;
	private String name;
	private String categoryId;
	private String description;
	private String venueId;
	private Date time;
	private List<String> artist;
	private List<String> language;
	private List<String> image;

	public Event() {
		super();
	}

	public Event(String _id, String name, String categoryId, String description, String venueId, Date time,
			List<String> artist, List<String> language, List<String> image) {
		super();
		this._id = _id;
		this.name = name;
		this.categoryId = categoryId;
		this.description = description;
		this.venueId = venueId;
		this.time = time;
		this.artist = artist;
		this.language = language;
		this.image = image;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<String> getArtist() {
		return artist;
	}

	public void setArtist(List<String> artist) {
		this.artist = artist;
	}

	public List<String> getLanguage() {
		return language;
	}

	public void setLanguage(List<String> language) {
		this.language = language;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

}
