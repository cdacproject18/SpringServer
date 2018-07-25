package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Ticket implements Serializable  {
	private static final long serialVersionUID = 1L;

	private String bookingid ;
	private  List<SeatLocation> seatlocation;
	private Double price;
	 public Ticket() {
		// TODO Auto-generated constructor stub
	}
	 
	
	public Ticket(String bookingid, List<SeatLocation> seatlocation, Double price) {
		super();
		this.bookingid = bookingid;
		this.seatlocation = seatlocation;
		this.price = price;
	}


	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public List<SeatLocation> getSeatlocation() {
		return seatlocation;
	}
	public void setSeatlocation(List<SeatLocation> seatlocation) {
		this.seatlocation = seatlocation;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public DBObject bsonFromPojo()
	{
		BasicDBObject doc= new BasicDBObject();
		doc.put("bookingid", this.getBookingid());
		doc.put("seatlocation", this.getSeatlocation());
		doc.put("price", this.getPrice());
		return doc;
	}
	
	public DBObject makePojoFromBson(DBObject bson)
	{
		BasicDBObject doc= (BasicDBObject)bson;
		this.bookingid= (String)doc.get("bookingid");
		this.seatlocation= (List<SeatLocation>)doc.get("seatlocation");
		this.price= (Double)doc.get("price");
		return doc;
	}
}
