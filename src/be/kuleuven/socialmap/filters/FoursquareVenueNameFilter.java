/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.FoursquareVenue;

/**
 * A Filter that searches for FoursquareVenues with a name containing a certain piece of text.
 *
 * @author Jasper Moeys
 */
public class FoursquareVenueNameFilter extends Filter<FoursquareVenue> {
    
    private String text = "";
    
    /**
     * 
     * 
     * @param text Only FoursquareVenues with a name containing this piece of text will be retained.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public FoursquareVenueNameFilter(String text, Filter<FoursquareVenue> filter) {
        super(filter);
        if(text != null){
            this.text = text;
        } else{
            throw new IllegalArgumentException("Text shouldn't be null.");
        }
    }
    
    @Override
    protected boolean retain(FoursquareVenue element) {
        return element != null && text.contains(element.getName());
    }
    
}
