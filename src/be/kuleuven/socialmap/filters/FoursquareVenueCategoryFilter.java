/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.FoursquareVenue;

/**
 * A Filter that searches for FoursquareVenues that belong to a certain category.
 *
 * @author Jasper Moeys
 */
public class FoursquareVenueCategoryFilter extends Filter<FoursquareVenue> {
    
    private String category;
    
    /**
     * 
     * @param category The name of the category the FoursquareVenues have to belong to.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public FoursquareVenueCategoryFilter(String category, Filter<FoursquareVenue> filter) {
        super(filter);
        if(category == null)
            throw new IllegalArgumentException("Category shouldn't be null.");
        this.category = category;
    }
    
    @Override
    protected boolean retain(FoursquareVenue element) {
        return element != null && element.getCategories().contains(category);
    }
    
}