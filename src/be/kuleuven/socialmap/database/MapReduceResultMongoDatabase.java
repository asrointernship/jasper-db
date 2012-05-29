/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.MapReduceResult;
import be.kuleuven.socialmap.model.Plottable;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * The database class for accessing data in a collection that is the result of a MapReduce operation on a collection containing {@link Plottable} data in a mongoDB database.
 *
 * @author Jasper Moeys
 */
public class MapReduceResultMongoDatabase implements Database<MapReduceResult> {

    private DBCollection collection;
    private Class<? extends Plottable> type;

    public MapReduceResultMongoDatabase(Mongo mongo, Properties properties, Class<? extends Plottable> type) throws SocialMapException {
        String typeName = type.getSimpleName().toLowerCase();
        String dbName = properties.getProperty("databaseName");
        String collectionName = properties.getProperty(typeName + "Reduced");

        if (dbName == null) {
            throw new SocialMapException("The 'databaseName' property can't be null.");
        }
        if (collectionName == null) {
            throw new SocialMapException("No '" + type + "Reduced' property defined.");
        }

        this.collection = mongo.getDB(dbName).getCollection(collectionName);
        this.type = type;
    }

    @Override
    public List<MapReduceResult> getAll() {
        int count = (int) collection.count();
        List<MapReduceResult> list = new ArrayList<MapReduceResult>(count);
        DBCursor find = collection.find();
        for(DBObject doc: find){
            list.add(toMapReduceResult(doc));
        }
        return list;
    }

    @Override
    public MapReduceResult getOne(Object id) {
        MapReduceResult res = null;
        BasicDBObject latlng = null;
        if (id instanceof List) {
            List<Object> list = (List<Object>) id;
            if (list.size() == 2) {
                Object latitude = list.get(0);
                Object longitude = list.get(1);
                latlng = new BasicDBObject();
                latlng.put("latitude", latitude);
                latlng.put("longitude", longitude);
            }
        } else if (id instanceof Map) {
            Map map = (Map) id;
            latlng = new BasicDBObject(map);
        }
        if (latlng != null) {
            BasicDBObject query = new BasicDBObject("_id", latlng);
            DBObject doc = collection.findOne(query);
            if (doc != null) {
                res = toMapReduceResult(doc);
            }
        }
        return res;
    }

    @Override
    public boolean update(MapReduceResult element) {
        throw new UnsupportedOperationException("Collections that are the result of a MapReduce operation shouldn't be changed.");
    }

    @Override
    public boolean insert(MapReduceResult element) {
        throw new UnsupportedOperationException("Collections that are the result of a MapReduce operation shouldn't be changed.");
    }

    @Override
    public boolean remove(MapReduceResult element) {
        throw new UnsupportedOperationException("Collections that are the result of a MapReduce operation shouldn't be changed.");
    }

    private MapReduceResult toMapReduceResult(DBObject doc) {
        DBObject id = (DBObject) doc.get("_id");
        
        float latitude = 0;
        if(id.get("latitude") instanceof Double){
            latitude = ((Double)id.get("latitude")).floatValue();
        } else if(id.get("latitude") instanceof Integer){
            latitude = ((Integer)id.get("latitude")).floatValue();
        }
        
        float longitude = 0;
        if(id.get("longitude") instanceof Double){
            longitude = ((Double)id.get("longitude")).floatValue();
        } else if(id.get("longitude") instanceof Integer){
            longitude = ((Integer)id.get("longitude")).floatValue();
        }
        
        DBObject value = (DBObject) doc.get("value");
        int count = 0;
        if(value.get("count") instanceof Double){
            count = ((Double)value.get("count")).intValue();
        } else if(value.get("count") instanceof Long){
            count = ((Long)value.get("count")).intValue();
        } else if(value.get("count") instanceof Integer){
            count = ((Integer)value.get("count"));
        }
        
        List<Object> ids = new ArrayList((List)value.get("ids"));
        
        return new MapReduceResult(latitude, longitude, ids, count, this.type);
    }
}