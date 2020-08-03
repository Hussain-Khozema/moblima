// javadocs done

package Control;
import Entity.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import Entity.Movie;
import Entity.MovieShowing;
import Entity.Cineplex;
import java.io.*;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.exit;

/** Manager to store data for cinema, booking, cineplex and holiday. <br>
 * All load methods retrieve data from .dat files in folder db.<br>
 * All save methods saves data into respective .dat files in folder db. <br>
 * 
 * @author SS3 Group 8
 * @see Manager
 */

public class FileManager {
	
	/**
	 * Returns an ArrayList of movies
	 * @return ArrayList of Movie objects
	 */
	public static ArrayList<Movie> loadMovies() {
    	ArrayList<Movie> movie = new ArrayList<Movie>();
    	movie = (ArrayList<Movie>) SerializeDB.readSerializedObject("db/movies.dat");
    	return movie;

    }
	/**
	 * Returns an ArrayList of movie showtimes
	 * @return ArrayList of MovieShowing objects
	 */
	public static ArrayList<MovieShowing> loadMovieShowtime() {
    	ArrayList<MovieShowing> movieShowings = new ArrayList<MovieShowing>();
    	movieShowings = (ArrayList<MovieShowing>) SerializeDB.readSerializedObject("db/shows.dat");
    	return movieShowings;
    	
    } 
	/**
	 * Returns an ArrayList of holidays
	 * @return ArrayList of Date objects
	 */
	public static ArrayList<Date> loadHolidays() {
    	ArrayList<Date> holidays = new ArrayList<Date>();
    	holidays = (ArrayList<Date>) SerializeDB.readSerializedObject("db/holidates.dat");
    	return holidays;
    	
    } 
	/**
	 * Returns an ArrayList of cineplexes
	 * @return ArrayList of Cineplex objects
	 */
	public static ArrayList<Cineplex> loadCineplex() {
    	ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    	cineplexes = (ArrayList<Cineplex>) SerializeDB.readSerializedObject("db/cineplexes.dat");
    	return cineplexes; 
    	
    }
	/**
	 * Returns an ArrayList of bookings
	 * @return ArrayList of Booking objects
	 */
	public static ArrayList<Booking> loadBooking() {
    	ArrayList<Booking> bookings = new ArrayList<Booking>();
    	bookings = (ArrayList<Booking>) SerializeDB.readSerializedObject("db/bookings.dat");
    	return bookings;
    }
	
    /**
     * Accepts ArrayList of movies and saves into "movies.dat"
     * @param list ArrayList of Movie objects
     */
    public static void saveMovieInfo(ArrayList<Movie> list){
    	SerializeDB.writeSerializedObject("db/movies.dat", list); 
    }
    /**
     * Accepts ArrayList of showtimes and saves into "shows.dat"
     * @param list ArrayList of MovieShowing objects
     */
    public static void saveMovieShowtime(ArrayList<MovieShowing> list){
    	SerializeDB.writeSerializedObject("db/shows.dat", list);
    }
    /**
     * Accepts ArrayList of dates and saves into "holidates.dat"
     * @param list ArrayList of Date objects
     */
    public static void saveHolidays(ArrayList<Date> list){
    	SerializeDB.writeSerializedObject("db/holidates.dat", list);
    }
    /**
     * Accepts ArrayList of cineplexes and saves into "cineplexes.dat"
     * @param list ArrayList of Cineplex objects
     */
    public static void saveCineplex(ArrayList<Cineplex> list){
    	SerializeDB.writeSerializedObject("db/cineplexes.dat", list);
    }
    /**
     * Accepts ArrayList of bookings and saves into "bookings.dat"
     * @param list ArrayList of Booking objects
     */
    public static void saveBooking(ArrayList<Booking> list){
    	SerializeDB.writeSerializedObject("db/bookings.dat", list);
    }
	

}