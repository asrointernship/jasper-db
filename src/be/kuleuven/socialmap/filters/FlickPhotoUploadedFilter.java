/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.FlickrPhoto;
import java.util.Date;

/**
 * Searches for FlickrPhotos uploaded in a certain interval.
 * The endpoints of the interval are not included.
 *
 * @author Jasper Moeys
 */
public class FlickPhotoUploadedFilter extends Filter<FlickrPhoto> {

    private Date start;
    private Date end;
    
    /**
     * 
     * @param start The start of the interval.
     * @param end The end of the interval.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public FlickPhotoUploadedFilter(Date start, Date end, Filter<FlickrPhoto> filter){
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
    protected boolean retain(FlickrPhoto element) {
        return element != null && start.before(element.getDateupload()) && end.after(element.getDateupload());
    }
}
