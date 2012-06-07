/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.filters;

import be.kuleuven.socialmap.model.Plottable;
import java.util.Random;

/**
 * A filter that randomly retains a certain percentage (approximately) of Plottable objects.
 *
 * @author Jasper Moeys
 */
public class RandomFilter<T extends Plottable> extends Filter<T> {

    Random rand = new Random();
    float perc;
    
    /**
     * Constructs a new RandomFilter.
     * 
     * @param perc The percentage of objects that will be retained, expressed as a value between 0 and 1.
     * @param filter The filter to combine this filter with, or null if it
     * shouldn't be combined.
     */
    public RandomFilter(float perc, Filter<T> filter) {
        super(filter);
        if(perc > 1 || perc < 0)
            throw new IllegalArgumentException("The percentage has to be expressed as a value between 0 and 1.");
        this.perc = perc;
    }

    
    
    @Override
    protected boolean retain(T element) {
        float random = rand.nextFloat();
        if(random<perc)
            return true;
        else
            return false;
    }
    
}
