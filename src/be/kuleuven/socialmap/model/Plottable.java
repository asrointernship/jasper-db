/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.model;

/**
 * Classes that need to be plottable on a map, should extend this class.
 *
 * @author Jasper Moeys
 */
public class Plottable {

    private float latitude;
    private float longitude;

    /**
     * Constructs a new Plottable object.
     *
     * @param latitude Has to be between -90 and 90.
     * @param longitude Has to be between -180 and 180.
     * 
     * @throws IllegalArgumentException if one of the above constraints is
     * violated.
     */
    public Plottable(float latitude, float longitude) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    /**
     * @see #Plottable(float, float) 
     */
    public void setLatitude(float latitude) {
        if (latitude >= -90 && latitude <= 90) {
            this.latitude = latitude;
        } else {
            throw new IllegalArgumentException("Latitude has to be between -90 and 90.");
        }
    }

    /**
     * @see #Plottable(float, float) 
     */
    public void setLongitude(float longitude) {
        if (longitude >= -180 && longitude <= 180) {
            this.longitude = longitude;
        } else {
            throw new IllegalArgumentException("Longitude has to be between -180 and 180.");
        }
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    /**
     * Two Plottable objects are equal when they have the same latitude and
     * longitude.
     *
     * @param obj The Object to compare this Plottable against
     * @return true if the given Object is a Plottable equivalent to this
     * Plottable, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plottable other = (Plottable) obj;
        if (Float.floatToIntBits(this.latitude) != Float.floatToIntBits(other.latitude)) {
            return false;
        }
        if (Float.floatToIntBits(this.longitude) != Float.floatToIntBits(other.longitude)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Float.floatToIntBits(this.latitude);
        hash = 97 * hash + Float.floatToIntBits(this.longitude);
        return hash;
    }

    @Override
    public String toString() {
        return "Plottable{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
