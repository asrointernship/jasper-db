/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.FlickrPhoto;

/**
 * A Filter that searches for FlickrPhotos containing a certain piece of text.
 *
 * @author Jasper Moeys
 */
public class FlickrPhotoOwnerFilter extends Filter<FlickrPhoto> {
    
    private String owner;
    
    /**
     * 
     * @param owner The owner whose FlickrPhotos you want.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public FlickrPhotoOwnerFilter(String owner, Filter<FlickrPhoto> filter){
        super(filter);
        if(owner != null){
            this.owner = owner;
        } else{
            throw new IllegalArgumentException("Owner shouldn't be null.");
        }
    }

    @Override
    protected boolean retain(FlickrPhoto element) {
        return element != null && owner.equals(element.getOwner());
    }
    
}
