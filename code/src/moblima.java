import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import Control.FileManager;
import Control.Manager;
import Control.MovieGoerMgr;
import Control.StaffMgr;
import Entity.Cinema;
import Entity.Cinema.ClassOfCinema;
import Entity.Cineplex;
import Entity.Movie;
import Entity.MovieShowing;
import Entity.Movie.Censorship;
import Entity.Movie.ShowingStatus;
import Entity.Movie.TypeOfMovie;
import Entity.Review;
import Entity.Seat;
import Entity.Seat.enumSeat;
import Entity.Booking;
import Entity.*;
/**
 *	This class is used to launch the application.
 * 	The commented code is for debugging. One can also create new cinema/movies etc
 *  for testing without cluttering the main code.
 *  
 *  @author SS3 Group 8
 */
public class moblima implements Serializable{
	
	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
        Manager.start();
    }
	
}