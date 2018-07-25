package com.eventaddaserver.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@SuppressWarnings("deprecation")
public class MongoFactory {
	private static Logger log = Logger.getLogger(MongoFactory.class);

	private static Mongo mongo;
	private static SimpleDateFormat sdf;
	
	private MongoFactory() {
	}

	// Returns a mongo instance.
	public static Mongo getMongo() {
		int port_no = 27017;
		String hostname = "localhost";
		if (mongo == null) {
			try {
				mongo = new Mongo(hostname, port_no);
			} catch (MongoException ex) {
				log.error(ex);
			}
		}
		return mongo;
	}

	// Fetches the mongo database.
	public static DB getDB(String db_name) {
		return getMongo().getDB(db_name);
	}

	// Fetches the collection from the mongo database.
	public static DBCollection getCollection(String db_name, String db_collection) {
		return getDB(db_name).getCollection(db_collection);
	}
	
	public static Date getDate(String dt) throws ParseException {
		sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		return sdf.parse(dt);
	}
}
