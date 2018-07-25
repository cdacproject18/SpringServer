package com.eventaddaserver.pojos;

import java.io.Serializable;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class SeatLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String section, location;
	 public SeatLocation() {
		
	}

	 
	public SeatLocation(String section, String location) {
		super();
		this.section = section;
		this.location = location;
	}


	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public DBObject bsonFromPojo()
	{
		BasicDBObject doc= new BasicDBObject();
		doc.put("section", this.getSection());
		
		doc.put("location", this.getLocation());
		return doc;
	}

	public DBObject makePojoFromBson(DBObject bson)
	{
		BasicDBObject doc= (BasicDBObject)bson;
		this.section= (String)doc.get("section");
		this.location= (String)doc.get("location");
		return doc;
	}
}
