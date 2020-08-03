package Entity;
import java.io.Serializable;
/**
 * Booking Class used during booking of tickets.
 * 
 * @see MovieGoerMgr#movieBooking
 * @see MovieGoerMgr#displayBookingHistory
 * @author SS3 Group 8
 */
public class Booking implements Serializable{
    private MovieTicket movieTicket;
    private Payment payment;
    
    
    /**
     * Constructor for Booking Object
     * @param movieTicket Contains movie showtime, seat and price of ticket
     * @param payment Contains payment information
     */
    public Booking(MovieTicket movieTicket, Payment payment) {// Constructor
        this.movieTicket = movieTicket;
        this.payment = payment;
    } 

    /**
     * Gets payment information
     * @return Payment object
     */
    public Payment getPaymentInfo(){
        return payment;
    } // returns the payment information about the book 

    /**
     * Returns booking information
     * @return String with booking information 
     */
    public String displayBooking(){
        return movieTicket.displayTicketInfo() + "\n\n" + payment.displayPayment();
    } // returns booking information
}