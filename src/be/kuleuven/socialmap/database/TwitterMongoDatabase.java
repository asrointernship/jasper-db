/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.FlickrPhoto;
import be.kuleuven.socialmap.model.Tweet;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * The database class for accessing Twitter data in a mongoDB database.
 *
 * @author Jasper Moeys
 */
public class TwitterMongoDatabase extends AbstractMongoDatabase<Tweet> {
    
    public TwitterMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException{
        super(mongo, properties, "twitterCollection");
    }
    
    @Override
    protected Tweet toT(DBObject doc){
        Object idObj = doc.get("_id");
        Long id = null;
        if(idObj instanceof Long)
            id = (Long)idObj;
        else if(idObj instanceof Integer)
            id = ((Integer)idObj).longValue();
        
        Object fromidObj = doc.get("from_user_id");
        Long from_user_id = null;
        if(fromidObj instanceof Long)
            from_user_id = (Long)fromidObj;
        else if(fromidObj instanceof Integer)
            from_user_id = ((Integer)fromidObj).longValue();
        
        Object toidObj = doc.get("to_user_id");
        Long to_user_id = null;
        if(toidObj instanceof Long)
            to_user_id = (Long)toidObj;
        else if(toidObj instanceof Integer)
            to_user_id = ((Integer)toidObj).longValue();
        
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
        
        String text = (String)doc.get("text");
        Date created_at = (Date)doc.get("created_at");
        String from_user = (String)doc.get("from_user");
        String to_user = (String)doc.get("to_user");
        
        return new Tweet(id, latitude, longitude, text, created_at, from_user_id, from_user, to_user_id, to_user);
    }
    
    @Override
    protected DBObject toDBObject(Tweet tweet){
        BasicDBObject doc = new BasicDBObject();
        doc.append("_id", tweet.getId());
        doc.append("latitude", (double)tweet.getLatitude());
        doc.append("longitude", (double)tweet.getLongitude());
        doc.append("text", tweet.getText());
        doc.append("created_at", tweet.getCreated_at());
        doc.append("from_user_id", tweet.getFrom_user_id());
        doc.append("from_user", tweet.getFrom_user());
        doc.append("to_user_id", tweet.getTo_user_id());
        doc.append("to_user", tweet.getTo_user());
        
        return doc;
    }

    @Override
    protected Object getElementId(Tweet element) {
        return element.getId();
    }
    
}
