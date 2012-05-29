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
public class FsVenueMongoDatabase extends AbstractMongoDatabase<FoursquareVenue> {

    public FsVenueMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException {
        super(mongo, properties, "fsVenueCollection");
    }

    @Override
    protected DBObject toDBObject(FoursquareVenue venue) {
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

    @Override
    protected FoursquareVenue toT(DBObject doc) {
        String id = (String) doc.get("_id");

        float latitude = 0;
        Object latObj = doc.get("latitude");
        if(latObj instanceof Double){
            latitude = ((Double)latObj).floatValue();
        } else if(latObj instanceof Integer){
            latitude = ((Integer)latObj).floatValue();
        }
        
        float longitude = 0;
        Object lngObj = doc.get("longitude");
        if(lngObj instanceof Double){
            longitude = ((Double)lngObj).floatValue();
        } else if(lngObj instanceof Integer){
            longitude = ((Integer)lngObj).floatValue();
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
    protected Object getElementId(FoursquareVenue element) {
        return element.getId();
    }
}
