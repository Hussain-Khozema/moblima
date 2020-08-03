// javadocs done
package Control;

import Entity.Movie;
import Entity.Cinema;
import Entity.Cineplex;
import Entity.MovieShowing;
import Entity.Booking;
import Entity.User;
import Boundary.MovieGoerInterface;
import Boundary.StaffInterface;

import java.util.ArrayList;
import java.util.Date;
import java.io.File;


/**
 * Manager class loads all data from files. Upon exiting application, it updates
 * and saves the relevant data in respective files. <br>
 * Uses User to determine if user exits application <br>
 * 
 * Customer and Staff functionality is handled by:
 * 
 * <pre>{@code
 * while (currentUser.isActive()) {
 *   if (currentUser.isMovieGoer()) {
 *       MovieGoerInterface.getInstance().interact();
 *   } else {
 *       StaffInterface.getInstance().interact();
 *   }
 *}}</pre>
 *
 * @see FileManager
 * @see MovieGoerInterface
 * @see StaffInterface
 * @See User
 * @author SS3 Group 8
 */
public class Manager {

    /**
     * Initializes all relevant data from respective .dat files <br>
     * Saves data on exit.
     */
    public static void start()  {
    	
    
    	FileManager filemanager = new FileManager();
        ArrayList<Movie> movies = filemanager.loadMovies();
        ArrayList<MovieShowing> movieShowings= filemanager.loadMovieShowtime();
        ArrayList<Booking> bookings= filemanager.loadBooking();
        ArrayList<Date> holidays= filemanager.loadHolidays();
        ArrayList<Cineplex> cineplexes= filemanager.loadCineplex(); 

        // initialize controls
        MovieGoerMgr.getInstance().initialize(holidays, movies, bookings, movieShowings);
        StaffMgr.getInstance().initialize(movies, bookings, movieShowings, holidays, cineplexes);

        // start interacting with user
        User currentUser = User.getInstance();
        System.out.println(
        "****************************************************************\n" +
        "================================================================\n" +
        "                                                                \n" +
        "        Movie Booking and Listing Management Application        \n" +
        "                                                                \n" +
        "================================================================\n" +
        "****************************************************************\n" 
        );
        while (currentUser.isActive()) {
            if (currentUser.isMovieGoer()) {
                MovieGoerInterface.getInstance().interact();
            } else {
                StaffInterface.getInstance().interact();
            }
        }
//        fileManager.commit();
        filemanager.saveMovieInfo(movies); 
        filemanager.saveCineplex(cineplexes);
        filemanager.saveBooking(bookings);
        filemanager.saveMovieShowtime(movieShowings);
        filemanager.saveHolidays(holidays);
        
    }
}