/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.Plottable;

/**
 * Searches for Plottable objects in a certain bounding box.
 *
 * @author Jasper Moeys
 */
public class LocationFilter<T extends Plottable> extends Filter<T> {
    
    private Plottable southWest;
    private Plottable northEast;
    
    /**
     * 
     * @param southWest The south west corner of the bounding box.
     * @param northEast The north east corner of the bounding box.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public LocationFilter(Plottable southWest, Plottable northEast, Filter<T> filter){
        super(filter);
        if(southWest == null)
            throw new IllegalArgumentException("SouthWest shouldn't be null.");
        if(northEast == null)
            throw new IllegalArgumentException("NorthEast shouldn't be null.");
        this.northEast = northEast;
        this.southWest = southWest;
    }
    
    /**
     * 
     * 
     * @param southWestLat The latitude of the south west corner of the bounding box.
     * @param southWestLng The longitude of the south west corner of the bounding box.
     * @param northEastLat The latitude of the north east corner of the bounding box.
     * @param northEastLng The longitude of the north east corner of the bounding box.
     * @param filter 
     */
    public LocationFilter(float southWestLat, float southWestLng, float northEastLat, float northEastLng, Filter<T> filter){
        super(filter);
        this.northEast = new Plottable(northEastLat, northEastLng);
        this.southWest = new Plottable(southWestLat, southWestLng);
    }

    @Override
    protected boolean retain(T element) {
        return element != null && element.getLatitude()>=southWest.getLatitude() && element.getLongitude()>=southWest.getLongitude() && element.getLatitude()<=northEast.getLatitude() && element.getLongitude()<=northEast.getLongitude();
    }
    
}
