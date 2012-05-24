/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.FlickrPhoto;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * The database class for accessing Flickr data in a mongoDB database.
 *
 * @author Jasper Moeys
 */
public class FlickrMongoDatabase implements Database<FlickrPhoto> {
    
    private DBCollection collection;
    
    public FlickrMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException{
        String dbName = properties.getProperty("databaseName");
        String collectionName = properties.getProperty("flickrCollection");
        
        if(dbName==null)
            throw new SocialMapException("The 'databaseName' property can't be null.");
        if(collectionName==null)
            throw new SocialMapException("The 'flickrCollection' property can't be null.");
        
        collection = mongo.getDB(dbName).getCollection(collectionName);
    }
    
    private FlickrPhoto toFlickrPhoto(DBObject doc){
        Object idObj = doc.get("_id");
        Long id = null;
        if(idObj instanceof Long)
            id = (Long)idObj;
        else if(idObj instanceof Integer)
            id = ((Integer)idObj).longValue();
        
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
        
        Date dateupload = (Date)doc.get("dateupload");
        String title = (String)doc.get("title");
        String owner = (String)doc.get("owner");
        String secret = (String)doc.get("secret");
        String farm = (String)doc.get("farm");
        String server = (String)doc.get("server");
        
        return new FlickrPhoto(id, latitude, longitude, dateupload, title, owner, secret, farm, server);
    }
    
    private DBObject toDBObject(FlickrPhoto flickr){
        BasicDBObject doc = new BasicDBObject();
        doc.append("_id", flickr.getId());
        doc.append("latitude", (double)flickr.getLatitude());
        doc.append("longitude", (double)flickr.getLongitude());
        doc.append("dateupload", flickr.getDateupload());
        doc.append("title", flickr.getTitle());
        doc.append("owner", flickr.getOwner());
        doc.append("secret", flickr.getSecret());
        doc.append("farm", flickr.getFarm());
        doc.append("server", flickr.getServer());
        
        return doc;
    }

    @Override
    public List<FlickrPhoto> getAll() {
        int count = (int) collection.count();
        List<FlickrPhoto> list = new ArrayList<FlickrPhoto>(count);
        DBCursor find = collection.find();
        for(DBObject doc: find){
            list.add(toFlickrPhoto(doc));
        }
        return list;
    }

    @Override
    public FlickrPhoto getOne(Object id) {
        BasicDBObject query = new BasicDBObject("_id", id);
        DBObject doc = collection.findOne(query);
        if(doc != null)
            return toFlickrPhoto(doc);
        else
            return null;
    }

    @Override
    public boolean update(FlickrPhoto element) {
        BasicDBObject query = new BasicDBObject("_id", element.getId());
        WriteResult res = collection.update(query, toDBObject(element));
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }

    @Override
    public boolean insert(FlickrPhoto element) {
        WriteResult res = collection.insert(toDBObject(element));
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }

    @Override
    public boolean remove(FlickrPhoto element) {
        BasicDBObject query = new BasicDBObject("_id", element.getId());
        WriteResult res = collection.remove(query);
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }
    
}
