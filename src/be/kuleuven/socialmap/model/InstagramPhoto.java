/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.model;

import java.util.Date;

/**
 * The InstagramPhoto class represents a photo posted by a user of instagr.am.
 *
 * @author Jasper Moeys
 */
public class InstagramPhoto extends Plottable {

    private String id;
    private Long locationID;
    private String locationName;
    private String filter;
    private String userID;
    private String username;
    private int like_count;
    private String url;
    private Date created_at;
    private String caption;

    /**
     * Constructs an InstagramPhoto.
     * 
     * @param id Can't be null.
     * @param latitude Has to be between -90 and 90.
     * @param longitude Has to be between -180 and 180.
     * @param locationID The id of the location where this photo was taken, if it's available.
     * @param locationName The name of the location where this photo was taken, if it's available.
     * @param filter The filter used on this photo.
     * @param userID The id of the user who posted this photo.
     * @param username The name of the user who posted this photo.
     * @param like_count The amount of likes the photo has.
     * @param url The url where the photo can be downloaded.
     * @param created_at The date when the photo was created.
     * @param caption The caption of this photo.
     */
    public InstagramPhoto(String id, float latitude, float longitude, Long locationID, String locationName, String filter, String userID, String username, int like_count, String url, Date created_at, String caption) {
        super(latitude, longitude);
        this.setId(id);
        this.setLocationID(locationID);
        this.setLocationName(locationName);
        this.setFilter(filter);
        this.setUserID(userID);
        this.setUsername(username);
        this.setLike_count(like_count);
        this.setUrl(url);
        this.setCreated_at(created_at);
        this.setCaption(caption);
    }

    
    
    public String getCaption() {
        return caption;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getCreated_at() {
        return created_at;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getFilter() {
        return filter;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getId() {
        return id;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setId(String id) {
        if(id != null){
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id can't be null.");
        }
    }

    public int getLike_count() {
        return like_count;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public Long getLocationID() {
        return locationID;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUrl() {
        return url;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserID() {
        return userID;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @see #InstagramPhoto(java.lang.String, float, float, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.util.Date, java.lang.String) 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "InstagramPhoto{" + "id=" + id + ", latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", locationID=" + locationID + ", locationName=" + locationName + ", filter=" + filter + ", userID=" + userID + ", username=" + username + ", like_count=" + like_count + ", url=" + url + ", created_at=" + created_at + ", caption=" + caption + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InstagramPhoto other = (InstagramPhoto) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    
    
}
