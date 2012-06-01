/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.InstagramPhoto;

/**
 * Searches for InstagramPhotos of a certain user.
 *
 * @author Jasper Moeys
 */
public class InstagramPhotoUserFilter extends Filter<InstagramPhoto> {
    
    private String userId;
    private String username;
    
    /**
     * 
     * @param string The username or the ID of the user.
     * @param isUsername True if string is the username, false if it's the ID.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoUserFilter(String string, boolean isUsername, Filter<InstagramPhoto> filter){
        super(filter);
        if(string == null){
            throw new IllegalArgumentException("String shouldn't be null.");
        }
        if(isUsername){
            this.username = string;
        } else {
            this.userId = string;
        }
    }

    @Override
    protected boolean retain(InstagramPhoto element) {
        boolean res = false;
        if(username != null){
            res = username.equals(element.getUsername());
        } else if(userId != null){
            res = userId.equals(element.getUserID());
        }
        return res;
   }
    
}