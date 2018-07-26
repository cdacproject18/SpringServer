package com.eventaddaserver.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.Booking;
import com.eventaddaserver.pojos.Category;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class BookingDao {
	static String db_name = "mydb", db_collection = "booking";

	// Add a new Booking to the mongo database.
	
	public String add(Booking book) {
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new category details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("_id", book.get_id());
			doc.put("timestamp", book.getTimestamp());
			doc.put("paymentstatus",book.getPaymentstatus());
			doc.put("eventid",book.getEventid());
			doc.put("customerid", book.getCustomerid());
			doc.put("nooftickets", book.getNooftickets());
			// Save a new category to the mongo collection.
			coll.insert(doc);
			return "Successfully booked";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	

	// Delete a booking from the mongo database.
	public String delete(String id) {
		try {
			// Fetching the required category from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected category from the mongo database.
			coll.remove(item);
			return "Booking cancelled successfully";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected category id to search.
		where_query.put("_id", id);
		return coll.findOne(where_query);
	}

	// Fetching a single booking details from the mongo database.
	public Booking findBookingById(String id) {
		Booking b= new Booking();
		DBObject dbo = getDBObject(id);

		b.set_id(dbo.get("_id").toString());
		try{
		b.setTimestamp(MongoFactory.getDate(dbo.get("timestamp").toString()));
		}catch(ParseException e)
		{
			b.set_id(dbo.get("id").toString());
			b.setCustomerid(dbo.get("customerid").toString());
			b.setEventid(dbo.get("eventid").toString());
			b.setNooftickets(dbo.get("nooftickets").toString());
			
		// Return booking object.
		return b;
	}
}
}
