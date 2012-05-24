/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import java.util.Properties;

/**
 * An abstract class providing methods to access the database classes. Construct
 * a suitable DatabaseManager with the {@link DatabaseFactory DatabaseFactory}.
 * Normally an appropriate DatabaseManager will be used automatically when you
 * call {@link DatabaseFactory#getDatabase(java.lang.Class, java.lang.Object[]) }. <br /><br /> All
 * DatabaseManagers should extend this class.<br /> DatabaseManager subclasses
 * should define a constructor accepting one Properties object as single
 * argument. <br />For every {@link Database} that the DatabaseManager subclass
 * can create it should define a method with an 'Object...' varargs argument returning a {@code Database<T>}.
 * The name of the method has to be the lowercased <a
 * href="http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html#getSimpleName()">simple
 * name</a> of the type of objects the Database has to return.<br /> For
 * instance:
 * <pre>
 * {@code
 * public Database<String> string(Object... args){
 *     return new StringDatabaseImplementation();
 * }
 * }
 * </pre>
 *
 * @author Jasper Moeys
 */
public abstract class DatabaseManager {

    private Properties properties;

    /**
     * The constructor for this class.
     *
     * @param properties the Properties containing the configuration information
     * for this application
     * @throws SocialMapException
     * @throws IllegalArgumentException if properties is null
     */
    public DatabaseManager(Properties properties) throws SocialMapException {
        this.setProperties(properties);
    }

    protected Properties getProperties() {
        return this.properties;
    }

    private void setProperties(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("The properties can't be null.");
        } else {
            this.properties = properties;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        closeConnection();
        super.finalize();
    }

    public abstract void closeConnection() throws SocialMapException;
}
