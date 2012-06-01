/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.InstagramPhoto;

/**
 * Searches for InstagramPhotos of a certain location.
 *
 * @author Jasper Moeys
 */
public class InstagramPhotoLocationFilter extends Filter<InstagramPhoto> {
    
    private long locationId;
    private String locationName;
    
    /**
     * 
     * @param username The name of the location.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoLocationFilter(String locationName, Filter<InstagramPhoto> filter){
        super(filter);
        if(locationName != null){
            this.locationName = locationName;
        } else {
            throw new IllegalArgumentException("LocationName shouldn't be null.");
        }
    }
    
    /**
     * 
     * @param locationId The ID of the location.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoLocationFilter(long locationId, Filter<InstagramPhoto> filter){
        super(filter);
        this.locationId = locationId;
    }

    @Override
    protected boolean retain(InstagramPhoto element) {
        boolean res = false;
        if(locationName != null){
            res = locationName.equals(element.getLocationName());
        } else if(element.getLocationID() != null){
            res = element.getLocationID().equals(locationId);
        }
        return res;
   }
    
}
