/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.InstagramPhoto;

/**
 * A Filter that searches for InstagramPhotos with a caption containing a certain piece of text.
 *
 * @author Jasper Moeys
 */
public class InstagramPhotoCaptionFilter extends Filter<InstagramPhoto> {

    private String filter = "";
    
    /**
     * 
     * 
     * @param text Only InstagramPhotos with a caption containing this piece of text will be retained.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoCaptionFilter(String text, Filter<InstagramPhoto> filter) {
        super(filter);
        if(text != null){
            this.filter = text;
        } else{
            throw new IllegalArgumentException("Text shouldn't be null.");
        }
    }
    
    @Override
    protected boolean retain(InstagramPhoto element) {
        return element != null && element.getCaption() != null && element.getCaption().contains(filter);
    }
    
}