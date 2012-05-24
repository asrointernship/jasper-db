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
public class TwitterMongoDatabase implements Database<Tweet> {
    
    private DBCollection collection;
    
    public TwitterMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException{
        String dbName = properties.getProperty("databaseName");
        String collectionName = properties.getProperty("twitterCollection");
        
        if(dbName==null)
            throw new SocialMapException("The 'databaseName' property can't be null.");
        if(collectionName==null)
            throw new SocialMapException("The 'twitterCollection' property can't be null.");
        
        collection = mongo.getDB(dbName).getCollection(collectionName);
    }
    
    private Tweet toTweet(DBObject doc){
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
        if(doc.get("latitude") instanceof Double){
            latitude = ((Double)doc.get("latitude")).floatValue();
        } else if(doc.get("latitude") instanceof Integer){
            latitude = ((Integer)doc.get("latitude")).floatValue();
        }
        
        float longitude = 0;
        if(doc.get("longitude") instanceof Double){
            longitude = ((Double)doc.get("longitude")).floatValue();
        } else if(doc.get("longitude") instanceof Integer){
            longitude = ((Integer)doc.get("longitude")).floatValue();
        }
        
        String text = (String)doc.get("text");
        Date created_at = (Date)doc.get("created_at");
        String from_user = (String)doc.get("from_user");
        String to_user = (String)doc.get("to_user");
        
        return new Tweet(id, latitude, longitude, text, created_at, from_user_id, from_user, to_user_id, to_user);
    }
    
    private DBObject toDBObject(Tweet tweet){
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
    public List<Tweet> getAll() {
        int count = (int) collection.count();
        List<Tweet> list = new ArrayList<Tweet>(count);
        DBCursor find = collection.find();
        for(DBObject doc: find){
            list.add(toTweet(doc));
        }
        return list;
    }

    @Override
    public Tweet getOne(Object id) {
        BasicDBObject query = new BasicDBObject("_id", id);
        DBObject doc = collection.findOne(query);
        if(doc != null)
            return toTweet(doc);
        else
            return null;
    }

    @Override
    public boolean update(Tweet element) {
        BasicDBObject query = new BasicDBObject("_id", element.getId());
        WriteResult res = collection.update(query, toDBObject(element));
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }

    @Override
    public boolean insert(Tweet element) {
        WriteResult res = collection.insert(toDBObject(element));
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }

    @Override
    public boolean remove(Tweet element) {
        BasicDBObject query = new BasicDBObject("_id", element.getId());
        WriteResult res = collection.remove(query);
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }
    
}
