/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.model;

import java.net.URL;
import java.util.Date;

/**
 * The FlickrPhoto class represents a geotagged picture posted by a user of
 * flickr.com.
 *
 * @author Jasper Moeys
 */
public class FlickrPhoto extends Plottable {

    private long id;
    private Date dateupload;
    private String title;
    private String owner;
    private String secret;
    private String farm;
    private String server;

    /**
     * Constructs a new FlickrPhoto.
     *
     * @param id
     * @param latitude Has to be between -90 and 90.
     * @param longitude Has to be between -180 and 180.
     * @param dateupload Can't be null.
     * @param title Can't be null.
     * @param owner Can't be null.
     * @param secret Can't be null.
     * @param farm Can't be null.
     * @param server Can't be null.
     *
     * @throws IllegalArgumentException if one of the above constraints is
     * violated.
     */
    public FlickrPhoto(long id, float latitude, float longitude, Date dateupload, String title, String owner, String secret, String farm, String server) {
        super(latitude, longitude);
        this.setId(id);
        this.setDateupload(dateupload);
        this.setTitle(title);
        this.setOwner(owner);
        this.setSecret(secret);
        this.setFarm(farm);
        this.setServer(server);
    }

    public Date getDateupload() {
        return dateupload;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setDateupload(Date dateupload) {
        if (dateupload != null) {
            this.dateupload = dateupload;
        } else {
            throw new IllegalArgumentException("Dateupload can't be null.");
        }
    }

    public String getFarm() {
        return farm;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setFarm(String farm) {
        if (farm != null) {
            this.farm = farm;
        } else {
            throw new IllegalArgumentException("Farm can't be null.");
        }
    }

    public long getId() {
        return id;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setOwner(String owner) {
        if (owner != null) {
            this.owner = owner;
        } else {
            throw new IllegalArgumentException("Owner can't be null.");
        }
    }

    public String getSecret() {
        return secret;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setSecret(String secret) {
        if (secret != null) {
            this.secret = secret;
        } else {
            throw new IllegalArgumentException("Secret can't be null.");
        }
    }

    public String getServer() {
        return server;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setServer(String server) {
        if (server != null) {
            this.server = server;
        } else {
            throw new IllegalArgumentException("Server can't be null.");
        }
    }

    public String getTitle() {
        return title;
    }

    /**
     * @see #FlickrPhoto(long, float, float, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title can't be null.");
        }
    }

    /**
     * Constructs the String of the URL where this picture can be downloaded.
     *
     * @return the URL of this photo.
     */
    public String getPhotoUrl() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("http://farm");
        buffer.append(farm);
        buffer.append(".staticflickr.com/");
        buffer.append(server);
        buffer.append("/");
        buffer.append(id);
        buffer.append("_");
        buffer.append(secret);
        buffer.append(".jpg");

        return buffer.toString();
    }

    @Override
    public String toString() {
        return "FlickrPhoto{" + "id=" + id + ", latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", dateupload=" + dateupload + ", title=" + title + ", owner=" + owner + ", secret=" + secret + ", farm=" + farm + ", server=" + server + '}';
    }

    /**
     * Two photos from Flickr are considered equal when they have the same ID.
     *
     * @param obj The Object to compare this FlickrPhoto against
     * @return true if the given Object is a FlickrPhoto equivalent to this
     * FlickrPhoto, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlickrPhoto other = (FlickrPhoto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}
