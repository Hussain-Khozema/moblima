package Entity;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;

/**
 * Movie contains details on each movie.
 * @see MovieGoerMgr
 * @see StaffMgr
 * @author SS3 Group 8
 *
 */
public class Movie implements Cloneable,Serializable{
    /**
     * Enum for showing status
     * @author SS3 Group 8
     *
     */
    public enum ShowingStatus {COMINGSOON, PREVIEW, NOWSHOWING, ENDOFSHOWING}
    /**
     * Enum for censorship
     * @author SS3 Group 8
     *
     */
    public enum Censorship {G, PG, PG13, NC16, M18, R21}
    /**
     * Censorship information for this Movie
     */
    private Censorship censorship;
    /**
     * Title of this Movie
     */
    private String title;
    /**
     * Showing Status of this Movie
     */
    private ShowingStatus showingStatus;
    /**
     * Enum for Type of Movie
     * @author SS3 Group 8
     *
     */
    public enum TypeOfMovie {DIGITAL, BLOCKBUSTER,NORMAL}
    /**
     * Type of Movie for this Movie
     */
    private TypeOfMovie typeOfMovie;
    /**
     * Synopsis of this Movie
     */
    private String synopsis;
    /**
     * Director of this Movie
     */
    private String director;
    /**
     * Array of cast members for this Movie
     */
    private String[] casts;
    /**
     * Rating based on reviews for this Movie
     */
    private double rating;
    /**
     * ArrayList containing reviews for this Movie
     */
    private ArrayList<Review> reviews;
    /**
     * Base Price of this Movie
     */
    private double basePrice;
    /**
     * Revenue from sales for this Movie
     */
    private int revenue;
    /**
     * Constructor for this Movie
     * @param title Title of this Movie
     * @param showingStatus Showing Status of this Movie (Enum)
     * @param typeOfMovie Type of Movie for this Movie (Enum)
     * @param synopsis Synopsis of this Movie
     * @param director Director of this Movie
     * @param casts2 Cast members in this Movie
     * @param rating Rating of this Movie
     * @param reviews Reviews for this Movie
     * @param basePrice Base Price of this Movie (Used for price calculation)
     * @param censorship Censorship Rating for this Movie (Enum)
     * @param revenue Revenue of this Movie
     */
    public Movie(String title, ShowingStatus showingStatus, TypeOfMovie typeOfMovie, String synopsis, 
    		String director, String[] casts2, double rating, ArrayList<Review> reviews,
    		double basePrice, Censorship censorship, int revenue) {
        this.title = title;
        this.showingStatus = showingStatus;
        this.typeOfMovie = typeOfMovie;
        this.synopsis = synopsis;
        this.director = director;
        this.casts = casts2;
        this.rating = rating;
        this.reviews = reviews;
        this.basePrice = basePrice;
        this.censorship = censorship;
        this.revenue = revenue;
    } // Constructor
    public Movie() {}

    /**
     * Displays information on Movie
     * @return String containing details of this Movie
     */
    public String displayInfo() {
    	String result = "\nTitle: " + title + "\nShowing status: " + showingStatus + "\nDirector: " + director + "\nCast: ";
    	String[] casts = getCast();
    	for (int i=0;i<casts.length;i++) 
    	{
    		result = result + casts[i];
    		if ( !(i==casts.length-1) ) {
    			result = result + ", ";
    		}
    	}
    	result = result + "\nRating: " + censorship + "\nSynopsis: " + synopsis;
        if (reviews.size() < 2) {
            result = result + "\nOverall reviewer rating: NA";
        }
        else {
        	result = result + "\nOverall reviewer rating: " + rating;
        }
        return result;
    } //get the reviews

    /**
     * Gets title of this Movie
     * @return String containing title
     */
    public String getTitle() {
        return title;
    } // get the movie title

    /**
     * Sets synopsis for this Movie
     * @param synopsis String containing new Synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    } // set the sypnosis

    /**
     * Gets revenue for this Movie
     * @return Integer value of revenue
     */
    public int getRevenue() {
        return revenue;
    } // returns the sale of the movie

    /**
     * Sets revenue for this Movie
     * @param revenue Integer value of new revenue
     */
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    } // set the sale of the movie

    /**
     * Gets base price for this Movie
     * @return Double value of base price
     */
    public double getBasePrice() {
        return basePrice;
    } // returns the base price of the movie
    
    /**
     * Gets array containing casts for this Movie
     * @return Array of Cast Members
     */
    public String[] getCast(){
    	return this.casts;
    }
    /**
     * Sets base price of this Movie
     * @param basePrice Double value of base price
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    } //sets the base price of the movie

    /**
     * Gets Showing Status of this Movie
     * @return Showing status (Enum)
     */
    public ShowingStatus getShowingStatus() {
        return showingStatus;
    } // returns the movie showing status

    /**
     * Sets Showing Status of this Movie
     * @param showingStatus New Showing Status for this Movie (Enum)
     */
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    } // sets movie showing status

    /**
     * Gets ArrayList of Reviews
     * @return ArrayList of Reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    } //gets the reviews list

    /**
     * Adds Review for this Movie
     * @param viewerReview New review object to be added
     */
    public void addReview(Review viewerReview) {
    	reviews.add(viewerReview);
    } // adds reviews to the list
    
    /**
     * Gets Type of Movie (Enum)
     * @return Type of Movie (Enum)
     */
    public TypeOfMovie getTypeOfMovie() {
        return typeOfMovie;
    } // returns the type of movie

    /**
     * Sets Type of Movie (Enum)
     * @param typeOfMovie New Type of Movie (Enum)
     */
    public void setTypeOfMovie(TypeOfMovie typeOfMovie) {
        this.typeOfMovie = typeOfMovie;
    } // sets the type of movie

    /**
     * Get Rating for this Movie
     * @return Double value of rating
     */
    public double getRating() {
        return rating;
    } // returns the overall rating of the movie
    
    /**
     * Get Director for this Movie
     * @return String containing name of director
     */
    public String getDirector() {
    	return director;
    } // sets the director of the movie

    /**
     * Set Rating for this Movie
     * @param rating Double value of new Rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    } //sets the overall rating of the movie

    /**
     * Gets Censorship for this Movie (Enum)
     * @return Censorship (Enum)
     */
    public Censorship getCensorship() {
        return censorship;
    }
    /**
     * Creates clone of object
     */
    public Object clone(){  
        try{  
            return super.clone();  
        }catch(Exception e){ 
            return null; 
        }
    }
}