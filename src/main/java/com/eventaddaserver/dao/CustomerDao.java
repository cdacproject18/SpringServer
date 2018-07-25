package com.eventaddaserver.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.*;
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
			c.setId(dbObject.get("id").toString());
			c.setName(dbObject.get("name").toString());
			c.setNumber(dbObject.get("number").toString());
			//c.setAddress(dbObject.get("name").toString());
			//a.setStreet(((DBObject)dbObject.get("address")).get("street").toString());
			//a.setCity(((DBObject)dbObject.get("address")).get("city").toString());
			//a.setState(((DBObject)dbObject.get("address")).get("state").toString());
			//c.setAddress(a);
			//c.setDob(new Date(dbObject.get("dob").toString()));
			c.setEmail(dbObject.get("email").toString());
			c.setGender(dbObject.get("gender").toString());
			// Adding the user details to the list.
			cust_list.add(c);
		}
		log.debug("Total records fetched from the mongo database are= " + cust_list.size());
		return cust_list;
	}
}
