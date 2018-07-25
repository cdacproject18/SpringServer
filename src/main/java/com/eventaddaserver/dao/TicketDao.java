package com.eventaddaserver.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.Category;
import com.eventaddaserver.pojos.SeatLocation;
import com.eventaddaserver.pojos.Ticket;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class TicketDao {
	static String db_name = "mydb", db_collection = "ticket";
	
	// Add a new category to the mongo database.
	public String add(Ticket t) {
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new category details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("bookingid", t.getBookingid());
			Iterator<SeatLocation> it=t.getSeatlocation().iterator();
			BasicDBList list= new BasicDBList();
			while(it.hasNext())
			{
				SeatLocation l=it.next();
				BasicDBObject obj= new BasicDBObject();
						obj.put("section",l.getSection());
						obj.put("location", l.getLocation());
				list.add(obj);
			}
			doc.put("seatlocation",list);
			doc.put("price", t.getPrice());
			coll.insert(doc);
			/*Iterator<SeatLocation> it=t.getSeatlocation().iterator();
			BasicDBObject seatloc=null,update=null;
			//List<BasicDBObject> li=new ArrayList<BasicDBObject>();
			Map
			while(it.hasNext()){
			seatloc = new BasicDBObject();
			seatloc.put("section", it.next().getSection());
			seatloc.put("location", it.next().getLocation());
			update = new BasicDBObject();
			update.put("$push", new BasicDBObject("seatlocation",seatloc));
			
			}
			coll.update(doc,update,true,true);*/
			// Save a new category to the mongo collection.
			
			return "Ticket added";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Delete a ticket on cancellation from the mongo database.
	public String delete(String bid) {
		try {
			// Fetching the required category from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(bid);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected category from the mongo database.
			coll.remove(item);
			return "Booking cancelled successfully";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Fetching a particular booking from the mongo database.
	private DBObject getDBObject(String bid) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected category id to search.
		where_query.put("bookingid", bid);
		return coll.findOne(where_query);
	}

	// Fetching a single category details from the mongo database.
	public Ticket findBookingById(String bid) {
		Ticket t= new Ticket();
		List<SeatLocation> list=new ArrayList<SeatLocation>();
		DBObject dbo = getDBObject(bid);

		t.setBookingid(dbo.get("bookingid").toString());
		BasicDBList seatListObject = (BasicDBList) dbo.get("seatlocation");
		System.out.println(seatListObject.toString());
		Iterator<Object> it=seatListObject.iterator();
		while(it.hasNext())
		{
		BasicDBObject bdo= (BasicDBObject)it.next();
		SeatLocation sl= new SeatLocation();
		sl.makePojoFromBson(bdo) ;
		
		list.add(sl);
		}
		t.setSeatlocation(list);
//		System.out.println(((DBObject)dbo.get("seatlocation")).get("section").toString());
		t.setPrice(Double.parseDouble(dbo.get("price").toString()));

		// Return category object.
		return t;
	}


}
