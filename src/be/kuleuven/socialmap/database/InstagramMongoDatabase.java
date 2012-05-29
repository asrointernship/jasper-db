/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.InstagramPhoto;
import be.kuleuven.socialmap.model.Tweet;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * The database class for accessing Instagram data in a mongoDB database.
 *
 * @author Jasper Moeys
 */
public class InstagramMongoDatabase extends AbstractMongoDatabase<InstagramPhoto> {
    
    public InstagramMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException{
        super(mongo, properties, "instagramCollection");
    }
    
    @Override
    protected InstagramPhoto toT(DBObject doc){
        String id = (String) doc.get("_id");
        
        
        String userID = (String) doc.get("userID");
        
        Object locIDObj = doc.get("locationID");
        Long locationID = null;
        if(locIDObj instanceof Long)
            locationID = (Long)locIDObj;
        else if(locIDObj instanceof Integer)
            locationID = ((Integer)locIDObj).longValue();
        
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
        
        int like_count = 0;
        Object lcObj = doc.get("like_count");
        if(lcObj instanceof Double){
            like_count = ((Double)lcObj).intValue();
        } else if(lcObj instanceof Integer){
            like_count = ((Integer)lcObj).intValue();
        }
        
        String caption = (String)doc.get("caption");
        Date created_at = (Date)doc.get("created_at");
        String url = (String)doc.get("url");
        String locationName = (String)doc.get("locationName");
        String username = (String)doc.get("username");
        String filter = (String)doc.get("filter");
        
        return new InstagramPhoto(id, latitude, longitude, locationID, locationName, filter, userID, username, like_count, url, created_at, caption);
    }
    
    @Override
    protected DBObject toDBObject(InstagramPhoto photo){
        BasicDBObject doc = new BasicDBObject();
        doc.append("_id", photo.getId());
        doc.append("latitude", (double)photo.getLatitude());
        doc.append("longitude", (double)photo.getLongitude());
        doc.append("caption", photo.getCaption());
        doc.append("created_at", photo.getCreated_at());
        doc.append("userID", photo.getUserID());
        doc.append("username", photo.getUsername());
        doc.append("filter", photo.getFilter());
        doc.append("url", photo.getUrl());
        doc.append("like_count", photo.getLike_count());
        doc.append("locationID", photo.getLocationID());
        doc.append("locationName", photo.getLocationName());
        
        return doc;
    }

    @Override
    protected Object getElementId(InstagramPhoto element) {
        return element.getId();
    }
    
}
