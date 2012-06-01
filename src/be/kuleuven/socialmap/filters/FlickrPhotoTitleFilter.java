/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.FlickrPhoto;

/**
 * A Filter that searches for FlickrPhotos with a title containing a certain piece of text.
 *
 * @author Jasper Moeys
 */
public class FlickrPhotoTitleFilter extends Filter<FlickrPhoto> {

    private String text = "";
    
    /**
     * 
     * 
     * @param text Only Flickrphotos with a title containing this piece of text will be retained.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public FlickrPhotoTitleFilter(String text, Filter<FlickrPhoto> filter) {
        super(filter);
        if(text != null){
            this.text = text;
        } else{
            throw new IllegalArgumentException("Text shouldn't be null.");
        }
    }
    
    @Override
    protected boolean retain(FlickrPhoto element) {
        return element != null && text.contains(element.getTitle());
    }
    
}

