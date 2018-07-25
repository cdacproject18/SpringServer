package com.eventaddaserver.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eventaddaserver.factory.MongoFactory;
import com.eventaddaserver.pojos.Category;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
@Transactional
public class CategoryDao {
	static String db_name = "mydb", db_collection = "category";

	// Fetch all users from the mongo database.
	public List<Category> getAll() {
		List<Category> categoryList = new ArrayList<Category>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Category category = new Category();
			category.set_id(dbObject.get("_id").toString());
			category.setName(dbObject.get("name").toString());
			category.setGenre(dbObject.get("genre").toString());

			// Adding the user details to the list.
			categoryList.add(category);
		}
		return categoryList;
	}

	// Add a new category to the mongo database.
	public String add(Category category) {
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new category details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("_id", category.get_id());
			doc.put("name", category.getName());
			doc.put("genre", category.getGenre());

			// Save a new category to the mongo collection.
			coll.insert(doc);
			return "Category added";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Update the selected category in the mongo database.
	public String edit(Category category) {
		try {
			// Fetching the category details.
			BasicDBObject existing = (BasicDBObject) getDBObject(category.get_id().toString());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("_id", category.get_id());
			edited.put("name", category.getName());
			edited.put("genre", category.getGenre());

			// Update the existing category to the mongo database.
			coll.update(existing, edited);
			return "Category updated";
		} catch (Exception e) {
			System.out.println("Failed: " + e.getMessage());
		}
		return "Failed";
	}

	// Delete a category from the mongo database.
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
	public Category findCategoryById(String id) {
		Category c = new Category();
		DBObject dbo = getDBObject(id);

		c.set_id(dbo.get("_id").toString());
		c.setName(dbo.get("name").toString());
		c.setGenre(dbo.get("genre").toString());

		// Return category object.
		return c;
	}
}
