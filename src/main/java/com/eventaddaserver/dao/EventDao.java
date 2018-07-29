package com.eventaddaserver.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.Event;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class EventDao {
	static String db_name = "mydb", db_collection = "event";

	// Fetch all event from the mongo database.
	public List<Event> getAll() {
		List<Event> eventList = new ArrayList<Event>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();

		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			ArrayList<String> artList = new ArrayList<String>();
			ArrayList<String> langList = new ArrayList<String>();

			Event event = new Event();
			event.set_id(dbObject.get("_id").toString());
			event.setName(dbObject.get("name").toString());
			event.setDescription(dbObject.get("description").toString());

			BasicDBList artListObject = (BasicDBList) dbObject.get("artist");
			for (Iterator<Object> it = artListObject.iterator(); it.hasNext();)
				artList.add(it.next().toString());

			event.setCategoryId(dbObject.get("categoryid").toString());

			BasicDBList langListObject = (BasicDBList) dbObject.get("language");
			for (Iterator<Object> it = langListObject.iterator(); it.hasNext();)
				langList.add(it.next().toString());

			try {
				event.setTime(MongoFactory.getDate(dbObject.get("time").toString()));
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}

			event.setVenueId(dbObject.get("venueid").toString());
			event.setArtist(artList);
			event.setLanguage(langList);

			// Adding the event details to the list.
			eventList.add(event);
		}
		return eventList;
	}

	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected event id to search.
		where_query.put("_id", id);
		return coll.findOne(where_query);
	}

	// Fetching a single event details from the mongo database.
	public Event findEventById(String id) {
		Event e = new Event();
		DBObject dbo = getDBObject(id);
		ArrayList<String> artList = new ArrayList<String>();
		ArrayList<String> langList = new ArrayList<String>();

		e.set_id(dbo.get("_id").toString());
		e.setName(dbo.get("name").toString());
		e.setDescription(dbo.get("description").toString());
		e.setCategoryId(dbo.get("categoryid").toString());

		try {
			e.setTime(MongoFactory.getDate(dbo.get("time").toString()));
		} catch (ParseException ex) {
			System.out.println(ex.getMessage());
		}

		BasicDBList artListObject = (BasicDBList) dbo.get("artist");
		BasicDBList langListObject = (BasicDBList) dbo.get("language");

		for (Iterator<Object> it = artListObject.iterator(); it.hasNext();)
			artList.add(it.next().toString());
		for (Iterator<Object> it = langListObject.iterator(); it.hasNext();)
			langList.add(it.next().toString());

		e.setVenueId(dbo.get("venueid").toString());
		e.setArtist(artList);
		e.setLanguage(langList);

		// Return event object.
		return e;
	}

	// Add a new event to the mongo database.
	public String add(Event event) {
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new event details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("_id", event.get_id());
			doc.put("name", event.getName());
			doc.put("description", event.getDescription());
			doc.put("artist", event.getArtist());
			doc.put("categoryid", event.getCategoryId());
			doc.put("language", event.getLanguage());
			doc.put("time", event.getTime());
			doc.put("venueid", event.getVenueId());

			// Save a new event to the mongo collection.
			coll.insert(doc);
			return "Event added";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Update the selected venue in the mongo database.
	public String edit(Event event) {
		try {
			// Fetching the event details.
			BasicDBObject existing = (BasicDBObject) getDBObject(event.get_id().toString());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("_id", event.get_id());
			edited.put("name", event.getName());
			edited.put("description", event.getDescription());
			edited.put("artist", event.getArtist());
			edited.put("categoryid", event.getCategoryId());
			edited.put("language", event.getLanguage());
			edited.put("time", event.getTime());
			edited.put("venueid", event.getVenueId());

			// Update the existing event to the mongo database.
			coll.update(existing, edited);
			return "Event updated";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Delete a event from the mongo database.
	public String delete(String id) {
		try {
			// Fetching the required event from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected event from the mongo database.
			coll.remove(item);
			return "Deleted item";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	public List<Event> getEventList (String id) {
		List<Event> eventList = new ArrayList<Event>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected event id to search.
		where_query.put("categoryid", id);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find(where_query);

		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			ArrayList<String> artList = new ArrayList<String>();
			ArrayList<String> langList = new ArrayList<String>();

			Event event = new Event();
			event.set_id(dbObject.get("_id").toString());
			event.setName(dbObject.get("name").toString());
			event.setDescription(dbObject.get("description").toString());

			BasicDBList artListObject = (BasicDBList) dbObject.get("artist");
			for (Iterator<Object> it = artListObject.iterator(); it.hasNext();)
				artList.add(it.next().toString());

			event.setCategoryId(dbObject.get("categoryid").toString());

			BasicDBList langListObject = (BasicDBList) dbObject.get("language");
			for (Iterator<Object> it = langListObject.iterator(); it.hasNext();)
				langList.add(it.next().toString());

			try {
				event.setTime(MongoFactory.getDate(dbObject.get("time").toString()));
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}

			event.setVenueId(dbObject.get("venueid").toString());
			event.setArtist(artList);
			event.setLanguage(langList);

			// Adding the event details to the list.
			eventList.add(event);
		}
		return eventList;

	}
}
