package Entity;

import java.io.Serializable;

/**
 * Review object contains user's review and rating for a Movie.
 * @author SS3 Group 8
 *
 */
public class Review implements Serializable{

    /**
     * Contains review submitted by user a Movie.
     */
    private String content;
    /**
     * Contains rating submitted by user for a Movie.
     */
    private int rating;

    /**
     * Constructor for Review
     * @param review String of review submitted by user
     * @param rating Integer value of rating submitted by user
     */
    public Review(String review, int rating) {
        this.content = review;
        this.rating = rating;
    } // Constructor

    /**
     * Gets the rating for this Review
     * @return Integer value of rating
     */
    public int getRating() {
        return rating;
    } // get the movie rating

    /**
     * Gets the content of review submitted by user
     * @return String containing review content
     */
    public String display() {
        return content;
    } // return the content of the review
}