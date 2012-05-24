/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import be.kuleuven.socialmap.exceptions.SocialMapException;
import be.kuleuven.socialmap.io.StaticIO;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * A factory class to create a suitable Database. See {@link #getDatabase(java.lang.Class, java.lang.Object[]) }.
 * <br /> The fully qualified class name of the DatabaseManager to be used
 * should be specified in the 'socialmap.properties' file as the property
 * 'databaseManager'.
 *
 * @author Jasper Moeys
 */
public class DatabaseFactory {

    private static volatile DatabaseManager manager;

    private DatabaseFactory() {
    }

    /**
     * Returns the DatabaseManager that the DatabaseFactory is using.
     * The first time this method or {@link #getDatabase(java.lang.Class, java.lang.Object[]) } is called, a new DatabaseManager will be created.
     *
     * @return A suitable DatabaseManager
     * @throws SocialMapException
     */
    public static DatabaseManager getDatabaseManager() throws SocialMapException {
        if (DatabaseFactory.manager == null) {
            Properties properties = StaticIO.getConfig();
            String database = properties.getProperty("databaseManager");
            if (database == null) {
                throw new SocialMapException("The 'databaseManager' property can't be null.");
            } else {
                try {
                    Class dbClass = Class.forName(database);
                    Constructor constructor = dbClass.getConstructor(Properties.class);

                    synchronized (DatabaseFactory.class) {
                        if (DatabaseFactory.manager == null) {
                            DatabaseFactory.manager = (DatabaseManager) constructor.newInstance(properties);
                        }
                    }

                } catch (InstantiationException ex) {
                    throw new SocialMapException(ex);
                } catch (IllegalAccessException ex) {
                    throw new SocialMapException(ex);
                } catch (IllegalArgumentException ex) {
                    throw new SocialMapException(ex);
                } catch (InvocationTargetException ex) {
                    if (ex.getCause() instanceof SocialMapException) {
                        throw (SocialMapException) ex.getCause();
                    } else {
                        throw new SocialMapException(ex.getCause());
                    }
                } catch (NoSuchMethodException ex) {
                    throw new SocialMapException(ex);
                } catch (SecurityException ex) {
                    throw new SocialMapException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new SocialMapException("The value of the 'databaseManager' property can't be resolved to an implemented DatabaseManager.", ex);
                } catch (ClassCastException ex) {
                    throw new SocialMapException(ex);
                }
            }
        }
        return DatabaseFactory.manager;
    }

    /**
     * This method will return an appropriate Database for retrieving objects of
     * type c.
     *
     * @param c The class for which you want to get a Database object
     * @param args Extra arguments that might be needed for the creation of the Database object
     * @return An appropriate Database
     * @throws SocialMapException
     */
    public static <T> Database<T> getDatabase(Class<T> c, Object... args) throws SocialMapException {
        Database<T> db = null;
        try {
            DatabaseManager dbm = getDatabaseManager();
            String name = c.getSimpleName().toLowerCase();
            db = (Database<T>) dbm.getClass().getDeclaredMethod(name, Object[].class).invoke(dbm, new Object[]{args});
        } catch (NoSuchMethodException ex) {
            throw new SocialMapException("No Database for retrieving objects of the class " + c.getSimpleName() + " has been implemented for this DatabaseManager.", ex);
        } catch (SecurityException ex) {
            throw new SocialMapException(ex);
        } catch (IllegalAccessException ex) {
            throw new SocialMapException(ex);
        } catch (IllegalArgumentException ex) {
            throw new SocialMapException(ex);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof SocialMapException) {
                throw (SocialMapException) ex.getCause();
            } else {
                throw new SocialMapException(ex.getCause());
            }
        } catch (ClassCastException ex) {
            throw new SocialMapException(ex);
        }
        return db;
    }
}
