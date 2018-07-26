package com.eventaddaserver.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class CustomerDao {
	
	static String db_name = "mydb", db_collection = "customer";
	private static Logger log = Logger.getLogger(CustomerDao.class);
	
	public List<Customer> getAll() {
		List<Customer> cust_list = new ArrayList<Customer>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Customer c= new Customer();
			Address a= new Address();
			c.set_id(dbObject.get("_id").toString());
			c.setName(dbObject.get("name").toString());
			c.setNumber(dbObject.get("number").toString());
			a.setStreet(((DBObject)dbObject.get("address")).get("street").toString());
			a.setCity(((DBObject)dbObject.get("address")).get("city").toString());
			a.setState(((DBObject)dbObject.get("address")).get("state").toString());
			c.setAddress(a);
			try {
				c.setDob(MongoFactory.getDate(dbObject.get("dob").toString()));
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
			c.setEmail(dbObject.get("email").toString());
			c.setGender(dbObject.get("gender").toString());
			// Adding the user details to the list.
			cust_list.add(c);
		}
		log.debug("Total records fetched from the mongo database are= " + cust_list.size());
		return cust_list;
	}
	
	// Add a new user to the mongo database.
	public String add(Customer cust) {
		
		int c=101;
		System.out.println("Adding a new user to the mongo database; Entered user_name is= " + cust.getName());
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new user details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("_id",cust.get_id());
			doc.put("name", cust.getName());
			doc.put("number", cust.getNumber());
			doc.put("dob", cust.getDob());
			doc.put("gender", cust.getGender());
			Address a=cust.getAddress();
			BasicDBObject add= new BasicDBObject();
			add.put("street",a.getStreet() );
			add.put("city",a.getCity() );
			add.put("state",a.getState() );
			System.out.println(a);
			doc.put("address",add);
			doc.put("email", cust.getEmail());

			// Save a new user to the mongo collection.
			coll.insert(doc);
			return "added successfully";
		} catch (Exception e) {
			
			log.error("An error occurred while saving a new customer to the mongo database", e);
		}
		return "Failed";
	}

	// Update the selected user in the mongo database.
	public String edit(Customer cust) {
		
		log.debug("Updating the existing customer in the mongo database; Entered user_id is= " + cust.get_id());
		try {
			// Fetching the user details.
			BasicDBObject existing = (BasicDBObject) getDBObject(cust.get_id());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("_id", cust.get_id());
			edited.put("name", cust.getName());
			edited.put("number", cust.getNumber());
			edited.put("dob", cust.getDob());
			edited.put("gender", cust.getGender());
			BasicDBObject add= new BasicDBObject();
			add.put("street",cust.getAddress().getStreet() );
			add.put("city",cust.getAddress().getCity() );
			add.put("state",cust.getAddress().getState() );
			edited.put("address",add);
			edited.put("email", cust.getEmail());

			// Update the existing user to the mongo database.
			coll.update(existing, edited);
			return "details updated";
		} catch (Exception e) {
			
			log.error("An error has occurred while updating an existing customer to the mongo database", e);
		}
		return "failed";
	}

	// Delete a user from the mongo database.
	public String delete(String id) {
		
		log.debug("Deleting an existing Customer from the mongo database; Entered user_id is= " + id);
		try {
			// Fetching the required user from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected user from the mongo database.
			coll.remove(item);
			return "Customer Removed Successfully";
		} catch (Exception e) {
			
			log.error("An error occurred while deleting an existing user from the mongo database", e);
		}
		return "Failed";
	}

	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected customer_id to search.
		where_query.put("_id", id);
		return coll.findOne(where_query);
	}

	// Fetching a single user details from the mongo database.
	public Customer findUserById(String id) {
		Customer u = new Customer();
		Address a=new Address();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();
		where_query.put("_id", id);

		DBObject dbo = coll.findOne(where_query);
		u.set_id(dbo.get("_id").toString());
		u.setName(dbo.get("name").toString());
		u.setNumber(dbo.get("number").toString());
		a.setStreet(((DBObject)dbo.get("address")).get("street").toString());
		a.setCity(((DBObject)dbo.get("address")).get("city").toString());
		a.setState(((DBObject)dbo.get("address")).get("state").toString());
		u.setAddress(a);
		try {
			u.setDob(MongoFactory.getDate(dbo.get("dob").toString()));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		u.setEmail(dbo.get("email").toString());
		u.setGender(dbo.get("gender").toString());

		// Return user object.
		return u;
	}
}
