package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.Date;

public class Booking  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String _id;
	private Date timestamp;
	private Boolean paymentstatus; 
	private String eventid;
	private String customerid;
	private String nooftickets;
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(String _id,Date timestamp, Boolean paymentstatus, String eventid, String customerid, String nooftickets) {
		super();
		this._id=_id;
		this.timestamp = timestamp;
		this.paymentstatus = paymentstatus;
		this.eventid = eventid;
		this.customerid = customerid;
		this.nooftickets = nooftickets;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(Boolean paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getNooftickets() {
		return nooftickets;
	}

	public void setNooftickets(String nooftickets) {
		this.nooftickets = nooftickets;
	}

	
}
