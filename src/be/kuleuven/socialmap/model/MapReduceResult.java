/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The MapReduceResult class represents an element that is the result of a MapReduce operation on a collection of {@link Plottable} data.
 *
 * @author Jasper Moeys
 */
public class MapReduceResult extends Plottable{
    
    private List<Object> ids;
    private int count;
    private Class<? extends Plottable> type;

    /**
     * Constructs a new MapReduceResult.
     * 
     * @param latitude Has to be between -90 and 90.
     * @param longitude Has to be between -180 and 180.
     * @param ids A list of all the id's of the elements with these coordinates. Null will be converted to an empty list.
     * @param count The number of elements with these coordinates. Can't be smaller than 0.
     * @param type The type of elements that this MapReduceResult is the result of.
     * 
     * @throws IllegalArgumentException if one of the above constraints is
     * violated.
     */
    public MapReduceResult(float latitude, float longitude, List<Object> ids, int count, Class<? extends Plottable> type) {
        super(latitude, longitude);
        this.setCount(count);
        this.setIds(ids);
        this.setType(type);
    }

    public int getCount() {
        return count;
    }

    /**
     * @see #MapReduceResult(float, float, java.util.List, int, java.lang.Class) 
     */
    public void setCount(int count) {
        if(count>=0){
            this.count = count;
        } else{
            throw new IllegalArgumentException("Count can't be smaller than 0.");
        }
    }

    public List<Object> getIds() {
        return ids;
    }

    /**
     * @see #MapReduceResult(float, float, java.util.List, int, java.lang.Class) 
     */
    public void setIds(List<Object> ids) {
        if(ids != null){
            this.ids = ids;
        } else{
            this.ids = new ArrayList<Object>();
        }
    }

    public Class<? extends Plottable> getType() {
        return type;
    }

    /**
     * @see #MapReduceResult(float, float, java.util.List, int, java.lang.Class) 
     */
    public void setType(Class<? extends Plottable> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MapReduceResult{" + "latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", count=" + count + ", type=" + type + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final MapReduceResult other = (MapReduceResult) obj;
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    
    
}
