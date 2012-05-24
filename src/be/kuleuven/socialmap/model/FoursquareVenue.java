/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The FoursquareVenue class represents a venue registered at foursquare.com.
 *
 * @author Jasper Moeys
 */
public class FoursquareVenue extends Plottable {

    private String id;
    private String name;
    private int checkinsCount;
    private int usersCount;
    private List<String> categories;

    /**
     *
     * @param id Can't be null.
     * @param latitude Has to be between -90 and 90.
     * @param longitude Has to be between -180 and 180.
     * @param name The name of this venue. Null will be converted to an empty
     * String.
     * @param checkinsCount Can't be less than 0.
     * @param usersCount Can't be less than 0.
     * @param categories A list of the categories this venue belongs to. Null
     * will be converted to an empty list.
     *
     * @throws IllegalArgumentException if one of the above constraints is
     * violated.
     */
    public FoursquareVenue(String id, float latitude, float longitude, String name, int checkinsCount, int usersCount, List<String> categories) {
        super(latitude, longitude);
        setId(id);
        setName(name);
        setCheckinsCount(checkinsCount);
        setUsersCount(usersCount);
        setCategories(categories);
    }

    public List<String> getCategories() {
        return categories;
    }

    /**
     * @see #FoursquareVenue(java.lang.String, float, float, java.lang.String, int, int, java.util.List) 
     */
    public void setCategories(List<String> categories) {
        if (categories != null) {
            this.categories = categories;
        } else {
            this.categories = new ArrayList<String>();
        }
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    /**
     * @see #FoursquareVenue(java.lang.String, float, float, java.lang.String, int, int, java.util.List) 
     */
    public void setCheckinsCount(int checkinsCount) {
        if (checkinsCount >= 0) {
            this.checkinsCount = checkinsCount;
        } else {
            throw new IllegalArgumentException("CheckinsCount can't be less than 0.");
        }
    }

    public String getId() {
        return id;
    }

    /**
     * @see #FoursquareVenue(java.lang.String, float, float, java.lang.String, int, int, java.util.List) 
     */
    public void setId(String id) {
        if (id != null) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id can't be null.");
        }
    }

    public String getName() {
        return name;
    }

    /**
     * @see #FoursquareVenue(java.lang.String, float, float, java.lang.String, int, int, java.util.List) 
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public int getUsersCount() {
        return usersCount;
    }

    /**
     * @see #FoursquareVenue(java.lang.String, float, float, java.lang.String, int, int, java.util.List) 
     */
    public void setUsersCount(int usersCount) {
        if (usersCount >= 0) {
            this.usersCount = usersCount;
        } else {
            throw new IllegalArgumentException("UsersCount can't be less than 0.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FoursquareVenue other = (FoursquareVenue) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "FoursquareVenue{" + "id=" + id + ", latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", name=" + name + ", checkinsCount=" + checkinsCount + ", usersCount=" + usersCount + ", categories=" + categories + '}';
    }
}
