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
public class FlickrMongoDatabase extends AbstractMongoDatabase<FlickrPhoto> {
    
    public FlickrMongoDatabase(Mongo mongo, Properties properties) throws SocialMapException{
        super(mongo, properties, "flickrCollection");
    }
    
    @Override
    protected FlickrPhoto toT(DBObject doc){
        Object idObj = doc.get("_id");
        Long id = null;
        if(idObj instanceof Long)
            id = (Long)idObj;
        else if(idObj instanceof Integer)
            id = ((Integer)idObj).longValue();
        
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
        
        Date dateupload = (Date)doc.get("dateupload");
        String title = (String)doc.get("title");
        String owner = (String)doc.get("owner");
        String secret = (String)doc.get("secret");
        String farm = (String)doc.get("farm");
        String server = (String)doc.get("server");
        
        return new FlickrPhoto(id, latitude, longitude, dateupload, title, owner, secret, farm, server);
    }
    
    @Override
    protected DBObject toDBObject(FlickrPhoto flickr){
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
    protected Object getElementId(FlickrPhoto element) {
        return element.getId();
    }

    
}
