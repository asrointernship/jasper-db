/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.socialmap.model;

import java.util.Date;

/**
 * The Tweet class represents a geotagged tweet posted by a user of twitter.com.
 *
 * @author Jasper Moeys
 */
public class Tweet extends Plottable {

    private long id;
    private String text;
    private Date created_at;
    private long from_user_id;
    private String from_user;
    private Long to_user_id;
    private String to_user;

    /**
     * Constructs a new Tweet.
     *
     * @param id
     * @param latitude Has to be between -90 and 90.
     * @param longitude Has to be between -180 and 180.
     * @param text Can't be null.
     * @param created_at Can't be null.
     * @param from_user_id
     * @param from_user Can't be null.
     * @param to_user_id
     * @param to_user
     *
     * @throws IllegalArgumentException if one of the above constraints is
     * violated.
     */
    public Tweet(long id, float latitude, float longitude, String text, Date created_at, long from_user_id, String from_user, Long to_user_id, String to_user) {
        super(latitude, longitude);
        this.setCreated_at(created_at);
        this.setId(id);
        this.setFrom_user(from_user);
        this.setFrom_user_id(from_user_id);
        this.setText(text);
        this.setTo_user(to_user);
        this.setTo_user_id(to_user_id);
    }

    public Date getCreated_at() {
        return created_at;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setCreated_at(Date created_at) {
        if (created_at != null) {
            this.created_at = created_at;
        } else {
            throw new IllegalArgumentException("Created_at can't be null.");
        }
    }

    public String getFrom_user() {
        return from_user;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setFrom_user(String from_user) {
        if (from_user != null) {
            this.from_user = from_user;
        } else {
            throw new IllegalArgumentException("From_user can't be null.");
        }
    }

    public long getFrom_user_id() {
        return from_user_id;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setFrom_user_id(long from_user_id) {
        this.from_user_id = from_user_id;
    }

    public long getId() {
        return id;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setId(long id) {
        this.from_user = from_user;
    }

    public String getText() {
        return text;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setText(String text) {
        if (text != null) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("Text can't be null.");
        }
    }

    public String getTo_user() {
        return to_user;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public Long getTo_user_id() {
        return to_user_id;
    }

    /**
     * @see #Tweet(long, float, float, java.lang.String, java.util.Date, long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public void setTo_user_id(Long to_user_id) {
        this.to_user_id = to_user_id;
    }

    @Override
    public String toString() {
        return "Tweet{" + "id=" + id + ", latitude=" + getLatitude() + ", longitude=" + getLongitude() + ", text=" + text + ", created_at=" + created_at + ", from_user_id=" + from_user_id + ", from_user=" + from_user + ", to_user_id=" + to_user_id + ", to_user=" + to_user + '}';
    }

    /**
     * Two tweets are considered equal when they have the same ID.
     * 
     * @param obj The Object to compare this Tweet against
     * @return true if the given Object is a Tweet equivalent to this Tweet,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tweet other = (Tweet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    
}
