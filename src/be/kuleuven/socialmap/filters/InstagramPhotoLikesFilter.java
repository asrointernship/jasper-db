/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.InstagramPhoto;

/**
 * A Filter that searches for InstagramPhoto with a certain amount of likes.
 *
 * @author Jasper Moeys
 */
public class InstagramPhotoLikesFilter extends Filter<InstagramPhoto> {
    
    private Integer min = null;
    private Integer max = null;
    
    /**
     * Constructs a new InstagramPhotoLikesFilter.
     * Min and max can't both be null.
     * 
     * @param min The minimum amount of likes the InstagramPhoto has to have, or null if there is no minimum.
     * @param max The maximum amount of likes the InstagramPhoto has to have, or null if there is no maximum.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public InstagramPhotoLikesFilter(Integer min, Integer max, Filter<InstagramPhoto> filter) {
        super(filter);
        if(min == null && max == null)
            throw new IllegalArgumentException("Min and max can't both be null.");
        if(max != null && min != null && max<min)
            throw new IllegalArgumentException("Max can't be less than min.");
        if(max != null && max<0)
            throw new IllegalArgumentException("Max can't be less than 0.");
        if(min != null && min<0)
            throw new IllegalArgumentException("Min can't be less than 0.");
        this.min = min;
        this.max = max;
    }
    
    @Override
    protected boolean retain(InstagramPhoto element) {
        boolean res = false;
        if(element != null){
            boolean ok = true;
            if(min != null)
                ok = min<=element.getLike_count();
            if(ok && max!=null)
                ok = max>=element.getLike_count();
            res = ok;
        }
        return res;
    }
    
}
