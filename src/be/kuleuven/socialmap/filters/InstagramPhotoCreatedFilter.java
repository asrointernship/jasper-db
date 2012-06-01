/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.InstagramPhoto;
import java.util.Date;

/**
 * Searches for InstagramPhotos created in a certain interval.
 * The endpoints of the interval are not included.
 * 
 * @author Jasper Moeys
 */
public class InstagramPhotoCreatedFilter extends Filter<InstagramPhoto> {
    
    private Date start;
    private Date end;
    
    /**
     * 
     * @param start The start of the interval.
     * @param end The end of the interval.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoCreatedFilter(Date start, Date end, Filter<InstagramPhoto> filter){
        super(filter);
        if(start == null)
            throw new IllegalArgumentException("Start shouldn't be null.");
        if(end == null)
            throw new IllegalArgumentException("End shouldn't be null.");
        if(end.before(start))
            throw new IllegalArgumentException("The end can't come before the start.");
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean retain(InstagramPhoto element) {
        return element != null && element.getCreated_at() != null && start.before(element.getCreated_at()) && end.after(element.getCreated_at());
    }
    
}
