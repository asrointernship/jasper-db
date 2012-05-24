/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.database;

import java.util.List;

/**
 * Every class representing a database should implement this interface. The
 * preferred way of accessing a Database class is through the {@link DatabaseFactory DatabaseFactory}.
 *
 * @param <T> The model class whose objects the implementing Database class will
 * save, retrieve, ... to/from the database.
 * @author Jasper Moeys
 */
public interface Database<T> {

    /**
     * Returns all the elements in this database. If there are no elements the
     * list will be empty.
     *
     * @return all the elements in this database
     */
    List<T> getAll();

    /**
     * Returns the element with the specified id in the database, or null if it
     * doesn't exist.
     *
     * @param id the id of the element to return
     * @return the element with the specified id in the database
     */
    T getOne(Object id);

    /**
     * Updates an element in the database. The element which will be updated, is
     * based on the id of the specified element.
     *
     * @param element the updated element you wish to update in the database
     * @return true if the update succeeded, false otherwise
     */
    boolean update(T element);

    /**
     * Insert an element in the database.
     *
     * @param element the element that will inserted in the database
     * @return true if the insert succeeded, false otherwise
     */
    boolean insert(T element);

    /**
     * Removes an element from the database.
     *
     * @param element the element that will removed from the database
     * @return true if the element was successfully removed, false otherwise
     */
    boolean remove(T element);
}
