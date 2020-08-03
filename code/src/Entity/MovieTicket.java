package Entity;

import java.io.Serializable;

/**
 * MovieTicket is used during booking. Contains:<br>
 * <ul>
 * <li>Seat booked</li>
 * <li>Price of ticket</li>
 * <li>Movie showtime</li>
 * </ul>
 * 
 * @see MovieGoerMgr
 * @see StaffMgr
 * @author SS3 Group 8
 *
 */
public class MovieTicket implements Serializable{

    /**
     * Movie Showtime
     */
    private MovieShowing movieShowing; // ticket of the movie
    /**
     * Seat information
     */
    private Seat seat; //seat number of the ticket
    /**
     * Price information
     */
    private double price; //price of the ticket
    /**
     * Accepts movie showtime, seat and price of ticket<br>
     * @param movieShowing Movie showtime
     * @param seat Seat
     * @param price Price of ticket
     */
    public MovieTicket(MovieShowing movieShowing, Seat seat, double price) { //constructor
        this.movieShowing = movieShowing;
        this.seat = seat;
        this.price = price;
    }
    
    /**
     * Generate String with movie information, seat and price
     * @return String with details
     */
    public String displayTicketInfo() { //returns the information of the ticket like the price and seat ID
        return movieShowing.displayMovieInfo() + "\nSeat: " + seat.getSeatID() + "\n\nTotal price: " + price + " SGD";
    }
}