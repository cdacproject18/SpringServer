package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.Date;

public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	private String location;
	private Date time;
	private Boolean flag;

	public Seat() {
		super();
	}

	public Seat(String location, Date time, Boolean flag) {
		super();
		this.location = location;
		this.time = time;
		this.flag = flag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
