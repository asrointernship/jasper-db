/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that all filters which can be combined with one another,
 * should extend.
 *
 * @author Jasper Moeys
 */
public abstract class Filter<T> {

    private Filter<T> filter;

    /**
     * Constructs a new Filter.
     *
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public Filter(Filter<T> filter) {
        this.filter = filter;
    }

    /**
     * This method will return a list with only those elements of the specified
     * list that {@link Filter#retain(java.lang.Object)} returns true for.
     *
     * @param list The list you want to filter.
     * @return The resulting filtered list.
     */
    public final List<T> filter(List<T> list) {
        ArrayList<T> res = new ArrayList<T>(list.size());
        for (T t : list) {
            if (_retain(t)) {
                res.add(t);
            }
        }
        res.trimToSize();
        return res;
    }

    /**
     * A private method for internal use.
     */
    private boolean _retain(T element) {
        return filter != null ? filter._retain(element) && this.retain(element) : this.retain(element);
    }

    /**
     * This method checks for an element whether it should be retained or
     * discarded by the filter.
     *
     * @param element The element to check.
     * @return True if the element should be retained, false if it should be
     * discarded.
     */
    protected abstract boolean retain(T element);
}
