package com.eventaddaserver.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.Seat;
import com.eventaddaserver.pojos.SeatLocation;
import com.eventaddaserver.pojos.Section;
import com.eventaddaserver.pojos.Ticket;
import com.eventaddaserver.pojos.Venue;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class SectionDao {
	static String db_name = "mydb", db_collection = "section";

	// Fetch all sections from the mongo database.
	public List<Section> getAll() {
		List<Section> sectionList = new ArrayList<Section>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			ArrayList<Seat> seatList = new ArrayList<Seat>();

			Section section = new Section();
			section.set_id(dbObject.get("_id").toString());
			section.setName(dbObject.get("name").toString());
			section.setNoOfSeats(dbObject.get("noofseats").toString());
			section.setPrice(Double.parseDouble(dbObject.get("price").toString()));
			BasicDBList secListObject = (BasicDBList) dbObject.get("seats");

			for (Iterator<Object> it = secListObject.iterator(); it.hasNext();) {
				DBObject seatObject = (DBObject) it.next();
				Seat seat = new Seat();
				seat.setLocation(seatObject.get("location").toString());
				System.out.println(seatObject.get("time"));
				seat.setFlag(Boolean.valueOf(seatObject.get("flag").toString()));
				try {
					seat.setTime(MongoFactory.getDate(seatObject.get("time").toString()));
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				seatList.add(seat);
			}

			section.setSeats(seatList);

			// Adding the user details to the list.
			sectionList.add(section);
		}
		return sectionList;
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
	public Section findSectionById(String id) {
		Section s = new Section();
		DBObject dbo = getDBObject(id);
		List<Seat> seatList = new ArrayList<Seat>();

		s.set_id(dbo.get("_id").toString());
		s.setName(dbo.get("name").toString());
		s.setNoOfSeats(dbo.get("noofseats").toString());
		s.setPrice(Double.parseDouble(dbo.get("price").toString()));

		BasicDBList seatListObject = (BasicDBList) dbo.get("seats");

		for (Iterator<Object> it = seatListObject.iterator(); it.hasNext();) {
			DBObject seatObject = (DBObject) it.next();
			Seat seat = new Seat();
			seat.setLocation(seatObject.get("location").toString());
			System.out.println(seatObject.get("time"));
			seat.setFlag(Boolean.valueOf(seatObject.get("flag").toString()));
			try {
				seat.setTime(MongoFactory.getDate(seatObject.get("time").toString()));
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
			seatList.add(seat);
		}

		s.setSeats(seatList);

		// Return category object.
		return s;
	}

	// Delete a section from the mongo database.
	public String delete(String id) {
		try {
			// Fetching the required category from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected category from the mongo database.
			coll.remove(item);
			return "Deleted item";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

}
