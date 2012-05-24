/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.FoursquareVenue;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The database class for accessing Foursquare venue data in a mongoDB database.
 *
 * @author Jasper Moeys
 */
public class FsVenueMongoDatabase implements Database<FoursquareVenue> {

    private DBCollection collection;

    public FsVenueMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException {
        String dbName = properties.getProperty("databaseName");
        String collectionName = properties.getProperty("fsVenueCollection");

        if (dbName == null) {
            throw new SocialMapException("The 'databaseName' property can't be null.");
        }
        if (collectionName == null) {
            throw new SocialMapException("The 'fsVenueCollection' property can't be null.");
        }

        collection = mongo.getDB(dbName).getCollection(collectionName);
    }

    private DBObject toDBObject(FoursquareVenue venue) {
        BasicDBObject doc = new BasicDBObject();
        doc.append("_id", venue.getId());
        doc.append("latitude", (double) venue.getLatitude());
        doc.append("longitude", (double) venue.getLongitude());
        doc.append("name", venue.getName());
        doc.append("usersCount", venue.getUsersCount());
        doc.append("checkinsCount", venue.getCheckinsCount());
        doc.append("categories", venue.getCategories());

        return doc;
    }

    private FoursquareVenue toFoursquareVenue(DBObject doc) {
        String id = (String) doc.get("_id");

        float latitude = 0;
        if (doc.get("latitude") instanceof Double) {
            latitude = ((Double) doc.get("latitude")).floatValue();
        } else if (doc.get("latitude") instanceof Integer) {
            latitude = ((Integer) doc.get("latitude")).floatValue();
        }

        float longitude = 0;
        if (doc.get("longitude") instanceof Double) {
            longitude = ((Double) doc.get("longitude")).floatValue();
        } else if (doc.get("longitude") instanceof Integer) {
            longitude = ((Integer) doc.get("longitude")).floatValue();
        }

        String name = (String) doc.get("name");

        int usersCount = 0;
        if (doc.get("usersCount") instanceof Double) {
            usersCount = ((Double) doc.get("usersCount")).intValue();
        } else if (doc.get("usersCount") instanceof Long) {
            usersCount = ((Long) doc.get("usersCount")).intValue();
        } else if (doc.get("usersCount") instanceof Integer) {
            usersCount = ((Integer) doc.get("usersCount"));
        }

        int checkinsCount = 0;
        if (doc.get("checkinsCount") instanceof Double) {
            checkinsCount = ((Double) doc.get("checkinsCount")).intValue();
        } else if (doc.get("checkinsCount") instanceof Long) {
            checkinsCount = ((Long) doc.get("checkinsCount")).intValue();
        } else if (doc.get("checkinsCount") instanceof Integer) {
            checkinsCount = ((Integer) doc.get("checkinsCount"));
        }

        List<String> categories = new ArrayList<String>((List<String>) doc.get("categories"));

        return new FoursquareVenue(id, latitude, longitude, name, checkinsCount, usersCount, categories);
    }

    @Override
    public List<FoursquareVenue> getAll() {
        int count = (int) collection.count();
        List<FoursquareVenue> list = new ArrayList<FoursquareVenue>(count);
        DBCursor find = collection.find();
        for (DBObject doc : find) {
            list.add(toFoursquareVenue(doc));
        }
        return list;
    }

    @Override
    public FoursquareVenue getOne(Object id) {
        BasicDBObject query = new BasicDBObject("_id", id);
        DBObject doc = collection.findOne(query);
        if (doc != null) {
            return toFoursquareVenue(doc);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(FoursquareVenue element) {
        BasicDBObject query = new BasicDBObject("_id", element.getId());
        WriteResult res = collection.update(query, toDBObject(element));
        boolean ok = false;
        if (res.getN() > 0) {
            ok = true;
        }

        return ok;
    }

    @Override
    public boolean insert(FoursquareVenue element) {
        WriteResult res = collection.insert(toDBObject(element));
        boolean ok = false;
        if (res.getN() > 0) {
            ok = true;
        }

        return ok;
    }

    @Override
    public boolean remove(FoursquareVenue element) {
        BasicDBObject query = new BasicDBObject("_id", element.getId());
        WriteResult res = collection.remove(query);
        boolean ok = false;
        if (res.getN() > 0) {
            ok = true;
        }

        return ok;
    }
}
