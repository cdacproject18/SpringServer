package com.eventaddaserver.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.Venue;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class VenueDao {
	static String db_name = "mydb", db_collection = "venue";

	// Fetch all venue from the mongo database.
	public List<Venue> getAll() {
		List<Venue> venueList = new ArrayList<Venue>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			ArrayList<String> secList = new ArrayList<String>();

			Venue venue = new Venue();
			venue.set_id(dbObject.get("_id").toString());
			venue.setName(dbObject.get("name").toString());
			venue.setLocation(dbObject.get("location").toString());
			venue.setContact(dbObject.get("contact").toString());
			BasicDBList secListObject = (BasicDBList) dbObject.get("section");

			for (Iterator<Object> it = secListObject.iterator(); it.hasNext();)
				secList.add(it.next().toString());

			venue.setSection(secList);

			// Adding the venue details to the list.
			venueList.add(venue);
		}
		return venueList;
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

	// Fetching a single category details from the mongo database.
	public Venue findVenueById(String id) {
		Venue v = new Venue();
		DBObject dbo = getDBObject(id);
		ArrayList<String> secList = new ArrayList<String>();

		v.set_id(dbo.get("_id").toString());
		v.setName(dbo.get("name").toString());
		v.setLocation(dbo.get("location").toString());
		v.setContact(dbo.get("contact").toString());
		BasicDBList secListObject = (BasicDBList) dbo.get("section");

		for (Iterator<Object> it = secListObject.iterator(); it.hasNext();)
			secList.add(it.next().toString());

		v.setSection(secList);

		// Return category object.
		return v;
	}

	// Add a new venue to the mongo database.
	public String add(Venue venue) {
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new category details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("_id", venue.get_id());
			doc.put("name", venue.getName());
			doc.put("location", venue.getLocation());
			doc.put("section", venue.getSection());
			doc.put("contact", venue.getContact());

			// Save a new venue to the mongo collection.
			coll.insert(doc);
			return "Venue added";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Update the selected venue in the mongo database.
	public String edit(Venue venue) {
		try {
			// Fetching the venue details.
			BasicDBObject existing = (BasicDBObject) getDBObject(venue.get_id().toString());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("_id", venue.get_id());
			edited.put("name", venue.getName());
			edited.put("location", venue.getLocation());
			edited.put("section", venue.getSection());
			edited.put("contact", venue.getContact());

			// Update the existing venue to the mongo database.
			coll.update(existing, edited);
			return "Venue updated";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Delete a venue from the mongo database.
	public String delete(String id) {
		try {
			// Fetching the required venue from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected venue from the mongo database.
			coll.remove(item);
			return "Deleted item";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}
}
