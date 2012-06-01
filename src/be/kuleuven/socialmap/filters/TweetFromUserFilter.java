/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.Tweet;

/**
 * Searches for Tweets posted by a certain user.
 *
 * @author Jasper Moeys
 */
public class TweetFromUserFilter extends Filter<Tweet> {
    
    private Long userid;
    private String username;
    
    /**
     * 
     * @param username The username of the user whose Tweets you want.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public TweetFromUserFilter(String username, Filter<Tweet> filter){
        super(filter);
        if(username != null){
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username shouldn't be null.");
        }
    }
    
    /**
     * 
     * @param userid The ID of the user whose Tweets you want.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public TweetFromUserFilter(long userid, Filter<Tweet> filter){
        super(filter);
        this.userid = userid;
    }

    @Override
    protected boolean retain(Tweet element) {
        boolean res = false;
        if(username != null){
            res = username.equals(element.getFrom_user());
        } else if(userid != null){
            res = userid.equals(element.getFrom_user_id());
        }
        return res;
   }
    
}
