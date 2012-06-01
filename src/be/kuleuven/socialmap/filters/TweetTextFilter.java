/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.Tweet;

/**
 * A Filter that searches for Tweets containing a certain piece of text.
 *
 * @author Jasper Moeys
 */
public class TweetTextFilter extends Filter<Tweet> {

    private String text = "";
    
    /**
     * 
     * 
     * @param text Only Tweets containing this piece of text will be retained.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public TweetTextFilter(String text, Filter<Tweet> filter) {
        super(filter);
        if(text != null){
            this.text = text;
        } else{
            throw new IllegalArgumentException("Text shouldn't be null.");
        }
    }
    
    @Override
    protected boolean retain(Tweet element) {
        return element != null && element.getText().contains(text);
    }
    
}
