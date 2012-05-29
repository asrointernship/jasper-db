/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.model.FlickrPhoto;
import be.kuleuven.socialmap.model.FoursquareVenue;
import be.kuleuven.socialmap.model.InstagramPhoto;
import be.kuleuven.socialmap.model.MapReduceResult;
import be.kuleuven.socialmap.model.Plottable;
import be.kuleuven.socialmap.model.Tweet;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * The DatabaseManager for MongoDB. <br />
 * This DatasebaseManager accepts the following properties:
 * <ul>
 * <li><b>host</b>: The hostname or ip of the database. Default is 127.0.0.1 .</li>
 * <li><b>port</b>: The port of the database. Default is 27017 .</li>
 * <li><b>databaseName</b> (required): The name of the database that contains the collections.</li>
 * <li><b>twitterCollection</b> (required): The name of the collection where the tweets are stored.</li>
 * <li><b>flickrCollection</b> (required): The name of the collection where the flickrphotos are stored.</li>
 * <li><b>fsVenueCollection</b> (required): The name of the collection where the foursquareVenues are stored.</li>
 * <li><b>instagramCollection</b> (required): The name of the collection where the InstagramPhotos are stored.</li>
 * <li><b>"lowerCasedSimpleClassName"Reduced</b> (required): The name of the collection where the result of the MapReduce operation on the collection of the specified type is stored.
 * <ul><li>Example: flickrphotoReduced=MapReducedFlickrCollection</li></ul></li>
 * </ul>
 *
 * @author Jasper Moeys
 */
public class MongoManager extends DatabaseManager {

    private Mongo mongo;

    public MongoManager(Properties properties) throws SocialMapException {
        super(properties);
        newMongo();
    }
    
    private void newMongo() throws SocialMapException{
        String host = getProperties().getProperty("host", "127.0.0.1");
        int port = Integer.parseInt(getProperties().getProperty("port", "27017"));
        try {
            mongo = new Mongo(host, port);
            DB test= mongo.getDB("test");
            test.getStats();
        } catch (UnknownHostException ex) {
            throw new SocialMapException(ex);
        } catch (MongoException ex) {
            throw new SocialMapException(ex);
        }
    }

    
    public Database<FlickrPhoto> flickrphoto(Object... args) throws SocialMapException {
        return new FlickrMongoDatabase(mongo, getProperties());
    }

    public Database<Tweet> tweet(Object... args) throws SocialMapException {
        return new TwitterMongoDatabase(mongo, getProperties());
    }
    
    public Database<MapReduceResult> mapreduceresult(Object... args) throws SocialMapException{
        if(args != null && args.length > 0 && args[0] instanceof Class){
            Class type = (Class) args[0];
            return new MapReduceResultMongoDatabase(mongo, getProperties(), type);
        } else {
            throw new SocialMapException("This Database needs an extra Class argument which represents the type that was MapReduced to this database.");
        }
    }
    
    public Database<FoursquareVenue> foursquarevenue(Object... args) throws SocialMapException{
        return new FsVenueMongoDatabase(mongo, getProperties());
    }
    
    public Database<InstagramPhoto> instagramphoto(Object... args) throws SocialMapException{
        return new InstagramMongoDatabase(mongo, getProperties());
    }

    @Override
    public void closeConnection() {
        mongo.close();
    }
}
