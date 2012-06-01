/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.InstagramPhoto;

/**
 * A Filter that searches for InstagramPhotos with a certain filter.
 *
 * @author Jasper Moeys
 */
public class InstagramPhotoFilterFilter extends Filter<InstagramPhoto> {

    private String filter = "";
    
    /**
     * 
     * @param photoFilter The filter to search for.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoFilterFilter(String photoFilter, Filter<InstagramPhoto> filter) {
        super(filter);
        if(photoFilter != null){
            this.filter = photoFilter;
        } else{
            throw new IllegalArgumentException("PhotoFilter shouldn't be null.");
        }
    }
    
    @Override
    protected boolean retain(InstagramPhoto element) {
        return element != null && element.getFilter() != null && element.getFilter().equals(filter);
    }
    
}