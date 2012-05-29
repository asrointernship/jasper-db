/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.Tweet;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * An abstract class for working with MongoDB collections.
 *
 * @author Jasper Moeys
 */
public abstract class AbstractMongoDatabase<T> implements Database<T> {
    
    private DBCollection collection;
    
    public AbstractMongoDatabase(Mongo mongo, Properties properties, String collectionPropertyName) throws SocialMapException{
        String dbName = properties.getProperty("databaseName");
        String collectionName = properties.getProperty(collectionPropertyName);
        
        if(dbName==null)
            throw new SocialMapException("The 'databaseName' property can't be null.");
        if(collectionName==null)
            throw new SocialMapException("The '" + collectionPropertyName + "' property can't be null.");
        
        collection = mongo.getDB(dbName).getCollection(collectionName);
    }
    
    protected final DBCollection getCollection(){
        return this.collection;
    }
    
    protected abstract DBObject toDBObject(T element);
    
    protected abstract T toT(DBObject doc);
    
    protected abstract Object getElementId(T element);

    @Override
    public List<T> getAll() {
        int count = (int) collection.count();
        List<T> list = new ArrayList<T>(count);
        DBCursor find = collection.find();
        for(DBObject doc: find){
            list.add(toT(doc));
        }
        return list;
    }

    @Override
    public T getOne(Object id) {
        BasicDBObject query = new BasicDBObject("_id", id);
        DBObject doc = collection.findOne(query);
        if(doc != null)
            return toT(doc);
        else
            return null;
    }

    @Override
    public boolean update(T element) {
        BasicDBObject query = new BasicDBObject("_id", getElementId(element));
        WriteResult res = collection.update(query, toDBObject(element));
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }

    @Override
    public boolean insert(T element) {
        WriteResult res = collection.insert(toDBObject(element));
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }

    @Override
    public boolean remove(T element) {
        BasicDBObject query = new BasicDBObject("_id", getElementId(element));
        WriteResult res = collection.remove(query);
        boolean ok = false;
        if(res.getN()>0)
            ok = true;
        
        return ok;
    }
    
}
